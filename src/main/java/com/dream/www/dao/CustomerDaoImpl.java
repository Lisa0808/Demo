package com.dream.www.dao;

import com.dream.www.common.JDBCUtils;
import com.dream.www.domain.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;
import java.util.UUID;

/**
 * User: changfangfang
 * Date: 2016/2/1
 * Email: fangfang.c@asou.com
 * 功能描述:
 */
public class CustomerDaoImpl implements CustomerDao {

    public List<Customer> findAll() {
        try{
            String sql = "select * from customer";
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            List<Customer> list = queryRunner.query(sql, new BeanListHandler<Customer>(Customer.class));
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Customer findById(String id){
        try{
            String sql = "select * from customer where id=?";
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            Customer customer = queryRunner.query(sql, new BeanHandler<Customer>(Customer.class), id);
            return customer;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void addCustomer(Customer customer){
        try{
            String sql = "insert into customer values (?,?,?,?,?,?,?,?)";
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            String id = UUID.randomUUID().toString().replaceAll("-","");
            Object[] obj = new Object[] { id, customer.getName(),
                    customer.getGender(), customer.getTel(), customer.getEmail(),
                    customer.getHobby(),customer.getType(), customer.getDescription() };
            queryRunner.update(sql, obj);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editCustomer(Customer customer){
        try{
            String sql = "update customer set name=?,gender=?,tel=?,email=?,hobby=?,type=?,description=? where id=?";
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            Object[] obj = new Object[] { customer.getName(),customer.getGender(),customer.getTel(),
                    customer.getEmail(), customer.getHobby(),customer.getType(), customer.getDescription() ,customer.getId()};
            queryRunner.update(sql, obj);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteCustomer(String id){
        try{
            String sql = "delete from customer where id = ?";
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            queryRunner.update(sql, id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
