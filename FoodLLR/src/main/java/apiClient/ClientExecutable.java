package apiClient;

import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONException;

import dto.Food;
import dto.Nutrients;
import jsonConverter.JSONSerializer;

public class ClientExecutable {

	private static Scanner scan;
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		ClientApi client = new ClientApi();
		boolean repeat = true;
		while(repeat){
			System.out.println("Enter the number corresponding to the operation you want: \n\t1: getting all foods, "
					+ "\n\t2: getting all foods sorted by quantity ascending, \n\t3: getting foods corresponding to a name,"
					+ "\n\t4: getting foods corresponding to a name and it's quantity,\n\t5: create a new food"
					+ ", \n\t6: update a certain food, \n\t7: delete a food, \n\t everything else for exiting the programm");
			int request = scan.nextInt();
			scan.nextLine();
			switch (request) {
			case 1:
				getAllFoods(client);
				break;
			case 2:		
				getAllFoodsSortedByQuantity(client);
				break;
			case 3:
				getFoodsByName(client);
				break;
			case 4:
				getFoodsByNameAndQuantity(client);
				break;
			case 5:
				CreateFood(client);			
				break;
			case 6:
				UpdateFood(client);			
				break;
			case 7:
				DeleteFood(client);
				break;
			default:
				repeat=false;
				break;
			}
		}
		scan.close();
	}
	
	private static void getAllFoods(ClientApi client){
		System.out.println("Getting all the foods !");
		System.out.println(client.getAll());
	}
	
	private static void getAllFoodsSortedByQuantity(ClientApi client){
		System.out.println("Getting all the foods sorted by quantity !");
		System.out.println(client.getSortedByQuantity());
	}
	
	private static void getFoodsByName(ClientApi client){
		System.out.print("Enter the name of your food: ");
		String name = scan.nextLine();
		System.out.println("Getting foods with the name "+name+" !");
		System.out.println(client.getByName(name));
	}
	
	private static void getFoodsByNameAndQuantity(ClientApi client){
		System.out.print("Enter the name of your food: ");
		String name = scan.nextLine();
		System.out.print("Enter the quantity of your food: ");
		double quantity = scan.nextDouble();
		scan.nextLine();
		System.out.println("Getting foods with the name "+name+" and the quantity "+quantity+" !");
		System.out.println(client.getByNameAndQuantity(name, quantity));	
	}
	
	private static void CreateFood(ClientApi client){
		Food food = CreateFoodObject();
		String request = null; 
		try {
			JSONSerializer serializer = new JSONSerializer();
			request = serializer.SerializeJSON(food);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int response = client.create(request);
		if(response==200){
			System.out.println("Insertion done");
		}
		else{
			System.out.println("Error in the transaction, code "+response);
		}
	}
		
	private static Food UpdateFoodObject(){
		Food food = CreateFoodObject();
		System.out.print("Enter the id of your food: ");
		scan.nextLine();
		food.setId(scan.nextLine());
		return food;
	}
	
	private static void UpdateFood(ClientApi client){
		System.out.println("Updating a food");
		Food food = UpdateFoodObject();
		String request = null; 
		try {
			JSONSerializer serializer = new JSONSerializer();
			request = serializer.SerializeJSON(food);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int response = client.update(request, food.getId());
		if(response==200){
			System.out.println("update done");
		}
		else{
			System.out.println("Error in the transaction, code "+response);
		}
	}
	
	private static Food CreateFoodObject(){
		Food food = new Food();
		System.out.print("Enter the name of your food: ");
		food.setName(scan.nextLine());
		System.out.print("Enter the name with ingredients of your food: ");
		food.setIngredients(scan.nextLine());
		System.out.print("Enter the quantity of your food: ");
		food.setQuantity(scan.nextDouble());
		scan.nextLine();
		System.out.print("Enter the measure unit of your food: ");
		food.setUnit(scan.nextLine());
		System.out.print("Enter the quantity per portion of your food: ");
		food.setportion_quantity(scan.nextDouble());
		scan.nextLine();
		System.out.print("Enter the unit per portion of your food: ");
		food.setportion_unit(scan.nextLine());
		HashMap<String, Nutrients> nutrients = new HashMap<String, Nutrients>();
		boolean repeat = true;
		while(repeat){
			System.out.println("Do you want to add a nutriment y/n ?");
			char letter = scan.next().charAt(0);
			if(letter == 'y'){
				scan.nextLine();
				Nutrients nutrient = new Nutrients();
				System.out.print("Enter the name of your nutriment: ");
				nutrient.setName(scan.nextLine());
				System.out.print("Enter the consumption per day of your nutriment: ");
				nutrient.setPer_Day(scan.nextDouble());
				scan.nextLine();
				System.out.print("Enter the consumption per Hundred of your nutriment: ");
				nutrient.setPer_Hundred(scan.nextDouble());
				scan.nextLine();
				System.out.print("Enter the consumption per Portion of your nutriment: ");
				nutrient.setPer_Portion(scan.nextDouble());
				scan.nextLine();
				System.out.print("Enter the unit of your nutriment: ");
				nutrient.setUnit(scan.nextLine());
				nutrients.put(nutrient.getName(), nutrient);
			}
			else{
				repeat = false;
			}
		}
		food.setNutrients(nutrients);
		return food;		
	}

	private static void DeleteFood(ClientApi client){
		System.out.println("Enter the food id you want to delete !");
		String id = scan.nextLine();
		int response = client.delete(id);
		if(response==200){
			System.out.println("Supression done");
		}
		else{
			System.out.println("Error in the transaction, code "+response);
		}	
	}
}
