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
	<!-- Google tag (gtag.js) -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=G-KZT7Y4P7RQ"></script>
	<script>
	window.dataLayer = window.dataLayer || [];
	function gtag(){dataLayer.push(arguments);}
	gtag('js', new Date());

	gtag('config', 'G-KZT7Y4P7RQ');
	</script>

	<title>Parental Alienation Support &amp; Education Database</title>
	<link rel="canonical" href="http://pasedb.org/" />
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
    <!-- Google Tag Manager -->
    <script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
    new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
    j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
    'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
    })(window,document,'script','dataLayer','GTM-NZLZDTC');</script>
    <!-- End Google Tag Manager -->
    </script>

</head>
<body>
    <!-- Google Tag Manager (noscript) -->
    <noscript><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-NZLZDTC"
    height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
    <!-- End Google Tag Manager (noscript) -->
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
									<td style="text-align: left"><h1>What is
											Parental Alienation?</h1><br> Parental alienation is the
										process, and the result, of psychological manipulation of a
										child into showing unwarranted fear, disrespect or hostility
										towards a parent and/or other family members. It is a
										distinctive form of psychological abuse and family violence,
										towards both the child and the rejected family members, that
										occurs almost exclusively in association with family
										separation or divorce, particularly where legal action is
										involved. The most common cause is one parent wishing to
										exclude the other parent from the life of their child<br><br>
										<hr style="width: 100%"> <h2>Mission Statement:</h2><br> Provide a simple, centralized location to
										allow easy access to support and educational sites relating to
										Parental Alienation</td>
										<br>
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
