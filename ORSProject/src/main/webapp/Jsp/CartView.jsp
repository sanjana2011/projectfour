<%@page import="in.co.rays.Proj4.controller.CartCtl"%>
<%@page import="in.co.rays.Proj4.util.DataUtility"%>
<%@page import="in.co.rays.Proj4.util.ServletUtility"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart View</title>
</head>
<body>
<form action="<%=ORSView.CART_CTL%>" method="post">

		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.Proj4.bean.CartBean"
			scope="request"></jsp:useBean>

		<%
		HashMap map = (HashMap) request.getAttribute("map");
		%>

		<div align="center">
			<h1 align="center" style="margin-bottom: -15; color: navy">
				<%
					if (bean != null && bean.getId() > 0) {
				%>Update<%
					} else {
				%>Add<%
					}
				%>
				Cart
			</h1>

			<div style="height: 15px; margin-bottom: 12px">
				<H3 align="center">
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font>
				</H3>

				<H3 align="center">
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font>
				</H3>
			</div>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>
				<tr>
					<th align="left">Customer<span style="color: red">*</span></th>
					<td><input type="text" name="customerName"
						placeholder="Enter First Name"
						value="<%=DataUtility.getStringData(bean.getCustomerName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("customerName", request)%></font></td>
				</tr>
				<%-- <tr>
					<th align="left">Product<span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("map", bean.getProduct(), map);%></td>

					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("product", request)%></font></td>
				</tr> --%>
				<tr>
					<th align="left">TransactionDate<span style="color: red">*</span></th>
					<td><input type="text" name="login"
						placeholder="Enter Email ID"
						value="<%=DataUtility.getStringData(bean.getTransactionDate())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage(" transactionDate", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Quantity<span style="color: red">*</span></th>
					<td><input type="text" name="text"
						placeholder="Enter quantity"
						value="<%=DataUtility.getStringData(bean.getQuantity())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("quantity", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=CartCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=CartCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=CartCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=CartCtl.OP_RESET%>">
						<%
							}
						%>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>