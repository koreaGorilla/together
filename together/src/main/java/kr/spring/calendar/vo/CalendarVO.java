package kr.spring.calendar.vo;

import javax.validation.constraints.NotEmpty;

public class CalendarVO {
	private int calendar_num;
	private int party_num;
	private int mem_num;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;
	
	@NotEmpty
	private String start_date;
	
	@NotEmpty
	private String end_date;
	
	@NotEmpty
	private String start_time;
	
	@NotEmpty
	private String end_time;
	
	@NotEmpty
	private String location;
	
	private String color;
	
	public int getCalendar_num() {
		return calendar_num;
	}
	
	public void setCalendar_num(int calendar_num) {
		this.calendar_num = calendar_num;
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
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getStart_date() {
		return start_date;
	}
	
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	
	public String getEnd_date() {
		return end_date;
	}
	
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	public String getStart_time() {
		return start_time;
	}
	
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	
	public String getEnd_time() {
		return end_time;
	}
	
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "CalendarVO [calendar_num=" + calendar_num + ", party_num=" + party_num + ", mem_num=" + mem_num
				+ ", title=" + title + ", content=" + content + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", location=" + location + ", color="
				+ color + "]";
	}
}
