package jsonConverter;

import org.json.JSONException;
import org.json.JSONObject;

import dto.Food;

public class JSONSerializer {
	//The tag for the json
	public static final String ID="id", NAME="name", 
			INGREDIENTS = "ingredients", UNIT = "unit", QUANTITY = "quantity", PORTION_QUANTITY = "portion_quantity", 
			PORTION_UNIT = "portion_unit", NUTRIENTS = "nutrients", 
			PER_HUNDRED = "per_Hundred", PER_PORTION = "per_Portion", PER_DAY = "per_Day";
	
	//We serialize a food into a json
	public String SerializeJSON(Food food) throws JSONException{
		JSONObject foodObject = new JSONObject();
		//We add avery value to the json object
		foodObject.put(ID, food.getId());
		foodObject.put(NAME, food.getName());
		foodObject.put(INGREDIENTS, food.getIngredients());
		foodObject.put(UNIT, food.getUnit());
		foodObject.put(QUANTITY, food.getQuantity());
		foodObject.put(PORTION_QUANTITY, food.getportion_quantity());
		foodObject.put(PORTION_UNIT, food.getportion_unit());
		//We add the hashmap to the json object
		foodObject.accumulate(NUTRIENTS, food.getNutrients());
		
		return(foodObject.toString());
	}
}
