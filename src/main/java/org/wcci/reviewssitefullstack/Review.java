package org.wcci.reviewssitefullstack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Category category;

	@Lob
	private String content;
	private String title;
	private String imageUrl;

	protected Review() {
	}

	public Review(Category category, String title, String imageUrl, String content) {
		this.category = category;
		this.title = title;
		this.imageUrl = imageUrl;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getImageUrl() {
		return imageUrl;
	}
		
	public Category getCategory() {
		return category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
