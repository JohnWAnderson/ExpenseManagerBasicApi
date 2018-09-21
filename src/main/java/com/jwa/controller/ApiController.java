package com.jwa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwa.errors.NotFoundApi;
import com.jwa.model.Item;
import com.jwa.model.User;
import com.jwa.repository.ItemRepository;
import com.jwa.repository.UserRepository;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/api") // This means URL's start with /demo (after Application path)
public class ApiController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping(path="/user")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping(path="/user")
	public void createUser(@RequestBody  User user){
		user.setItems(new ArrayList<>());
		if(user.getUsername() == null)
			throw new NotFoundApi("Invalid user");
		userRepository.save(user);
	}
	
	@PutMapping("/user/{userID}")
	public ResponseEntity<Object> updateUser(@RequestBody  User user, @PathVariable int userID) {
		Optional<User> theUserOption = userRepository.findById(userID);
		if(!theUserOption.isPresent()) 
			throw new NotFoundApi("Didn't find user");
		User theUser = theUserOption.get();
		theUser.setUsername(user.getUsername());
		userRepository.save(theUser);
		return null;
	}	
	
	@GetMapping("/user/{userID}")
	public Optional<User> getUser(@PathVariable int userID) {
		Optional<User> theUser = userRepository.findById(userID);
		if(!theUser.isPresent()) 
			throw new NotFoundApi("Didn't find user");
		return theUser;
	}	
	
	@DeleteMapping("/user/{userID}")
	public void deleteUser(@PathVariable int userID) {
		userRepository.deleteById(userID);
	}

	@GetMapping(path="/userName/{userName}")
	public Optional<User> getUserId(@PathVariable String userName) {
		Optional<User> theUser = userRepository.findByUsername(userName);
		if(!theUser.isPresent()) 
			throw new NotFoundApi("Didn't find user");
		return theUser;
	}
	
	@GetMapping(path="/user/{userID}/item")
	@CrossOrigin("http://localhost:8080")
	public @ResponseBody List<Item>  getAllItems(@PathVariable int userID) {
		Optional<User> userOption = userRepository.findById(userID);
		if(!userOption.isPresent()) 
			throw new NotFoundApi("Didn't find items");
		return userOption.get().getItems();
		
	}
	
	@PostMapping(path="/user/{userID}/item")
	public void createItem(@RequestBody Item item, @PathVariable int userID){
		Optional<User> userOption = userRepository.findById(userID);
		if(!userOption.isPresent()) 
			throw new NotFoundApi("Didn't find User");
		User theUser = userOption.get();
		item.setUser(theUser);
		itemRepository.save(item);
	}
	
	@GetMapping("/user/{userID}/item/{itemID}")
	public Optional<Item> getItem(@PathVariable int userID, @PathVariable int itemID) {
		Optional<Item> theItem =  itemRepository.findById(itemID);
		if(!theItem.isPresent() || theItem.get().getUser().getId() != userID) 
			throw new NotFoundApi("User have that item");
		return theItem;
	}
	
	@PutMapping("/user/{userID}/item/{itemID}")
	public ResponseEntity<Object> updateUserItem(@RequestBody  Item item, @PathVariable int userID, @PathVariable int itemID) {
		Optional<Item> itemOption = itemRepository.findById(itemID);
		if(!itemOption.isPresent() || itemOption.get().getUser().getId() != userID)  
			throw new NotFoundApi("Didn't find item");
		Item theItem = itemOption.get();
		if(item.getCost() > 0) {
			theItem.setCost(item.getCost());}
		if(item.getDescrition() != null) {
			theItem.setDescrition(item.getDescrition());}
		if(item.getName() != null) {
			theItem.setName(item.getName());}
		itemRepository.save(theItem);
		return null;
	}	
	
	@DeleteMapping("/user/{userID}/item/{itemID}")
	public void deleteUserItem(@PathVariable int userID, @PathVariable int itemID ) {
		Optional<Item> itemOption = itemRepository.findById(itemID);
		if(!itemOption.isPresent() || itemOption.get().getUser().getId() != userID)
			throw new NotFoundApi("Didn't find item");
		itemRepository.delete(itemOption.get());
	}
}
