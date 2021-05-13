package tweets.TweetsConsumer.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tweets.TweetsConsumer.model.Tweet;
import tweets.TweetsConsumer.service.TweetService;

@RestController
@RequestMapping(path = { "/tweets" })
public class TweetController {

	@Autowired
	private TweetService tweetService;

	@GetMapping("")
	ResponseEntity<List<Tweet>> all() {

		return new ResponseEntity<>(tweetService.findAll(), HttpStatus.OK);

	}

	@GetMapping(path = "/{id}/valid")
	ResponseEntity<Object> valid(@PathVariable Long id) {

		Tweet tweet = tweetService.findById(id);

		if (tweet != null) {

			return new ResponseEntity<>(tweetService.validTweet(tweet), HttpStatus.OK);

		} else {

			return new ResponseEntity<>("Tweet not found", HttpStatus.NOT_FOUND);

		}

	}

	@GetMapping("/validTweets/{user}")
	ResponseEntity<List<Tweet>> validTweetsByUser(@PathVariable String user) {

		return new ResponseEntity<>(tweetService.findValidByUser(user.replace("_", " ")), HttpStatus.OK);

	}

	@GetMapping("/hashtags")
	ResponseEntity<Set<String>> getHashTags() {

		return new ResponseEntity<>(tweetService.getTopHashTags(), HttpStatus.OK);

	}

}
