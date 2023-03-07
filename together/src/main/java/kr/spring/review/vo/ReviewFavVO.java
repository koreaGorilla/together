package kr.spring.review.vo;

public class ReviewFavVO {
	private int r_fav_num;
	private int r_num;
	private int mem_num;
	
	public int getR_fav_num() {
		return r_fav_num;
	}
	public void setR_fav_num(int r_fav_num) {
		this.r_fav_num = r_fav_num;
	}
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	@Override
	public String toString() {
		return "ReviewFavVO [r_fav_num=" + r_fav_num + ", r_num=" + r_num + ", mem_num=" + mem_num + "]";
	}
	
	
}
