<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <!DOCTYPE html> -->
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>StepAhead - My Profile!</title>

<link href="resources/styles/bootstrap.min.css" rel="stylesheet">
<link href="resources/styles/datepicker3.css" rel="stylesheet">
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
			<li><a href="/stepahead/myPostedJobs.htm"><svg
						class="glyph stroked checkmark"> <use
						xlink:href="#stroked-checkmark" /></svg> My Job Posts </a></li>
			<li><a href="/stepahead/myFavorites.htm"><svg
						class="glyph stroked star"> <use xlink:href="#stroked-star" /></svg>
					My Favorites</a></li>
			<li class="disabled"><a href="#"><svg
						class="glyph stroked pencil"> <use
						xlink:href="#stroked-pencil"></use></svg> My Blogs</a></li>
			<li class="disabled"><a href="#"><svg
						class="glyph stroked empty message"> <use
						xlink:href="#stroked-empty-message" /></svg> Notifications </a></li>

			<li role="presentation" class="divider"></li>
			<li class="active"><a href="/stepahead/myProfile.htm"> <c:choose>
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
				<li class="active">My Profile</li>
			</ol>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">My Profile</h1>
				<c:if test="${requestScope.profileSaved == 'true'}">
					<div class="alert bg-success" role="alert"
						style="background-color: #8ad919;">
						<svg class="glyph stroked checkmark"> <use
							xlink:href="#stroked-checkmark"></svg>
						Your Profile is saved successfully!
					</div>
				</c:if>
			</div>
		</div>
		<!--/.row-->


		<div class="row">
			<div class="col-lg-12">
				<spring:form commandName="loggedInUser" role="form"
					action="hrPersonProfile.htm" method="POST">
					<div class="panel panel-default">
						<div class="panel-heading">Personal Details</div>
						<div class="panel-body">

							<div class="col-md-6">

								<div class="form-group">
									<label>First Name:</label>
									<spring:input path="firstName" class="form-control"
										placeholder="Placeholder" />
								</div>

								<div class="form-group">
									Phone Number:
									<spring:input path="contact" class="form-control" />
								</div>

								<div class="form-group">
									Gender:&nbsp;
									<spring:radiobutton path="gender" value="M" />
									Male &nbsp;&nbsp;&nbsp;
									<spring:radiobutton path="gender" value="F" />
									Female
								</div>

								<div class="form-group">
									<label>Address 1:</label>
									<spring:textarea path="address.address1" rows="1"
										class="form-control" />
								</div>

								<div class="form-group">
									City:
									<spring:input path="address.city" class="form-control" />
								</div>

								<div class="form-group">
									Zip Code:
									<spring:input path="address.zipCode" class="form-control" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Last Name:</label>
									<spring:input path="lastName" class="form-control" />
								</div>

								<div class="form-group">
									Email Id:
									<spring:input path="emailId" class="form-control"
										disabled="true" />
								</div>
								<div class="form-group">
									<span>&nbsp;</span>
								</div>

								<div class="form-group">
									<label>Address 2:</label>
									<spring:textarea path="address.address2" rows="1"
										class="form-control" />
								</div>

								<div class="form-group">
									State:
									<spring:input path="address.state" class="form-control" />
								</div>

								<div class="form-group">
									<span>&nbsp;</span>
								</div>
							</div>

						</div>

						<div class="panel-heading">Company Details</div>
						<div class="panel-body">

							<div class="col-md-6">
								<div class="form-group">
									<label>Company:</label>
									<spring:select path="company.companyName" class="form-control">
										<spring:option value="NONE"
											label="-------- Select Company --------"></spring:option>
										<spring:options items="${requestScope.companies}" />
									</spring:select>
								</div>

								<div class="form-group">
									<label>Domain:</label>
									<spring:select path="domain.domainName" class="form-control">
										<spring:option value="NONE"
											label="-------- Select Domain --------"></spring:option>
										<spring:options items="${requestScope.domains}" />
									</spring:select>
								</div>
								<div style="float: right;">
									<input type="submit" class="btn btn-primary"
										value="Save Profile" />
								</div>
							</div>

							<div class="col-md-6">

								<div class=form-group>
									<span>&nbsp;</span>
								</div>
								<div class="form-group">
									<label>Position:</label>
									<spring:select path="currentPosition.positionTitle"
										class="form-control">
										<spring:option value="NONE"
											label="-------- Select Position --------"></spring:option>
										<spring:options items="${requestScope.positions}" />
									</spring:select>
								</div>
							</div>
						</div>
					</div>

				</spring:form>
			</div>

			<!-- /.col-->
		</div>
		<!-- /.row -->

	</div>
	<!--/.main-->

	<script src="resources/js/jquery-1.11.1.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/chart.min.js"></script>
	<script src="resources/js/chart-data.js"></script>
	<script src="resources/js/easypiechart.js"></script>
	<script src="resources/js/easypiechart-data.js"></script>
	<script src="resources/js/bootstrap-datepicker.js"></script>
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
	</script>
</body>

</html>
