package com.zy.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.crud.bean.Employee;
import com.zy.crud.bean.EmployeeExample;
import com.zy.crud.bean.EmployeeExample.Criteria;
import com.zy.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getAll(){
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	/**
	 * 员工保存
	 */
	public void saveEmp(Employee employee){
		employeeMapper.insertSelective(employee);
	}
	
	/**
	 * 检验用户名是否可用
	 * @param empName
	 * @return
	 */
	public boolean checkUser(String empName) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		if(count==0){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 按照员工id查询员工
	 */
	public Employee getEmp(Integer id){
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}
	
	/**
	 * 员工更新
	 */
	public void updateEmp(Employee employee){
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	
	/**
	 * 根据Id删除单个员工
	 * @param id
	 */
	public void deleteEmpById(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 批量删除
	 */
	public void deletEmpBatch(List<Integer> ids){
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		//delete from tb1_emp where emp_id in (1,2,3,)
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
		
	}
}
