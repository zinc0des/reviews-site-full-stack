package org.wcci.reviewssitefullstack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewsControllerMockMvcTest {
	// Setup
		@Resource
		private MockMvc mvc;
		
		@MockBean
		private ReviewRepository reviewRepo;
		
		@MockBean
		private CategoryRepository categoryRepo;
		
		@Mock
		private Review review;
		
		@Mock
		private Review anotherReview;
		
		@Mock
		private Category category;
		
		@Mock
		private Category anotherCategory;
		
		@Test 
		public void shouldRouteToSingleReviewView() throws Exception {
			long arbitraryReviewId = 1;
			when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
			mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
		}
		
		@Test
		public void shouldBeOkForSingleReview() throws Exception {
			long arbitraryReviewId = 1;
			when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
			mvc.perform(get("/review?id=1")).andExpect(status().isOk());
		}
		
		@Test
		public void shouldNotBeOkForSingleReview() throws Exception {
			mvc.perform(get("/review?id=1")).andExpect(status().isNotFound());
		}
		
		@Test
		public void shouldPutSingleReviewIntoModel() throws Exception {
			when(reviewRepo.findById(1L)).thenReturn(Optional.of(review));
			
			mvc.perform(get("/review?id=1")).andExpect(model().attribute("review",  is(review)));
		}
		
		// Testing for all reviews
		@Test
		public void shouldRouteToAllReviewsView() throws Exception {
			mvc.perform(get("/show-reviews")).andExpect(view().name(is("reviews")));
		}
		
		@Test
		public void shouldBeOkForAllReviews() throws Exception {
			mvc.perform(get("/show-reviews")).andExpect(status().isOk());
		}
		
		@Test
		public void shouldPutAllReviewsIntoModel() throws Exception{
			Collection<Review> allReviews = Arrays.asList(review, anotherReview);
			when(reviewRepo.findAll()).thenReturn(allReviews);
			
			mvc.perform(get("/show-reviews")).andExpect(model().attribute("reviews", is(allReviews)));
		}
		
		// Testing for single Category
		@Test
		public void shouldRouteToSingleCategoryView() throws Exception {
			long arbitraryCategoryId = 1;
			when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
			mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
		}
		
		@Test
		public void shouldBeOkForSingleCategory() throws Exception {
			long arbitraryCategoryId = 1;
			when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
			mvc.perform(get("/category?id=1")).andExpect(status().isOk());
		}
		
		@Test
		public void shouldNotBeOkForSingleCategory() throws Exception {
			mvc.perform(get("/category?id=1")).andExpect(status().isNotFound());
		}
		
		@Test
		public void shouldPutSingleCategoryIntoModel() throws Exception {
			when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
			
			mvc.perform(get("/category?id=1")).andExpect(model().attribute("category", is(category)));
		}
		
		// Testing for all Categories
		@Test
		public void shouldRouteToAllCategoriesView() throws Exception {
			mvc.perform(get("/show-categories")).andExpect(view().name(is("categories")));
		}
		
		@Test
		public void shouldBeOkForAllCategories() throws Exception {
			mvc.perform(get("/show-categories")).andExpect(status().isOk());
		}
		
		@Test
		public void shouldPutAllCategoriesIntoModel() throws Exception {
			Collection<Category> allCategories = Arrays.asList(category, anotherCategory);
			when(categoryRepo.findAll()).thenReturn(allCategories);
			
			mvc.perform(get("/show-categories")).andExpect(model().attribute("categories", is(allCategories)));
		}
}
