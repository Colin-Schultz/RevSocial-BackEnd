package tests;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.network.controller.PostController;
import com.network.controller.UserController;
import com.network.model.User;
/*
 * JUNIT
 * 
 * 		Unit testing is meant to test your code as independently as possible.
 * 		
 * 		There are other independent units of code that require other units,
 * 			they can cause the entire group to fail. So you want to test
 * 			them as independently as possible.
 * 
 * 		TDD
 * 			Test Driven Development is the ART of writing code to follow
 * 				a testing procedure.
 * 
 * 			phases
 * 				red, green, refactor
 * 
 * MOCKITO
 * 	
 * 		Used for mocking dependencies that our classes have on another.
 */
public class Tests {

	
	
	
	
	@Test
	public void testHash() {
		//System.out.println(UtilityFunctions.encryptPassword("potato"));
		assertEquals("Testing for Encryption Working", "8ee2027983915ec78acc45027d874316", UserController.encryptPassword("potato"));
	}
	@Test
	public void testHashFail() {
		assertNotEquals("8ee2027983915ec78acc45027d874316", UserController.encryptPassword("banana"));
	}
	
	@Test
	public void testSetNameTrue() {
		User user = new User();
		user.setFirstName("apple");
		assertEquals("apple",user.getUsername());
	}
	
	@Test
	public void testFailUserName() {
		User user = new User();
		user.setFirstName("apple");
		assertEquals("orange",user.getUsername());
		
	}
	
	
	
	
	
}
