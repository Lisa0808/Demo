package com.dream.www.web;

import com.dream.www.common.ThreadPoolUtils;
import com.dream.www.dao.AccountDao;
import org.junit.Test;

import java.sql.SQLException;

/**
 * User: changfangfang
 * Date: 2016/1/22
 * Email: fangfang.c@asou.com
 * 功能描述:
 */
public class Demo {
    static AccountDao accountDao = new AccountDao();

    @Test
    public void demo2(){
        ThreadPoolUtils threadPoolUtils = new ThreadPoolUtils();
        for (int x=0;x<50;x++){
            final int finalX = x;
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(5000);
                        System.out.println(finalX +"--线程--");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPoolUtils.addProcess(runnable);
        }
    }

    @Test
    public void demo1() throws SQLException {
//        accountDao.add("chang0808","java0808");
//        accountDao.delete(1);
        accountDao.update("hao111",2);
    }

}
