﻿<!DOCTYPE html>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="content-type">
<title>Parental Alienation Support & Education</title>
<link href="menu.css" media="all" rel="stylesheet" type="text/css">

<%
	new org.pasedb.pasedbui.Counter(request);
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!%>
<%
	
%>


<style type="text/css">
.auto-style5 {
	color: #FF0000;
}

.auto-style7 {
	color: #FF0000;
	text-align: center;
}

.links {
	width: 800px;
	border: 1px solid #0000FF;
	border-collapse: collapse;
	padding: 3px;
}

.links td {
	padding: 3px;
}

.links tbody {
	border: 1px solid #0000FF;
	padding: 3px;
}

img {
	border-style: none;
}
</style>
</head>
<body>
	<div align="center">
		<table style="border: 1px solid #AAAAAA; width: 900px">
			<tr>
				<td><%@ include file="banner.htm"%></td>
			</tr>
			<tr>
				<td><%@ include file="menu.htm"%></td>
			</tr>
			<tr>
				<td>
					<table style="width: 100%">
						<tr>
							<td style="text-align: left">
								<div align="center">
									<table class="links" style="width: 300px">
										<tr>
											<td>Username:</td>
											<td><input type="text" id="un"></td>
										</tr>
										<tr>
											<td>Password:</td>
											<td><input type="text" id="un"></td>
										</tr>
										<tr>
											<td colspan="2" align="right"><input type="submit"
												name="login" value="Login"></td>
										</tr>
										<tr>
											<td colspan="2" align="center">sign-up | forgot password
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<!--
				<center>
					<%@ include file="footer.htm"%>
					</center>
					-->
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
