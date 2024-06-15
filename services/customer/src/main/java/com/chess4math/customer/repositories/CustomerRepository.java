package com.chess4math.customer.repositories;

import com.chess4math.customer.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>, PagingAndSortingRepository<Customer, String> {

    @Override
    Page<Customer> findAll(Pageable pageable);
}
