package org.wcci.reviewssitefullstack;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Category category;

	@ManyToMany
	private Collection<Tag>	tags;
	
	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;
	
	@Lob
	private String content;
	private String title;
	private String imageUrl;

	
	protected Review() {}

	public Review(Category category, String title, String imageUrl, String content, Tag...tags) {
		this.category = category;
		this.title = title;
		this.imageUrl = imageUrl;
		this.content = content;
		this.tags = new HashSet<>(Arrays.asList(tags));
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

	public Collection<Tag> getTags() {
		return tags;
	}

//collection of comments that will go into review entity
	public Collection<Comment> getComments() {
		return comments;
	}
	
	// Methods
	public void addTag(Tag newTag) {
		this.tags.add(newTag);
	}
	
	//allowing the collection to have a tag removed
	public void removeTag(Tag tagToRemove) {
		tags.remove(tagToRemove);
		
	}
	
	// hashCode() & equals() for entity id
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
