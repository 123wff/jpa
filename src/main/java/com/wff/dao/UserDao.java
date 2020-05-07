package com.wff.dao;


import com.wff.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,Long> ,JpaSpecificationExecutor<User> {
}
