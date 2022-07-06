
package evoting.dto;


public class VoteDTO {

    @Override
    public String toString() {
        return "VoteDTO{" + "candidateId=" + candidateId + ", voterId=" + voterId + ", gender=" + gender + '}';
    }

    public VoteDTO(String candidateId, String voterId, String gender) {
        this.candidateId = candidateId;
        this.voterId = voterId;
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }
    private String candidateId;
    private String voterId;
    private String gender;
}
