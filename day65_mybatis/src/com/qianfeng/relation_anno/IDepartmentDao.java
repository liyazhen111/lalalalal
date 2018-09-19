package com.qianfeng.relation_anno;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

public interface IDepartmentDao {
	// 嵌套查询，不支持嵌套结果的写法
	// many 表示多的关系，相当于collection
	// @Many 中指定要执行的另外的sql语句
	//		fetchType 设置是否懒加载
	//		select 嵌套查询时，执行的另外的sql
	@Select("select * from t_dept where did=#{id}")
	@Results(value={@Result(property="did", column="did", id=true),
			@Result(property="dname", column="dname"),
			@Result(property="emps", column="did", many=@Many(fetchType=FetchType.EAGER, select="com.qianfeng.relation_anno.IEmployeeDao.findByDeptId"))
	})
	public Department findById(int id);
	
	
	
	
}
