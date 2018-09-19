package com.qianfeng.anno;

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
//		demo.findPersonByid();
//		demo.findPersonByNameandAge();
		demo.addPerson();
	}
	
	public void findPersonByid(){
		SqlSession session = sessionFactory.openSession();
		IPersonDao mapper = session.getMapper(IPersonDao.class);
		
		Person person = mapper.findById(4);
		System.out.println(person.getName());
		
		session.close();
		
		
		
	}
	
	public void findPersonByNameandAge(){
		SqlSession session = sessionFactory.openSession();
		IPersonDao mapper = session.getMapper(IPersonDao.class);
		
//		Person person = mapper.findByNameAndAge("zhangsan", 20);
		Person person = mapper.findByName("zhangsan");
		System.out.println(person.getName());
		
		session.close();
		
		
		
	}
	
	public void addPerson(){
		SqlSession session = sessionFactory.openSession();
		IPersonDao mapper = session.getMapper(IPersonDao.class);
		
		Person p = new Person();
		p.setName("Tom");
		p.setAge(40);
		mapper.add(p);
		System.out.println(p.getId());
		session.commit();
		session.close();
	}
	

}
