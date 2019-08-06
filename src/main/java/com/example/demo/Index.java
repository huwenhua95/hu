package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.sql.*;

@RestController
public class Index {
    @RequestMapping("/insert")
    public String insert(String name, int age) {
        try {
            System.out.println("insert" + name + " ," + age);
            Class.forName("com.mysql.jdbc.Driver");
            String jdbc = "jdbc:mysql://127.0.0.1:3306/mao?characterEncoding=GBK";
            Connection conn = DriverManager.getConnection(jdbc, "root", "123456789");//链接到数据库

            Statement state = conn.createStatement();   //容器
            String sql = "insert into user (name,age) values('" + name + "'," + age + ")";
            System.out.println(sql);
            state.executeUpdate(sql);         //将sql语句上传至数据库执行
            conn.close();//关闭通道
        } catch (Exception e) {
            System.err.println(e);
            return "未添加成功";

        }
        System.out.println("执行了方法");
        return "1:李四:20 <br/> 2:王五:21";
    }

    @RequestMapping("/drop")
    public String drop(int id, String name) {
        try {
            System.out.println("drop start" + id + "," + name);
            Class.forName("com.mysql.jdbc.Driver");
            String jdbc = "jdbc:mysql://127.0.0.1:3306/mao?characterEncoding=GBK";
            Connection conn = DriverManager.getConnection(jdbc, "root", "123456789");//链接到数据库

            Statement state = conn.createStatement();   //容器
            String sql = "delete from user where id=" + id;
            System.out.println("删除了");

            state.executeUpdate(sql);         //将sql语句上传至数据库执行

            conn.close();//关闭通道

        } catch (Exception e) {
            System.err.println(e);
            return "不成功";
        }
        System.out.println("执行了方法");
        return "1:张三:20<br/> 2:王妩:25";

    }

    @RequestMapping("/change")
    public String change(String name, int age, int id) {
        try {
            System.out.println("change start" + name + age + id);
            Class.forName("com.mysql.jdbc.Driver");
            String jdbc = "jdbc:mysql://127.0.0.1:3306/mao?characterEncoding=GBK";
            Connection conn = DriverManager.getConnection(jdbc, "root", "123456789");//链接到数据库

            Statement state = conn.createStatement();   //容器
            String sql="update user set name='"+name+"',age="+age+" where id="+id;
            System.out.println(sql);
            System.out.println("修改了");

            state.executeUpdate(sql);         //将sql语句上传至数据库执行

            conn.close();//关闭通道
        } catch (Exception e) {
            System.err.println(e);
            return "不成功";
        }
        System.out.println("执行了方法");
        return "1:张三:20";
        }

            @RequestMapping("/select")
            public String select(String name,int age) {
               String a = "";
                try {
                    System.out.println("select start" );
                    Class.forName("com.mysql.jdbc.Driver");//加载驱动
                    String jdbc = "jdbc:mysql://127.0.0.1:3306/mao?characterEncoding=GBK";
                    Connection conn = DriverManager.getConnection(jdbc, "root", "123456789");//链接到数据库
                    Statement state = conn.createStatement();   //容器

                    String sql = "select * from user where name='" + name + "' and age=" + age;
                   if (isaBoolean(name) && age>0){
                        sql="select * from user where name='" + name + "' and age=" + age;
                    }
                    if (!isaBoolean(name) && age<=0){
                        sql="select * from user where name='" + name + "' and age=" + age;
                    }

                    System.out.println(sql);
                    System.out.println("查看了");
                    ResultSet rs = state.executeQuery(sql);         //将sql语句上传至数据库执行

                    while (rs.next()) {
                        int id1 = rs.getInt("id");
                        String name1 = rs.getString(2);// 根据列索引取得列的值,列索引从1开始
                        String age1 = rs.getString("age");
                        System.out.println(id1);
                        System.out.println(name1);
                        System.out.println(age1);
                        a += "id:" + id1 + ",name:" + name1 + ",age:" + age1 + ".<br/>";
                        //a+=name1+"<br/>";

                    }
                    rs.close();
                    state.close();
                    conn.close();//关闭通道
                } catch (Exception e) {
                    System.err.println(e);
                    return "查看不成功";
                }
                System.out.println("执行了方法");
                //return "1:zhangsan:12 <br/> 2:zhangsan2:11";
                return a;
            }
           private boolean isaBoolean(String name) {
                return "".equals(name)|| name ==null;
            }
        }


