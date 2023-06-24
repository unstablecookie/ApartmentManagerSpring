package org.domainname.service;

import org.domainname.repository.PropertyRepository;
import org.domainname.repository.UserPagingRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import org.domainname.entity.Property;
import org.domainname.entity.User;
import org.domainname.service.PropertyService;
import org.domainname.service.PropertyServiceImp;
import org.domainname.repository.PropertyPagingRepository;

@Service
public class PropertyServiceImp implements PropertyService{
	
	private PropertyRepository propertyRepository;
	private PropertyPagingRepository propertyPagingRepository;
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
	public void setUserRepository(PropertyRepository propertyRepository,PropertyPagingRepository propertyPagingRepository) {
		this.propertyRepository = propertyRepository;
		this.propertyPagingRepository = propertyPagingRepository;
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
	
	@Transactional 
	public List<Property> listAllPaged(Pageable pageable){
		Page<Property> page = (Page<Property>)propertyPagingRepository.findAll(pageable);
		return page.getContent();
	}
	
	@Transactional
	public void deleteSelected(List<Property> list) {
		propertyRepository.deleteInBatch(list);
	}
	
	@Transactional
	public void deleteProperty(Long id) {
		propertyRepository.delete(id);
	}
	
	@Transactional
	public void deleteProperty(Property property) {
		propertyRepository.delete(property);;
	}
}
