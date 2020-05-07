package com.wff.test;

import com.wff.dao.CustomerDao;
import com.wff.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据id查询
     */
    @Test
    public void testFindOne(){
       Customer customer= customerDao.findOne(3l);
        System.out.println(customer);
    }
    @Test
    public void testSave(){
        Customer customer=new Customer();
        customer.setCustName("黑马");
        customer.setCustIndustry("IT");
        customer.setCustLevel("vip");
        customerDao.save(customer);
    }
    @Test
    public void testUpdate(){
        Customer customer=new Customer();
        customer.setCustId(4L);
        customer.setCustName("黑马程序员");
        customerDao.save(customer);
    }
    @Test
    public void testDelete(){
        customerDao.delete(3l);
    }
    @Test
    public void testFindAll(){
        List<Customer> customers= customerDao.findAll();
        for(Customer customer:customers){
            System.out.println(customer);
        }
    }
    @Test
    public void testCount(){
       long count=customerDao.count();
       System.out.println(count);
    }
    @Test
    @Transactional
    public void testGetOne(){
       Customer customer=customerDao.getOne(4L);
        System.out.println(customer);
    }
    @Test
    public void testJpql(){
        Customer customer=customerDao.findJpql("传智播客");
        System.out.println(customer);
    }
    @Test
    public void testNameAndId(){
        Customer customer=customerDao.findNameAndId("传智播客",4l);
        System.out.println(customer);
    }

    @Transactional
    @Test
    @Rollback(value = false)
    public void testUpdateJpql(){
       customerDao.updateCustomer(4L,"尚硅谷");
    }
    @Test
    public void testFindAllSql(){
        List<Object[]> customers= customerDao.findSql();
        for(Object[] obj:customers){
            System.out.println(Arrays.toString(obj));
        }
    }
    @Test
    public void testNaming(){
        Customer customer=customerDao.findByCustName("传智播客");
        System.out.println(customer);
    }
}
