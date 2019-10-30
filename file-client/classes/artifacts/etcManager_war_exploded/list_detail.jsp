<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta charset="utf-8">
	<title>ETC营销数据展示----机构：${organizationName}(${ip})</title>
	<meta name="description" content="overview &amp; stats">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<!-- basic styles -->

	<meta name="description" content="overview &amp; stats">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link href="/assets/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="/assets/css/font-awesome.min.css">
<!--[if IE 7]>
  <link rel="stylesheet" href="/assets/css/font-awesome-ie7.min.css" />
  <![endif]-->

  <!-- page specific plugin styles -->

  <link rel="stylesheet" href="/assets/css/jquery-ui-1.10.3.custom.min.css">
  <link rel="stylesheet" href="/assets/css/chosen.css">
  <link rel="stylesheet" href="/assets/css/datepicker.css">
  <link rel="stylesheet" href="/assets/css/bootstrap-timepicker.css">
  <link rel="stylesheet" href="/assets/css/daterangepicker.css">
  <link rel="stylesheet" href="/assets/css/colorpicker.css">


  <!-- fonts -->

  <link rel="stylesheet" href="/assets/css/ace-fonts.css">

  <!-- ace styles -->

  <link rel="stylesheet" href="/assets/css/ace.min.css">
  <link rel="stylesheet" href="/assets/css/ace-rtl.min.css">
  <link rel="stylesheet" href="/assets/css/ace-skins.min.css">
  <link rel="stylesheet" href="/assets/css/myCss.css">

<!--[if lte IE 8]>
  <link rel="stylesheet" href="/assets/css/ace-ie.min.css" />
  <![endif]-->

  <!-- inline styles related to this page -->

  <!-- ace settings handler -->

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
<script src="/assets/js/html5shiv.js"></script>
<script src="/assets/js/respond.min.js"></script>
<![endif]-->
<script src="/assets/js/ace-extra.min.js"></script>
</head>

<body>
	<div class="navbar navbar-default" id="navbar">
		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand">
					<small>
						<i class="icon-cogs"></i>
						ETC营销数据展示----机构：${organizationName}(${ip})
					</small>
				</a><!-- /.brand -->
			</div><!-- /.navbar-header -->

			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle">
							<img class="nav-user-photo" src="/assets/avatars/avatar2.png" alt="Jason's Photo">
							<span class="user-info">
								<small>欢迎,</small>
							</span>
							<i class="icon-caret-down"></i>
						</a>
						<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li>
								<a data-target="#modal_4" data-toggle="modal" href="#"> 
									<i class="icon-user"></i>
									修改个人信息
								</a>
							</li>

							<li class="divider"></li>

							<li>
								<a href="user_loginOut">
									<i class="icon-off"></i>
									退出
								</a>
							</li>
						</ul>
					</li>
				</ul><!-- /.ace-nav -->
			</div><!-- /.navbar-header -->
		</div><!-- /.container -->
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#">
				<span class="menu-text"></span>
			</a>
			<div class="sidebar" id="sidebar">
				<!-- 菜单导航开始 -->
				<ul class="nav nav-list">
					<li class="open">
						<a href="/listUser" >
							<i class="icon-coffee"></i>
							<span class="menu-text">用户管理</span>
						</a>
					</li>

				</ul><!-- /.nav-list -->
				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
				</div>
				<!-- 菜单导航结束 -->
			</div>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>
					<ul class="breadcrumb">
						<li>
							<i class="icon-home home-icon"></i>
							<a >首页</a>
						</li>		
		<!--<li>
			<i class="icon-home home-icon"></i>
			<a href="/module/security/index.html">首页</a>
			</li>
			<li class="active">用户管理</li> -->
		</ul><!-- .breadcrumb -->
	</div>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="user?method=userQueryByName" method="post" id="info_classify_FormId">
						<div class="col-xs-12">
							<div class="well">
							</div>
						</div>	
					</form>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
							<div class="table-responsive">
								<form action = "user?method=delGoods" method="post" id="formSubmit">
								<table id="sample-table-1" class="table table-striped table-bordered table-hover dataTable">
									<thead>
										<tr>
											<th>开卡机构号</th>
											<th class="hidden-480">开卡时间</th>
											<th>开卡柜员EHR</th>
											<th>ETC卡号</th>
											<th>营销人员HER</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="item">
										
										<tr>
											<td>${item.orgId}</td>
											<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><font name="menuName">${item.ehrName}</font></td>
											<td>${item.etcNo}</td>
											<td>${item.saleName}</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
								</form>
							</div>
							<nav>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<!--文字信息-->
			<div class="col-md-6">
				当前第 ${pageInfo.pageNum} 页.总共 ${pageInfo.pages} 页.一共 ${pageInfo.total} 条记录
			</div>

			<!--点击分页-->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
					<ul class="pagination">

						<li><a href="${pageContext.request.contextPath}/listDetailByUid?pageNum=1&uid=${uid}">首页</a></li>

						<!--上一页-->
						<li>
							<c:if test="${pageInfo.hasPreviousPage}">
								<a href="${pageContext.request.contextPath}/listDetailByUid?pageNum=${pageInfo.pageNum-1}&uid=${uid}" aria-label="Previous">
									<span aria-hidden="true">«</span>
								</a>
							</c:if>
						</li>

						<!--循环遍历连续显示的页面，若是当前页就高亮显示，并且没有链接-->
						<c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
							<c:if test="${page_num == pageInfo.pageNum}">
								<li class="active"><a href="#">${page_num}</a></li>
							</c:if>
							<c:if test="${page_num != pageInfo.pageNum}">
								<li><a href="${pageContext.request.contextPath}/listDetailByUid?pageNum=${page_num}&uid=${uid}">${page_num}</a></li>
							</c:if>
						</c:forEach>

						<!--下一页-->
						<li>
							<c:if test="${pageInfo.hasNextPage}">
								<a href="${pageContext.request.contextPath}/listDetailByUid?pageNum=${pageInfo.pageNum+1}&uid=${uid}"
								   aria-label="Next">
									<span aria-hidden="true">»</span>
								</a>
							</c:if>
						</li>

						<li><a href="${pageContext.request.contextPath}/listDetailByUid?pageNum=${pageInfo.pages}&uid=${uid}">尾页</a></li>
					</ul>
				</nav>
			</div>
		</div>



		<div class="ace-settings-container" id="ace-settings-container">
			<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
				<i class="icon-cog bigger-150"></i>
			</div>
			<div class="ace-settings-box" id="ace-settings-box">
				<div>
					<div class="pull-left">
						<select id="skin-colorpicker" class="hide">
							<option data-skin="default" value="#438EB9">#438EB9</option>
							<option data-skin="skin-1" value="#222A2D">#222A2D</option>
							<option data-skin="skin-2" value="#C6487E">#C6487E</option>
							<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
						</select>
					</div>
					<span>&nbsp; Choose Skin</span>
				</div>
				<div>
					<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
					<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
				</div>
				<div>
					<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
					<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
				</div>
				<div>
					<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
					<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
				</div>
				<div>
					<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
					<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
				</div>
				<div>
					<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
					<label class="lbl" for="ace-settings-add-container">
						Inside
						<b>.container</b>
					</label>
				</div>
			</div>
		</div>
	</div>

	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
		<i class="icon-double-angle-up icon-only bigger-110"></i>
	</a>
