package ca.sheridancollege.ibarraw.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Meal {

	//drink, mainDish, treat, dayOfWeek, description, inStock
	private Long id;
	private String drink;
	private String mainDish;
	private String treat;
	private String dayOfWeek;
	private String description;
	private String inStock;
	
}
