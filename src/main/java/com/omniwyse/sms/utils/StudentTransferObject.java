package com.omniwyse.sms.utils;

import java.sql.Date;

public class StudentTransferObject {
	private String name;
	private String emailid;
	private String parentemailid;
	private String username;
	private String password;
	private String parentaddress;
	private long contactnumber;
	public long getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(long contactnumber) {
		this.contactnumber = contactnumber;
	}
	private String fathername;
	private String mothername;
	private String address;
	private Date dateofbirth;
	private Date dateofjoining;
	private String gender;
	private String gradename;
	private String syllabustype;
	private String admissionnumber;
	private long houseid;
	private long id;
	private String housename;
	private long gradeid;
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
		
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	public String getMothername() {
		return mothername;
	}
	public void setMothername(String mothername) {
		this.mothername = mothername;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSyllabustype() {
		return syllabustype;
	}
	public void setSyllabustype(String syllabustype) {
		this.syllabustype = syllabustype;
	}
	
	public String getParentemailid() {
		return parentemailid;
	}
	public void setParentemailid(String parentemailid) {
		this.parentemailid = parentemailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getParentaddress() {
		return parentaddress;
	}
	public void setParentaddress(String parentaddress) {
		this.parentaddress = parentaddress;
	}
	
	
	public long getHouseid() {
		return houseid;
	}
	public void setHouseid(long houseid) {
		this.houseid = houseid;
	}
	
	
	public long getGradeid() {
		return gradeid;
	}
	public void setGradeid(long gradeid) {
		this.gradeid = gradeid;
	}
	
	public String getName() {
		return name;
	}
	public String getHousename() {
			return housename;
		}
	public void setHousename(String housename) {
			this.housename = housename;
		}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public Date getDateofjoining() {
		return dateofjoining;
	}
	public void setDateofjoining(Date dateofjoining) {
		this.dateofjoining = dateofjoining;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGradename() {
		return gradename;
	}
	public void setGradename(String gradename) {
		this.gradename = gradename;
	}
	
	
	public String getAdmissionnumber() {
		return admissionnumber;
	}
	public void setAdmissionnumber(String admissionnumber) {
		this.admissionnumber = admissionnumber;
	}
		public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
