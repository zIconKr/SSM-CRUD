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
 * @author ��ӱ
 * @date 2017��9��26�� ����5:31:47
 * @Decription ����dao��Ĺ���
 * 				�Ƽ�Spring����Ŀʹ��Spring�ĵ�Ԫ���ԣ������Զ�ע��������Ҫ�����
 * 				1.����SpringTestģ��
 * 				2.@ContextConfigurationָ��Spring�����ļ���λ��
 * 				3.ֱ��Autowired����
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
	 * ����DepartmentMapper
	 */
	@Test
	public void testCRUD(){
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		DepartmentMapper departmentMapper = applicationContext.getBean(DepartmentMapper.class);
		System.out.println(departmentMapper);	
		
		//1.���뼸������
//		departmentMapper.insertSelective(new Department(null, "������"));
//		departmentMapper.insertSelective(new Department(null, "���Բ�"));
	
		//2.����Ա�����ݣ�����Ա������
//		employeeMapper.insertSelective(new Employee(null, "Jack", "M","Jack@163.com", 1));
//		employeeMapper.insertSelective(new Employee(null, "Jim", "M","Jim@163.com", 1));
//		employeeMapper.insertSelective(new Employee(null, "Mike", "M","Mike@163.com", 1));
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		//3.��������Ա����ʹ�ÿ���ִ������������sqlSession
//		for(int i = 0; i< 1000; i++){
//			String sex = (i%2==0)?"G":"M";
//			String uid = UUID.randomUUID().toString().substring(0, 5)+i;
//			Integer dId =(i%2==0)?1:2;
//			mapper.insertSelective(new Employee(null, uid, sex, uid+"@163.com", dId));
//		}
		//4.���Բ�ѯԱ��ʱ�����ֲ�����Ϣ
		System.out.println(employeeMapper.selectByPrimaryKeyWithDept(1));
	}
	
}
