
package evoting.dto;

import java.util.Objects;

public class CandidateDetails {

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.cid);
        hash = 17 * hash + Objects.hashCode(this.userid);
        hash = 17 * hash + Objects.hashCode(this.cname);
        hash = 17 * hash + Objects.hashCode(this.symbol);
        hash = 17 * hash + Objects.hashCode(this.party);
        hash = 17 * hash + Objects.hashCode(this.city);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CandidateDetails other = (CandidateDetails) obj;
        if (!Objects.equals(this.cid, other.cid)) {
            return false;
        }
        if (!Objects.equals(this.userid, other.userid)) {
            return false;
        }
        if (!Objects.equals(this.cname, other.cname)) {
            return false;
        }
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        if (!Objects.equals(this.party, other.party)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        return true;
    }

    public CandidateDetails() {
    }

    public CandidateDetails(String cid, String userid, String cname, String symbol, String party, String city) {
        this.cid = cid;
        this.userid = userid;
        this.cname = cname;
        this.symbol = symbol;
        this.party = party;
        this.city = city;
    }

    @Override
    public String toString() {
        return "CandidateDetails{" + "cid=" + cid + ", userid=" + userid + ", cname=" + cname + ", symbol=" + symbol + ", party=" + party + ", city=" + city + '}';
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    private String cid;
    private String userid;
    private String cname;
    private String symbol;
    private String party;
    private String city;

}
