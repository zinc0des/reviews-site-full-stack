package org.wcci.reviewssitefullstack;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
	
	Optional<Tag> findFirstByName(String tagName);

}
