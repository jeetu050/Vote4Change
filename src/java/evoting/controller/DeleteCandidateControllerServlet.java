
package evoting.controller;

import evoting.dao.CandidateDAO;
import evoting.dto.CandidateDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DeleteCandidateControllerServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RequestDispatcher rd = null;
        HttpSession sess = request.getSession();
        String userid = (String)sess.getAttribute("userid");
        if(userid == null)
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        
        String choosen = (String)request.getParameter("data");
        
     
            try
            {
                if(choosen != null && choosen.equals("cid"))
                {

                    ArrayList<String> allCidList = CandidateDAO.getAllCandidatesId();
                    System.out.println("Inside If");
                    request.setAttribute("candidateid", allCidList);
                    request.setAttribute("result", "candidatelist");
                }
                else
                {
                    CandidateDetails cd = CandidateDAO.getDetailsById(choosen);
                    System.out.println("Inside Else");
                    request.setAttribute("candidate", cd);
                    request.setAttribute("result", "details");
                }
                
                rd = request.getRequestDispatcher("admindeletecandidate.jsp");
                
            }
            catch(Exception ex)
            {
                rd = request.getRequestDispatcher("showexception.jsp");
                request.setAttribute("Exception", ex);
                rd.forward(request, response);
            }
            finally{
                System.out.println("Finally Inside DeleteCandidateControllerServlet");
                rd.forward(request, response);
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
