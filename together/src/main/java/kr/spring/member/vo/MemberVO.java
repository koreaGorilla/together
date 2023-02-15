package kr.spring.member.vo;

import java.sql.Date;

public class MemberVO {
	private int mem_num;
	private String mem_id;
	private int mem_auth;
	private String mem_pw;
	private String mem_cell;
	private String mem_email;
	private String mem_zipcode;
	private String mem_address1;
	private String mem_address2;
	private String hobby;
	private byte[] photo;
	private String photo_name;
	private Date reg_date;
	private Date modify_date;
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getMem_auth() {
		return mem_auth;
	}
	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_cell() {
		return mem_cell;
	}
	public void setMem_cell(String mem_cell) {
		this.mem_cell = mem_cell;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_zipcode() {
		return mem_zipcode;
	}
	public void setMem_zipcode(String mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}
	public String getMem_address1() {
		return mem_address1;
	}
	public void setMem_address1(String mem_address1) {
		this.mem_address1 = mem_address1;
	}
	public String getMem_address2() {
		return mem_address2;
	}
	public void setMem_address2(String mem_address2) {
		this.mem_address2 = mem_address2;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getPhoto_name() {
		return photo_name;
	}
	public void setPhoto_name(String photo_name) {
		this.photo_name = photo_name;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	@Override
	public String toString() {
		return "MemberVO [mem_num=" + mem_num + ", mem_id=" + mem_id + ", mem_auth=" + mem_auth + ", mem_pw=" + mem_pw
				+ ", mem_cell=" + mem_cell + ", mem_email=" + mem_email + ", mem_zipcode=" + mem_zipcode
				+ ", mem_address1=" + mem_address1 + ", mem_address2=" + mem_address2 + ", hobby=" + hobby
				+ ", photo_name=" + photo_name + ", reg_date=" + reg_date + ", modify_date=" + modify_date + "]";
	}
	
	
}