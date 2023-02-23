package kr.spring.member.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
	private int mem_num;
	@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String mem_id;
	private int mem_auth;
	@NotEmpty
	private String mem_name;
	@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String mem_pw;
	@NotEmpty
	private String mem_cell;
	@Email
	@NotEmpty
	private String mem_email;
	@Size(min=5,max=5)
	private String mem_zipcode;
	@NotEmpty
	private String mem_address1;
	@NotEmpty
	private String mem_address2;
	private String hobby;
	private byte[] photo;
	private String photo_name;
	private Date reg_date;
	private Date modify_date;
	
	//비밀번호 변경시 현재 비밀번호를 저장하는 용도
	@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String now_passwd;
	//====비밀번호 일치 여부 체크====//
	public boolean isCheckedPassword(String userPasswd) {
		if(mem_auth > 1 && mem_pw.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	
	//====이미지 BLOB 처리====//
		//(주의)폼에서 파일업로드 파라미터네임은 반드시 upload로
		//지정해야 함
		public void setUpload(MultipartFile upload)
				                    throws IOException{
			//MultipartFile -> byte[]
			setPhoto(upload.getBytes());
			//파일 이름
			setPhoto_name(upload.getOriginalFilename());
		}
		
		//====checkbox 데이터 처리====//
		//form:checkbox에서 사용할 수 있도록 String -> String[]로 변환
		public String[] getF_hobby() {
			String[] f_hobby = null;
			if(hobby!=null) f_hobby = hobby.split(",");
			return f_hobby;
		}
		//String[] -> String
		public void setF_hobby(String[] f_hobby) {
			if(f_hobby!=null) {
				this.hobby = "";
				for(int i=0;i<f_hobby.length;i++) {
					if(i>0) this.hobby += ",";
					this.hobby += f_hobby[i];
				}
			}
		}
	
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
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
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
	
	public String getNow_passwd() {
		return now_passwd;
	}


	public void setNow_passwd(String now_passwd) {
		this.now_passwd = now_passwd;
	}


	@Override
	public String toString() {
		return "MemberVO [mem_num=" + mem_num + ", mem_id=" + mem_id + ", mem_auth=" + mem_auth + ", mem_name="
				+ mem_name + ", mem_pw=" + mem_pw + ", mem_cell=" + mem_cell + ", mem_email=" + mem_email
				+ ", mem_zipcode=" + mem_zipcode + ", mem_address1=" + mem_address1 + ", mem_address2=" + mem_address2
				+ ", hobby=" + hobby + ", photo_name=" + photo_name + ", reg_date=" + reg_date + ", modify_date="
				+ modify_date + ", now_passwd=" + now_passwd + "]";
	}



	}
	
