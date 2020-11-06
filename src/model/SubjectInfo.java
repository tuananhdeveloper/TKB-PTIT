package model;

import com.tuananh.WeekDay;

public class SubjectInfo {
	private int practice;
	private WeekDay weekDay;
	private int begin;
	private int st;
	private String room;
	private String teacherCode;
	private String week;
	
	private String startDate;
	private String endDate;
	public SubjectInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SubjectInfo(WeekDay weekDay, int begin, int st, String room, String week, String startDate, String endDate) {
		super();
		this.weekDay = weekDay;
		this.begin = begin;
		this.st = st;
		this.room = room;
		this.week = week;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getPractice() {
		return practice;
	}

	public void setPractice(int practice) {
		this.practice = practice;
	}

	public WeekDay getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(WeekDay weekDay) {
		this.weekDay = weekDay;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getSt() {
		return st;
	}

	public void setSt(int st) {
		this.st = st;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
