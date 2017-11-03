<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>StepAhead - Manage Packages!</title>

<link href="resources/styles/bootstrap.min.css" rel="stylesheet">
<link href="resources/styles/datepicker3.css" rel="stylesheet">
<link href="resources/styles/bootstrap-table.css" rel="stylesheet">
<link href="resources/styles/styles.css" rel="stylesheet">
<link href="resources/images/favicon.ico" rel="shortcut icon"
	type="image/x-icon" />

<!--Icons-->
<script src="resources/js/lumino.glyphs.js"></script>

<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->

</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/stepahead/"><img
				src="resources/images/favicon.ico" alt="icon" style="height: 100%;">&nbsp;<span>Step</span>Ahead</a>
			<ul class="user-menu">
				<li class="dropdown pull-right"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"><c:choose>
							<c:when test="${requestScope.loggedInUser.gender == 'F'}">
								<svg class="glyph stroked female user"> <use
									xlink:href="#stroked-female-user" /></svg>
							</c:when>
							<c:otherwise>
								<svg class="glyph stroked male-user"> <use
									xlink:href="#stroked-male-user"></use></svg>
							</c:otherwise>
						</c:choose> Welcome, ${requestScope.loggedInUser.firstName}
						${requestScope.loggedInUser.lastName} <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="/stepahead/myProfile.htm"><c:choose>
									<c:when test="${requestScope.loggedInUser.gender == 'F'}">
										<svg class="glyph stroked female user"> <use
											xlink:href="#stroked-female-user" /></svg>
									</c:when>
									<c:otherwise>
										<svg class="glyph stroked male-user"> <use
											xlink:href="#stroked-male-user"></use></svg>
									</c:otherwise>
								</c:choose> Profile</a></li>
						<li><a href="/stepahead/login.htm?action=logout"> <svg
									class="glyph stroked cancel"> <use
									xlink:href="#stroked-cancel"></use> </svg> Logout
						</a></li>
					</ul></li>
			</ul>
		</div>

	</div>
	<!-- /.container-fluid --> </nav>

	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<ul class="nav menu">
			<li role="presentation" class="divider"></li>
			<li><a href="/stepahead/"><svg class="glyph stroked home">
					<use xlink:href="#stroked-home"></use></svg> Home </a></li>
			<li class="disabled"><a href="#"><svg
						class="glyph stroked male user "> <use
						xlink:href="#stroked-male-user" /></svg> Manage Users </a></li>
			<li><a href="/stepahead/manageCompanies.htm"><svg
						class="glyph stroked location pin"> <use
						xlink:href="#stroked-location-pin" /></svg> Manage Companies </a></li>
			<li class="active"><a href="/stepahead/managePackages.htm"><svg
						class="glyph stroked tag"> <use xlink:href="#stroked-tag" /></svg>
					Manage Packages </a></li>
			<li><a href="/stepahead/managePositions.htm"><svg
						class="glyph stroked desktop computer and mobile"> <use
						xlink:href="#stroked-desktop-computer-and-mobile" /></svg> Manage
					Positions </a></li>
			<li><a href="/stepahead/manageDomains.htm"><svg
						class="glyph stroked hourglass"> <use
						xlink:href="#stroked-hourglass" /></svg> Manage Domains </a></li>
			<li class="disabled"><a href="#"><svg
						class="glyph stroked pencil"> <use
						xlink:href="#stroked-pencil" /></svg> Manage Blogs </a></li>
			<li role="presentation" class="divider"></li>
			<li><a href="/stepahead/myProfile.htm"> <c:choose>
						<c:when test="${requestScope.loggedInUser.gender == 'F'}">
							<svg class="glyph stroked female user"> <use
								xlink:href="#stroked-female-user" /></svg>
						</c:when>
						<c:otherwise>
							<svg class="glyph stroked male-user"> <use
								xlink:href="#stroked-male-user"></use></svg>
						</c:otherwise>
					</c:choose> Profile
			</a></li>
			<li><a href="/stepahead/login.htm?action=logout"> <svg
						class="glyph stroked cancel"> <use
						xlink:href="#stroked-cancel"></use> </svg> Logout
			</a></li>
			<li role="presentation" class="divider"></li>
		</ul>

	</div>
	<!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="/stepahead/"><svg class="glyph stroked home">
						<use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">Manage Packages</li>
			</ol>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Manage Packages</h1>
				<c:if test="${requestScope.addedPackage == 'true'}">
					<div id="addMsg" class="alert bg-success" role="alert"
						style="background-color: #8ad919;">
						<svg class="glyph stroked checkmark"> <use
							xlink:href="#stroked-checkmark"></svg>
						Package Added successfully!<a class="pull-right"
							href="javascript:closeMsg();"> <span
							class="glyphicon glyphicon-remove"></span>
						</a>
					</div>
				</c:if>
				<c:if test="${requestScope.deletedPackage == 'true'}">
					<div id="deleteMsg" class="alert bg-success" role="alert"
						style="background-color: #8ad919;">
						<svg class="glyph stroked checkmark"> <use
							xlink:href="#stroked-checkmark"></svg>
						Package Deleted successfully!<a class="pull-right"
							href="javascript:closeMsg();"> <span
							class="glyphicon glyphicon-remove"></span>
						</a>
					</div>
				</c:if>
				<div class="panel panel-default">
					<div class="panel-heading">Packages</div>
					<div class="panel-body">
						<form action="managePackages.htm" method="POST">
							<table data-toggle="table" data-pagination="true">
								<thead>
									<tr>
										<th data-align="right" data-width="150">Package Id</th>
										<th data-width="200">Minimum Salary</th>
										<th data-width="200">Maximum Salary</th>
										<th data-width="350">Position</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="pck" items="${requestScope.packages}">
										<tr id="${pck.packageId}">
											<td><c:out value="${pck.packageId}"></c:out></td>
											<td><c:out value="${pck.minSal}"></c:out></td>
											<td><c:out value="${pck.maxSal}"></c:out></td>
											<td><c:out value="${pck.position.positionTitle}"></c:out></td>
											<td><button type="submit" class="btn btn-primary"
													onclick="return deletePackage(${pck.packageId});"
													name="btnDelete" id="delete${pck.packageId}">
													<svg class="glyph stroked trash" style="height: 1.42857em;">
													<use xlink:href="#stroked-trash" /></svg>
												</button></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
							<input type="hidden" value="delete" name="action" id="action" />
							<input type="hidden" name="packId" id="packId" value="" />
						</form>
						<div>
							<a href="javascript:showAddPackage();" id="addPackageLnk"
								role="button" class="btn btn-primary"><svg
									class="glyph stroked plus sign"
									style="height: 1.42em; width: 1.42em;"> <use
									xlink:href="#stroked-plus-sign" /></svg> Add Package</a>
						</div>
					</div>
				</div>
				<div id="divAddPackage" style="display: none;" class="form-group">
					<div class="panel panel-default">
						<div class="panel-heading">Add Package</div>
						<div class="panel-body">
							<spring:form action="managePackages.htm" method="POST"
								commandName="package">
								<div class="form-group" style="width: 350px;">
									<label>Position:</label>
									<spring:select path="position.positionTitle"
										class="form-control" id="selPosition">
										<spring:option value="NONE"
											label="-------- Select Position --------"></spring:option>
										<spring:options items="${requestScope.positions}" />
									</spring:select>
								</div>
								<div class="form-group" style="width: 200px;">
									<label>Minimum Salary:</label>
									<spring:input path="minSal" class="form-control" id="minSal" />
								</div>
								<div class="form-group" style="width: 200px;">
									<label>Maximum Salary:</label>
									<spring:input path="maxSal" class="form-control" id="maxSal" />
								</div>
								<input type="hidden" value="add" name="action" />
								<input type="submit" value="Add" id="btnAdd"
									class="btn btn-primary" onclick="return addPackage();" />
							</spring:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/.row-->
	</div>
	<!--/.main-->

	<script src="resources/js/jquery-1.11.1.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/chart.min.js"></script>
	<script src="resources/js/chart-data.js"></script>
	<script src="resources/js/easypiechart.js"></script>
	<script src="resources/js/easypiechart-data.js"></script>
	<script src="resources/js/bootstrap-datepicker.js"></script>
	<script src="resources/js/bootstrap-table.js"></script>

	<script>
		!function($) {
			$(document)
					.on(
							"click",
							"ul.nav li.parent > a > span.icon",
							function() {
								$(this).find('em:first').toggleClass(
										"glyphicon-minus");
							});
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function() {
			if ($(window).width() > 768)
				$('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function() {
			if ($(window).width() <= 767)
				$('#sidebar-collapse').collapse('hide')
		})

		function showAddPackage() {
			$("#divAddPackage").css("display", "block");
			$("#addPackageLnk").attr("disabled", "true");
		}
	</script>

	<script type="text/javascript">
		function addPackage() {
			$('#selPosition').val()
			if ($('#selPosition').val() == "NONE") {
				alert("Please select Position.");
				return false;
			}
			else if ($("#minSal").val().trim() == ""
					|| $("#minSal").val().trim() == 0) {
				alert("Please enter Minimum Salary.");
				return false;
			}
			else if ($("#maxSal").val().trim() == ""
					|| $("#maxSal").val().trim() == 0) {
				alert("Please enter Maximum Salary.");
				return false;
			}
			else{
				var maxVal = $("#maxVal").val();
				var minVal = $("#minVal").val();
				
				if (Number(minVal) > Number(maxVal)) {
					alert("Minimum Salary must be less than Maximum Salary.");
					return false;
				}
				else {
					$("#action").val('add');
					return true;
				}
			}
		}

		function closeMsg() {
			$("#addMsg").css("display", "none");
			$("#deleteMsg").css("display", "none");
		}

		function deletePackage(packageId) {
			$("#packId").val(packageId);
			return true;
		}
	</script>
</body>
</html>