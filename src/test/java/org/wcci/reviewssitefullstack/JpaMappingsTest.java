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
}


