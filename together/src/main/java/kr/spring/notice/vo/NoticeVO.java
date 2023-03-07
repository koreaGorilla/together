package kr.spring.notice.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class NoticeVO {
	private int notice_num;
	@NotEmpty
	private String notice_title;
	@NotEmpty
	private String notice_content;
	private int notice_hit;
	private Date notice_date;
	private Date notice_modifydate;
	private byte[] notice_file;
	private String notice_filename;
	private String ip;
	private int mem_num;
	
	private String mem_id;
	private String mem_nick_name;
	private byte[] photo;
	private String photo_name;
	
	
	//파일 업로드 처리
		public void setUpload(MultipartFile upload)
		                            throws IOException{
			//MultipartFile -> byte[] 변환
			setNotice_file(upload.getBytes());
			//파일명 구하기
			setNotice_filename(upload.getOriginalFilename());
		}
	
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public int getNotice_hit() {
		return notice_hit;
	}
	public void setNotice_hit(int notice_hit) {
		this.notice_hit = notice_hit;
	}
	public Date getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}
	public Date getNotice_modifydate() {
		return notice_modifydate;
	}
	public void setNotice_modifydate(Date notice_modifydate) {
		this.notice_modifydate = notice_modifydate;
	}
	public byte[] getNotice_file() {
		return notice_file;
	}
	public void setNotice_file(byte[] notice_file) {
		this.notice_file = notice_file;
	}
	public String getNotice_filename() {
		return notice_filename;
	}
	public void setNotice_filename(String notice_filename) {
		this.notice_filename = notice_filename;
	}
	
	
	
	
	


	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
	public String getMem_nick_name() {
		return mem_nick_name;
	}
	public void setMem_nick_name(String mem_nick_name) {
		this.mem_nick_name = mem_nick_name;
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
		return "NoticeVO [notice_num=" + notice_num + ", notice_title=" + notice_title + ", notice_content="
				+ notice_content + ", notice_hit=" + notice_hit + ", notice_date=" + notice_date
				+ ", notice_modifydate=" + notice_modifydate + ", notice_filename=" + notice_filename + ", ip=" + ip
				+ ", mem_num=" + mem_num + ", mem_id=" + mem_id + ", mem_nick_name=" + mem_nick_name + ", photo_name="
				+ photo_name + "]";
	}

	
	
	
}
