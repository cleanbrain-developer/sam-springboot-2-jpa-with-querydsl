package com.example;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomerQueryRepository {
	private final QCustomer qCustomer = new QCustomer("customer");
	private final JPAQueryFactory jpaQueryFactory;

	// 2023-11-18 clean_brain : java 1.8에서 사용하는 hibernate 5.6은 insert-select 만 지원함
	//  6.0부터 insert 문 사용 가능
	// @Transactional
	// public void create(Customer customer) {
	// 	this.jpaQueryFactory.insert(qCustomer)
	// 		.columns(qCustomer.firstName, qCustomer.lastName)
	// 		.values(customer.getFirstName(), customer.getLastName())
	// 		.execute();
	// }

	public List<Customer> getCustomerList() {
		return this.jpaQueryFactory.selectFrom(qCustomer)
			.fetch();
	}

	public Customer getCustomer(long id) {
		return this.jpaQueryFactory.selectFrom(qCustomer)
			.where(qCustomer.id.eq(id))
			.fetchOne();
	}

	public List<Customer> getCustomerListByLastName(String lastName) {
		return this.jpaQueryFactory.selectFrom(qCustomer)
			.where(qCustomer.lastName.likeIgnoreCase(lastName))
			.fetch();
	}
}