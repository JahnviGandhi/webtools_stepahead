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
<title>StepAhead - View Job!</title>
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
			<li><a href="/stepahead/myResume.pdf" target="_blank"><svg
						class="glyph stroked clipboard with paper"> <use
						xlink:href="#stroked-clipboard-with-paper" /></svg> Resume </a></li>
			<li><a href="/stepahead/myJobs.htm"><svg
						class="glyph stroked checkmark"> <use
						xlink:href="#stroked-checkmark" /></svg> My Jobs </a></li>
			<li class="disabled"><a href="#"><svg
						class="glyph stroked star"> <use xlink:href="#stroked-star" /></svg>
					My Favorites</a></li>
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
				<li class="active">View Job</li>
			</ol>
		</div>
		<!--/.row-->


		<div class="panel-body">
			<c:if test="${requestScope.applied == 'true'}">
				<div class="alert bg-success" role="alert"
					style="background-color: #8ad919;">
					<svg class="glyph stroked checkmark"> <use
						xlink:href="#stroked-checkmark"></svg>
					Your Application is sent successfully!
				</div>
			</c:if>
			<c:if test="${requestScope.applied == 'false'}">
				<div class="alert bg-danger" role="alert"
					style="background-color: #f9243f;">
					<svg class="glyph stroked cancel"> <use
						xlink:href="#stroked-cancel"></svg>
					Your Application is not sent successfully!
				</div>
			</c:if>
			<c:choose>
				<c:when test="${requestScope.jobFound == 'false'}">
					<div class="form-group">
						<label>No Such Job Found!</label>
					</div>
				</c:when>
				<c:otherwise>
					<form action="applyForJob.htm" method="POST">

						<div class="panel panel-info">
							<div class="panel-heading">
								${requestScope.job.jobTitle}
								<p style="float: right;">
									<svg class="glyph stroked clock"
										style="height: 1.42857em; width: 1.42857em; padding-right: 5px;">
									<use xlink:href="#stroked-clock" /></svg>${job.postedDate}</p>
							</div>
							<div class="panel-body">
								<div class="form-group">
									<label>Company:</label> ${requestScope.job.company.companyName}
								</div>
								<div class="form-group">
									<label>Location:</label> ${requestScope.job.jobLocation}
								</div>
								<div class="form-group">
									<label>Position:</label>
									${requestScope.job.jobPackage.position.positionTitle}
								</div>
								<div class="form-group">
									<label>Package:</label>
									$&nbsp;${requestScope.job.jobPackage.minSal} -
									$&nbsp;${requestScope.job.jobPackage.maxSal}
								</div>
								<div class="form-group">
									<label>Responsibilities:</label>
									<p>${requestScope.job.jobResponsibilities}</p>
								</div>
								<div class="form-group">
									<label>Skills:</label>
									<p>${requestScope.job.skills}</p>
								</div>
								<div class="form-group">
									<label>Additional Skills:</label>
									<p>${requestScope.job.additionalSkills}</p>
								</div>
								<div class="form-group" style="float: right;">
									<c:choose>
										<c:when test="${requestScope.applied == 'true'}">
											<button type="submit" name="btnApply" class="btn btn-primary"
												disabled="disabled">
												<svg class="glyph stroked checkmark"
													style="height: 2em; width: 2em; padding-right: 5px; ">
												<use xlink:href="#stroked-checkmark" /></svg>
												Apply
											</button>
										</c:when>
										<c:otherwise>
											<button type="submit" name="btnApply" class="btn btn-primary">
												<svg class="glyph stroked checkmark"
													style="height: 2em; width: 2em; padding-right: 5px; ">
												<use xlink:href="#stroked-checkmark" /></svg>
												Apply
											</button>
										</c:otherwise>
									</c:choose>
									<a href="#" name="btnSave" class="btn btn-primary disabled">
										<svg class="glyph stroked download"
											style="height: 2em; width: 2em; padding-right: 5px;"> <use
											xlink:href="#stroked-download" /></svg>Save Description
									</a>
								</div>
							</div>
							<input type="hidden" value="apply" name="action" id="action" />
							<input type="hidden" value="${job.jobId}" name="jobId" id="jobId" />
						</div>

					</form>
				</c:otherwise>
			</c:choose>
		</div>

		<div class="row">
			<div class="col-lg-12"></div>

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
	</script>
</body>
</html>