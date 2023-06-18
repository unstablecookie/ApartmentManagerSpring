package org.domainname.service;

import org.domainname.repository.PropertyRepository;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import org.domainname.entity.Property;
import org.domainname.entity.User;
import org.domainname.service.PropertyService;
import org.domainname.service.PropertyServiceImp;

@Service
public class PropertyServiceImp implements PropertyService{
	
	private PropertyRepository propertyRepository;
	private Sort sortByType(){
		return new Sort(Sort.Direction.ASC, "type");
	}
	private Sort sortByArea(){
		return new Sort(Sort.Direction.ASC, "area");
	}
	private Sort sortByBuild(){
		return new Sort(Sort.Direction.ASC, "build");
	}
	
	@Autowired
	public void setUserRepository(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}
	
	@Transactional
	public List<Property> fetchProperty(){
		return (List<Property>)propertyRepository.findAll();
	}
	
	@Transactional
	public Property getById(Long id) {
		return (Property)propertyRepository.findOne(id);
	}
	
	@Transactional
	public Property saveProperty(Property property) {
		return (Property)propertyRepository.save(property);
	}
	
	@Transactional
	public List<Property> listAll(String keyword){
		if(keyword != null) {
			return (List<Property>)propertyRepository.search(keyword);
		}
		return  (List<Property>)propertyRepository.findAll();
	}
	
	@Transactional
	public List<Property> fetchPropertyByType(){
		return (List<Property>)propertyRepository.findAll(sortByType());
	}
	
	@Transactional
	public List<Property> fetchPropertyByArea(){
		return (List<Property>)propertyRepository.findAll(sortByArea());
	}
	
	@Transactional
	public List<Property> fetchPropertyByBuild(){
		return (List<Property>)propertyRepository.findAll(sortByBuild());
	}
}
