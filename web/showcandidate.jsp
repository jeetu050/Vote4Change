<%-- 
    Document   : showcandidate
    Created on : 1 Jun, 2021, 11:57:01 PM
    Author     : adity
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,evoting.dto.CandidateInfo"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="jsscript/vote.js"></script>
    <script src="jsscript/jquery.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link href="stylesheet/backgroundimage.css" rel="stylesheet">
    <link href="stylesheet/pageheader.css" rel="stylesheet">
    <link href="stylesheet/showcandidate.css" rel="stylesheet">
    <title>Show Candidate</title>
</head>
<body>
    <%
        String userid=(String)session.getAttribute("userid");
        System.out.println("Inside SC.jsp");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
            StringBuffer displayBlock = new StringBuffer("");
            displayBlock.append("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+
                "<div class='subcandidate'>Whom Do you want to VOTE ?</div>"
                +"<div class='logout'><a href='login.html'>Logout</a></div>"
                +"</div><div class='buttons'>");
            ArrayList<CandidateInfo> candidateList = (ArrayList<CandidateInfo>)request.getAttribute("candidateList");
            
            for(CandidateInfo c:candidateList)
            {
                System.out.println("Inside SC.jsp FOR LOOP...");
                displayBlock.append("<input type='radio' name='flat' id='"+c.getCandidateId()+"' value='"+c.getCandidateId()+"' onclick='addvote()'>");
                displayBlock.append("<label for='"+c.getCandidateId()+"'><img src='data:image/jpg;base64,"+c.getSymbol()+"' style='width:300px; height:200px;'/></label>");
                displayBlock.append("<br><div class='candidateprofile'><p>CandidateId : "+c.getCandidateId()+"<br />");
                displayBlock.append("Candidate Name : "+c.getCandidateName()+"<br />");
                displayBlock.append("Party : "+c.getParty()+"<br /></div>");
                
            }
            out.println(displayBlock.toString());
        %>
</body>
