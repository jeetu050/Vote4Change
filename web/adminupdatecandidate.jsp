<%-- 
    Document   : adminupdatecandidate
    Created on : 17 Jun, 2021, 6:00:21 PM
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
                    
                    
                    
                    sb.append("<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>");
                    
                    sb.append("<tr><th>User Id : </th><td>"+adhar_no+"</td></tr><br />");
                    sb.append("<tr><th>Candidate Name : </th><td>"+cname+"</td></tr><br />");
                    sb.append("<tr><th>City : </th><td><input type='text' id='city' /></td></tr><br />");
                    sb.append("<tr><th>Party : </th><td><input type='text' id='party'></td></tr><br />");
                    sb.append("<tr><td colspan='2'><input type='file' name='files' value='Select Image'></td></tr><br />");
                    sb.append("<tr><th><input type='button' value='Update Candidate' onclick='updateCandidate()' id='addcnd'></th></table></form>");
                    
                    org.json.JSONObject json = new org.json.JSONObject();
                    json.put("subdetails",sb.toString());
                    out.println(json);
                }
    %>

