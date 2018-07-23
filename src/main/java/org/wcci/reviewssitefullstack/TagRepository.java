package org.wcci.reviewssitefullstack;


import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {

	Tag findByName(String tagName);


}
