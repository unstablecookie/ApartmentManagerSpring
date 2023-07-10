package org.domainname;

import static org.junit.Assert.assertEquals;

import org.domainname.entity.User;

import org.junit.Test;

public class UserTest {
	
	@Test
	public void testEmptyConstructorUser() {
		try {
			User user = new User();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMainConstructor() {
		try {
			User user = new User("fname","lname","uname");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUserValues() {
		User user = new User("fname","lname","uname");
		assertEquals("fname",user.getFirstName());
		assertEquals("lname",user.getLastName());
		assertEquals("uname",user.getUserName());

	}
	
	@Test
	public void testSetAndGetUserValues() {
		User user = new User();
		user.setId(1l);
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setUserName("uname");
		assertEquals(1l,user.getId());
		assertEquals("fname",user.getFirstName());
		assertEquals("lname",user.getLastName());
		assertEquals("uname",user.getUserName());
		
	}
}
