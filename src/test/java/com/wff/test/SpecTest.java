package com.wff.test;

import com.wff.dao.CustomerDao2;
import com.wff.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class SpecTest  {

    @Autowired
    private CustomerDao2 customerDao2;

    @Test
    public void testSpec(){
        /**
         * 自定义查询条件
         *      1.实现Specification接口（提供泛型：查询的对象类型）
         *      2.实现toPredicate方法（构造查询条件）
         *      3.需要借助方法参数中的两个参数（
         *          root：获取需要查询的对象属性
         *          CriteriaBuilder：构造查询条件的，内部封装了很多的查询条件（模糊匹配，精准匹配）
         *       ）
         *  案例：根据客户名称查询，查询客户名为传智播客的客户
         *          查询条件
         *              1.查询方式
         *                  cb对象
         *              2.比较的属性名称
         *                  root对象
         *
         */
        Specification<Customer> spec=new Specification<Customer>() {
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName=root.get("custName");
                 Predicate predicate= criteriaBuilder.equal(custName,"传智播客");
                return predicate;
            }
        };

        Customer customer=customerDao2.findOne(spec);
        System.out.println(customer);
    }
    @Test
    public void testSpec2(){
        /**
         *  root:获取属性
         *      客户名
         *      所属行业
         *  cb：构造查询
         *      1.构造客户名的精准匹配查询
         *      2.构造所属行业的精准匹配查询
         *      3.将以上两个查询联系起来
         */
        Specification<Customer> spec=new Specification<Customer>() {
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName=root.get("custName");
                Path<Object> custIndustry=root.get("custIndustry");

                Predicate p1= criteriaBuilder.equal(custName,"传智播客");
                Predicate p2= criteriaBuilder.equal(custIndustry,"IT");
                //3.将多个查询条件组合到一起：组合（满足条件一并且满足条件二：与关系，满足条件一或满足条件二即可：或关系）
                Predicate and=criteriaBuilder.and(p1,p2);
                return and;
            }
        };

        Customer customer=customerDao2.findOne(spec);
        System.out.println(customer);
    }
    @Test
    public void testSpec3() {
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = criteriaBuilder.like(custName.as(String.class), "传智播客%");
                return predicate;
            }
        };
        //  List<Customer> list =customerDao2.findAll(spec);
        //for(Customer customer:list){
        // System.out.println(customer);
        //}
        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> list = customerDao2.findAll(spec, sort);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
    @Test
    public void testSpec4(){
        Specification specification=null;
        Pageable pageable=new PageRequest(0,2);
        Page<Customer> page=customerDao2.findAll(null,pageable);
        System.out.println(page.getContent());//得到数据集合列表
        System.out.println(page.getTotalElements());//得到数据集合列表
        System.out.println(page.getTotalPages());//得到总页数

    }
}
