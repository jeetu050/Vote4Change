
package evoting.controller;

import evoting.dao.UserDAO;
import evoting.dto.UserDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteUserControllerServlet extends HttpServlet {
    
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
        
        String choosen = (String)request.getParameter("userId");
        try{
            StringBuffer displayBlock = new StringBuffer();
            
            if(choosen != null && choosen.equals("userId")){
                displayBlock.append("<option value=''>Choose Id</option>");
                ArrayList<String> userIdList = UserDAO.getAllUserIds();
                
                for(int i=0; i < userIdList.size(); i++)
                {
                    String uid = userIdList.get(i);
                    displayBlock.append("<option value="+uid+">"+uid+"</option>");
                }
                out.println(displayBlock.toString());
            }
            else{
                displayBlock.append("<div style='color:white; font-weight:bold;'><table>");
                ArrayList<UserDetail> userDetails = UserDAO.getUserDetailsById(choosen);
                UserDetail ud = userDetails.get(0);
                displayBlock.append("<tr><td>Username : </td><td>"+ud.getUserName()+"</td><tr>");
                displayBlock.append("<tr><td>Email : </td><td>"+ud.getEmail()+"</td><tr>");
                displayBlock.append("<tr><td>Mobile no : </td><td>"+ud.getMobile()+"</td><tr>");
                displayBlock.append("<tr><td>Address : </td><td>"+ud.getAddress()+"</td><tr>");
                displayBlock.append("<tr><td>City : </td><td>"+ud.getCity()+"</td><tr>");
                displayBlock.append("<tr><td><input type='button' value='Confirm' onclick='deleteChoosenUser()'></td><tr></table></div>");
                out.println(displayBlock.toString());
            }
        }
        
        catch(Exception ex)
            {
                System.out.println("Inside DeleteUserControllerServlet");
                rd = request.getRequestDispatcher("showexception.jsp");
                request.setAttribute("Exception", ex);
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
