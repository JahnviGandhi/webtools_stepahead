<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>StepAhead - Sign Up!</title>
<link rel="stylesheet" type="text/css"
	href="resources/styles/styles.css" />
<link rel="stylesheet" type="text/css"
	href="resources/styles/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="resources/styles/datepicker3.css" />
<link rel="shortcut icon" type="image/x-icon"
	href="resources/images/favicon.ico" />
</head>
<body>
	<div class="row">
		<div
			class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					Sign Up
					<!--  <div style="float: right; height: 100%; width: 100%;"> -->
					<img src="resources/images/StepAhead_Logo.jpg" alt="StepAhead Logo"
						style="height: 100%; float: right;" />

				</div>
				<div class="panel-body">
					<spring:form commandName="user" role="form" method="POST">
						<fieldset>
							<div class="radio">
								<label><input type="radio" id="radioJobSeeker"
									value="JobSeeker" checked="checked" name="radioType" /> Job
									Seeker </label> &nbsp;&nbsp;&nbsp; <label> <input type="radio"
									id="radioHrPerson" value="HrPerson" name="radioType" />
									Employer
								</label>
							</div>
							<br />
							<div class="form-group">
								First Name:
								<spring:input path="firstName" class="form-control" />

							</div>
							<div class="form-group has-error">
								<spring:errors path="firstName" class="form-control"
									placeholder="Error"></spring:errors>
							</div>
							<div class="form-group">
								Last Name:
								<spring:input path="lastName" class="form-control" />
							</div>
							<div class="form-group has-error">
								<spring:errors path="lastName" class="form-control"
									placeholder="Error"></spring:errors>
							</div>
							<div class="form-group">
								Gender:&nbsp;
								<spring:radiobutton path="gender" value="M" />
								Male &nbsp;&nbsp;&nbsp;
								<spring:radiobutton path="gender" value="F" />
								Female
							</div>
							<div class="form-group has-error">
								<spring:errors path="gender" class="form-control"
									placeholder="Error"></spring:errors>
							</div>
							<div class="form-group">
								Phone Number:
								<spring:input path="contact" class="form-control" />
							</div>
							<div class="form-group has-error">
								<spring:errors path="contact" class="form-control"
									placeholder="Error"></spring:errors>
							</div>
							<div class="form-group">
								Email Id:
								<spring:input path="emailId" class="form-control" />
							</div>
							<div class="form-group has-error">
								<spring:errors path="emailId" class="form-control"
									placeholder="Error"></spring:errors>
							</div>
							<br />
							<div class="form-group">
								User Name:
								<spring:input path="userName" class="form-control" />
							</div>
							<div class="form-group has-error">
								<spring:errors path="userName" class="form-control"
									placeholder="Error"></spring:errors>
							</div>
							<div class="form-group">
								Password:
								<spring:password path="password" class="form-control" />
							</div>
							<div class="form-group has-error">
								<spring:errors path="password" class="form-control"
									placeholder="Error"></spring:errors>
							</div>
							<input type="submit" value="Sign Up!" name="btnSubmit"
								class="btn btn-primary" />
							<!-- <a href="/stepahead/signup.htm" class="btn btn-primary">Sign
								Up!</a>  -->
						</fieldset>
					</spring:form>
				</div>
			</div>
		</div>
		<!-- /.col-->
	</div>
	<!-- /.row -->



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