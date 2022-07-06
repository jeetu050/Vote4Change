
package evoting.dao;

import evoting.dbutill.DBConnection;
import evoting.dto.CandidateDTO;
import evoting.dto.CandidateDetails;
import evoting.dto.CandidateInfo;
import evoting.dto.ElectionResult;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

public class CandidateDAO {
    private static PreparedStatement ps,ps1,ps2,ps3,ps4,ps5,ps6,ps7,ps8,ps9,ps10,ps11;
    
    private static Statement st;
    
    static 
    {
    try
    {
        st = DBConnection.getConnection().createStatement();
        ps = DBConnection.getConnection().prepareStatement("select count(*) from candidate");
        
        ps1 = DBConnection.getConnection().prepareStatement("select username from user_details where adhar_no=? AND adhar_no NOT IN (select user_id from candidate)");
        ps2 = DBConnection.getConnection().prepareStatement("select distinct city from user_details");
         ps3 = DBConnection.getConnection().prepareStatement("insert into candidate values(?,?,?,?,?)");
         
         ps4 = DBConnection.getConnection().prepareStatement("select * from candidate where candidate_id=?");
        
         ps5 = DBConnection.getConnection().prepareStatement("Select candidate_id,username, party, symbol from candidate , user_details where candidate.user_id = user_details.adhar_no and candidate.city = (select city from user_details where adhar_no = ?)");
         
         ps6 = DBConnection.getConnection().prepareStatement("select username from user_details where adhar_no=?");
         
         ps7 = DBConnection.getConnection().prepareStatement("update candidate set city=?, party=?, symbol=? where candidate_id=?");
         ps8 = DBConnection.getConnection().prepareStatement("delete from candidate where candidate_id=?");
         ps9 = DBConnection.getConnection().prepareStatement("select symbol from candidate where party=?");
         ps10 = DBConnection.getConnection().prepareStatement("Select gender from user_details where adhar_no=?");
         ps11 = DBConnection.getConnection().prepareStatement("select candidate_id from candidate where city=? AND party=?");
    }
    catch(SQLException ex)
    {
        ex.printStackTrace();
    }
    
    }
    public static boolean checkCandidate(String city, String party) throws Exception
    {
        ps11.setString(1, city);
        ps11.setString(2, party);
        ResultSet rs = ps11.executeQuery();
        if(rs.next())
            return true;
        else 
            return false;
    }
    public static String getGenderById(String uid) throws SQLException{
        ps10.setString(1, uid);
        ResultSet rs = ps10.executeQuery();
        if (rs.next()){
            return rs.getString(1);
        }
        else{
            return null;
        }
    }
    public static boolean deleteCandidateById(String candidateId) throws SQLException{
        if(candidateId != null)
        {
            ps8.setString(1, candidateId);
            return ps8.executeUpdate() != 0;
            
        }
        else{
            return false;
        }
    }
    public static ArrayList<String> getAllCandidatesId() throws SQLException
    {
        ArrayList<String> candidateList = new ArrayList<>();
        
            ResultSet rs = st.executeQuery("select candidate_id from candidate");
            while(rs.next())
            {
                System.out.println(rs.getString(1));
                candidateList.add(rs.getString(1));
            }
        
        return candidateList;
        
    }
    public static boolean updateCandidateByCid(String city, String party, InputStream symbol, String cid) throws SQLException {
        
        if(city != null && party != null && symbol != null && cid != null){
            ps7.setString(1, city);
            ps7.setString(2, party);
            System.out.println("Inside DAO : "+symbol);
            ps7.setBinaryStream(3, symbol);
            ps7.setString(4, cid);
            return ps7.executeUpdate() != 0;
        }
        else{
            System.out.println("Inside else CandidateDAO update Candidate Method ......");
            System.out.println("City : "+city);
            System.out.println("Party : "+party);
            System.out.println("Symbol : "+symbol);
            System.out.println("Cid : "+cid);
            System.out.println("Some Values are null ");
            return false;
        }
        
        
    }
    public static String getSymbolByParty(String party) throws Exception{
        ps9.setString(1, party);
        ResultSet rs = ps9.executeQuery();
        Blob blob;
        InputStream inputStream;
        byte []buffer;
        byte [] imageBytes;
        int bytesRead;
        String base64Image;
        ByteArrayOutputStream outputStream;
        if(rs.next())
        {
            blob = rs.getBlob(1);
            inputStream = blob.getBinaryStream();
            outputStream = new ByteArrayOutputStream();
            buffer = new byte[4096];
            bytesRead = -1;
            while((bytesRead = inputStream.read(buffer)) != -1)
            {
                outputStream.write(buffer, 0, bytesRead);
            }
            imageBytes = outputStream.toByteArray();
            Base64.Encoder en = Base64.getEncoder();
            base64Image = en.encodeToString(imageBytes);
            System.out.println("Insie DAO : "+base64Image);
            return base64Image;
        }
        return null;
        
    }
    public static CandidateDetails getDetailsById(String cid) throws Exception
    {
        ps4.setString(1, cid);
        ResultSet rs = ps4.executeQuery();
        CandidateDetails cd = new CandidateDetails();
        Blob blob;
        InputStream inputStream;
        byte []buffer;
        byte [] imageBytes;
        int bytesRead;
        String base64Image;
        ByteArrayOutputStream outputStream;
        if(rs.next())
        {
            blob = rs.getBlob(4);
            inputStream = blob.getBinaryStream();
            outputStream = new ByteArrayOutputStream();
            buffer = new byte[4096];
            bytesRead = -1;
            while((bytesRead = inputStream.read(buffer)) != -1)
            {
                outputStream.write(buffer, 0, bytesRead);
            }
            imageBytes = outputStream.toByteArray();
            Base64.Encoder en = Base64.getEncoder();
            base64Image = en.encodeToString(imageBytes);
            System.out.println("Insie DAO : "+base64Image);
            cd.setSymbol(base64Image);
            cd.setCid(cid);
            System.out.println("User Name from Candidate Dao Method .. "+getUserNameById(rs.getString(3)));
            cd.setCname(getUserNameById(rs.getString(3)));
            cd.setParty(rs.getString(2));
            cd.setCity(rs.getString(5));
            cd.setUserid(rs.getString(3));
        }
        return cd;
    }
    public static boolean addCandidate(CandidateDTO obj) throws SQLException
    {
        ps3.setString(1, obj.getCandidateId());
        ps3.setString(2, obj.getParty());
        ps3.setString(3, obj.getUserid());
        ps3.setBinaryStream(4, obj.getSymbol());
        ps3.setString(5, obj.getCity());
        
        return ps3.executeUpdate() != 0;
    }
    
