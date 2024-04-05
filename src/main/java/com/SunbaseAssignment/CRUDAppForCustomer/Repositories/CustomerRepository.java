package com.SunbaseAssignment.CRUDAppForCustomer.Repositories;

import com.SunbaseAssignment.CRUDAppForCustomer.Entities.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Integer> {
  Page<Customer> findByFirstNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String key1, String key2, Pageable pageable);
}
