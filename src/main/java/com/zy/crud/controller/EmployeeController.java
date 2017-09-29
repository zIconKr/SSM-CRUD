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
 * @author 周颖
 * @date 2017年9月26日 下午8:24:32
 * @Decription 处理员工的CRUD请求
 */
@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * 根据id来删除单个员工信息
	 * 批量删除+单个删除  
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg deleteEmpById(@PathVariable("ids")String ids){
		//批量删除
		if (ids.contains("-")) {
			List<Integer> list_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			for (String string : str_ids) {
				list_ids.add(Integer.parseInt(string));
			}
			employeeService.deletEmpBatch(list_ids);
		}else {//单个删除
			employeeService.deleteEmpById(Integer.parseInt(ids));
		}
		return Msg.success();
	}
	
	/**
	 * 如果直接发送ajax=PUT形式的请求
	 * 封装的数据只有empId
	 * 需要配置HttpPutFormContentFilter  过滤器
	 * 
	 * 保存员工
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	public Msg updateEmp(Employee employee){
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	
	/**
	 * 根据id查询员工
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id){
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	
	/**
	 * 检查用户名是否可用
	 * @param empName
	 * @return Msg.code=====100可用
	 * 		   Msg.code=====200不可用
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
	 * 员工保存
	 * 1.支持JSR303校验
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result){
		if(result.hasErrors()){
			//校验失败返回失败
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
	 * 需要导入Json包======Jackson Databind
	 * 员工查询
	 * @param pageNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/emps")
	public Msg getEmpsWithJson(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum){
		//引入PageHelper分页插件
		//在查询之前调用分页插件
		PageHelper.startPage(pageNum, 5);
		//startPage之后紧跟着查询
		List<Employee> employees = employeeService.getAll();
		//使用PageInfo包装查询后的结果，只需要将pageInfo返回
		PageInfo pageInfo = new PageInfo<>(employees,5);
		
		return Msg.success().add("pageInfo", pageInfo);
	}
	
	
	/**
	 * 查询员工数据（分页查询）
	 * @return
	 */
//	@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum,
			Model model){
		//引入PageHelper分页插件
		//在查询之前只需要调用
		PageHelper.startPage(pageNum, 5);
		//startPage后面紧跟着的这个查询就是一个分页查询
		List<Employee> employees = employeeService.getAll();
		//使用PageInfo包装查询后的结果，只需要将pageInfo交给页面
		//封装了详细的分页信息，包括我们查询出来的数据,可以传入连续显示的页数
		PageInfo pageInfo = new PageInfo(employees,5);
		model.addAttribute("pageInfo",pageInfo);
		return "list";
	}
}
