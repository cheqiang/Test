<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta charset="utf-8">
	<title>文件管理系统</title>
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
						文件管理系统
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
						<a href="#" >
							<i class="icon-coffee"></i>
							<span class="menu-text">文件管理</span>
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
			<!-- PAGE CONTENT BEGINS -->
			<form class="form-horizontal"  enctype="multipart/form-data" role="form" action="/uploadFile" method="post" id="info_classify_FormId">

				<div class="col-xs-12">
					<div class="col-xs-9">
					<input name="fileName" type="file" class="form-control">
					</div>
					<input  class="btn btn-sm btn-success" type="submit" value="上传文件">
				</div>
				<div class="col-xs-12">
					<div class="well">
					</div>
				</div>
			</form>


		</div>

		<div class="row">
			<div class="col-xs-12 version-detail">
				<div class="row">
					<div class="col-xs-12">
						<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
							<div class="table-responsive">
								<table id="sample-table-1" class="table table-striped table-bordered table-hover dataTable">
									<thead>
										<tr>
											<th>id</th>
											<th class="hidden-480">uuid</th>
											<td>size</td>
											<td>type</td>
											<td>name</td>
											<td>oldName</td>
											<td>createTime</td>
											<td>path</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="item">
											<tr>
												<td>${item.id}</td>
												<td>${item.uuid}</td>
												<td>${item.size}</td>
												<td>${item.type}</td>
												<td>${item.name}</td>
												<td>${item.oldName}</td>
												<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>${item.path}</td>
												<td>
													<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
														<a href="download?uuid=${item.uuid}">
															<button class="btn btn-xs btn-success">
																<i class="icon-download-alt bigger-120"></i>
															</button>
														</a>
													</div>
												</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
							<nav>
							</nav>
						</div>
					</div>
				</div>

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

  &copy; 大公司







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
	})
</script>

</body>
