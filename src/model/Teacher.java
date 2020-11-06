package model;

public class Teacher {

	private String teacherCode;
	private String name;
	public Teacher(String teacherCode, String name) {
		super();
		this.teacherCode = teacherCode;
		this.name = name;
	}
	public String getTeacherCode() {
		return teacherCode;
	}
	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
