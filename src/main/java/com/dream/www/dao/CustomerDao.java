package com.dream.www.dao;

import com.dream.www.domain.Customer;

import java.util.List;

/**
 * User: changfangfang
 * Date: 2016/2/1
 * Email: fangfang.c@asou.com
 * 功能描述:
 */
public interface CustomerDao {
    public List<Customer> findAll();
    public Customer findById(String id);
    public void addCustomer(Customer customer);
    public void editCustomer(Customer customer);
    public void deleteCustomer(String id);
}
