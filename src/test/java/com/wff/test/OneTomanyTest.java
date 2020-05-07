package com.wff.test;

import com.wff.dao.CustomerDao2;
import com.wff.dao.LinkManDao;
import com.wff.domain.Customer;
import com.wff.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class OneTomanyTest {
    @Autowired
    private CustomerDao2 customerDao2;
    @Autowired
    private LinkManDao linkManDao;
    @Test
    @Rollback(value = false)
    @Transactional
    public void testAdd(){

        Customer customer=new Customer();
        customer.setCustName("百度");

        LinkMan linkMan=new LinkMan();
        linkMan.setLkmName("小李");

        customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);

        customerDao2.save(customer);
        linkManDao.save(linkMan);

    }

    @Test
    @Rollback(value = false)
    @Transactional
    public void testCascadeAdd(){

        Customer customer=new Customer();
        customer.setCustName("百度1");

        LinkMan linkMan=new LinkMan();
        linkMan.setLkmName("小李1");

        customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);

        customerDao2.save(customer);
       // linkManDao.save(linkMan);

    }
    @Test
    @Rollback(value = false)
    @Transactional
    public void testCascadeDelete(){

      Customer customer=customerDao2.findOne(1l);

    customerDao2.delete(customer);
    }
}
