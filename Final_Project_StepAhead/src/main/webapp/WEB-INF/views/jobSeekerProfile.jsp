<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
					action="jobSeekerProfile.htm" method="POST"
					enctype="multipart/form-data">
					<div class="panel panel-default">
						<div class="panel-heading">Personal Details</div>
						<div class="panel-body">

							<div class="col-md-6">

								<div class="form-group">
									<label>First Name:</label>
									<spring:input path="firstName" class="form-control"
										placeholder="Placeholder" id="firstName" />
								</div>

								<div class="form-group">
									Phone Number:
									<spring:input path="contact" class="form-control" id="contact" />
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
										class="form-control" id="address1" />
								</div>

								<div class="form-group">
									City:
									<spring:input path="address.city" class="form-control"
										id="city" />
								</div>

								<div class="form-group">
									Zip Code:
									<spring:input path="address.zipCode" class="form-control"
										id="zipCode" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Last Name:</label>
									<spring:input path="lastName" class="form-control"
										id="lastName" />
								</div>

								<div class="form-group">
									Email Id:
									<spring:input path="emailId" class="form-control"
										disabled="true" id="emailId" />
								</div>
								<div class="form-group">
									<span>&nbsp;</span>
								</div>

								<div class="form-group">
									<label>Address 2:</label>
									<spring:textarea path="address.address2" rows="1"
										class="form-control" id="address2" />
								</div>

								<div class="form-group">
									State:
									<spring:input path="address.state" class="form-control"
										id="state" />
								</div>

								<div class="form-group">
									<span>&nbsp;</span>
								</div>
							</div>

						</div>

						<div class="panel-heading">Professional Details</div>
						<div class="panel-body">

							<div class="col-md-6">
								<div class="form-group">
									<label>Position:</label>
									<spring:select path="currentPosition.positionTitle"
										class="form-control" id="selPosition">
										<spring:option value="NONE"
											label="-------- Select Position --------"></spring:option>
										<spring:options items="${requestScope.positions}" />
									</spring:select>
								</div>
								<div class="form-group">
									<label>Resume:</label>
									<c:choose>
										<c:when test="${empty loggedInUser.resume}">
											<spring:input path="resumeFile" type="file" id="resumeFile" />
										</c:when>
										<c:otherwise>
											${loggedInUser.resume}
										</c:otherwise>
									</c:choose>
								</div>
								<div class="form-group">
									<span>&nbsp;</span>
								</div>
								<div style="float: right;">
									<input type="submit" class="btn btn-primary"
										value="Save Profile" onclick="return saveProfile();" />
									<!-- onclick="return saveProfile();" -->
								</div>
							</div>

							<div class="col-md-6">
								<div class="form-group">
									<label>Keywords:</label>
									<spring:textarea path="keywords" id="keywords" rows="3"
										class="form-control"></spring:textarea>
								</div>

								<div class="form-group">
									<span>&nbsp;</span>
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
	<script type="text/javascript">
		function saveProfile() {
			if ($("#firstName").val().trim() == ""
					|| $("#firstName").val().trim() == null) {
				alert("Please enter First Name.");
				return false;
			}
			if ($("#lastName").val().trim() == ""
					|| $("#lastName").val().trim() == null) {
				alert("Please enter Last Name.");
				return false;
			}
			if ($("#contact").val().trim() == ""
					|| $("#contact").val().trim() == null) {
				alert("Please enter contact.");
				return false;
			}
			if ($("#emailId").val().trim() == ""
					|| $("#emailId").val().trim() == null) {
				alert("Please enter EmailId.");
				return false;
			}
			if ($("#address1").val().trim() == ""
					|| $("#address1").val().trim() == null) {
				alert("Please enter Address 1.");
				return false;
			}
			if ($("#address2").val().trim() == ""
					|| $("#address2").val().trim() == null) {
				alert("Please enter Address 2.");
				return false;
			}
			if ($("#city").val().trim() == ""
					|| $("#city").val().trim() == null) {
				alert("Please enter City.");
				return false;
			}
			if ($("#state").val().trim() == ""
					|| $("#state").val().trim() == null) {
				alert("Please enter State.");
				return false;
			}
			if ($("#zipCode").val().trim() == ""
					|| $("#zipCode").val().trim() == null) {
				alert("Please enter Zip Code.");
				return false;
			}
			if ($("#selPosition").val() == "NONE") {
				alert("Please Select Position.");
				return false;
			}
			if ($("#resumeFile").val().trim() == ""
					|| $("#resumeFile").val().trim() == null) {
				alert("Please select File to upload.");
				return false;
			}
			if ($("#keywords").val().trim() == ""
					|| $("#keywords").val().trim() == null) {
				alert("Please enter keywords.");
				return false;
			}

			if (!checkMimeType()) {
				alert("Please upload a PDF file only.");
				return false;
			}

			return true;

		}

		function checkMimeType() {
			var file = $('#resumeFile').val();
			var exts = [ 'pdf' ];
			// first check if file field has any value
			if (file) {
				// split file name at dot
				var get_ext = file.split('.');
				// reverse name to check extension
				get_ext = get_ext.reverse();
				// check file type is valid as given in 'exts' array
				if ($.inArray(get_ext[0].toLowerCase(), exts) > -1) {
					//alert( 'Allowed extension!' );
					return true;
				} else {
					//alert( 'Invalid file!' );
					return false;
				}
			}
		}
	</script>
</body>
</html>