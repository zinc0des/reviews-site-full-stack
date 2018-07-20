package org.wcci.reviewssitefullstack;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JpaMappingsTest {
	
	@Resource
	private TestEntityManager entityManager;

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private TagRepository tagRepo;
	
	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category", "description"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getName(), is("category"));
	}
	
	@Test
	public void shouldGenerateCategoryId() {
		Category category = categoryRepo.save(new Category("category", "description"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(categoryId, is(greaterThan(0L)));
	}
	@Test
	public void shouldSaveAndLoadReview() {
		Category indian = categoryRepo.save(new Category("indian", "description"));
		Review review = reviewRepo.save(new Review(indian, "title", "imageUrl", "content"));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getTitle(), is("title"));
	}
	
	@Test
	public void shouldEstablishCategoryToReviewRelationship() {
		Category indian = categoryRepo.save(new Category("indian", "description"));
		
		Review review = reviewRepo.save(new Review(indian, "title", "imageUrl", "content"));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getCategory(), is(indian));
		
	}
	@Test
	public void shouldFindReviewsForCategory() {
		Category indian = categoryRepo.save(new Category("indian", "description"));
		Review review1 = reviewRepo.save(new Review(indian, "title1", "imageUrl", "content"));
		Review review2 = reviewRepo.save(new Review(indian, "title2", "imageUrl", "content"));
		Collection<Review> reviewsForCategory = reviewRepo.findByCategory(indian);
		assertThat(reviewsForCategory, containsInAnyOrder(review1, review2));
	
	}
	
	@Test
	public void shouldSaveAndLoadTags() {
		Tag tag = tagRepo.save(new Tag("tag_name"));
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Tag> result = tagRepo.findById(tagId);
		tag = result.get();
		assertThat(tag.getName(), is("tag_name"));
	}

	@Test
	public void shouldGenerateTagId() {
		Tag tag = tagRepo.save(new Tag("tag_name"));
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(tagId, is(greaterThan(0L)));
	}

	@Test
	public void shouldEstablishReviewToTagRelationship() {
		// Tag is not the owner, so we must create tags first
		Tag tag1 = tagRepo.save(new Tag("tag1"));
		Tag tag2 = tagRepo.save(new Tag("tag2"));
		
		Category indian = categoryRepo.save(new Category("indian", "description"));
		
		Review review1 = reviewRepo.save(new Review(indian, "review1title", "imageUrl", "content", tag1, tag2));

		long reviewId = review1.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review1 = result.get();
		assertThat(review1.getTags(), containsInAnyOrder(tag1,tag2));
	}
	
	@Test
	public void shouldFindReviewsForTag() {
		Tag tag1 = tagRepo.save(new Tag("tag1"));
		
		Category indian = categoryRepo.save(new Category("indian", "description"));
		
		Review review1 = reviewRepo.save(new Review(indian, "review1title", "imageUrl", "content", tag1));
		Review review2 = reviewRepo.save(new Review(indian, "review2title", "imageUrl", "content", tag1));
		
		Collection<Review> reviewsForTag = reviewRepo.findByTags(tag1);
		
		assertThat(reviewsForTag, containsInAnyOrder(review1, review2));
	}

	@Test
	public void shouldFindReviewsForTagId() {
		Tag tag1 = tagRepo.save(new Tag("tag1"));
		long tagId = tag1.getId();
		
		Category indian = categoryRepo.save(new Category("indian", "description"));
		
		Review review1 = reviewRepo.save(new Review(indian, "review1title", "imageUrl", "content", tag1));
		Review review2 = reviewRepo.save(new Review(indian, "review2title", "imageUrl", "content", tag1));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForTopic = reviewRepo.findByTagsId(tagId);
		
		assertThat(reviewsForTopic, containsInAnyOrder(review1, review2));
	}

}