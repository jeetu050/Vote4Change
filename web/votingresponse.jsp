<%-- 
    Document   : votingresponse
    Created on : 14 Jun, 2021, 9:08:31 PM
    Author     : adity
--%>

<%@ page import="evoting.dto.CandidateInfo" %>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link href="stylesheet/backgroundimage.css" rel="stylesheet">
    <link href="stylesheet/pageheader.css" rel="stylesheet">
    <link href="stylesheet/showcandidate.css" rel="stylesheet">
    <title>Voting Details </title>
    </head>
    
    <body>
        <%
            String userid = (String)session.getAttribute("userid");
            if(userid == null)
            {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
                return;
            }
            CandidateInfo c = (CandidateInfo)session.getAttribute("candidate");
            StringBuffer displayBlock = new StringBuffer();
            displayBlock.append("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>");
            if(c == null)
            {
                displayBlock.append("<div class='subcandidate'>Sorry! Your vote could not be casted</div><br><br>");
                displayBlock.append("<div><h4 id='logout'><a href='LoginServlet?logout=logout'>Logout</a></h4></div>");
                out.println(displayBlock.toString());
            }
            else
            {
                displayBlock.append("<div class='subcandidate'>Thank you for Voting!</div><br><br>");
                displayBlock.append("<br><div class='candidateprofile'>Your Vote Added Successfully!</div>");
                
                displayBlock.append("<strong>You voted for </strong><img src='data:image/jpg;base64,"+c.getSymbol()+"' style='width=300px;height:200px;' />");
                displayBlock.append("<br><div class='candidateprofile'><p>CandidateId : "+c.getCandidateId()+"<br />");
                displayBlock.append("Candidate Name : "+c.getCandidateName()+"<br />");
                displayBlock.append("Party : "+c.getParty()+"<br /></div>");
                out.println(displayBlock.toString());
            }
         %>
    </body>
    
</html>
