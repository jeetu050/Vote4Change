
package evoting.dao;

import evoting.dbutill.DBConnection;
import evoting.dto.CandidateInfo;
import evoting.dto.VoteDTO;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

public class VoteDAO {
    private static PreparedStatement ps1, ps2, ps3,ps4,ps5,ps6,ps7;
    private static Statement st;  
    
    static
    {
        try
        {
            ps1 = DBConnection.getConnection().prepareStatement("select candidate_id from voting where voter_id=?");
            ps2 = DBConnection.getConnection().prepareStatement("select candidate_id,username, party, symbol from candidate , user_details where candidate.user_id = user_details.adhar_no AND candidate.candidate_id = ?");
            ps3 = DBConnection.getConnection().prepareStatement("insert into voting values(?, ?, ?)");
            ps4 = DBConnection.getConnection().prepareStatement("select candidate_id, count(*) as vote_obt from voting group by candidate_id order by vote_obt desc");
            ps5 = DBConnection.getConnection().prepareStatement("select  candidate.party, count(*) from candidate, voting where candidate.candidate_id=voting.candidate_id group by candidate.party");
            ps6 = DBConnection.getConnection().prepareStatement("select count(*) from voting");
            ps7 = DBConnection.getConnection().prepareStatement("select count(*) from voting where gender='Male'");
            st = DBConnection.getConnection().createStatement();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    public static String getGenderPercentage() throws SQLException{
        ResultSet rs = ps7.executeQuery();
        rs.next();
        String num = "" + rs.getInt(1);
        rs = ps6.executeQuery();
        rs.next();
        num += ":" + rs.getInt(1);
        return num;
    }
    public static String getCandidateId(String userid) throws SQLException
    {
        ps1.setString(1, userid);
        ResultSet rs = ps1.executeQuery();
        if(rs.next())
        {
            return rs.getString(1);
        }
        return null;
    }
    public static CandidateInfo getVote(String candidateId) throws Exception
    {
        System.out.println("Inside get vote");
        ps2.setString(1, candidateId);
        ResultSet rs = ps2.executeQuery();
        System.out.println("Query executed successfully");
        CandidateInfo cd = new CandidateInfo();
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
            cd.setSymbol(base64Image);
            cd.setCandidateId(candidateId);
            cd.setCandidateName(rs.getString(2));
            cd.setParty(rs.getString(3));
            System.out.println(cd);
            
        }
        return cd;
    }
    public static boolean addVote(VoteDTO obj) throws SQLException
    {
        ps3.setString(1, obj.getCandidateId());
        ps3.setString(2, obj.getVoterId());
        ps3.setString(3, obj.getGender());
        return (ps3.executeUpdate() != 0);
        
    }  
    public static Map<String , Integer> getResult() throws SQLException{   //it will return the result on the basis of candidate
        Map<String, Integer> result = new LinkedHashMap<>();
        ResultSet rs = ps4.executeQuery();
        while(rs.next())
        {
            System.out.println("Candidate ID : "+rs.getString(1));
            System.out.println("Count Votes : "+rs.getInt(2));
            result.put(rs.getString(1), rs.getInt(2));
        }
        return result;
    }
    public static Map<String, Integer> getResultBasedOnParty() throws SQLException {
        Map<String, Integer> result = new LinkedHashMap<>();
        ResultSet rs = ps5.executeQuery();
        while(rs.next())
        {
            System.out.println("Party : "+rs.getString(1));
            System.out.println("Votes Count : "+rs.getInt(2));
            result.put(rs.getString(1), rs.getInt(2));
        }
        return result;
    }
    public static int getVoteCount() throws SQLException{
        ResultSet rs = st.executeQuery("select count(*) from voting");
        if(rs.next())
            return rs.getInt(1);
        return 0;
    }
    
}
