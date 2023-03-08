package kr.spring.review.vo;

import kr.spring.util.DurationFromNow;

public class ReviewReplyVO {
	private int c_num;
	private String c_date;
	private String c_modify_date;
	private String c_content;
	private int mem_num;
	private int r_num;
	
	private String mem_name;
	private byte[] photo;//프로필 이미지
	private String photo_name;
	
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public String getC_date() {
		return c_date;
	}
	public void setC_date(String c_date) {
		this.c_date = DurationFromNow.getTimeDiffLabel(c_date);
	}
	public String getC_modify_date() {
		return c_modify_date;
	}
	public void setC_modify_date(String c_modify_date) {
		this.c_modify_date =DurationFromNow.getTimeDiffLabel(c_modify_date);
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
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
	
	@Override
	public String toString() {
		return "ReviewReplyVO [c_num=" + c_num + ", c_date=" + c_date + ", c_modify_date=" + c_modify_date
				+ ", c_content=" + c_content + ", mem_num=" + mem_num + ", r_num=" + r_num + ", mem_name=" + mem_name
				+ ", photo_name=" + photo_name + "]";
	} 
	
	
}
