package com.wff.dao;

import com.wff.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerDao2 extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

}
