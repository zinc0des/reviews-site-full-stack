package org.wcci.reviewssitefullstack;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	@ManyToMany(mappedBy ="tags")
	private Collection<Review> reviews;

	// Constructors
	protected Tag() {} //JPA Default Constructor
	
	public Tag(String name) {
		this.name = name;
	}
	
	// Getters
	public String getName() {
		return name;
	}
	
	public long getId() {
		return id;
	}
	
	public Collection<Review> getReviews() {
		return reviews;
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
		Tag other = (Tag) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
