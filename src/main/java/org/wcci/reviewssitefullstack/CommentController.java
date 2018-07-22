package org.wcci.reviewssitefullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {
	
	@Resource
	private CommentRepository commentRepo;
	
	@Resource
	private ReviewRepository reviewRepo;

	//Build RequestMapping for the controller
	
	//Using HTML forms, dynamically add comments to a review
	
	//RequestMapping tied to a button
	@RequestMapping("/add-comment")
	//return view of template-add author name in text field, grab appropriate review ID
	//exact matches for the variables is very important
	public String addComment(String author, Long reviewId, String content) {
	//Find a review by Id
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
	
		Comment newComment = new Comment(author, review, content);
		commentRepo.save(newComment);
		
		return "redirect:/review?id=" + reviewId;
		
	}
	
	
	
}
