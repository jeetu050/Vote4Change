<%
    String userid = (String)session.getAttribute("userid");
    if(userid == null){
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    else
    {
        out.println("failed");
    }
    %> 