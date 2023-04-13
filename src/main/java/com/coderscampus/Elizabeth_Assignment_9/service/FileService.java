package com.coderscampus.Elizabeth_Assignment_9.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderscampus.Elizabeth_Assignment_9.domain.Recipe;

@Service
public class FileService {
	
	private List<Recipe> recipesList = new ArrayList<>();

	@Autowired
	public List<Recipe> recipesList() {	
		return recipesList;
	}
	
	@Autowired
	public List<Recipe> getRecipesList() throws IOException {		
		if(recipesList.size() > 0) {
			return recipesList();
		}
		
		return readCSV();
	}
	
	@SuppressWarnings("deprecation")
	public List<Recipe> readCSV() throws IOException {
		
		Reader in = new FileReader("recipes.txt");
	
		Iterable<CSVRecord> recipes = CSVFormat.DEFAULT.withDelimiter(',').withEscape('\\').withFirstRecordAsHeader().withSkipHeaderRecord().withIgnoreSurroundingSpaces().parse(in);
		for (CSVRecord recipe : recipes) {
			Integer cookingMinutes = Integer.parseInt(recipe.get(0));
			Boolean dairyFree = Boolean.parseBoolean(recipe.get(1));
			Boolean glutenFree = Boolean.parseBoolean(recipe.get(2));
			String instructions = recipe.get(3);
			Double preparationsMinutes = Double.parseDouble(recipe.get(4));
			Double pricePerServing = Double.parseDouble(recipe.get(5));
			Integer readyInMinutes = Integer.parseInt(recipe.get(6));
			Integer servings = Integer.parseInt(recipe.get(7));
			Double spoonacularScore = Double.parseDouble(recipe.get(8));
			String title = recipe.get(9);
			Boolean vegan = Boolean.parseBoolean(recipe.get(10));
			Boolean vegetarian = Boolean.parseBoolean(recipe.get(11));
			
			Recipe recipeData = new Recipe();
			
			recipeData.setCookingMinutes(cookingMinutes);
			recipeData.setDairyFree(dairyFree);
			recipeData.setGlutenFree(glutenFree);
			recipeData.setInstructions(instructions);
			recipeData.setPreparationMinutes(preparationsMinutes);
			recipeData.setPricePerServing(pricePerServing);
			recipeData.setReadyInMinutes(readyInMinutes);
			recipeData.setServings(servings);
			recipeData.setSpoonacularScore(spoonacularScore);
			recipeData.setTitle(title);
			recipeData.setVegan(vegan);
			recipeData.setVegetarian(vegetarian);
			
			recipesList().add(recipeData);
		}
		
		return recipesList();
	}
	

}