<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>StepAhead - Email Verified!</title>
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
			<c:if test="${requestScope.verified == 'true'}">
				<div class="alert bg-success" role="alert"
					style="background-color: #8ad919;">
					<svg class="glyph stroked checkmark"> <use
						xlink:href="#stroked-checkmark"></svg>
					Your Email Id has been verified!
				</div>
			</c:if>
			<div class="panel-body">
				You can now <a href="/stepahead/">Step Ahead</a> by logging in.
			</div>
		</div>
	</div>
</body>
</html>