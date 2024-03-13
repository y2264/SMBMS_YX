package cn.smbms.pojo;

import org.springframework.stereotype.Component;

@Component
public class WorkBean {

    private int userId;
    private String userName;
    private String address;
    private String mobile;

    public WorkBean() {
    }

    public WorkBean(int userId, String userName, String address, String mobile) {
        this.userId = userId;
        this.userName = userName;
        this.address = address;
        this.mobile = mobile;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
