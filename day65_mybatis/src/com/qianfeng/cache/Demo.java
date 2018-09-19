package com.qianfeng.cache;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Demo {
	private static SqlSessionFactory sessionFactory;
	static{
		// 读取配置文件，获取Reader对象
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mybatis.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获取SqlSessionFactory对象
		sessionFactory = new SqlSessionFactoryBuilder().build(reader);
					
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo demo = new Demo();
		demo.findPersonByid();
	}
	
	public void findPersonByid(){
		SqlSession session = sessionFactory.openSession();
		IPersonDao mapper = session.getMapper(IPersonDao.class);
		
		Person person = mapper.findById(4);
		System.out.println(person.getName());
		//一级缓存，获取数据时，先从缓存中找，如果有直接返回，如果没有，再从数据库中查询，并将查询的数据放入缓存中
		Person p2 = mapper.findById(4);
		System.out.println(p2.getName());
		
		session.close();
		
		SqlSession session2 = sessionFactory.openSession();
		IPersonDao mapper2 = session2.getMapper(IPersonDao.class);
		Person p3 = mapper2.findById(4);
		System.out.println(p3.getName());
		session2.close();
		
	}
	

}
