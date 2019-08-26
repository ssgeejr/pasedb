<%@ include file="meta.htm"%>
<%
	new org.pasedb.pasedbui.Counter(request);
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="org.pasedb.pasedbui.*"%>
<%!String[] context = { "Parental Alienation", "Parental Rights", "In the Media", "Court/Legal", "Support Groups",
			"Comments", "Books/Reading" };%>
<%
	StringBuffer lnks = new StringBuffer();
	try {
		lnks.append(new HtmlEngine().generateTable(new ElicitEngine().getLinks(0,5)));
	} catch (Exception ex) {
		ex.printStackTrace();
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Parental Alienation Support &amp; Education</title>
<link href="menu.css" media="all" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="pasedb.css">
</head>
<body>
	<style type="text/css">
.auto-style5 {
	font-size: 8pt;
	color: #FF0000;
}

.auto-style6 {
	color: #FF0000;
}

.auto-style7 {
	color: #FF0000;
	font-size: large;
	text-align: center;
}
</style>
	<div align="center">
		<table style="border: 1px solid #AAAAAA; width: 900px">
			<tbody>
				<tr>
					<td><%@ include file="banner.htm"%></td>
				</tr>
				<tr>
					<td><%@ include file="menu.htm"%></td>
				</tr>
				<tr>
					<td>
						<table style="width: 100%">
							<tbody>
							<tr>
								<td style="text-align: Center;color:red;"><b>New features coming later in August</b>
								<br>Historical navigation (see more than 25 articles), view latest article added date/time and quick view most recent articles
								<br>&nbsp;<br>
								</td>
							</tr>
								<tr>
									<td style="text-align: left"><strong>What is
											Parental Alienation?</strong><br> Parental alienation is the
										process, and the result, of psychological manipulation of a
										child into showing unwarranted fear, disrespect or hostility
										towards a parent and/or other family members. It is a
										distinctive form of psychological abuse and family violence,
										towards both the child and the rejected family members, that
										occurs almost exclusively in association with family
										separation or divorce, particularly where legal action is
										involved. The most common cause is one parent wishing to
										exclude the other parent from the life of their child<br>
										<hr style="width: 100%"> <strong>Mission
											Statement:</strong><br> Provide a simple, centralized location to
										allow easy access to support and educational sites relating to
										Parental Alienation</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<hr style="width: 100%">
						<table class="links">
							<%=lnks.toString()%>
						</table>	
					</td>
				</tr>
				<tr>
					<td>
						<!--
				<center>					<%@ include file="footer.htm"%>					</center>					--> <br>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
