package kr.spring.chat.vo;

import java.sql.Date;


public class ChatVO {
	private int chat_num;
	private String message;
	private Date timestamp;
	private int party_num;
	private int mem_num;
	
	private int read_count;
	private String mem_id;
	
	public int getChat_num() {
		return chat_num;
	}
	public String getMessage() {
		return message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public int getParty_num() {
		return party_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public int getRead_count() {
		return read_count;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setChat_num(int chat_num) {
		this.chat_num = chat_num;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public void setParty_num(int party_num) {
		this.party_num = party_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}



	
	
}
