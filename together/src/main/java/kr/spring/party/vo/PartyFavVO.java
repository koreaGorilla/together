package kr.spring.party.vo;

public class PartyFavVO {
	private int p_fav_num;
	private int party_num;
	private int mem_num;
	
	public int getP_fav_num() {
		return p_fav_num;
	}
	public void setP_fav_num(int p_fav_num) {
		this.p_fav_num = p_fav_num;
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
	
	@Override
	public String toString() {
		return "PartyFavVO [p_fav_num=" + p_fav_num + ", party_num=" + party_num + ", mem_num=" + mem_num + "]";
	}
	
	
}
