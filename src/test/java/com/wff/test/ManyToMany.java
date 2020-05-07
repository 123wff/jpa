package com.wff.test;

import com.wff.dao.RoleDao;
import com.wff.dao.UserDao;
import com.wff.domain.Role;
import com.wff.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class ManyToMany {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testSave(){
        User user =new User();
        user.setUserName("小李");
        Role role =new Role();
        role.setRoleName("java程序员");
        userDao.save(user);
        user.getRoles().add(role);
        roleDao.save(role);
    }
}
