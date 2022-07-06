<%-- 
    Document   : adminshowcandidate
    Created on : 31 May, 2021, 11:54:23 PM
    Author     : adity
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="evoting.dto.CandidateDetails"%>


<%
    String userid=(String)session.getAttribute("userid");
                System.out.println("user id : "+userid);
                if(userid==null)
                {
                    response.sendRedirect("accessdenied.html");
                    return;
                } 
                String result = (String)request.getAttribute("result");
                
                    StringBuffer sb = new StringBuffer();
                System.out.println("Result : "+result);
                if(result != null && result.equalsIgnoreCase("candidatelist"))
                {
                    
                    ArrayList<String> cid = (ArrayList<String>)request.getAttribute("candidateid");
                    sb.append("<option value=''>Choose Id</option>");
                    for(String c : cid)
                        {
                            
                            sb.append("<option value='"+c+"'>"+c+"</option>");

                        }
                    org.json.JSONObject json = new org.json.JSONObject();
                        json.put("cid", sb.toString());
                        
                        out.println(json);
                        System.out.println("In Admin Show Candidate");
                        System.out.println(sb.toString());
                        
                        
                }
                else if(result != null && result.equals("details"))
                {
                    CandidateDetails cd = (CandidateDetails)request.getAttribute("candidate");
                    String adhar_no = cd.getUserid();
                    String cname = cd.getCname();
                    String city = cd.getCity();
                    String party = cd.getParty();
                    String image = "<img src='data:image/jpg;base64,"+cd.getSymbol()+"'style='width=300px;height=200px;'/>";
                    sb.append("<h4>Here are the Details of Selected Candidate ..<h4><br /><table><tr><td>User Id : </td><td>"+adhar_no+"</td></tr> <tr><td>Candidate Name : </td><td>"+cname+"</td> </tr>"
                            + "<tr><td>City : </td><td>"+city+"</td></tr><tr><td>Party : </td><td>"+party+"</td></tr><tr><td>Symbol : </td><td>"+image+"</td><tr><tr><td><input type='button' value='Delete Candidate' onclick='deleteChoosenCandidate()'></table>");
                    org.json.JSONObject json = new org.json.JSONObject();
                    json.put("subdetails",sb.toString());
                    out.println(json);
                    
                }
    %>
