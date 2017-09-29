package com.zy.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.crud.bean.Employee;
import com.zy.crud.bean.Msg;
import com.zy.crud.service.EmployeeService;

/**
 * 
 * @author ��ӱ
 * @date 2017��9��26�� ����8:24:32
 * @Decription ����Ա����CRUD����
 */
@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * ����id��ɾ������Ա����Ϣ
	 * ����ɾ��+����ɾ��  
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg deleteEmpById(@PathVariable("ids")String ids){
		//����ɾ��
		if (ids.contains("-")) {
			List<Integer> list_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			for (String string : str_ids) {
				list_ids.add(Integer.parseInt(string));
			}
			employeeService.deletEmpBatch(list_ids);
		}else {//����ɾ��
			employeeService.deleteEmpById(Integer.parseInt(ids));
		}
		return Msg.success();
	}
	
	/**
	 * ���ֱ�ӷ���ajax=PUT��ʽ������
	 * ��װ������ֻ��empId
	 * ��Ҫ����HttpPutFormContentFilter  ������
	 * 
	 * ����Ա��
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	public Msg updateEmp(Employee employee){
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	
	/**
	 * ����id��ѯԱ��
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id){
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	
	/**
	 * ����û����Ƿ����
	 * @param empName
	 * @return Msg.code=====100����
	 * 		   Msg.code=====200������
	 */
	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkUser(@RequestParam(value="empName")String empName){
		boolean b = employeeService.checkUser(empName);
		if (b) {
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
	
	
	/**
	 * Ա������
	 * 1.֧��JSR303У��
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result){
		if(result.hasErrors()){
			//У��ʧ�ܷ���ʧ��
			Map<String, Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}
		
		
	}
	
	
	/**
	 * ��Ҫ����Json��======Jackson Databind
	 * Ա����ѯ
	 * @param pageNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/emps")
	public Msg getEmpsWithJson(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum){
		//����PageHelper��ҳ���
		//�ڲ�ѯ֮ǰ���÷�ҳ���
		PageHelper.startPage(pageNum, 5);
		//startPage֮������Ų�ѯ
		List<Employee> employees = employeeService.getAll();
		//ʹ��PageInfo��װ��ѯ��Ľ����ֻ��Ҫ��pageInfo����
		PageInfo pageInfo = new PageInfo<>(employees,5);
		
		return Msg.success().add("pageInfo", pageInfo);
	}
	
	
	/**
	 * ��ѯԱ�����ݣ���ҳ��ѯ��
	 * @return
	 */
//	@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
			Model model){
		//����PageHelper��ҳ���
		//�ڲ�ѯ֮ǰֻ��Ҫ����
		PageHelper.startPage(pageNum, 5);
		//startPage��������ŵ������ѯ����һ����ҳ��ѯ
		List<Employee> employees = employeeService.getAll();
		//ʹ��PageInfo��װ��ѯ��Ľ����ֻ��Ҫ��pageInfo����ҳ��
		//��װ����ϸ�ķ�ҳ��Ϣ���������ǲ�ѯ����������,���Դ���������ʾ��ҳ��
		PageInfo pageInfo = new PageInfo(employees,5);
		model.addAttribute("pageInfo",pageInfo);
		return "list";
	}
}
