package tweets.TweetsConsumer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.data.geo.Point;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tweet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	private String user;

	@Column
	@Lob
	private String text;

	@Column(nullable = true)
	private Point location;

	@Column(nullable = false)
	private Boolean valid;

	public Tweet() {
	}

	public Tweet(String user, String text, Point location, Boolean valid) {

		this.user = user;

		this.text = text;

		this.location = location;

		this.valid = valid;
	}

	public List<String> hashTags() {

		String text = this.text;

		Pattern pattern = Pattern.compile("#(\\S+)");

		Matcher mat = pattern.matcher(text);

		List<String> hashtags = new ArrayList<String>();

		while (mat.find()) {
			hashtags.add(mat.group(1));
		}

		return hashtags;
	}

	@Override
	public String toString() {
		StringBuilder result;

		result = new StringBuilder();
		result.append(this.getClass().getName()).append(" {").append("id = ").append(this.getId()).append(", user = ")
				.append(this.getUser()).append(", text = ").append(this.getText()).append(", valid = ")
				.append(this.getValid()).append("}");

		return result.toString();
	}

}