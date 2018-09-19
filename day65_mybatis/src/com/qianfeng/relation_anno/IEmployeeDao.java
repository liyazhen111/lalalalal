package com.qianfeng.relation_anno;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface IEmployeeDao {

	@Select("select * from t_emp where deptId=#{did}")
	public List<Employee> findByDeptId(int id);
	
	// one 表示一的关系，相当于association标签
	@Select("select * from t_emp where eid=#{id}")
	@Results(value={@Result(property="eid", column="eid", id=true),
			@Result(property="ename", column="ename"),
			@Result(property="dept", column="deptId", one=@One(select="com.qianfeng.relation_anno.IDepartmentDao.findById"))
	})
	public Employee findById(int id);
}
