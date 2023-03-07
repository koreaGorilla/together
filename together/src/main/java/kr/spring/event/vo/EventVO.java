package kr.spring.event.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class EventVO {
	private int event_num;
	@NotEmpty
	private String event_title;
	@NotEmpty
	private String event_content;
	private int event_hit;
	private Date event_date;
	private String event_start;
	private String event_end;
	private String estart_time;
	private String eend_time;
	private byte[] event_file;
	private String event_filename;
	private int mem_num;
	
	
	//파일 업로드 처리
		public void setUpload(MultipartFile upload)
		                            throws IOException{
			//MultipartFile -> byte[] 변환
			setEvent_file(upload.getBytes());
			//파일명 구하기
			setEvent_filename(upload.getOriginalFilename());
		}

	public int getEvent_num() {
		return event_num;
	}
	public void setEvent_num(int event_num) {
		this.event_num = event_num;
	}
	public String getEvent_title() {
		return event_title;
	}
	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}
	public String getEvent_content() {
		return event_content;
	}
	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}
	public int getEvent_hit() {
		return event_hit;
	}
	public void setEvent_hit(int event_hit) {
		this.event_hit = event_hit;
	}
	public Date getEvent_date() {
		return event_date;
	}
	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
	public String getEvent_start() {
		return event_start;
	}
	public void setEvent_start(String event_start) {
		this.event_start = event_start;
	}
	public String getEvent_end() {
		return event_end;
	}
	public void setEvent_end(String event_end) {
		this.event_end = event_end;
	}
	public String getEstart_time() {
		return estart_time;
	}
	public void setEstart_time(String estart_time) {
		this.estart_time = estart_time;
	}
	public String getEend_time() {
		return eend_time;
	}
	public void setEend_time(String eend_time) {
		this.eend_time = eend_time;
	}
	public byte[] getEvent_file() {
		return event_file;
	}
	public void setEvent_file(byte[] event_file) {
		this.event_file = event_file;
	}
	public String getEvent_filename() {
		return event_filename;
	}
	public void setEvent_filename(String event_filename) {
		this.event_filename = event_filename;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	
	@Override
	public String toString() {
		return "EventVO [event_num=" + event_num + ", event_title=" + event_title + ", event_content=" + event_content
				+ ", event_hit=" + event_hit + ", event_date=" + event_date + ", event_start=" + event_start
				+ ", event_end=" + event_end + ", estart_time=" + estart_time + ", eend_time=" + eend_time
				+ ", event_filename=" + event_filename + ", mem_num=" + mem_num + "]";
	}
	
	
}



