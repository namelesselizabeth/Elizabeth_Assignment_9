package com.coderscampus.Elizabeth_Assignment_9.web;

import java.io.IOException;
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

	@GetMapping("/gluten-free")
	public List<Recipe> glutenFree() throws IOException { 
		return fileService.readCSV().stream().filter(g -> g.getGlutenFree()).collect(Collectors.toList());
	}

	@GetMapping("/vegan")
	public List<Recipe> vegan() throws IOException {	
		return fileService.readCSV().stream().filter(v -> v.getVegan()).collect(Collectors.toList());
	}
	
	@GetMapping("/vegan-and-gluten-free")
	public List<Recipe> veganGluten() throws IOException {
		return fileService.readCSV().stream().filter(v -> v.getVegan()).filter(g -> g.getGlutenFree())
				.collect(Collectors.toList());
	}
	
	@GetMapping("/vegetarian")
	public List<Recipe> vegetarian() throws IOException {
		return fileService.readCSV().stream().filter(veg -> veg.getVegetarian()).collect(Collectors.toList());
	}
	
	@GetMapping("/all-recipes")
	public List<Recipe> allRecipes() throws IOException {
		
		return fileService.readCSV();
	}
	
}
