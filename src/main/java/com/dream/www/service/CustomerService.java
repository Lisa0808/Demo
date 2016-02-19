package com.dream.www.service;

import com.dream.www.dao.CustomerDao;
import com.dream.www.dao.CustomerDaoImpl;
import com.dream.www.domain.Customer;

import java.util.List;

/**
 * User: changfangfang
 * Date: 2016/2/1
 * Email: fangfang.c@asou.com
 * 功能描述:
 */
public class CustomerService {

    static CustomerDao customerDao = new CustomerDaoImpl();

    public void addCustomer(Customer customer) {
        customerDao.addCustomer(customer);
    }

    public List<Customer> listCustomer(){
        List<Customer> list = customerDao.findAll();
        return list;
    }

    public Customer findOne(String id){
        Customer customer = customerDao.findById(id);
        return customer;
    }

    public void editOne(Customer customer){
        customerDao.editCustomer(customer);
    }

    public void deleteOne(String id){
        customerDao.deleteCustomer(id);
    }
}
