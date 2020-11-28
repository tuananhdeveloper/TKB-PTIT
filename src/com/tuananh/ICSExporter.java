package com.tuananh;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Connection;
import org.jsoup.Connection.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;

import model.Subject;
import model.SubjectInfo;
import net.fortuna.ical4j.validate.ValidationException;
import utils.Converter;
import utils.ICSFatory;

public class ICSExporter {
	
	private String studentCode;
	private String token;
	
	public static String url = "http://qldt.ptit.edu.vn/default.aspx?page=thoikhoabieu&sta=1&id=";
	private ArrayList<Subject> subjects = new ArrayList<Subject>();
		
	public ICSExporter(String studentCode, String token) {
		this.studentCode = studentCode;
		this.token = token;
	}
	
	public boolean export(HttpServletResponse response) throws URISyntaxException, IOException {
		ServletOutputStream outputStream = response.getOutputStream();

		
		try {
			Document doc = Jsoup.connect(url + studentCode).get();
			if(doc.getElementsByClass("grid-roll2").size() == 0) {
				return false;
			}
			else {
				response.setContentType("text/calendar");
				response.addHeader("Content-Disposition", "attachment; filename=\"mycalendar.ics\"");
				Cookie cookie = new Cookie("downloadToken", token);
				cookie.setPath("/");
				cookie.setMaxAge(40);
				response.addCookie(cookie);
				
				Element element = doc.getElementsByClass("grid-roll2").get(0);
				for(Element e: element.children()) {
					Elements nodes = e.child(0).child(0).children();
					String name = nodes.get(SubjectTitle.NAME).html();
					
					List<SubjectInfo> subjectInfos = new ArrayList<SubjectInfo>();
					
					
					WeekDay weekDay[] = Converter.convert(getStringsFromNodes(nodes.get(SubjectTitle.WEEKDAY).children()));
					int begin[] = Converter.convertInts(getStringsFromNodes(nodes.get(SubjectTitle.BEGIN).children()));
					int st[] = Converter.convertInts(getStringsFromNodes(nodes.get(SubjectTitle.ST).children()));
					String room[] = getStringsFromNodes(nodes.get(SubjectTitle.ROOM).children());
					String week[] = getStringsFromNodes(nodes.get(SubjectTitle.WEEK).children());
					String th[] = getStringsFromNodes(nodes.get(SubjectTitle.PRACTICE).children());
					String teacherCode[] = getStringsFromNodes(nodes.get(SubjectTitle.TEACHER_CODE).children());
					
					String startDate[] = getStartDate(nodes.get(SubjectTitle.WEEK).children());
					String endDate[] = getStartDate(nodes.get(SubjectTitle.WEEK).children());
					
					
					for(int i = 0; i < weekDay.length; i++) {
						SubjectInfo subjectInfo = new SubjectInfo(weekDay[i], begin[i], st[i], room[i], week[i], startDate[i], endDate[i]);
						if(th[i].equals("")) {
							subjectInfo.setPractice(0);
						}
						else subjectInfo.setPractice(Integer.parseInt(th[i]));
						subjectInfo.setTeacherCode(teacherCode[i]);
						subjectInfos.add(subjectInfo);
						
					}
					
					Subject subject = new Subject(name, subjectInfos);
					
					//set more properties
					
					String code = nodes.get(SubjectTitle.CODE).html();
					subject.setCode(code);
					
					int group = Integer.parseInt(nodes.get(SubjectTitle.GROUP).html());
					subject.setGroup(group);
					
					String classCode = nodes.get(SubjectTitle.CLASS_CODE).html();
					subject.setClassCode(classCode);
					
					subjects.add(subject);
				}
				
				ICSFatory.exportICS(subjects, outputStream);

			
				return true;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
	
	public String[] getStringsFromNodes(Elements nodes) {
		
		String str[] = new String[nodes.size()];
		if(nodes.size() == 0) return str;
		for(int i = 0; i < nodes.size()-1; i++) {
			str[i] = nodes.get(i).child(0).child(0).child(0).html();
		}
		str[nodes.size()-1] = nodes.get(nodes.size()-1).html();
		return str;
	}
	
	public String[] getStartDate(Elements nodes) {
		String startDate[] = new String[nodes.size()];
		if(nodes.size() == 0) return startDate;
		for(int i = 0; i < nodes.size()-1; i++) {
			Element tmp = nodes.get(i).child(0).child(0).child(0);
			startDate[i] = tmp.attr("onmouseover").split("'")[1].split("--")[0];
		}
		
		Element tmp = nodes.get(nodes.size()-1);
		startDate[nodes.size()-1] = tmp.attr("onmouseover").split("'")[1].split("--")[0];
		return startDate;
	}
	
	public String[] getEndDate(Elements nodes) {
		String endDate[] = new String[nodes.size()];
		if(nodes.size() == 0) return endDate;
		for(int i = 0; i < nodes.size()-1; i++) {
			Element tmp = nodes.get(i).child(0).child(0).child(0);
			endDate[i] = tmp.attr("onmouseover").split("'")[1].split("--")[1];
		}
		
		Element tmp = nodes.get(nodes.size()-1);
		endDate[nodes.size()-1] = tmp.attr("onmouseover").split("'")[1].split("--")[1];
		return endDate;
	}
	
}
