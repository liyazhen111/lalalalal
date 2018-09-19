package com.qianfeng.relation_anno;

import java.util.List;

public class Department {

	private Integer did;
	private String dname;
	// 一个部门下有多个员工
	private List<Employee> emps;
	
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public List<Employee> getEmps() {
		return emps;
	}
	public void setEmps(List<Employee> emps) {
		this.emps = emps;
	}
	
	
	
}
