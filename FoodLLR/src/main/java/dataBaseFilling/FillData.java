package dataBaseFilling;

import org.json.JSONException;

import apiClient.ClientApi;
import dto.Food;
import jsonConverter.*;

public class FillData {

	//We need to launch the spring application before launching this main
	public static void main(String[] args) {
		//We connect to the openfood database
		Connection connection = new Connection();
		//We create a jsonreader with the value of the connection
		JSonReaderOpenFood reader = new JSonReaderOpenFood();
		try {
			//We read the result and put them into the database
			reader.read(connection.initiateConnection());
			System.out.println("Filling the data into OpenFoodLLR");
		} catch (JSONException e) {
			System.out.println("Unable to fill the database !");
		}
		//We insert all result inside the db with posts request
		ClientApi client = new ClientApi();

		//We add every object of our food list inside the db
		for (Food food : reader.getFoods()) {
			String request = null; 

			try {
				//We serialize our food inside a json
				JSONSerializer serializer = new JSONSerializer();
				request = serializer.SerializeJSON(food);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//We add our food with the json
			client.create(request);		
			};
	}

}
