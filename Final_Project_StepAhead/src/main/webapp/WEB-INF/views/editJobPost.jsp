<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>StepAhead - My Job Posts!</title>

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
				<li class="active">Edit Job Post</li>
			</ol>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Edit Job Post</h1>

				<div class="panel panel-default">
					<div class="panel-heading">Job Post</div>
					<div class="panel-body">
						<spring:form action="editJobPost.htm" method="POST"
							commandName="job">
							<div class="col-md-6">
								<div class="form-group">
									<label>Job Title:</label>
									<spring:input path="jobTitle" id="jobTitle"
										class="form-control" auto-focus="" />
									<span>&nbsp;</span>
								</div>
								<div class="form-group">
									<label>Company:</label>
									<spring:select path="company.companyName" class="form-control"
										disabled="true">
										<spring:option value="NONE"
											label="-------- Select Company --------"></spring:option>
										<spring:options items="${requestScope.companies}" />
									</spring:select>
								</div>
								<div class="form-group">
									<label>Responsibilities:</label>
									<spring:textarea path="jobResponsibilities" rows="14"
										id="jobResponsibilities" class="form-control" />
								</div>
								<div class="form-group">
									<label><spring:checkbox path="isActive" /> &nbsp;Is
										Active?</label>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Job Location:</label>
									<spring:input path="jobLocation" id="jobLocation"
										class="form-control" />
									<span style="font-style: italic;">Eg.: New York, NY;
										Boston, MA</span>
								</div>
								<div class="form-group">
									<label>Domain:</label>
									<spring:select path="domain.domainName" class="form-control"
										id="selDomain">
										<spring:option value="NONE"
											label="-------- Select Domain --------"></spring:option>
										<spring:options items="${requestScope.domains}" />
									</spring:select>
								</div>
								<div class="form-group">
									<label>Job Package:</label>
									<spring:select path="jobPackage.packageId" class="form-control"
										id="selPackage">
										<spring:option value="0"
											label="-------- Select Package --------"></spring:option>
										<c:forEach var="pc" items="${requestScope.packages}">
											<spring:option value="${pc.packageId}">
												<c:out value="${pc}"></c:out>
											</spring:option>
										</c:forEach>
										<!-- <spring:options items="${requestScope.packages}" /> -->
									</spring:select>
								</div>
								<div class="form-group">
									<label>Skills:</label>
									<spring:textarea path="skills" class="form-control" rows="4"
										id="skills" />
								</div>
								<div class="form-group">
									<label>Additional Skills:</label>
									<spring:textarea path="additionalSkills" class="form-control"
										rows="4" id="additionalSkills" />
								</div>
							</div>
							<input type="hidden" value="${job.jobId}" name="jobId" />
							<input type="hidden" value="edit" name="action" />
							<input type="submit" value="Update Job" id="btnUpdate"
								class="btn btn-primary" onclick="return updateJob();" />
						</spring:form>
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
	</script>

	<script type="text/javascript">
		function updateJob() {

			if ($("#jobTitle").val().trim() == ""
					|| $("#jobTitle").val().trim() == null) {
				alert("Please enter Job Title.");
				return false;
			}
			if ($("#jobLocation").val().trim() == ""
					|| $("#jobLocation").val().trim() == null) {
				alert("Please enter Job Location.");
				return false;
			}
			if ($("#jobResponsibilities").val().trim() == ""
					|| $("#jobResponsibilities").val().trim() == null) {
				alert("Please enter Job Responsibilities.");
				return false;
			}
			if ($("#skills").val().trim() == ""
					|| $("#skills").val().trim() == null) {
				alert("Please enter Skills.");
				return false;
			}
			if ($('#selDomain').val() == "NONE") {
				alert("Please select Domain.");
				return false;
			}
			if ($('#selPackage').val() == 0) {
				alert("Please select Package.");
				return false;
			} else
				return true;
		}
	</script>
</body>

</html>