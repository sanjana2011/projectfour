<%@page import="in.co.rays.Proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.Proj4.bean.CartBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.Proj4.util.DataUtility"%>
<%@page import="in.co.rays.Proj4.util.ServletUtility"%>
<%@page import="in.co.rays.Proj4.controller.CartListCtl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart List View</title>
</head>
<body>
    <%@include file="Header.jsp"%>

    <jsp:useBean id="bean" class="in.co.rays.Proj4.bean.CartBean"
        scope="request"></jsp:useBean>

    <div align="center">
        <h1 align="center" style="margin-bottom: -15; color: navy;">Cart
            List</h1>

        <div style="height: 15px; margin-bottom: 12px">
            <h3>
                <font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
            </h3>
            <h3>
                <font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
            </h3>
        </div>

        <form action="<%=ORSView.CART_LIST_CTL%>" method="post">
            <%
                Object nextObj = request.getAttribute("nextListSize");
                int nextListSize = (nextObj != null) ? DataUtility.getInt(nextObj.toString()) : 0;

                List<CartBean> clist = (List<CartBean>) request.getAttribute("clist");
                List<CartBean> list = (List<CartBean>) ServletUtility.getList(request);

                int pageNo = ServletUtility.getPageNo(request);
                int pageSize = ServletUtility.getPageSize(request);
                int index = ((pageNo - 1) * pageSize) + 1;
                
                
            %>

            <input type="hidden" name="pageNo" value="<%=pageNo%>">
            <input type="hidden" name="pageSize" value="<%=pageSize%>">

            <table style="width: 100%">
                <tr>
                    <td align="center">
                        <label><b>First Name :</b></label>
                        <input type="text" name="customerName" placeholder="Enter customerName"
                            value="<%=ServletUtility.getParameter("customerName", request)%>">&emsp;

                        <label><b>TransactionDate:</b></label>
                        <input type="text" name="transactionDate" placeholder="Enter transactionDate"
                            value="<%=ServletUtility.getParameter("transactionDate", request)%>">&emsp;

                        <%-- <label><b>Product : </b></label>
                        <%=HTMLUtility.getList("product", String.valueOf(bean.getProduct()), clist)%>&emsp; --%>
                        
 <label><b>Product : </b></label>
<%=HTMLUtility.getList("product", String.valueOf(bean.getProduct()), (HashMap<String, String>) request.getAttribute("productList"))%>

                        <input type="submit" name="operation" value="<%=CartListCtl.OP_SEARCH%>"> &nbsp;
                        <input type="submit" name="operation" value="<%=CartListCtl.OP_RESET%>">
                    </td>
                </tr>
            </table>
            <br>

            <table border="1" style="width: 100%; border: groove;">
                <tr style="background-color: #e1e6f1e3;">
                    <th width="5%"><input type="checkbox" id="selectall" /></th>
                    <th width="5%">S.No</th>
                    <th width="13%">First Name</th>
                    <th width="13%">Product</th>
                    <th width="23%">TransactionDate</th>
                    <th width="10%">Quantity</th>
                    <th width="5%">Edit</th>
                </tr>

                <%
                    if (list != null && !list.isEmpty()) {
                        Iterator<CartBean> it = list.iterator();
                        while (it.hasNext()) {
                            CartBean rowBean = it.next();
                            if (rowBean != null) {
                %>
                <tr>
                    <td style="text-align: center;">
                        <input type="checkbox" class="case" name="ids" value="<%=rowBean.getId()%>">
                    </td>
                    <td style="text-align: center;"><%=index++%></td>
                    <td style="text-align: center; text-transform: capitalize;"><%=rowBean.getCustomerName()%></td>
                    <td style="text-align: center; text-transform: capitalize;"><%=rowBean.getProduct()%></td>
                    <td style="text-align: center; text-transform: lowercase;"><%=rowBean.getTransactionDate()%></td>
                    <td style="text-align: center;"><%=rowBean.getQuantity()%></td>
                    <td style="text-align: center;"><a href="CartCtl?id=<%=rowBean.getId()%>">Edit</a></td>
                </tr>
                <%
                            }
                        }
                    } else {
                %>
                <tr>
                    <td colspan="7" style="text-align:center; color:red;">
                        No records found
                    </td>
                </tr>
                <%
                    }
                %>
            </table>

            <table style="width: 100%">
                <tr>
                    <td style="width: 25%">
                        <input type="submit" name="operation" value="<%=CartListCtl.OP_PREVIOUS%>"
                            <%=pageNo > 1 ? "" : "disabled"%>>
                    </td>
                    <td align="center" style="width: 25%">
                        <input type="submit" name="operation" value="<%=CartListCtl.OP_NEW%>">
                    </td>
                    <td align="center" style="width: 25%">
                        <input type="submit" name="operation" value="<%=CartListCtl.OP_DELETE%>">
                    </td>
                    <td style="width: 25%" align="right">
                        <input type="submit" name="operation" value="<%=CartListCtl.OP_NEXT%>"
                            <%=nextListSize != 0 ? "" : "disabled"%>>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
