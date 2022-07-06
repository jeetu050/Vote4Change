<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="evoting.dto.CandidateDetails,evoting.dto.ElectionResult,java.util.LinkedHashMap,java.util.Iterator" %>

<%
            String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
            System.out.println("Inside Election Result");
            String choosen = (String)request.getAttribute("choosen");
            if(choosen != null && choosen.equals("candidate")){
                
                LinkedHashMap<CandidateDetails,Integer> resultDetails = (LinkedHashMap)request.getAttribute("result");
                int votecount = (int)request.getAttribute("votecount");
                Iterator it = resultDetails.entrySet().iterator();
                StringBuffer displayBlock = new StringBuffer("<h3>Here are the Results Based On Candidate!</h3><br /><table>");
                displayBlock.append("<tr><th>Candidate Id</th><th>Candidate Name</th><th>Party</th><th>Symbol</th><th>City</th><th>Vote Count</th><th>Vote %</th>");
                while(it.hasNext())
                {
                    Map.Entry<CandidateDetails,Integer> e = (Map.Entry)it.next();
                    CandidateDetails cd = e.getKey();
                    float voteper = (e.getValue()*100.0f)/votecount;
                    displayBlock.append("<tr><td>"+cd.getCid()+"</td><td>"+cd.getCname()+"</td><td>"+cd.getParty()+"</td><td><img src='data:image/jpg;base64,"+cd.getSymbol()+"' style='width:300px;height:200px;' /></td><td>"+cd.getCity()+"</td><td>"+e.getValue()+"</td><td>"+voteper+"</td></tr>");
                }
                displayBlock.append("</table>");
                out.println(displayBlock);
            }
            else if(choosen != null && choosen.equals("party"))
            {
                Map<String, Integer> result = (Map)request.getAttribute("result");
                int votecount = (int)request.getAttribute("votecount");
                Iterator it = result.entrySet().iterator();
                StringBuffer displayBlock = new StringBuffer("<h3>Here are the Results Based On Party!</h3><br /><table>");
                displayBlock.append("<tr><th>Party</th><th>Symbol</th><th>Vote %</th>");
                while(it.hasNext())
                {
                    Map.Entry<ElectionResult, Integer> e = (Map.Entry)it.next();
                    ElectionResult er = e.getKey();
                    float voteper = (e.getValue()*100.0f)/votecount;
                    displayBlock.append("<tr><td>"+er.getParty()+"</td><td><img src='data:image/jpg;base64,"+er.getSymbol()+"' style='width:300px;height:200px;' /></td><td>"+voteper+"</td></tr>");
                }
                displayBlock.append("</table>");
                out.println(displayBlock);
            }
            else if(choosen != null && choosen.equals("gender"))
            {
                String per = (String)request.getAttribute("per");
                String [] percentage = per.split(":");
                float male = (Float.parseFloat(percentage[0])/Float.parseFloat(percentage[1]) * 100);
                StringBuffer displayBlock = new StringBuffer("<table>");
                displayBlock.append("<tr><th> Gender </th><th> Vote % </th></tr>");
                displayBlock.append("<tr><td> पुरुष </td><td>" + male + "</td><tr>");
                displayBlock.append("<tr><td> महिला </td><td>" + (100 - male) + "</td><tr>");
                out.println(displayBlock);
            }
%>