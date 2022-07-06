
package evoting.dao;

import evoting.dbutill.DBConnection;
import evoting.dto.UserDTO;
import evoting.dto.UserDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static PreparedStatement ps,ps1,ps2;
    private static Statement st;
    static 
    {
    try
    {
        st = DBConnection.getConnection().createStatement();
        ps = DBConnection.getConnection().prepareStatement("select user_type from user_details where adhar_no=? and password=?");
        ps1 = DBConnection.getConnection().prepareStatement("Delete from user_details where adhar_no=?");
        ps2 = DBConnection.getConnection().prepareStatement("Select username , email,mobile_no,address,City from user_details where adhar_no=?");
    }
    catch(SQLException ex)
    {
        ex.printStackTrace();
    }
    }
    
    public static ArrayList<UserDetail> getUserDetailsById(String userId) throws SQLException {
        ArrayList<UserDetail> userDetails = new ArrayList<>();
        ps2.setString(1,userId);
        ResultSet rs = ps2.executeQuery();
        while(rs.next()){
            UserDetail ud = new UserDetail();
            ud.setUserId(userId);
            ud.setUserName(rs.getString(1));
            ud.setEmail(rs.getString(2));
            ud.setMobile(rs.getString(3));
            ud.setAddress(rs.getString(4));
            ud.setCity(rs.getString(5));
            userDetails.add(ud);
        }
        return userDetails;
    }
    public static ArrayList<String> getAllUserIds() throws SQLException
    {
        ArrayList<String> userIdList = new ArrayList<>();
        
        ResultSet rs = st.executeQuery("select adhar_no from user_details where adhar_no NOT IN (select adhar_no from user_details where user_type='Admin')");
        while(rs.next()){
            userIdList.add(rs.getString(1));
        }
        return userIdList;
    }
    public static boolean deleteUserById(String userId) throws SQLException {
        System.out.println("Inside UserDAO in Delete User");
        ps1.setString(1, userId);
        return ps1.executeUpdate() != 0;
        
    }
    public static List<UserDetail> getUserDetails() throws SQLException {
        System.out.println("Inside DAO");
        ArrayList<UserDetail> userDetails = new ArrayList<>();
        ResultSet rs = st.executeQuery("Select adhar_no, username,address, city, email,mobile_no from user_details where adhar_no NOT IN (select adhar_no from user_details where user_type='Admin' )");
        while(rs.next()){
            UserDetail ud = new UserDetail();
            ud.setUserId(rs.getString(1));
            ud.setUserName(rs.getString(2));
            ud.setAddress(rs.getString(3));
            ud.setCity(rs.getString(4));
            ud.setEmail(rs.getString(5));
            ud.setMobile(rs.getString(6));
            System.out.println("User Details : "+ud);
            userDetails.add(ud);
            
        }
        return userDetails;
    }
    public static String validateUser(UserDTO user) throws SQLException{
        ps.setString(1 , user.getUserid());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return rs.getString(1);
        return null;
    }
}
