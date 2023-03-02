package kr.spring.partymember.vo;

import java.util.Date;

public class PartyMemberVO {
	private int partymem_num;
	private int party_auth;
	private Date partymem_reg_date;
	private int party_num;
	private int mem_num;
	
	
	private String mem_id;
	private String mem_name;
	
	public int getPartymem_num() {
		return partymem_num;
	}
	public void setPartymem_num(int partymem_num) {
		this.partymem_num = partymem_num;
	}
	public int getParty_auth() {
		return party_auth;
	}
	public void setParty_auth(int party_auth) {
		this.party_auth = party_auth;
	}
	public Date getPartymem_reg_date() {
		return partymem_reg_date;
	}
	public void setPartymem_reg_date(Date partymem_reg_date) {
		this.partymem_reg_date = partymem_reg_date;
	}
	public int getParty_num() {
		return party_num;
	}
	public void setParty_num(int party_num) {
		this.party_num = party_num;
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
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	@Override
	public String toString() {
		return "PartyMemberVO [partymem_num=" + partymem_num + ", party_auth=" + party_auth + ", partymem_reg_date="
				+ partymem_reg_date + ", party_num=" + party_num + ", mem_num=" + mem_num + ", mem_id=" + mem_id
				+ ", mem_name=" + mem_name + "]";
	}
	
	
}
