<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>StepAhead - Applications!</title>

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
			<li class="active"><a href="/stepahead/myPostedJobs.htm"><svg
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
				<li class="active">Applications</li>
			</ol>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Applications</h1>
				<c:if test="${requestScope.profileSaved == 'true'}">
					<div class="alert bg-success" role="alert"
						style="background-color: #8ad919;">
						<svg class="glyph stroked checkmark"> <use
							xlink:href="#stroked-checkmark"></svg>
						Job Seeker Profile is saved successfully!
					</div>
				</c:if>
				<c:if test="${requestScope.profileSaved == 'false'}">
					<div class="alert bg-danger" role="alert"
						style="background-color: #f9243f;">
						<svg class="glyph stroked checkmark"> <use
							xlink:href="#stroked-checkmark"></svg>
						Job Seeker Profile is not saved successfully!
					</div>
				</c:if>
				<c:if test="${requestScope.alreadySaved == 'true'}">
					<div class="alert bg-warning" role="alert"
						style="background-color: #ffb53e;">
						<svg class="glyph stroked checkmark"> <use
							xlink:href="#stroked-checkmark"></svg>
						Job Seeker Profile is already saved successfully!
					</div>
				</c:if>
				<div class="panel-heading">Job Post</div>
				<div class="panel-body">
					<div class="col-md-4">
						<div class="form-group">
							<label>Job Id:</label> ${job.jobId}
						</div>

					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label>Job Title:</label> ${job.jobTitle}
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label>Job Location:</label> ${job.jobLocation}
						</div>
					</div>
				</div>


				<div class="panel panel-default">
					<div class="panel-heading">Applications</div>
					<div class="panel-body">
						<form action="saveProfile.htm" method="POST">
							<table data-toggle="table" data-pagination="true">
								<thead>
									<tr>
										<th data-width="175">First Name</th>
										<th data-width="175">Last Name</th>
										<th data-width="175">Current Position</th>
										<th data-width="175">Contact</th>
										<th>Resume</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="jobSeeker" items="${requestScope.applicants}">
										<tr id="${jobSeeker.personId}">
											<td><c:out value="${jobSeeker.firstName}"></c:out></td>
											<td><c:out value="${jobSeeker.lastName}"></c:out></td>
											<td><c:out
													value="${jobSeeker.currentPosition.positionTitle}"></c:out></td>
											<td><c:out value="${jobSeeker.emailId}"></c:out></td>
											<td><a
												href="/stepahead/viewResume/${jobSeeker.personId}"
												target="_blank" class="btn"><svg
														class="glyph stroked clipboard with paper"
														style="height: 1.42em; width: 1.42em; padding-right: 5px;">
													<use xlink:href="#stroked-clipboard-with-paper" /></svg>${jobSeeker.firstName}_${jobSeeker.lastName}_Resume</a></td>
											<td>
												<button type="submit" id="save${jobSeeker.personId}"
													onclick="return saveProfile(${jobSeeker.personId})"
													class="btn btn-link">
													<svg class="glyph stroked star"
														style="height: 1.42em; width: 1.42em; padding-right: 5px;">
													<use xlink:href="#stroked-star" /></svg>
													Save Profile
												</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<input type="hidden" value="saveProfile" name="action"
								id="action" /> <input type="hidden" name="personId"
								id="personId" value="" />
						</form>
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
		function saveProfile(personId) {
			$("#personId").val(personId);
			//alert($("#personId").val());
		}
	</script>
</body>

</html>