<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="org.pasedb.pasedbui.*"%>
<%
String data = "";
try {
	data = new HitCounter().getHitCounts();
} catch (Exception ex) {
	ex.printStackTrace();
}
%>

<!DOCTYPE html>
<html>
<title>PASEDB Hit Counter (UTC)</title>
<body>
	<div align="center">
		<table style="width: 400pt;"  border="1">
			<tbody>
				<tr>
					<td style="text-align: center;"><strong>&nbsp;HIT
							COUNTER (UTC)</strong></td>
				</tr>
				<tr>
					<td>
						<table style="width: 100%;">
							<tbody>
								<tr>
									<td style="text-align: right;">Date&nbsp;&nbsp;</td>
									<td>&nbsp;Count</td>
								</tr>
								<%= data %>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
