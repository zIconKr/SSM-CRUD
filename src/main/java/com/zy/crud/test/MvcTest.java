package com.zy.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.zy.crud.bean.Employee;

/**
 * 
 * @author ��ӱ
 * @date 2017��9��26�� ����9:19:41
 * @Decription ʹ��Spring����ģ���ṩ�Ĳ��������ܣ�����curd����ȷ��
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" })
public class MvcTest {
	// ����Springmvc��ioc
	@Autowired
	WebApplicationContext context;
	// ����mvc����,��ȡ��������
	MockMvc mockMvc;

	@Before
	public void initMokMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

	}

	@Test
	public void testPage() throws Exception{
		//ģ�������õ�����ֵ
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pageNum", "1"))
				.andReturn();
		
		//����ɹ����������л���pageInfo:����ȡ��pageInfo������֤
		MockHttpServletRequest request = result.getRequest();
		PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
		System.out.println("��ǰҳ�룺"+pageInfo.getPageNum());
		System.out.println("��ҳ��"+pageInfo.getPages());
		System.out.println("size"+pageInfo.getPageSize());
		System.out.println("�ܼ�¼��"+pageInfo.getTotal());
		System.out.println("��ҳ����Ҫ������ʾ��ҳ��");
		int[] nums = pageInfo.getNavigatepageNums();
		for (int i : nums) {
			System.out.println(i);
		}
		//��ȡԱ������
		List<Employee> list = pageInfo.getList();
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}

}
