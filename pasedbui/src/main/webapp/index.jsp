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
	ResponseItem ri = new ResponseItem();
	try {
		ri = new ElicitEngine().getLinks(0,0);
		lnks.append(ri.getHtml());
	} catch (Exception ex) {
		ex.printStackTrace();
	}
%>

<!DOCTYPE html>
<html>
<head>
	<title>Parental Alienation Support &amp; Education Database</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<meta http-equiv="content-language" content="en">
	<meta name="title" content="Parental Alienation Support & Education Database" />
	<meta name="description" content="Crowdsourced links for Parental Alienation: support, education, references, stories, comments, links, legal cases, court rulings and more ..."">
	<meta name="robots" content="index, follow">
	<meta name="keywords" content="parental alienation, dmv-v, parental rights, alienation, mad mother syndrome, mad mother, parental alienation syndrome, dr chidress, pas, pa, parental alienation tactics, prevent parental alienation, child abuse" />
	<meta name="author" content="PASEDB Team">
	<meta property="og:title" content="Parental Alienation Support & Education Database" />
	<meta property="og:type" content="article" />
	<meta property="og:url" content="http://pasedb.org/" />
	<meta property="og:image" content="http://pasedb.org/ogimg/logo-pasedb_sm.jpg" />
	<meta property="og:description" content="Crowdsourced links for Parental Alienation: support, education, references, stories, comments, links, legal cases, court rulings and more ..." />
	<meta property="og:site_name" content="PASEDB" />
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
						<hr style="width: 100%">
						<table style="width: 100%" class="links">
							<%=lnks.toString()%>
						</table>	
					</td>
				</tr>
				<tr>
					<td>
					<center><%= ri.getPrev() %> <%= ri.getNext() %></center>
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
