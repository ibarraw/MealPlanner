package ca.sheridancollege.ibarraw.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.ibarraw.beans.Meal;
import ca.sheridancollege.ibarraw.database.DatabaseAccess;

@Controller
public class MealController {

	@Autowired
	private DatabaseAccess da;
	
	
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("mealList", da.getMealList());
		model.addAttribute("meal", new Meal());
		return "index";
	}
	
	@PostMapping("/insertMeal")
	public String insertMeal(Model model, @ModelAttribute Meal meal) {
		
		//if checkbox is not checked this returns null
		//if it is null, then set the meal to "False" String type manually
		if (meal.getInStock() == null) {
			System.out.println("checkbox is: " + meal.getInStock());
			meal.setInStock("No");
		}
		else { 
			//else if not null, then checkbox returns "on" as it is boolean type
			//if it is not null, then set the meal to "True" String type manually
			System.out.println("checkbox is: " + meal.getInStock());
			meal.setInStock("Yes");	
		}
		
		da.insertMeal(meal); //insert the new meal here
		
		model.addAttribute("mealList", da.getMealList());
		model.addAttribute("meal", new Meal());
		
		return "index";
	}
	
	@GetMapping("/deleteMealById/{id}")
	public String deleteMealById(Model model, @PathVariable Long id) {
		da.deleteMealById(id);
		
		model.addAttribute("mealList", da.getMealList());
		model.addAttribute("meal", new Meal());
		
		return "index";
	}
	
	@GetMapping("/editMealById/{id}")
	public String editMealById(Model model, @PathVariable Long id) {
		Meal meal = da.getMealListById(id).get(0);
		model.addAttribute("meal", meal);
		da.deleteMealById(id);
		model.addAttribute("mealList", da.getMealList());
		
		return "index";
	}
	
	@GetMapping("/shopping")
	public String shopping(Model model) {
		   
	   List<Meal> meal = da.getMealList();
	   model.addAttribute("meal", new Meal());
	   model.addAttribute("mealList", meal);
	
	   return "shopping";
	}
}
