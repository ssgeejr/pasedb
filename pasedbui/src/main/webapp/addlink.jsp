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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"
	import="org.pasedb.pasedbui.*"%>
<%!
//--static
%>
<%
	StringBuffer bfr = new StringBuffer();
	try{
		String addurl = request.getParameter("addurl");
		if(addurl == null)
			System.out.println("do note save ...");
		else{
			System.out.println("... SAVE FORM VALUE ...");
			System.out.println(": " + request.getParameter("url"));
			System.out.println(": " + request.getParameter("comment"));		
	    	ArrayList<Integer> tags = new ArrayList<Integer>();
	    	tags.add(0);
	    	int tagval = -1;
			try{tagval = Integer.parseInt(request.getParameter("_cbpr"));tags.add(tagval);}catch(Exception ex){}
			try{tagval = Integer.parseInt(request.getParameter("_cbim"));tags.add(tagval);}catch(Exception ex){}
			try{tagval = Integer.parseInt(request.getParameter("_cbco"));tags.add(tagval);}catch(Exception ex){}
			try{tagval = Integer.parseInt(request.getParameter("_cbsg"));tags.add(tagval);}catch(Exception ex){}
			System.out.println("_cbpr: " + request.getParameter("_cbpr"));
			System.out.println("_cbim: " + request.getParameter("_cbim"));
			System.out.println("_cbco: " + request.getParameter("_cbco"));
			System.out.println("_cbsg: " + request.getParameter("_cbsg"));
			
			LinkItem li = new AddNewLink().fetchOGMetaData(request.getParameter("url"),request.getParameter("comment"),tags,-99);
			
			
			bfr.append(new HtmlEngine().generateTable(li));
			
//			bfr.append("<img src=\"" + li.getImgurl() + "\" height=\"" + li.getDisplayHeight() + "\"  width=\"" + li.getDisplayWidth() + "\"><br>");
//			bfr.append("<b>Title:</b>" + li.getTitle() + "<br>");
//			bfr.append("<b>Description:</b> " + li.getDescription() + "<br>");
 		}
	}catch(Exception ex){}
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

.auto-style1 {
	text-align: right;
}

table.customTable {
	width: 100%;
	background-color: #FFFFFF;
	border-collapse: collapse;
	border-width: 2px;
	border-color: #FF0000;
	border-style: solid;
	color: #000000;
}

table.customTable td, table.customTable th {
	padding: 1px;
}

table.customTable thead {
	background-color: #7EA8F8;
}

input[type=text], textarea, input[type=submit] {
	background-color: #ffffcc;
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
img { border-style: none; }
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
				<td class="auto-style7"><strong>OFFICIAL RELEASE JUNE
						22, 2019</strong> <br> LOGIN</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<strong><span class="auto-style5">VALIDATION
								TESTING underway - site may (or MAY NOT) load correctly</span></strong>
					</div>
				</td>
			</tr>
			<tr>
				<td>

					<form action="addlink.jsp" method="post">
						<div align="center">
							<div>
								<table style="width: 800px" class="customTable">
									<tr>
										<td>Add Link</td>
									</tr>
									<tr>
										<td><input name="_cbpr" type="checkbox" value="1" />Parental
											Rights <input name="_cbim" type="checkbox" value="2" />In the
											Media <input name="_cbco" type="checkbox" value="3" />Court
											Opinions <input name="_cbsg" type="checkbox" value="4" />Support</td>
									</tr>
									<tr>
										<td>
											<table style="width: 100%">
												<tr style="vertical-align: top">
													<td style="width: 125px" class="auto-style1">URL</td>
													<td><input name="url" style="width: 99%;" type="text" />&nbsp;</td>
												</tr>
												<tr style="vertical-align: top">
													<td style="width: 125px" class="auto-style1">Comments</td>
													<td><textarea name="comment" rows="3"
															style="width: 99%;"></textarea>&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td class="auto-style1"><input name="addurl"
											style="width: 148px; height: 28px" type="submit"
											value="Add Link" />&nbsp;</td>
									</tr>
								</table>
<br>
								<table class="links">
									<%=bfr.toString()%>
								</table>
							</div>
						</div>
					</form>
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
