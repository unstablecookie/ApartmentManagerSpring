package org.domainname.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.domainname.entity.Property;
import org.domainname.entity.User;

public interface PropertyService {
	
	List<Property> fetchProperty();
	Property getById(Long id);
	Property saveProperty(Property property);
	List<Property> listAll(String keyword);
	List<Property> fetchPropertyByType();
	List<Property> fetchPropertyByArea();
	List<Property> fetchPropertyByBuild();
}
