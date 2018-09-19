package com.qianfeng.relation_anno;

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
//		demo.findDeptById();
		demo.findEmpById();
		
	}
	
	public void findDeptById(){
		SqlSession session = sessionFactory.openSession();
		IDepartmentDao deptDao = session.getMapper(IDepartmentDao.class);
		
		Department dept = deptDao.findById(1);
		System.out.println(dept.getDname());
		System.out.println(dept.getEmps().size());
		System.out.println(dept.getEmps().get(0).getEname());
		
		session.close();
		
		
		
	}
	
	public void findEmpById(){
		SqlSession session = sessionFactory.openSession();
		IEmployeeDao mapper = session.getMapper(IEmployeeDao.class);
		Employee employee = mapper.findById(1);
		System.out.println(employee.getEname());
		System.out.println(employee.getDept().getDname());
		
		session.close();
		
		
		
	}
	
	
}
