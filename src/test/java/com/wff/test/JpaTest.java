package com.wff.test;

import com.wff.domain.Customer;
import com.wff.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

public class JpaTest {

    @Test
    public void testSave(){
       // 1.加载配置文件创建工厂（实体管理器工厂）对象
        //EntityManagerFactory factory= Persistence.createEntityManagerFactory("myjpa");
        //2.通过实体管理器工厂获取实体管理器
       // EntityManager em=factory.createEntityManager();
        EntityManager em= JpaUtils.getEntityManager();
        EntityTransaction tx=em.getTransaction();
        //3.获取事务对象，开启事务
        tx.begin();
        Customer customer=new Customer();
        customer.setCustIndustry("教育");
        customer.setCustName("传智播客2");
       // 4.完成增删改查操作
        em.persist(customer);
        //5.提交事务（回滚事务）
        tx.commit();
       // 6.释放资源
        em.close();
    }
    @Test
    public void testFind(){
        EntityManager em=JpaUtils.getEntityManager();
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        Customer customer=em.find(Customer.class,1L);
        System.out.println(customer);
        tx.commit();
        em.close();
    }
    @Test
    public void testFind2(){
        EntityManager em=JpaUtils.getEntityManager();
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        Customer customer=em.getReference(Customer.class,1L);
        System.out.println(customer);
        tx.commit();
        em.close();
    }
    @Test
    public void testDelete(){
        EntityManager em=JpaUtils.getEntityManager();
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        //删除对象
        Customer customer=em.getReference(Customer.class,1L);
        em.remove(customer);
        System.out.println(customer);
        tx.commit();
        em.close();
    }
    @Test
    public void testUpdate(){
        EntityManager em=JpaUtils.getEntityManager();
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        //更新对象对象
        Customer customer=em.getReference(Customer.class,2l);
        customer.setCustIndustry("IT");
        em.merge(customer);
        System.out.println(customer);
        tx.commit();
        em.close();
    }
    @Test
    public void testFindAll(){
        EntityManager em=JpaUtils.getEntityManager();
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        //查询全部
       String jpql="from com.wff.domain.Customer";
        //创建查询对象
        Query query=em.createQuery(jpql);
        //封装结果集
        List list=query.getResultList();
        for(Object object:list){
            System.out.println(object);
        }
        tx.commit();
        em.close();
    }
    @Test
    public void testOrders(){
        EntityManager em=JpaUtils.getEntityManager();
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        //查询全部
        String jpql="from Customer order by custId desc";
        //创建查询对象
        Query query=em.createQuery(jpql);
        //封装结果集
        List list=query.getResultList();
        for(Object object:list){
            System.out.println(object);
        }
        tx.commit();
        em.close();
    }
    @Test
    public void testCount(){
        EntityManager em=JpaUtils.getEntityManager();
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        //查询全部
        String jpql="select count(custId) from  Customer";
        //创建查询对象
        Query query=em.createQuery(jpql);
       Object result=query.getSingleResult();
       System.out.println(result);
        tx.commit();
        em.close();
    }
    @Test
    public void testPaged(){
        EntityManager em=JpaUtils.getEntityManager();
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        //查询全部
        String jpql=" from  Customer";
        //创建查询对象
        Query query=em.createQuery(jpql);
        //对参数赋值
        query.setFirstResult(0);
        query.setMaxResults(2);
        List list=query.getResultList();
        for(Object object:list){
            System.out.println(object);
        }
        tx.commit();
        em.close();
    }
    @Test
    public void testCondition(){
        EntityManager em=JpaUtils.getEntityManager();
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        //查询全部
        String jpql=" from  Customer where custName like ?";
        //创建查询对象
        Query query=em.createQuery(jpql);
        //对参数赋值
        query.setParameter(1,"传智播客%");
        List list=query.getResultList();
        for(Object object:list){
            System.out.println(object);
        }
        tx.commit();
        em.close();
    }
}
