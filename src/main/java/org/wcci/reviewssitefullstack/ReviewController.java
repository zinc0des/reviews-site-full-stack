package org.wcci.reviewssitefullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ReviewController {

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	TagRepository tagRepo;

	@RequestMapping("/show-reviews")
	public String getAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return "reviews";
	}

	@RequestMapping("/review")
	public String getAReview(@RequestParam(value="id") long reviewId, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(reviewId);
		
		if(review.isPresent() ) {
			model.addAttribute("reviews", review.get());
			return "review";
		}
		throw new ReviewNotFoundException();
	}

	@RequestMapping("/show-categories")
	public String getAllCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return "categories";
	}

	@RequestMapping("/category")
	public String getACategory(@RequestParam(value="id") long categoryId, Model model) throws CategoryNotFoundException {
		Optional<Category> category = categoryRepo.findById(categoryId);
		
		if (category.isPresent() ) {
			model.addAttribute("categories", category.get());
			return "category";
		}
		throw new CategoryNotFoundException();
	}
	
	@RequestMapping("/tag")
	public String findOneTag(@RequestParam(value="id")long tagId, Model model) throws TagNotFoundException {
		Optional<Tag> tag = tagRepo.findById(tagId);
		
		if(tag.isPresent()) {
			model.addAttribute("tags", tag.get());
			return "tag";
		}
		throw new TagNotFoundException();
	}

	@RequestMapping("/show-tags")
	public String findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return "tags";
	}
	
	// Add a newly created tag directly to a specific review using a form.
	@PostMapping("/new-tag")
	public RedirectView createTagForReview(
		@RequestParam(value="tagName") String tagName,
		@RequestParam(value="reviewId") Long reviewId,
		Model model) {
		
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		
		if (reviewResult.isPresent()) {
			Tag createdTag = tagRepo.save(new Tag(tagName));
			Review review = reviewResult.get();
			review.addTag(createdTag);
			reviewRepo.save(review);
		}

		return new RedirectView("/review?id=" + reviewId);
	}
	
	@PostMapping("/remove-tag-from-review")
	public RedirectView removeTagForReview(
			@RequestParam(value="tagName") String tagName,
			@RequestParam(value="reviewId") Long reviewId,
			Model model) {
			
			Optional<Review> reviewResult = reviewRepo.findById(reviewId);
			
			if (reviewResult.isPresent()) {
				Review review = reviewResult.get();
				
				Optional<Tag> tag = tagRepo.findFirstByName(tagName);
				
				review.deleteTag(tag.get());
				reviewRepo.save(review);
			}
			
			return new RedirectView("/review?id=" + reviewId);
	}
	
	
}
	

	