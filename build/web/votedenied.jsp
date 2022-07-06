<%@page import="evoting.dto.CandidateInfo"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="jsscript/vote.js"></script>
    <script src="jsscript/jquery.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link href="stylesheet/backgroundimage.css" rel="stylesheet">
    <link href="stylesheet/pageheader.css" rel="stylesheet">
    <link href="stylesheet/showcandidate.css" rel="stylesheet">
    <title>Vote Already been Casted</title>
</head>
<body>
    <%
        
        String userid=(String)session.getAttribute("userid");
        System.out.println("Inside VD.jsp");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
            CandidateInfo ci = (CandidateInfo)request.getAttribute("candidate");
            StringBuffer displayBlock = new StringBuffer("");
            displayBlock.append("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+
                "<div class='subcandidate'>Vote has already been Casted!</div>"
                +"<div class='logout'><a href='login.html'>Logout</a></div>"
                +"</div><div class='buttons'>");
            displayBlock.append("<div class='candidateprofile'>Your Vote Details are given below ...</div><br />");
            displayBlock.append("<br><div class='candidateprofile'><p>CandidateId : "+ci.getCandidateId()+"<br />");
            displayBlock.append("Candidate Name : "+ci.getCandidateName()+"<br />");
            displayBlock.append("Party : "+ci.getParty()+"<br />");
            displayBlock.append("<label for='"+ci.getCandidateId()+"'>Symbol : <img src='data:image/jpg;base64,"+ci.getSymbol()+"' style='width:300px; height:200px;'/></label></div>");
            
            
            out.println(displayBlock);
     %>
</body>

