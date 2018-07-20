package org.wcci.reviewssitefullstack;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewsPopulator implements CommandLineRunner {

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private TagRepository tagRepo;

	@Override
	public void run(String... args) throws Exception {
			
		Category indian = categoryRepo.save(new Category("Indian", "Indian food is rich, spicy and savoury. "
			+ "It is also extremely varied as different regions of the subcontinent have different methods of " 
			+ "preparing food."));
		
		Category italian = categoryRepo.save(new Category("Italian", "Traditional Central Italian cuisine uses " 
				+ "ingredients such as tomatoes, all kinds of meat, fish, and pecorino cheese."));
		
		Category greek = categoryRepo.save(new Category("Greek", "The foods of Greece not only taste amazing, " 
				+ "it's extremely healthy for you. Greece has one of the healthiest diets in the world."));
		
		Tag indianFood = tagRepo.save(new Tag("indian-food"));
		Tag bestRecipes = tagRepo.save(new Tag("best-recipes"));
		Tag italianRecipes = tagRepo.save(new Tag("italian-recipes"));
		Tag greekFood = tagRepo.save(new Tag("greek-food"));
		Tag healthyRecipes = tagRepo.save(new Tag("healthy-recipes"));
		Tag quickRecipes = tagRepo.save(new Tag("quick-recipes"));
		Tag worldFoods = tagRepo.save(new Tag("world-foods"));
		Tag top10ethnic = tagRepo.save(new Tag("top-10-ethnic-foods"));
		
		Review biryani = reviewRepo.save(new Review(indian, "Chicken Biryani", "/images/biryani.jpg",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse id fermentum neque. "
				+ "Ut et dictum quam. In non accumsan sem. Donec quam tortor, ultricies eu arcu at, porta "
				+ "sollicitudin mi. Vestibulum aliquam volutpat fringilla. Sed nec porta elit. Phasellus nec "
				+ "sapien nisl. Sed nec tellus ac augue mattis pulvinar sed vel sem. Vestibulum nec dapibus " 
				+ "neque, id vestibulum ligula.", 
				 indianFood, bestRecipes, top10ethnic)); //Tags
		
		Review spaghetti = reviewRepo.save(new Review(italian, "Spaghetti and Meatballs", "/images/spaghetti.jpg",
				"Vestibulum erat urna, sollicitudin in ligula at, hendrerit ullamcorper dui. In quis diam eros. "
				+ "Pellentesque sit amet vulputate tortor. Nunc volutpat finibus laoreet. Pellentesque nec " 
				+ "ornare nibh, vel sollicitudin tellus. Vivamus efficitur dignissim tortor sagittis fringilla.",
				italianRecipes, top10ethnic, bestRecipes)); //Tag
		
		Review tabouleh = reviewRepo.save(new Review(greek, "Tabouleh Salad", "/images/greek.jpg", "Nunc sodales " 
				+ "urna massa, nec finibus tellus vulputate in. Nam at lorem vehicula, auctor neque eu, lacinia arcu. "
				+ "Vivamus vestibulum nibh aliquam sodales faucibus. Nulla facilisi. Aliquam varius sodales enim, a "
				+ "gravida diam ornare et. Nam varius feugiat tortor vitae tincidunt.",
				greekFood, healthyRecipes, bestRecipes, top10ethnic)); //Tag
	}
}	