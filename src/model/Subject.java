package model;
import java.util.List;

import com.tuananh.WeekDay;

public class Subject {
	private String code;
	private String name;
	private int group;
	private int stc;
	private String classCode;
	private int stchp;
	
	private List<SubjectInfo> subjectInfoList;
	
	public Subject() {
		super();
	}
	
	public Subject(String name, List<SubjectInfo> subjectInfoList) {
		super();
		this.name = name;
		this.subjectInfoList = subjectInfoList;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getStc() {
		return stc;
	}

	public void setStc(int stc) {
		this.stc = stc;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public int getStchp() {
		return stchp;
	}

	public void setStchp(int stchp) {
		this.stchp = stchp;
	}

	public List<SubjectInfo> getSubjectInfo() {
		return subjectInfoList;
	}

	public void setSubjectInfo(List<SubjectInfo> subjectInfoList) {
		this.subjectInfoList = subjectInfoList;
	}
	
	
}
