package com.dream.www.web;

import com.dream.www.common.ThreadPoolUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * User: changfangfang
 * Date: 2016/1/22
 * Email: fangfang.c@asou.com
 * 功能描述:
 */
public class RequestDemo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        if ("clear".equalsIgnoreCase(type)){
            clearProduct(request, response);
        }else if("addc".equalsIgnoreCase(type)){
            productServlet(request, response);
        }else if("visit".equalsIgnoreCase(type)){
            visitServlet(request, response);
        }else if("redirect".equalsIgnoreCase(type)){
            demo4(request, response);
        }else if("forward".equalsIgnoreCase(type)){
            demo3(request, response);
        }else if("sign".equalsIgnoreCase(type)){
            demo2(request, response);
        }else if("buy".equalsIgnoreCase(type)){
            sessionBuy(request, response);
        }else if("sclear".equalsIgnoreCase(type)){
            sessionClear(request, response);
        }else if("upload".equalsIgnoreCase(type)){
            upload(request, response);
        }else if("td".equalsIgnoreCase(type)){
            threadDemo();
        }
    }

    public void threadDemo(){
        ThreadPoolUtils threadPoolUtils = new ThreadPoolUtils();
        for (int x=0;x<50;x++){
            final int finalX = x;
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println(finalX +"--线程--"+new Date().toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPoolUtils.addProcess(runnable);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void upload(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html;charset=utf8");

        //上传保存文件的目录地址
        String path_dir = "D:\\work3\\dream-160118\\src\\main\\webapp\\download";
        //创建工厂，指定缓存大小，以及临时目录
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory(1024*20,new File(path_dir));
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        try {
            List<FileItem> fileItemList = servletFileUpload.parseRequest(request);
            if (fileItemList.size()>0){
                for (FileItem fileItem:fileItemList){
                    if (fileItem.getFieldName().contains("upload") && fileItem.getName().length()>0){
                        String fileName = UUID.randomUUID().toString().replaceAll("-", "")+"_"+fileItem.getName().substring(fileItem.getName().lastIndexOf("\\")+1);
                        File file_dest = new File(path_dir+"/"+fileName);
                        fileItem.write(file_dest);
                        fileItem.delete();
                    }
                }
            }

            /*InputStream inputStream = fileItem.getInputStream();
            File file_dest = new File(path_dir+"/"+fileName);
            file_dest.createNewFile();
            FileOutputStream fos = new FileOutputStream(file_dest);
            int len=0;
            byte[] bytes = new byte[1024];
            while ((len=inputStream.read(bytes))!=-1){
                fos.write(bytes,0,len);
            }
            fos.close();*/
        } catch (Exception e) {
            if(e instanceof FileUploadBase.FileSizeLimitExceededException) {
                request.setAttribute("msg", "您上传的单个文件大小超出了限制！");
                request.getRequestDispatcher("/page/fileup.jsp").forward(request, response);
            }else if(e instanceof FileUploadBase.SizeLimitExceededException) {
                request.setAttribute("msg", "您上传的整个表单大小超出了限制！");
                request.getRequestDispatcher("/page/fileup.jsp").forward(request, response);
            }else{
                System.out.println(e.getMessage());
                request.setAttribute("msg", e.getMessage());
                request.getRequestDispatcher("/page/fileup.jsp").forward(request, response);
            }
        }
    }

    //清除session
    public void sessionClear(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String session_name = "cart";
        HttpSession session = request.getSession();
        session.setAttribute(session_name,null);
        session.invalidate();//销毁session
        //request.getRequestDispatcher("/").forward(request,response);
        response.sendRedirect("/");
    }

    public void sessionBuy(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String session_name = "cart";
        String id = request.getParameter("id");
        String[] names = {"电视机","手机","冰箱","空调","电脑"};
        String name = names[Integer.parseInt(id)];
        Map<String,Integer> map  = (Map<String, Integer>) request.getSession().getAttribute(session_name);
        if (map==null){
            map = new HashMap<String, Integer>();
            map.put(name,1);
        }else {
            Integer num = map.get(name);
            if (num==null){
                map.put(name,1);
            }else{
                map.put(name,++num);
            }
        }
        request.getSession().setAttribute(session_name,map);
        response.setContentType("text/html;charset=utf8");
        response.getWriter().write("<h3>"+map+"</h3>");
    }

    //session中添加参数
    public void sessionAdd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        HttpSession session = request.getSession();
        session.setAttribute("name","chang");
        String url = "/";
        response.encodeURL(url);
    }

    //清空浏览记录
    public void clearProduct(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String cookie_name = "history";
        Cookie cookie = new Cookie(cookie_name,"");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.getWriter().write(cookie.getValue());
    }

    //cookie添加id
    public void productServlet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/html;charset=utf8");
        String id = request.getParameter("id");
        if (id!=null && id.length()>0){
            Cookie[] cookies = request.getCookies();
            String cookies_name = "history";
            Cookie cookie = null;
            for (Cookie c:cookies){
                if (c.getName().equalsIgnoreCase(cookies_name)){
                    cookie = c;
                    break;
                }
            }
            if (cookie==null){
                cookie = new Cookie(cookies_name,id);
            }else{
                List<String> list_id = Arrays.asList(cookie.getValue().split(","));
                if (!list_id.contains(id)){
                    cookie = new Cookie(cookies_name,cookie.getValue()+","+id);
                }
            }
            response.addCookie(cookie);
            response.getWriter().write(cookie.getValue());
        }
    }

    //上次的访问时间
    public void visitServlet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/html;charset=utf8");
        String cookie_name = "last_name";
        Cookie[] cookies = request.getCookies();
        Cookie cookie_now = null;
        for (Cookie cookie:cookies){
            System.out.println(cookie.getName()+"--"+cookie.getValue());
            if (cookie.getName().equalsIgnoreCase(cookie_name)){
                cookie_now = cookie;
                break;
            }
        }
        if (cookie_now==null){
            response.getWriter().write("<h1>第一次登录</h1>");
        }else{
            long time = Long.parseLong(cookie_now.getValue());
            Date date = new Date(time);
            response.getWriter().write("<h1>上次登录时间:"+date.toString()+"</h1>");
        }
        Cookie c = new Cookie(cookie_name,System.currentTimeMillis()+"");
        response.addCookie(c);
    }

    //重定向
    //重定向两次请求 两次响应
    //重定向URL地址改变第二个资源地址
    //重定向可以定向到任何网站
    //重定向/来自客户端必须要写工程名
    public void demo4(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.sendRedirect("/response/refresh.jsp");
    }

    //转发
    //转发一次请求 一次响应
    //转发URL地址 不变
    //转发只能转发给同一个网站内部资源
    public void demo3(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.getRequestDispatcher("/response/refresh.jsp").forward(request,response);
    }

    //接受表单提交过来的数据
    public void demo2(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        Map<String,String[]> map = request.getParameterMap();
        for (String name:map.keySet()){
            System.out.println(name+"--"+map.get(name));
        }

        String username = new String(request.getParameter("username").getBytes("ISO-8859-1"),"UTF-8");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String[] hobby = request.getParameterValues("hobby");
        String[] roles =  request.getParameterValues("role");
        String city = request.getParameter("city");
        //String info = request.getParameter("info");
        String info = new String(request.getParameter("info").getBytes("ISO-8859-1"),"UTF-8");

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("您的用户名是："+username+"<br/>");
        response.getWriter().write("您的密码是："+password+"<br/>");
        response.getWriter().write("您的性别是："+sex+"<br/>");
        response.getWriter().write("您的爱好是："+Arrays.toString(hobby)+"<br/>");
        response.getWriter().write("您的角色是："+ Arrays.toString(roles)+"<br/>");
        response.getWriter().write("您的城市是："+city+"<br/>");
        response.getWriter().write("您的信息是："+info+"<br/>");
    }

    //指定返回状态
    public void demo1(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String ip = request.getRemoteHost();
        System.out.println("ip--" + ip);
        String method = request.getMethod();
        System.out.println("method--" + method);
        String queryString = request.getQueryString();
        System.out.println("queryString--" + queryString);
        String contextPath = request.getContextPath();
        System.out.println("contextPathontextPath--"+contextPath);
        response.sendRedirect(contextPath+"/index.jsp");
    }
}
