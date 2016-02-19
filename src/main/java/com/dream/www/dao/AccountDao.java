package com.dream.www.dao;

import com.dream.www.common.JDBCUtils;
import com.dream.www.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: changfangfang
 * Date: 2016/2/1
 * Email: fangfang.c@asou.com
 * 功能描述:
 */
public class AccountDao {

    public List<Account> findAll() throws SQLException {
        String sql = "select * from account";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        List<Account> list = (List<Account>) queryRunner.query(sql, new ResultSetHandler() {
            public Object handle(ResultSet rs) throws SQLException {
                List<Account> list = new ArrayList<Account>();
                while (rs.next()) {
                    Account account = new Account();
                    account.setId(rs.getInt("id"));
                    account.setName(rs.getString("name"));
                    account.setDesc(rs.getString("desc"));

                    list.add(account);
                }
                return list;
            }
        });
        return list;
    }

    public Account findOne(Integer id) throws SQLException {
        String sql = "select * from account where id = "+id;
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Account account = (Account) queryRunner.query(sql, new ResultSetHandler(){
            public Object handle(ResultSet rs) throws SQLException {
                Account account = new Account();
                if(rs.next()){
                    account.setId(rs.getInt("id"));
                    account.setName(rs.getString("name"));
                    account.setDesc(rs.getString("desc"));
                }
                return account;
        }});
        return account;
    }

    public void add(String name , String desc) throws SQLException {
        String sql = "insert into account values (null,?,?)";
        //创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        //写一个添加
        queryRunner.update(sql, name , desc);
    }

    public void delete(Integer id) throws SQLException {
        String sql = "delete from account where id = ?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql, id);
    }

    public void update(String desc , Integer id) throws SQLException {
        String sql = "UPDATE account SET `desc` = ? WHERE id = ?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql,desc,id);
    }
}
