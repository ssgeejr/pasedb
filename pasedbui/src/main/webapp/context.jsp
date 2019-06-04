<!DOCTYPE html>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="content-type">
<title>Parental Alienation Support & Education</title>
<link href="menu.css" media="all" rel="stylesheet" type="text/css">
<%@ include file="meta.htm"%>
<!--
<% 
	// new org.pasedb.pasedbui.Counter(request);
%>
-->
<%@ page
		language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		%>
<%!
	String[] context ={"Parental Alienation","Parental Rights","In the Media","Court Opinions","Support Groups","Comments"};
%>
<%
	int cntx = 0;
	try{cntx = Integer.parseInt(request.getParameter("id").toString());}catch(Exception ex){}
%>


<style type="text/css">
.auto-style5 {
	font-size: 8pt;
	color: #FF0000;
}
.auto-style7 {
	color: #FF0000;
	font-size: large;
	text-align: center;
}
</style>
</head>
<body>
	<div align="center">
	<table style="border: 1px solid #AAAAAA;width: 900px">
		<tr>
			<td>
				<%@ include file="banner.htm"%>
			</td>
		</tr>
		<tr>
		<td>
		<%@ include file="menu.htm"%></td>
		</tr>
		<tr>
		<td class="auto-style7">
		<strong>OFFICIAL RELEASE JUNE 22, 2019</strong>
		<br>
		<%= context[cntx] %>
		</td>
		</tr>
		<tr>
		<td>
<div align="center">
		<strong><span class="auto-style5">VALIDATION TESTING underway - site 
		may (or MAY NOT) load correctly</span></strong></div>
</td>
		</tr>
		<tr>
			<td>
				<table style="width: 100%">
					<tr>
						<td style="text-align: left">THIS IS WHERE THE LOADED 
						LINKS WILL APPEAR BASED ON CONTEXT ... <br>
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
