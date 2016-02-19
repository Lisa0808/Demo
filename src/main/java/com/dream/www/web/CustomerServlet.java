package com.dream.www.web;

import com.dream.www.domain.Customer;
import com.dream.www.service.CustomerService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * User: changfangfang
 * Date: 2016/2/1
 * Email: fangfang.c@asou.com
 * 功能描述:
 */
public class CustomerServlet extends HttpServlet {
    static CustomerService customerService = new CustomerService();

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String operate = request.getParameter("operate");
        if ("add".equalsIgnoreCase(operate)){
            addCustomer(request,response);
        }else if ("edit".equalsIgnoreCase(operate)){
            editCustomer(request,response);
        }else if ("editPage".equalsIgnoreCase(operate)){
            editPageCustomer(request, response);
        }else if ("delete".equalsIgnoreCase(operate)){
            deleteCustomer(request, response);
        }else if ("list".equalsIgnoreCase(operate)){
            listCustomer(request,response);
        }
    }

    public void listCustomer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<Customer> list = customerService.listCustomer();
        System.out.println(list);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/page/listc.jsp").forward(request,response);
    }

    public void deleteCustomer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String id = request.getParameter("id");
        customerService.deleteOne(id);
        response.sendRedirect("/customer?operate=list");
    }

    public void editPageCustomer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String id = request.getParameter("id");
        Customer customer = customerService.findOne(id);
        request.setAttribute("customer",customer);
        request.getRequestDispatcher("/page/editc.jsp").forward(request,response);
    }

    public void editCustomer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        Map map = request.getParameterMap();
        Customer customer = new Customer();
        try {
            BeanUtils.populate(customer,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] hobby = request.getParameterValues("hobby");
        if (hobby!=null){
            String hobbies = Arrays.toString(hobby);
            hobbies = hobbies.substring(1,hobbies.length()-1);
            customer.setHobby(hobbies);
        }
        //编码
//        String name = request.getParameter("name");
//        if (name!=null){
//            name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
//            customer.setName(name);
//        }
//        String desc = request.getParameter("description");
//        if (desc!=null){
//            desc = new String(desc.getBytes("ISO-8859-1"),"UTF-8");
//            customer.setDescription(desc);
//        }
        customerService.editOne(customer);
        response.sendRedirect("/customer?operate=list");
    }

    public void addCustomer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //使用令牌防刷新
        String sessionToken = (String) request.getSession().getAttribute("token");
        String requestToken = request.getParameter("token");
        System.out.println("sessionToken---"+sessionToken);
        System.out.println("requestToken---" + requestToken);
        //令牌使用一次就清空
        request.getSession().removeAttribute("token");
        if (sessionToken==null || !requestToken.equalsIgnoreCase(sessionToken)){
            response.getWriter().println("请不要刷新页面。。。");
            return ;
        }
        //接收参数
        Map map = request.getParameterMap();
        //封装参数
        Customer customer = new Customer();
        try {
            BeanUtils.populate(customer,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收数组
        String[] hobby = request.getParameterValues("hobby");
        if (hobby!=null){
            String hobbies = Arrays.toString(hobby);
            hobbies = hobbies.substring(1,hobbies.length()-1);
            customer.setHobby(hobbies);
        }
        //编码
//        String name = request.getParameter("name");
//        if (name!=null){
//            name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
//            customer.setName(name);
//        }
//        String desc = request.getParameter("description");
//        if (desc!=null){
//            desc = new String(desc.getBytes("ISO-8859-1"),"UTF-8");
//            customer.setDescription(desc);
//        }
        //保存
        customerService.addCustomer(customer);
        response.sendRedirect("/customer?operate=list");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);
    }
}
