package com.coderscampus.Elizabeth_Assignment_9.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coderscampus.Elizabeth_Assignment_9.domain.Recipe;
import com.coderscampus.Elizabeth_Assignment_9.service.FileService;

@RestController
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	public List<Recipe> recipesList = new ArrayList<>();

	@GetMapping("/gluten-free")
	public String glutenFree() throws IOException {
		List<Recipe> glutenFree =  fileService.readCSV(recipesList).stream().filter(gluten -> gluten.getGlutenFree()).collect(Collectors.toList()); 
		return glutenFree.toString();
	}
	
	@GetMapping("/vegan")
	public String vegan() throws IOException {
		List<Recipe> vegan = fileService.readCSV(recipesList).stream().filter(v -> v.getVegan()).collect(Collectors.toList());
		return vegan.toString();
	}
	
	@GetMapping("/vegan-and-gluten-free")
	public String veganGluten() throws IOException {
		List<Recipe> veganAndGlutenFree = fileService.readCSV(recipesList).stream().filter(v -> v.getVegan()).filter(g -> g.getGlutenFree())
				.collect(Collectors.toList());
		return veganAndGlutenFree.toString();
	}
	
	@GetMapping("/vegetarian")
	public String vegetarian() throws IOException {
		List<Recipe> vegetarian = fileService.readCSV(recipesList).stream().filter(veg -> veg.getVegetarian()).collect(Collectors.toList());
		return vegetarian.toString();
	}
	
	@GetMapping("/all-recipes")
	public String allRecipes() throws IOException {
		
		return fileService.readCSV(recipesList).toString();
	}
	
}
