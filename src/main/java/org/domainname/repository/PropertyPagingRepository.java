package org.domainname.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.domainname.entity.Property;

@Repository
public interface PropertyPagingRepository extends PagingAndSortingRepository<Property,Integer>{}