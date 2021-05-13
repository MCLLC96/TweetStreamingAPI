package tweets.TweetsConsumer.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import tweets.TweetsConsumer.model.Tweet;
import tweets.TweetsConsumer.repository.TweetRepository;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Service
public class TweetService {

	@Autowired
	private TweetRepository tweetRepository;

	@Value("${number.followers}")
	private Integer followers;

	@Value("${languages}")
	private List<String> languages;

	@Value("${top.hashtags}")
	private Integer nHashTags;

	public void streamingAPI() {

		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();

		StatusListener listener = new StatusListener() {

			@Override
			public void onStatus(Status status) {

				String lang = status.getLang();

				int userFollowers = status.getUser().getFollowersCount();

				try {

					if (languages.contains(lang) && userFollowers > followers) {

						String text = new String(status.getText().getBytes(StandardCharsets.UTF_8),
								StandardCharsets.UTF_8);

						String userName = new String(status.getUser().getName().getBytes(StandardCharsets.UTF_8),
								StandardCharsets.UTF_8);

						Point location = null;

						if (status.getGeoLocation() != null) {

							location = new Point(status.getGeoLocation().getLatitude(),
									status.getGeoLocation().getLongitude());

						}

						Tweet tweet = new Tweet(userName, text, location, false);

						tweetRepository.save(tweet);

					}

				} catch (Exception e) {

					System.out.println(e.getMessage());

				}

			}

			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}

			public void onScrubGeo(long userId, long upToStatusId) {
			}

			public void onStallWarning(StallWarning warning) {
			}

			public void onException(Exception ex) {

				ex.printStackTrace();
			}
		};

		twitterStream.addListener(listener);

		twitterStream.sample();
	}

	public List<Tweet> findAll() {

		return tweetRepository.findAll();

	}

	public List<Tweet> findValidByUser(String name) {

		return tweetRepository.findByValidTrueAndUser(name);

	}

	public Tweet save(Tweet tweet) {

		return tweetRepository.save(tweet);

	}

	public Tweet findById(Long id) {

		return tweetRepository.findById(id).orElse(null);

	}

	public Set<String> getTopHashTags() {

		List<Tweet> tweets = tweetRepository.findAll();

		List<String> hashTags = new ArrayList<>();

		for (Tweet t : tweets) {
			hashTags.addAll(t.hashTags());
		}

		Map<String, Long> hashTagsGroup = hashTags.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		Map<String, Long> top = hashTagsGroup.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(nHashTags)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		return top.keySet();
	}

	public Tweet validTweet(Tweet tweet) {

		tweet.setValid(true);

		return tweetRepository.save(tweet);

	}

}
