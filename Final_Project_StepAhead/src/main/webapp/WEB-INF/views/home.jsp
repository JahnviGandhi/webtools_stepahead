<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
<%@ page session="false"%>
<html>
<head>
<title>StepAhead - Log In!</title>
<link rel="stylesheet" type="text/css"
	href="resources/styles/styles.css" />
<link rel="stylesheet" type="text/css"
	href="resources/styles/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="resources/styles/datepicker3.css" />
<link rel="shortcut icon" type="image/x-icon"
	href="resources/images/favicon.ico" />

<!--Icons-->
<script src="resources/js/lumino.glyphs.js"></script>

</head>
<body>
	<div class="row">
		<div
			class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					Log in
					<!--  <div style="float: right; height: 100%; width: 100%;"> -->
					<img src="resources/images/StepAhead_Logo.jpg" alt="StepAhead Logo"
						style="height: 100%; float: right;" />
				</div>
				<div class="panel-body">
					<spring:form commandName="user" role="form" method="POST"
						action="login.htm">
						<fieldset>
							<div class="form-group">
								User Name:
								<spring:input path="userName" class="form-control" autofocus=""
									id="uname" />
							</div>
							<div class="alert bg-danger" role="alert"
								style="background-color: #f9243f; display: none;"
								id="errorUname">
								<svg class="glyph stroked cancel">
									<use xlink:href="#stroked-cancel">
								</svg>
								Please enter User Name. <a class="pull-right" href="/stepahead/">
									<span class="glyphicon glyphicon-remove"></span>
								</a>
							</div>
							<div class="form-group">
								Password:
								<spring:password path="password" class="form-control" id="pwd" />
							</div>
							<div class="alert bg-danger" role="alert"
								style="background-color: #f9243f; display: none;"
								id="errorUname" id="errorPwd">
								<svg class="glyph stroked cancel">
									<use xlink:href="#stroked-cancel">
								</svg>
								Please enter Password. <a class="pull-right" href="/stepahead/">
									<span class="glyphicon glyphicon-remove"></span>
								</a>
							</div>
							<c:choose>
								<c:when
									test="${requestScope.verified == 'false' || requestScope.authenticated == 'false'}">
									<input type="submit" value="Log In!" name="btnSubmit"
										class="btn btn-primary" disabled="disabled"
										onclick="return false;" />
								</c:when>
								<c:otherwise>
									<input type="submit" value="Log In!" name="btnSubmit"
										class="btn btn-primary" onclick="return checkInput();" />
								</c:otherwise>
							</c:choose>
							<a href="/stepahead/signup.htm" class="btn btn-primary">Sign
								Up!</a>
						</fieldset>
					</spring:form>
				</div>

			</div>
			<c:if test="${requestScope.verificationSent == 'true'}">
				<div class="alert bg-warning" role="alert"
					style="background-color: #ffb53e;">
					<svg class="glyph stroked checkmark">
							<use xlink:href="#stroked-checkmark">
							</svg>
					Verification Link is sent to your EmailId.
				</div>
			</c:if>
			<c:if test="${requestScope.authenticated == 'false'}">
				<div class="alert bg-danger" role="alert"
					style="background-color: #f9243f;">
					<svg class="glyph stroked cancel">
							<use xlink:href="#stroked-cancel">
							</svg>
					Username/Password is not valid. Please try again. <a
						class="pull-right" href="/stepahead/"> <span
						class="glyphicon glyphicon-remove"></span>
					</a>
				</div>
			</c:if>
			<c:if test="${requestScope.verified == 'false'}">
				<div class="alert bg-danger" role="alert"
					style="background-color: #f9243f;">
					<svg class="glyph stroked flag">
							<use xlink:href="#stroked-flag">
							</svg>
					User Account is not verified. Please check your EmailId. <a
						class="pull-right" href="/stepahead/"> <span
						class="glyphicon glyphicon-remove"></span>
					</a>
				</div>
			</c:if>
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
	<script type="text/javascript" src="resources/js/login.js"></script>
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
