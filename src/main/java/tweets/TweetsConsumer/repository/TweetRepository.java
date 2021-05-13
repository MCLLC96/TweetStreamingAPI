package tweets.TweetsConsumer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tweets.TweetsConsumer.model.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

	@Query
	public List<Tweet> findByValidTrueAndUser(String user);

	@Modifying
	@Query("update Tweet t set t.valid = :valid where t.id = :id")
	Tweet updatePhone(@Param(value = "id") Long id, @Param(value = "valid") Boolean valid);

}
