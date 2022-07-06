package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import import org.json.JSONObject;
import java.util.ArrayList;
import evoting.dto.CandidateDetails;

public final class adminshowcandidate_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

                String userid=(String)session.getAttribute("userid");
                if(userid==null)
                {
                    response.sendRedirect("accessdenied.html");
                    return;
                } 
                String result = (String)request.getAttribute("result");
                JSONObject json = new JSONObject();
                    StringBuffer sb = new StringBuffer();
                if(result != null && result.equalsIgnoreCase("candidatelist"))
                {
                    ArrayList<String> cid = (ArrayList<String>)request.getAttribute("candidateid");
                    
                    for(String c : cid)
                        {
                            sb.append("<option value='"+c+"'>"+c+"</option>");

                        }
                        json.put("cid", sb.toString());
                        out.println(sb.toString());
                        
                }
                else if(result != null && result.equals("details"))
                {
                    CandidateDetails cd = (CandidateDetails)request.getAttribute("candidate");
                    String str = "<img src='data:image/jpg:base64,"+cd.getSymbol()+"'style='width=300px;height=200px;'/>";
                    
                }
                
             
             
                
            
    
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
