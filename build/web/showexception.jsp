<%-- 
    Document   : showexception
    Created on : 4 May, 2021, 12:16:58 PM
    Author     : adity
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Exception ex = (Exception)request.getAttribute("exception");
    System.out.println("Exception is : "+ex);
    System.out.println("Inside  Show Exception");
    out.println("Some Exception Occured : "+ex.getMessage());
 %>
