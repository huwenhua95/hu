package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
@RequestMapping("/user")
	public String user(String name,int age) {
		List list = new ArrayList<>();
		PreparedStatement psta = null;
		//ResultSet rs = null;
		try {
			System.out.println("select start");
			Class.forName("com.mysql.jdbc.Driver");//加载驱动
			String jdbc = "jdbc:mysql://127.0.0.1:3306/mao?characterEncoding=GBK";
			Connection conn = DriverManager.getConnection(jdbc, "root", "123456789");//链接到数据库
			Statement state = conn.createStatement();   //容器
			String sql = "select * from user where name='" + name + "' and age=" + age;
			System.out.println(sql);
			System.out.println("查看了");
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {

			list.add( rs.getInt("id"));
				String name1 = rs.getString(2);// 根据列索引取得列的值,列索引从1开始
				String age1 = rs.getString("age");
				//System.out.println(id1);
				System.out.println(name1);
				System.out.println(age1);
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
		return null;
		}
	}