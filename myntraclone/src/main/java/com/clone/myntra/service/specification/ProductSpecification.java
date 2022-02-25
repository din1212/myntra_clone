package com.clone.myntra.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.clone.myntra.repository.entity.Product;
import com.clone.myntra.service.model.ProductSearchRequestModel;

public class ProductSpecification implements Specification<Product> {

	private final ProductSearchRequestModel requestModel;
	
	public ProductSpecification(ProductSearchRequestModel model) {
		this.requestModel = model;
	}


	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicates = new ArrayList<>();
		
		if(requestModel.isNotNull()) {
			predicates.add(criteriaBuilder.equal(root.get("rating"), requestModel.getRating()));
		}
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}


}
