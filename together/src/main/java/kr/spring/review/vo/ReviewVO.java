package kr.spring.review.vo;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class ReviewVO {
	private int r_num;
	private Date r_date;
	private Date r_modify_date;
	private String party_name;
	private int party_num;
	private String r_content;
	private byte[] r_photo; //리뷰 이미지
	private String r_photoname;
	private int mem_num;
	
	//리뷰 테이블에 없는 거
	private String mem_name;
	private String mem_id;
	private byte[] photo; //프로필 이미지
	private String photo_name;
	private int fav;
	//파일 업로드 처리
	public void setUpload(MultipartFile upload) throws IOException{
		//MultipartFile -> byte[] 변환
		setR_photo(upload.getBytes());
		//파일명 구하기
		setR_photoname(upload.getOriginalFilename());
	}
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public Date getR_date() {
		return r_date;
	}
	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}
	public Date getR_modify_date() {
		return r_modify_date;
	}
	public void setR_modify_date(Date r_modify_date) {
		this.r_modify_date = r_modify_date;
	}
	public String getParty_name() {
		return party_name;
	}
	public void setParty_name(String party_name) {
		this.party_name = party_name;
	}
	public int getParty_num() {
		return party_num;
	}
	public void setParty_num(int party_num) {
		this.party_num = party_num;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public byte[] getR_photo() {
		return r_photo;
	}
	public void setR_photo(byte[] r_photo) {
		this.r_photo = r_photo;
	}
	public String getR_photoname() {
		return r_photoname;
	}
	public void setR_photoname(String r_photoname) {
		this.r_photoname = r_photoname;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
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
	public int getFav() {
		return fav;
	}
	public void setFav(int fav) {
		this.fav = fav;
	}
	@Override
	public String toString() {
		return "ReviewVO [r_num=" + r_num + ", r_date=" + r_date + ", r_modify_date=" + r_modify_date + ", party_name="
				+ party_name + ", party_num=" + party_num + ", r_content=" + r_content + ", r_photoname=" + r_photoname
				+ ", mem_num=" + mem_num + ", mem_name=" + mem_name + ", mem_id=" + mem_id + ", photo_name="
				+ photo_name + ", fav=" + fav + "]";
	}

	
	
	
	
}
