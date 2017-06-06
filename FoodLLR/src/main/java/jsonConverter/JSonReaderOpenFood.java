package jsonConverter;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dto.Food;
import dto.Nutrients;

public class JSonReaderOpenFood {
	private static ArrayList<Food> foods;
	
	//all keys for the json
	public static final String DATA = "data", ID="id", NAME_TRANSLATIONS="name_translations", 
			INGREDIENT_TRANSLATION = "ingredients_translations", DE="de", FR="fr", IT="it", 
			EN = "en", UNIT = "unit", QUANTITY = "quantity", PORTION_QUANTITY = "portion_quantity", 
			PORTION_UNIT = "portion_unit", NUTRIENTS = "nutrients", PROTEIN = "protein", 
			PER_HUNDRED = "per_hundred", SUGAR = "sugar", SALT = "salt", FIBER = "fiber",
			SATUREDFAT = "saturedFat", PER_PORTION = "per_portion", PER_DAY = "per_day", CARBOHYDRATES = "carbohydrates", 
			FAT = "fat", ENERGY_KCAL = "energy_kcal", ENERGY = "energy";

	//read the data from a json inside a food object
	public void read(String dataFromAPI) throws JSONException{
		foods = new ArrayList<Food>();
		//We create an object with the whole json file
		JSONObject mainObject = new JSONObject(dataFromAPI);
		//The data array symbolize all object into the JSON file
		JSONArray dataArray = mainObject.getJSONArray(DATA);
		//We take all objects separately
		for(int i=0; i < dataArray.length(); i++){
			//Each object is a new food
			Food food = new Food();
			
			//Shortcut to the food object
			JSONObject foodObject = dataArray.getJSONObject(i);
			
			//we get back the id of the object
			food.setId(foodObject.getInt(ID)+"");
			
			//Shortcut to the name translation object
			JSONObject nameTranslationObject = foodObject.getJSONObject(NAME_TRANSLATIONS);
			JSONObject ingredientTranslationObject = foodObject.getJSONObject(INGREDIENT_TRANSLATION);

			//We try to set the name to french, if not english, then german, italian and if nothing we enter a default value
			try {
				food.setName(nameTranslationObject.getString(FR));
				food.setIngredients(ingredientTranslationObject.getString(FR));
			} catch (JSONException e) {
				try{
					food.setName(nameTranslationObject.getString(EN));
					food.setIngredients(ingredientTranslationObject.getString(EN));
				} catch (JSONException e2){
					try{
						food.setName(nameTranslationObject.getString(DE));
						food.setIngredients(ingredientTranslationObject.getString(DE));
					} catch (JSONException e3){
						try{
							food.setName(nameTranslationObject.getString(IT));
							food.setIngredients(nameTranslationObject.getString(IT));

						} catch (JSONException e4){
							food.setName("NONAME");
							food.setIngredients("NONAME");
							System.out.println("The food you entered has no ingredient in it");
						}
					}
				}
			}
			
			//We set up the different values of the food (unit, quanity and by portion)
			try {
				food.setUnit(foodObject.getString(UNIT));
			} catch (JSONException e) {
				food.setUnit("");
			}	
			try {
				food.setQuantity(foodObject.getDouble(UNIT));
			} catch (JSONException e) {
				food.setQuantity(0.0);
			}				
			try {
				food.setportion_quantity(foodObject.getDouble(PORTION_QUANTITY));
			} catch (JSONException e) {
				food.setportion_quantity(0.0);
			}	
			try {
				food.setportion_unit(foodObject.getString(PORTION_UNIT));
			} catch (JSONException e) {
				food.setportion_unit("");
			}				
			
			//We take the object nutrient and insert them into the hashmap
			JSONObject nutrients = foodObject.getJSONObject(NUTRIENTS);
			
			HashMap<String, Nutrients> foodMap = new HashMap<String, Nutrients>();
			
			foodMap.put(SALT, addIngredient(food, nutrients, SALT));
			foodMap.put(PROTEIN, addIngredient(food, nutrients, PROTEIN));
			foodMap.put(FIBER, addIngredient(food, nutrients, FIBER));
			foodMap.put(SUGAR, addIngredient(food, nutrients, SUGAR));
			foodMap.put(CARBOHYDRATES, addIngredient(food, nutrients, CARBOHYDRATES));
			foodMap.put(SATUREDFAT, addIngredient(food, nutrients, SATUREDFAT));
			foodMap.put(ENERGY_KCAL, addIngredient(food, nutrients, ENERGY_KCAL));
			foodMap.put(ENERGY, addIngredient(food, nutrients, ENERGY));
			
			//We add the hashmap to the food
			food.setNutrients(foodMap);
			
			//We add the food object to the food list
			foods.add(food);
		}
	}
		
	//this method add a given nutrients to a given food
	private static Nutrients addIngredient(Food food, JSONObject nutrients, String ingredientName) {
		JSONObject ingredientObject = null;

		try {
			ingredientObject = nutrients.getJSONObject(ingredientName);
		} catch (JSONException e1) {
		// TODO Auto-generated catch block
			System.out.println("NO "+ingredientName+" ADDED !");
			return null;
		}
		
		Nutrients ingredient = new Nutrients();
		JSONObject nameTranslationObject = null;
		try {
			nameTranslationObject = ingredientObject.getJSONObject(NAME_TRANSLATIONS);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

//		We set up the fat translations and their values and handle where there is none
		try {
			ingredient.setName(nameTranslationObject.getString(FR));
		} catch (JSONException e) {
			try{
				ingredient.setName(nameTranslationObject.getString(EN));
			} catch (JSONException e2){
				try{
					ingredient.setName(nameTranslationObject.getString(DE));
				} catch (JSONException e3){
					try{
						ingredient.setName(nameTranslationObject.getString(IT));
					} catch (JSONException e4){
						ingredient.setName("NONAME");
						System.out.println("The "+ingredientName+" you entered has no ingredient in it");
					}
				}
			}
		}
		//We add the value to the nutrient
		try {
			ingredient.setUnit(ingredientObject.getString(UNIT));
		} catch (JSONException e) {
			ingredient.setUnit("");
		}	
		try {
			ingredient.setPer_Hundred(ingredientObject.getDouble(PER_HUNDRED));
		} catch (JSONException e) {
			ingredient.setPer_Hundred(0.0);
		}
		try {
			ingredient.setPer_Portion(ingredientObject.getDouble(PER_PORTION));
		} catch (JSONException e) {
			ingredient.setPer_Portion(0.0);
		}
		try {
			ingredient.setPer_Day(ingredientObject.getDouble(PER_DAY));
		} catch (JSONException e) {
			ingredient.setPer_Day(0.0);
		}
		//We return the value
		return ingredient;
	}
	
	public ArrayList<Food> getFoods() {
		return foods;
	}
}
