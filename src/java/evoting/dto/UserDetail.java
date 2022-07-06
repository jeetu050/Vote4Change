
package evoting.dto;

public class UserDetail {

    public UserDetail() {
    }

    public UserDetail(String userId, String userName, String address, String city, String email, String mobile) {
        this.userId = userId;
        this.userName = userName;
        this.address = address;
        this.city = city;
        this.email = email;
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "UserDetail{" + "userId=" + userId + ", userName=" + userName + ", address=" + address + ", city=" + city + ", email=" + email + ", mobile=" + mobile + '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    private String userId;
    private String userName;
    private String address;
    private String city;
    private String email;
    private String mobile;
}
