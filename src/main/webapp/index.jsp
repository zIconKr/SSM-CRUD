<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- 导入jquery -->
<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
<!-- 导入bootstrap -->
<link
	href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>

<!-- 员工修改的模态框 -->
	<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" ">员工修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label  class="col-sm-2 control-label">empName</label>
							<div class="col-sm-10">
								<p class="from-control-static" id="empName_update_static"></p>
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">email</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control"
									id="email_update_input" placeholder="xxx@xxx.xxx"> 
									<span class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender"  value="G"
									checked="checked"> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender"  value="M"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">deptName</label>
							<div class="col-sm-4">
								<!-- 部门提交部门id即可 -->
								<select class="form-control" name="dId" id="dept_update_select">
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="emp_update_btn">修改</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 员工添加的模态框 -->
	<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">员工添加</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="empName_add_input" class="col-sm-2 control-label">empName</label>
							<div class="col-sm-10">
								<input type="text" name="empName" class="form-control"
									id="empName_add_input" placeholder="empName">
									<span class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="email_add_input" class="col-sm-2 control-label">email</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control"
									id="email_add_input" placeholder="xxx@xxx.xxx"> 
									<span class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender1_add_input" value="G"
									checked="checked"> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender1_add_input" value="M"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">deptName</label>
							<div class="col-sm-4">
								<!-- 部门提交部门id即可 -->
								<select class="form-control" name="dId" id="dept_add_select">
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 搭建显示页面 -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
				<button class="btn btn-danger" id="emp_delete_all_btn">删除</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="check_all"/>
							</th>
							<th>#</th>
							<th>empName</th>
							<th>gender</th>
							<th>email</th>
							<th>departName</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>

		<!-- 显示分页信息 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-6" id="page_info_area"></div>
			<!-- 分页条信息 -->
			<div class="col-md-6" id="page_nav_area"></div>
		</div>
	</div>

	<script type="text/javascript">
		//总记录数
		var totalRecord;
		//当前页
		var currentPage;

		//1.页面加载完成之后，直接去发送一个ajax请求，要到分页数据
		$(function() {
			//去首页
			to_page(1);
		});

		function to_page(pageNum) {
			$.ajax({
				url : "${APP_PATH}/emps",
				data : "pageNum=" + pageNum,
				type : "get",
				success : function(result) {
					//1.解析并显示员工数据
					build_emps_table(result);
					//2.解析并显示分页信息
					build_page_info(result);
					//3.接续显示分页条
					build_page_nav(result);
				}
			});
		}

		function build_emps_table(result) {
			//清空table表格
			$("#emps_table tbody").empty();
			var emps = result.extend.pageInfo.list;
			$.each(emps, function(index, item) {
				var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var genderTd = $("<td></td>").append(
						item.gender == "G" ? "男" : "女");
				var emailTd = $("<td></td>").append(item.email);
				var deptNameTd = $("<td></td>")
						.append(item.department.deptName);
				var editBtn = $("<button></button>").addClass(
						"btn btn-info btn-sm edit_btn").append(
						$("<span></span>").addClass(
								"glyphicon glyphicon-pencil")).append(" 编辑");
				editBtn.attr("edit-id",item.empId);
				var deleteBtn = $("<button></button>").addClass(
						"btn btn-danger btn-sm delete_btn").append(
						$("<span></span>")
								.addClass("glyphicon glyphicon-trash")).append(" 删除");
				deleteBtn.attr("delete-name",item.empName);
				deleteBtn.attr("delete-id",item.empId);
				var btnTd = $("<td></td").append(editBtn).append(" ").append(
						deleteBtn);
				$("<tr></tr>").append(checkBoxTd).append(empIdTd).append(empNameTd).append(
						genderTd).append(emailTd).append(deptNameTd).append(
						btnTd).appendTo("#emps_table tbody");
			});
		}

		//解析显示分页信息
		function build_page_info(result) {
			$("#page_info_area").empty();
			var pi = result.extend.pageInfo;
			currentPage = pi.pageNum;
			var totalPages = pi.pages;
			var totalNum = pi.total;
			totalRecord = pi.total;
			
			$("#page_info_area").append(
					"当前" + currentPage + "页,总" + totalPages + "页数,总条数"
							+ totalNum);
		}

		//解析显示分页条
		function build_page_nav(result) {
			$("#page_nav_area").empty();
			//page_nav_area
			var pi = result.extend.pageInfo;

			var ul = $("<ul></ul>").addClass("pagination");

			var firstPageLi = $("<li></li>").append(
					$("<a></a>").append("首页").attr("href", "#"));
			var prePageLi = $("<li></li>").append(
					$("<a></a>").append("&laquo;").attr("href", "#"));

			if (pi.hasPreviousPage == false) {
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			} else {
				firstPageLi.click(function() {
					to_page(1);
				});
				prePageLi.click(function() {
					to_page(pi.pageNum - 1);
				});
			}

			var nextPageLi = $("<li></li>").append(
					$("<a></a>").append("&raquo;").attr("href", "#"));
			var finalPageLi = $("<li></li>").append(
					$("<a></a>").append("末页").attr("href", "#"));

			if (pi.hasNextPage == false) {
				nextPageLi.addClass("disabled");
				finalPageLi.addClass("disabled");
			} else {
				nextPageLi.click(function() {
					to_page(pi.pageNum + 1);
				});
				finalPageLi.click(function() {
					to_page(pi.pages);
				});
			}
			//添加首页和前一页的提示
			ul.append(firstPageLi).append(prePageLi);

			//遍历页码号,并给ul中添加
			$.each(pi.navigatepageNums, function(index, item) {
				var numLi = $("<li></li>").append(
						$("<a></a>").append(item).attr("href", "#"));
				if (pi.pageNum == item) {
					numLi.addClass("active");
				}
				numLi.click(function() {
					to_page(item);
				});

				ul.append(numLi);
			});
			//添加下一页和末页的提示
			ul.append(nextPageLi);
			ul.append(finalPageLi);
			var nav = $("<nav></nav>").append(ul);
			nav.appendTo("#page_nav_area");
		}

		function reset_form(ele){
			//清除内容
			$(ele)[0].reset();
			//清除样式
			$(ele).find("*").removeClass("has-error has-success");
			$(ele).find(".help-block").text("");
		}
		
		//点击新增按钮弹出模态框
		$("#emp_add_modal_btn").click(function() {
			//清除表单样式及内容
			reset_form("#empAddModal form")
			
			//发送ajax请求，查出部门信息，显示在下拉列表
			getDepts("#dept_add_select");

			//弹出模态框
			$("#empAddModal").modal({
				backdrop : "static"
			});
		});

		//查出所有的部门信息并显示在下拉列表中
		function getDepts(ele) {
			//清空之前下拉列表中的值
			$(ele).empty();
			$.ajax({
				url : "${APP_PATH}/depts",
				type : "GET",
				success : function(result) {
					//console.log(result);
					//$("#dept_add_select").append();
					$.each(result.extend.depts, function() {
						var optionEle = $("<option ></option>").append(
								this.deptName).attr("value", this.deptId);
						optionEle.appendTo(ele);
					});
				}
			});
		}

		//校验表单数据
		function validate_add_form() {
			//1.拿到要校验的数据，使用正则表达式
			var empName = $("#empName_add_input").val();
			var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5}$)/;
			if (!regName.test(empName)) {
				show_validate_msg("#empName_add_input","error","用户名可以是2-5位中文或者6-16位英文和数字的组合");
				return false;
			} else {
				show_validate_msg("#empName_add_input","success","");
				
			}
			//2.校验邮箱信息
			var email = $("#email_add_input").val();
			//var regEmail = /^([a-zA-Z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (!regEmail.test(email)) {
				show_validate_msg("#email_add_input","error","邮箱格式不正确");
				//alert("邮箱格式不正确");
				//$("#email_add_input").parent().addClass("has-error");
				//$("#email_add_input").next("span").text("邮箱格式不正确");
				return false;
			} else {
				show_validate_msg("#email_add_input","success","");
				//$("#email_add_input").parent().addClass("has-success");
				//$("#email_add_input").next("span").text("");

			}
			return true;

		}
		
		function show_validate_msg(ele,status,msg){
			//清楚当前元素的校验状态
			$(ele).parent().removeClass("has-success has-error");
			if("success"==status){
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);
			}else if("error"==status){
//				alert("用户名可以是2-5位中文或者6-16位英文和数字的组合");
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
			}
		}

		//检查用户名是否可用
		$("#empName_add_input").change(function(){
			
			//发送ajax请求
			var empName = this.value;
			$.ajax({
				url:"${APP_PATH}/checkuser",
				data:"empName="+empName,
				type:"POST",
				success:function(result){
					if(result.code==100){
						show_validate_msg("#empName_add_input","success","用户名可用");
						$("#emp_save_btn").attr("ajax-value","success");
					}else{
						show_validate_msg("#empName_add_input","error","用户名不可用");
						$("#emp_save_btn").attr("ajax-value","error");
					}
				}
			});
		});
		
		
		//点击新增模态框中保存按钮
		$("#emp_save_btn").click(function() {
			//1.模态框中填写的表单数据提交给服务器进行保存
			//先对要提交给服务器的数据进行校验
			if(!validate_add_form()){
				return false;
			}
			//判断之前change的ajax是否成功
			if($(this).attr("ajax-value")=="error"){
				return false;
			}
			//2.发送ajax请求保存员工
			$.ajax({
				url : "${APP_PATH}/emp",
				type : "POST",
				data : $("#empAddModal form").serialize(),
				success : function(result) {
					if(result.code == 200){
						if(undefined !=result.extend.errorFields.email){
							//显示邮箱错误信息
							show_validate_msg("#email_add_input","error",result.extend.errorFields.email);
						}
						if(undefined !=result.extend.errorFields.empName){
							//显示员工名字的错误信息
							show_validate_msg("#empName_add_input","error",result.extend.errorFields.empName);
						}
					}else{
					//员工保存成功
					//1.关闭模态框
					$("#empAddModal").modal('hide');
					//2.来到最后一页，显示最后一页即可
					to_page(totalRecord);
					}
				}

			});
		});
		
		//为编辑按钮绑定点击事件,由于在我们按钮是在ajax发送请求后才创建的编辑按钮
		//所以不能直接使用click,需要使用on()
		$(document).on("click",".edit_btn",function(){
			//1.查出员工信息
			getEmp($(this).attr("edit-id"));
			//2.查出部门信息，并显示部门列表
			getDepts("#dept_update_select");
			//3.把员工Id传递给模态框的更新按钮
			$("#emp_update_btn").attr("edit-id",$(this).attr("edit-id"));
			//4.弹出模态框
			$("#empUpdateModal").modal({
				backdrop : "static"
			});
		});
		
		//根据员工Id获取员工信息
		function getEmp(id){
			$.ajax({
				url:"${APP_PATH}/emp/"+id,
				type:"GET",
				success:function(result){
					var empData = result.extend.emp;
					$("#empName_update_static").text(empData.empName);
					$("#email_update_input").val(empData.email);
					$("#empUpdateModal input[name=gender]").val([empData.gender]);
					$("#empUpdateModal select").val([empData.dId]);
					
				}
			});
		}
		
		//点击编辑中的更新按钮，更新员工信息
		$("#emp_update_btn").click(function(){
			//验证邮箱是否合法
			var email = $("#email_update_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (!regEmail.test(email)) {
				show_validate_msg("#email_update_input","error","邮箱格式不正确");
				return false;
			} else {
				show_validate_msg("#email_update_input","success","");
			}
			//发送ajax请求
			$.ajax({
				url:"${APP_PATH}/emp/"+$(this).attr("edit-id"),
				data:$("#empUpdateModal form").serialize(),
				type:"PUT",
				success:function(result){
					alert(result.msg);
					//1.关闭模态框
					$("#empUpdateModal").modal('hide');
					//2.回到本页面
					to_page(currentPage);
				}
			});
			
		});
		
		//点击单个删除按钮
		$(document).on("click",".delete_btn",function(){
			//1.弹出是否确认删除
			var empName=$(this).attr("delete-name");
			var empId = $(this).attr("delete-id");
			if(confirm("确认删除【"+empName+"】")){
				//确认则发送ajax请求
				$.ajax({
					url:"${APP_PATH}/emp/"+empId,
					type:"DELETE",
					success:function(result){
						alert(result.msg);
						to_page(currentPage);
					}
				});
			}
		});
		
		//全选或全不选
		$("#check_all").click(function(){
			$(".check_item").prop("checked",$(this).prop("checked"));
		});
		
		//check_item
		$(document).on("click",".check_item",function(){
			var flag = $(".check_item:checked").length==$(".check_item").length;
			$("#check_all").prop("checked",flag);
		});
		
		//点击全部删除====批量删除
		$("#emp_delete_all_btn").click(function(){
			var empNames="";
			var ids = ""
			$.each($(".check_item:checked"),function(){
				empNames += $(this).parents("tr").find("td:eq(2)").text()+"   ";
				ids += $(this).parents("tr").find("td:eq(1)").text()+"-";
			});
			ids = ids.substring(0,ids.length-1);
			if(confirm("确认删除【"+empNames+"】吗？")){
				//发送ajax请求
				$.ajax({
					url:"${APP_PATH}/emp/"+ids,
					type:"DELETE",
					success:function(result){
						alert(result.msg);
						to_page(currentPage);
					}
				});
			}
		});
		
	</script>

</body>
</html>