    public static String getNewId() throws SQLException{
        ResultSet rs = ps.executeQuery();
        rs.next();
        return "C"+(100+rs.getInt(1)+1);
        
        
    }
    public static String getUserNameById(String userid) throws SQLException 
    {
        ps6.setString(1, userid);
        ResultSet rs = ps6.executeQuery();
        if(rs.next())
        {
            System.out.println("Inside getUserNameById Name : "+rs.getString(1));
            return rs.getString(1);
        }
        else
        {
            
            return null;
        }
    }
        public static String getUserNameById2(String userid) throws SQLException 
    {
        ps1.setString(1, userid);
        ResultSet rs = ps1.executeQuery();
        if(rs.next())
        {
            System.out.println("Inside getUserNameById Name : "+rs.getString(1));
            return rs.getString(1);
        }
        else
        {
            
            return null;
        }
    }
    public static ArrayList<String> getCity() throws SQLException{
        ArrayList<String> cityList = new ArrayList<>();
        ResultSet rs = ps2.executeQuery();
        while(rs.next())
        {
            cityList.add(rs.getString(1));
        }
        return cityList;
    }
    public static ArrayList<CandidateInfo> viewCandidate(String adhar_id) throws Exception
    {
        System.out.println("Inside View Candidate");
        ArrayList<CandidateInfo> candidateList = new ArrayList<>();
        ps5.setString(1, adhar_id);
        ResultSet rs = ps5.executeQuery();
        Blob blob;
        InputStream inputStream;
        byte []buffer;
        byte [] imageBytes;
        int bytesRead;
        String base64Image;
        ByteArrayOutputStream outputStream;
        while(rs.next())
        {
            System.out.println("Inside While");
            blob = rs.getBlob(4);
            System.out.println(blob);
            inputStream = blob.getBinaryStream();
            outputStream = new ByteArrayOutputStream();
            buffer = new byte[4096];
            bytesRead = -1;
            while((bytesRead = inputStream.read(buffer)) != -1)
            {
                outputStream.write(buffer, 0, bytesRead);
            }
            imageBytes = outputStream.toByteArray();
            Base64.Encoder en = Base64.getEncoder();
            base64Image = en.encodeToString(imageBytes);
            CandidateInfo cd = new CandidateInfo();
            cd.setSymbol(base64Image);
            cd.setCandidateId(rs.getString(1));
            cd.setCandidateName(rs.getString(2));
            cd.setParty(rs.getString(3));
            candidateList.add(cd);
            System.out.println("Candidate Details :"+cd);
        }
        return candidateList;
    }
}
