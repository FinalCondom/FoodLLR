package ch.hes.FoodLLR;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import dto.Food;

public interface FoodRepository extends MongoRepository<Food, String> {
	//use to find by a name without being case sensitive
	List<Food> findByNameIgnoreCase(String name);
	
	//use to find by a name without being case sensitive and a quantity
	List<Food> findByQuantityAndNameIgnoreCase(double quantity, String name);
}	