</div>

<!-- 弹出层 -->

<div id="modal_3" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">选择数量</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form class="form-horizontal form" action="user_addUser" method="post" id="goumaiForm" cansumbit="yes">
								<div class="col-md-12">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">用户名:</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" name="username" maxlength="100" placeholder="名称">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">密码:</label>
										<div class="col-sm-9">
											<input type="password" class="form-control" name="password" maxlength="100" placeholder="密码" >
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">角色:</label>
										<div class="col-sm-9">
											<select class="form-control" name="role">
												<option value="2">服务员</option>
												<option value="3">移动用户</option>
												<option value="4">普通用户</option>
											</select>
										</div>
									</div>
								</div>
							</form>
						</div><!-- /.col -->
					</div><!-- /.row --><!-- /.main-content -->
				</div>
			</div>
			<div class="modal-footer">
				<button id="goumai" class="btn btn-sm btn-success" data-dismiss="modal">
					<i class="icon-plus"></i>确定
				</button>
				<button class="btn btn-sm btn-danger" data-dismiss="modal">
					<i class="icon-remove"></i>取消
				</button>
			</div>
		</div>
	</div>
</div>







<!-- basic scripts -->

<!--[if !IE]> -->

<script type="text/javascript">
	window.jQuery || document.write("<script src='/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

<script type="text/javascript">
	if("ontouchend" in document) document.write("<script src='/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<script src="/assets/js/jquery.dataTables.min.js"></script>
<script src="/assets/js/jquery.dataTables.bootstrap.js"></script>

<!-- ace scripts -->

<script src="/assets/js/ace-elements.min.js"></script>
<script src="/assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script src="/assets/js/date-time/daterangepicker.min.js"></script>
<script src="/assets/js/date-time/moment.min.js"></script>
<script type="text/javascript">
	jQuery(function($) {
		$('[data-rel=tooltip]').tooltip();
		$('#onlineTime').daterangepicker({
			"ranges": {
				"Today": [
				"2016-06-12T09:12:55.445Z",
				"2016-06-12T09:12:55.446Z"
				],
				"Yesterday": [
				"2016-06-11T09:12:55.446Z",
				"2016-06-11T09:12:55.451Z"
				],
				"Last 7 Days": [
				"2016-06-06T09:12:55.451Z",
				"2016-06-12T09:12:55.451Z"
				],
				"Last 30 Days": [
				"2016-05-14T09:12:55.451Z",
				"2016-06-12T09:12:55.451Z"
				],
				"This Month": [
				"2016-05-31T16:00:00.000Z",
				"2016-06-30T15:59:59.999Z"
				],
				"Last Month": [
				"2016-04-30T16:00:00.000Z",
				"2016-05-31T15:59:59.999Z"
				]
			},
			"linkedCalendars": false,
			"startDate": "06/06/2016",
			"endDate": "06/12/2016"
		}, function(start, end, label) {
			console.log("New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')");
		});
	})
</script>

</body>
