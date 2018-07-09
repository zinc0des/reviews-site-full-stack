package org.wcci.reviewssitefullstack;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	@Lob
	private String description;
	

	@OneToMany(mappedBy = "category")
	private Collection<Review> reviews;

	
	protected Category() {
		}

	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Collection<Review> getReviews() {
		return reviews;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
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
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		return id == ((Category) obj).id;
	}
}