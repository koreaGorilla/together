package kr.spring.party.vo;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class PartyVO {
	private int party_num;
	private String party_name;
	private String party_content;
	private String party_filename;
	private Date party_reg_date;
	private int party_reg_type;
	private byte[] party_photo;
	private String party_photo_name;
	private int mem_num;
	private int party_hobby; 
	
	private String mem_name;
	private byte[] photo;//프로필 이미지
	private String photo_name;
	
	//파일 업로드 처리
	public void setUpload(MultipartFile upload)
			throws IOException{
		//MultipartFile -> byte[] 변환
		setParty_photo(upload.getBytes());
		//파일명 구하기
		setParty_photo_name(upload.getOriginalFilename());
	}

	public int getParty_num() {
		return party_num;
	}

	public void setParty_num(int party_num) {
		this.party_num = party_num;
	}

	public String getParty_name() {
		return party_name;
	}

	public void setParty_name(String party_name) {
		this.party_name = party_name;
	}

	public String getParty_content() {
		return party_content;
	}

	public void setParty_content(String party_content) {
		this.party_content = party_content;
	}

	public String getParty_filename() {
		return party_filename;
	}

	public void setParty_filename(String party_filename) {
		this.party_filename = party_filename;
	}

	public Date getParty_reg_date() {
		return party_reg_date;
	}

	public void setParty_reg_date(Date party_reg_date) {
		this.party_reg_date = party_reg_date;
	}

	public int getParty_reg_type() {
		return party_reg_type;
	}

	public void setParty_reg_type(int party_reg_type) {
		this.party_reg_type = party_reg_type;
	}

	public byte[] getParty_photo() {
		return party_photo;
	}

	public void setParty_photo(byte[] party_photo) {
		this.party_photo = party_photo;
	}

	public String getParty_photo_name() {
		return party_photo_name;
	}

	public void setParty_photo_name(String party_photo_name) {
		this.party_photo_name = party_photo_name;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public int getParty_hobby() {
		return party_hobby;
	}

	public void setParty_hobby(int party_hobby) {
		this.party_hobby = party_hobby;
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
		return "PartyVO [party_num=" + party_num + ", party_name=" + party_name + ", party_content=" + party_content
				+ ", party_filename=" + party_filename + ", party_reg_date=" + party_reg_date + ", party_reg_type="
				+ party_reg_type + ", party_photo_name=" + party_photo_name + ", mem_num=" + mem_num + ", party_hobby="
				+ party_hobby + ", mem_name=" + mem_name + ", photo_name=" + photo_name + "]";
	}

	
}
