package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.apache.axiom.util.UIDGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.tuananh.ICSExporter;
import com.tuananh.WeekDay;

import model.Subject;
import model.SubjectInfo;
import model.Teacher;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.HostInfo;
import net.fortuna.ical4j.util.UidGenerator;


public class ICSFatory {
	
	private static TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
	private static TimeZone timezone = registry.getTimeZone("Asia/Ho_Chi_Minh");
	private static int number = 0;
	private static boolean start = false;
	
	private static ArrayList<Teacher> teachers;
	
	public static void exportICS(ArrayList<Subject> subjects, OutputStream output) throws ParseException, IOException, URISyntaxException {
		
		teachers = new ArrayList<>();
		Calendar calendar = new Calendar();
		calendar.getProperties().add(new ProdId("-//Tuan Anh//TKB-PTIT 1.0//VN"));
		calendar.getProperties().add(Version.VERSION_2_0);
		calendar.getProperties().add(CalScale.GREGORIAN);
		
		for(Subject subject: subjects) {
			
			String name = subject.getName();
			for(SubjectInfo subjectInfo: subject.getSubjectInfo()) {
				String week = subjectInfo.getWeek();
				
				String teacherName;
				if(!teacherExist(subjectInfo.getTeacherCode())) {
					teacherName = getTeacherName(ICSExporter.url + subjectInfo.getTeacherCode());
					teachers.add(new Teacher(subjectInfo.getTeacherCode(), teacherName));
				}
				else {
					teacherName = getTeacher(subjectInfo.getTeacherCode());
				}
				
				number = 0;
				start = false;
				for(int i = 0; i < week.length(); i++) {
					
					if( week.charAt(i) != '-') {
						start = true;
						
						java.util.Calendar c = java.util.Calendar.getInstance(timezone);
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
						c.setTime(simpleDateFormat.parse(subjectInfo.getStartDate()));
						
						int st = subjectInfo.getSt();
						java.util.Calendar start = getCalendar(c.get(java.util.Calendar.YEAR), 
								c.get(java.util.Calendar.MONTH), 
								c.get(java.util.Calendar.DATE),
								TimeFactory.getTimeBegin(subjectInfo.getBegin()),
								0);
						
						java.util.Calendar end = getCalendar(c.get(java.util.Calendar.YEAR), 
								c.get(java.util.Calendar.MONTH), 
								c.get(java.util.Calendar.DATE),
								TimeFactory.getTimeEnd(subjectInfo.getBegin()+st-1),
								0);
						
						start.add(java.util.Calendar.DATE, number);
						end.add(java.util.Calendar.DATE, number);
						
						start.add(java.util.Calendar.DATE, subjectInfo.getWeekDay().ordinal() - WeekDay.MONDAY.ordinal());
						end.add(java.util.Calendar.DATE, subjectInfo.getWeekDay().ordinal() - WeekDay.MONDAY.ordinal());
						
						DateTime dateTimeStart = new DateTime(start.getTime());
						dateTimeStart.setTimeZone(timezone);
						DateTime dateTimeEnd = new DateTime(end.getTime());
						dateTimeEnd.setTimeZone(timezone);
						
						VEvent event = new VEvent(dateTimeStart, dateTimeEnd, name + " - Phòng: " + subjectInfo.getRoom());
						
						String uniqueID = UUID.randomUUID().toString();
						event.getProperties().add(new Uid(uniqueID+ "-TKB-PTIT"));
						
						//get description
						String code = subject.getCode();
						int group = subject.getGroup();
						int practice = subjectInfo.getPractice();
						String th = "";
						if(practice == 0) {
							th = "";
						}
						else th = " tổ thực hành " + practice;
						
						String room = subjectInfo.getRoom();
						WeekDay weekDay = subjectInfo.getWeekDay();
						int begin = subjectInfo.getBegin();
						
						String classCode = subject.getClassCode();
						
						StringBuilder sb = new StringBuilder();
						sb.append("Mã môn học: " + code + " nhóm " + group + th + "\n") ;
						sb.append("Tên môn học: " + name + "\n");
						sb.append("Phòng học: " + room + "\n");
						sb.append("Thứ: " + TimeFactory.getWeekDay(weekDay) + "\n");
						sb.append("Tiết bắt đầu: " + begin + "\n");
						sb.append("Số tiết: " + st + "\n");
						sb.append("Giảng viên: " + teacherName  + "\n");
						sb.append("Lớp: " + classCode);
						
						event.getProperties().add(new Description(sb.toString()));
						
						//System.out.println("working");
						//add event
						calendar.getComponents().add(event);
					}
					
					if(start) {
						number += 7;
					}
				}
			}
		}
		
		CalendarOutputter outputter = new CalendarOutputter();
		outputter.output(calendar, output);
		
	}
	
	private static boolean teacherExist(String teacherCode) {
		for(Teacher teacher: teachers) {
			if(teacher.getTeacherCode().equals(teacherCode)) return true;
		}
		return false;
	}
	
	private static String getTeacher(String teacherCode) {
		for(Teacher teacher: teachers) {
			if(teacher.getTeacherCode().equals(teacherCode)) return teacher.getName();
		}
		return null;
	}
	
	private static String getTeacherName(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			Element element = doc.getElementById("ctl00_ContentPlaceHolder1_ctl00_lblContentTenSV");
			return element.html();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private static java.util.Calendar getCalendar(int year, int month, int dayOfMonth, int hour, int minute) {
		java.util.Calendar cal = java.util.Calendar.getInstance(timezone);
		cal.set(java.util.Calendar.YEAR, year);
		cal.set(java.util.Calendar.MONTH, month);
		cal.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth);
		cal.set(java.util.Calendar.HOUR_OF_DAY, hour);
		cal.set(java.util.Calendar.MINUTE, minute);
		return cal;
	}
}
