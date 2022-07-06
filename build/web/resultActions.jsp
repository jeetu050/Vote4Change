<%-- 
    Document   : resultActions
    Created on : 23 Jun, 2021, 10:53:27 AM
    Author     : adity
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsscript/adminOptions.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">
        <link href="stylesheet/result.css" rel="stylesheet">
        <title>Result Actions Page</title>
    </head>
    <body>
        <%
            String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
            out.println("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+
        "<div class='subcandidate'>Admin Actions Page</div><br><br>"+
                    "<div class='logout'><a href='login.html'>logout</a></div></div>"+
        "<div class='container'>"+
            "<div id='dv1' onclick='electionresult()'><img src='images/candidateWiseResult.jpg' height='255px' width='250px'><br><h3>Result Based on Candidate</h3></div>"+
            "<div id='dv2' onclick='electionresultBasedOnParty()'><img src='images/partyWiseResult2.png' height='250px' width='250px'><br><h3>Result Based on Party</h3></div>"+
            "<div id='dv3' onclick='electionresultBasedOnGender()'><img src='images/genderWiseResult.png' height='250px' width='250px'><br><h3>Result Based on Gender</h3></div>"+
            "<br><br><div align='center' id='result'></div>"+
        "</div>");
        %>
    </body>
</html>
