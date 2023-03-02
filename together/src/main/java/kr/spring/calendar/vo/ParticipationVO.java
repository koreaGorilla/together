package kr.spring.calendar.vo;

public class ParticipationVO {
	private int p_num;
	private int calendar_num;
	private int mem_num;
	private int participate;
	
	public int getP_num() {
		return p_num;
	}
	
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	
	public int getCalendar_num() {
		return calendar_num;
	}
	
	public void setCalendar_num(int calendar_num) {
		this.calendar_num = calendar_num;
	}
	
	public int getMem_num() {
		return mem_num;
	}
	
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	public int getParticipate() {
		return participate;
	}
	
	public void setParticipate(int participate) {
		this.participate = participate;
	}

	@Override
	public String toString() {
		return "ParticipationVO [p_num=" + p_num + ", calendar_num=" + calendar_num + ", mem_num=" + mem_num
				+ ", participate=" + participate + "]";
	}
}
