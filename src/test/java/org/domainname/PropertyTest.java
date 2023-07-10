package org.domainname;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.domainname.entity.Property;
import org.domainname.entity.AptType;
import org.domainname.entity.User;

import org.junit.Test;

public class PropertyTest {

	@Test
	public void testEmptyConstructorProperty() {
		try {
			Property property = new Property();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMainConstructor() {
		User user = new User();
		try {
			Property property = new Property(AptType.APARTMENT,20,1999,user);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPropertyValues() {
		User user = new User();
		Property property = new Property(AptType.APARTMENT,20,1999,user);
		assertEquals(AptType.APARTMENT,property.getType());
		assertEquals(20,property.getArea());
		assertEquals(1999,property.getBuild());
		assertNotNull(property.getUser());
	}
	
	@Test
	public void testSetAndGetPropertyValues() {
		User user = new User();
		Property property = new Property();
		property.setType(AptType.HOUSE);
		property.setBuild(2000);
		property.setPhoto(new byte[] {});
		property.setUser(user);
		assertEquals(AptType.HOUSE,property.getType());
		assertEquals(2000,property.getBuild());
		assertArrayEquals(new byte[] {},property.getPhoto());
		assertEquals(user,property.getUser());
	}
}
