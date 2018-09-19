package com.qianfeng.anno;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface IPersonDao {
	// 在接口的方法中，通过注解，给出需要执行的sql语句
	@Select("select * from person")
	public List<Person> findAll();
	
//	@Select("select * from person where id=#{id}")
	@Select("select id,name as pname,age from person where id=#{id}")
	@ResultMap("personResult") //指定使用的映射关系
	public Person findById(int id);
	
	// 如果方法有多个参数，需要使用@Param注解，@Param中指定的key值要和sql中#{}中的key值一致
	@Select("select * from person where name=#{uname} and age=#{age}")
	public Person findByNameAndAge(@Param("uname") String name, @Param("age") int age);
	
	@Select("select id,name as pname,age from person where name=#{name}")
	// @Results 表示映射关系的结果集，如果字段名和类中属性名相同，可以不用写,id是3.4后的版本增加的
	// @Result 指定字段和属性的映射关系,id=true 表示对应的列是主键
	@Results(id="personResult", value={
			@Result(property="id", column="id", id=true),
			@Result(property="name", column="pname"),
			@Result(property="age", column="age")})
	public Person findByName(String name);
	
	@Insert("insert into person(name,age) values(#{name}, #{age})")
//	@SelectKey(statement="select last_insert_id()",  // 获取自增值的语句
//		keyProperty="id",  // 将获取的自增值绑定到哪个属性
//		before=false,     // 设置是否插入之前获取值
//		resultType=int.class // 返回值的类型
//			)// 获取自增的id值
	@Options(useGeneratedKeys=true, keyProperty="id") // 设置标签属性的方式，本例是针对mysql获取自增id的属性
	public void add(Person p);
	
	@Delete("delete from person where id=#{id}")
	public void deleteById(int id);
	
	@Update("update person set name=#{name},age=#{age} where id=#{id}")
	public void update(Person p);
}
