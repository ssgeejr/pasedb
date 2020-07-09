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
								<td style="text-align: Center;color:red;"><b>
								Change Request:<br></b>Add [next][previous] 
								nagivation, add search, add "highlight" link on 
								index&nbsp;<br>
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
					<table width="100%" border="1">
						<tr>
						<td>
						Facebook Groups
						</td>
						</tr>
						<tr>
						<td>
							<ul>
								<li><a href="https://www.facebook.com/groups/alliancetosolveparentalalienation/">Alliance to Solve Parental Alienation</a>: This is a Facebook group led by Dr. Childress  and Dorcy Pruter for the discussion of the various solution pathways for the pathology of "parental alienation" (AB-PA).</li>
								<li><a href="https://www.facebook.com/groups/pasolution/">Parental Alienation Support and Information Solution</a>:  Our mission is for members to create reunifications. Love is the answer. We are survivors of parental alienation: parents, children, and family. We also welcome supporters bringing awareness.</li>
								<li><a href="https://www.facebook.com/groups/wendyperrysupportgroup/">Parental Alienation Support And Education - North Texas and Worldwide</a>: Our mission is to create awareness, education and support for the public and professionals about the signs, remedies and prevention of parental alienation.</li>
							</ul>
						</td>
						</tr>
					</table>				
				</td>				
				</tr>
				<tr>
				<td>
				<div align="center">
				<b>Last 5 Posts</b>
				</div>
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
