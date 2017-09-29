package com.zy.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zy.crud.bean.Department;
import com.zy.crud.bean.Employee;
import com.zy.crud.dao.DepartmentMapper;
import com.zy.crud.dao.EmployeeMapper;

/**
 * 
 * @author 周颖
 * @date 2017年9月26日 下午5:31:47
 * @Decription 测试dao层的工作
 * 				推荐Spring的项目使用Spring的单元测试，可以自动注入我们需要的组件
 * 				1.导入SpringTest模块
 * 				2.@ContextConfiguration指定Spring配置文件的位置
 * 				3.直接Autowired即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	/**
	 * 测试DepartmentMapper
	 */
	@Test
	public void testCRUD(){
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		DepartmentMapper departmentMapper = applicationContext.getBean(DepartmentMapper.class);
		System.out.println(departmentMapper);	
		
		//1.插入几个部门
//		departmentMapper.insertSelective(new Department(null, "开发部"));
//		departmentMapper.insertSelective(new Department(null, "测试部"));
	
		//2.生成员工数据，测试员工插入
//		employeeMapper.insertSelective(new Employee(null, "Jack", "M","Jack@163.com", 1));
//		employeeMapper.insertSelective(new Employee(null, "Jim", "M","Jim@163.com", 1));
//		employeeMapper.insertSelective(new Employee(null, "Mike", "M","Mike@163.com", 1));
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		//3.批量插入员工：使用可以执行批量操作的sqlSession
//		for(int i = 0; i< 1000; i++){
//			String sex = (i%2==0)?"G":"M";
//			String uid = UUID.randomUUID().toString().substring(0, 5)+i;
//			Integer dId =(i%2==0)?1:2;
//			mapper.insertSelective(new Employee(null, uid, sex, uid+"@163.com", dId));
//		}
		//4.测试查询员工时，出现部门信息
		System.out.println(employeeMapper.selectByPrimaryKeyWithDept(1));
	}
	
}
