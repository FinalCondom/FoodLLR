package dto;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//This is the standard class for the food object
@Document
public class Food{
	@Id
	private String id;
	private String name, ingredients;
	//this hashmap contain all the nutrients into the food
	private HashMap<String, Nutrients> nutrients;
	private String unit, portion_unit;
	private double quantity, portion_quantity;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getportion_unit() {
		return portion_unit;
	}
	public void setportion_unit(String portion_unit) {
		this.portion_unit = portion_unit;
	}
	public double getportion_quantity() {
		return portion_quantity;
	}
	public void setportion_quantity(double portion_quantity) {
		this.portion_quantity = portion_quantity;
	}
	public HashMap<String, Nutrients> getNutrients() {
		return nutrients;
	}
	public void setNutrients(HashMap<String, Nutrients> nutrients) {
		this.nutrients = nutrients;
	}	
}