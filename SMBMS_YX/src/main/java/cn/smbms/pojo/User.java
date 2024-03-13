package cn.smbms.pojo;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户类
 * @author fangj
 */
@Component
public class User {
	private Integer id; //id 
	private String userCode; //用户编码
	private String userName; //用户名称
	private String userPassword; //用户密码
	private Integer gender; //性别
	private Date birthday;  //出生日期
	private String phone;   //电话
	private String address; //地址
	private Integer userRole;  //用户角色
	private Integer createdBy; //创建者
	private Date creationDate; //创建时间
	private Integer modifyBy;  //更新者
	private Date modifyDate;   //更新时间
	
	private String userRoleName; //用户角色名称
	private Integer age;//年龄
	
	public Integer getAge() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String[] str = simpleDateFormat.format(birthday).trim().split("-");
		int selectYear = Integer.parseInt(str[0]);
		// 得到当前时间的年
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		// 用当前年减去生日年
		int age = yearNow - selectYear;
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	//Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		//将日期类型转换为String类型
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(birthday);
	}

	public void setBirthday(String birthday) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday1 = null;
		try {
			if(birthday != null) {
				birthday1 = simpleDateFormat.parse(birthday);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.birthday = birthday1;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userCode='" + userCode + '\'' +
				", userName='" + userName + '\'' +
				", userPassword='" + userPassword + '\'' +
				", gender=" + gender +
				", birthday='" + birthday + '\'' +
				", phone='" + phone + '\'' +
				", address='" + address + '\'' +
				", userRole=" + userRole +
				", createdBy=" + createdBy +
				", creationDate=" + creationDate +
				", modifyBy=" + modifyBy +
				", modifyDate=" + modifyDate +
				", userRoleName='" + userRoleName + '\'' +
				", age=" + age +
				'}';
	}
}
