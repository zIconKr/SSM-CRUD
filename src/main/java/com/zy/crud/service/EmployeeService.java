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
	 * ��ѯ����Ա��
	 * @return
	 */
	public List<Employee> getAll(){
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	/**
	 * Ա������
	 */
	public void saveEmp(Employee employee){
		employeeMapper.insertSelective(employee);
	}
	
	/**
	 * �����û����Ƿ����
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
	 * ����Ա��id��ѯԱ��
	 */
	public Employee getEmp(Integer id){
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}
	
	/**
	 * Ա������
	 */
	public void updateEmp(Employee employee){
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	
	/**
	 * ����Idɾ������Ա��
	 * @param id
	 */
	public void deleteEmpById(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * ����ɾ��
	 */
	public void deletEmpBatch(List<Integer> ids){
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		//delete from tb1_emp where emp_id in (1,2,3,)
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
		
	}
}
