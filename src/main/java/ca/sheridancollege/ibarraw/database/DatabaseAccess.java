package ca.sheridancollege.ibarraw.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.ibarraw.beans.Meal;

@Repository
public class DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	//id, drink, mainDish, treat, dayOfWeek, description, inStock
	public void insertMeal(Meal meal) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "INSERT INTO meal(drink, mainDish, treat, dayOfWeek, description, inStock)"
				+ " VALUES(:drink, :mainDish, :treat, :dayOfWeek, :description, :inStock)";
		
		namedParameters.addValue("drink", meal.getDrink());
		namedParameters.addValue("mainDish", meal.getMainDish());
		namedParameters.addValue("treat", meal.getTreat());
		namedParameters.addValue("dayOfWeek", meal.getDayOfWeek());
		namedParameters.addValue("description", meal.getDescription());
		namedParameters.addValue("inStock", meal.getInStock());
		
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0)
			System.out.println("A meal was successfully inserted into the database");
			System.out.println(meal);
	}
	
	public List<Meal> getMealList() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM meal";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Meal>(Meal.class));
	}
	
	public void deleteMealById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM meal WHERE id = :id";
		namedParameters.addValue("id", id);
		
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0) {
			System.out.println("Meal with id = " + id + " deleted from database");
		}
	}
	
	
	public List<Meal> getMealListById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * from meal WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Meal>(Meal.class));
	}
}
