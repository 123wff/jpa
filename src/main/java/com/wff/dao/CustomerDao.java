package com.wff.dao;

import com.wff.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

    @Query(value = "from Customer where custName= ? ")
    public Customer findJpql(String custName);

    @Query(value = "from Customer where custName= ? and custId= ? ")
    public Customer findNameAndId(String custName,Long id);

    @Modifying
    @Query(value = "update Customer set custName=?2 where custId=?1 ")
    public void updateCustomer(Long custId,String custName);

    @Query(value = "select * from cst_customer",nativeQuery = true)
    public List<Object []> findSql();

    public Customer findByCustName(String name);
}
