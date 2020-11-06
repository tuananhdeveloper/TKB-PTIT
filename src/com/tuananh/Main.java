package com.tuananh;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.UUID;

import org.apache.axiom.util.UIDGenerator;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;


public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
//		TimeZone timezone = registry.getTimeZone("Asia/Ho_Chi_Minh");
//		
//		java.util.Calendar cal = java.util.Calendar.getInstance(timezone);
//		cal.set(java.util.Calendar.YEAR, 2020);
//		cal.set(java.util.Calendar.MONTH, java.util.Calendar.NOVEMBER);
//		cal.set(java.util.Calendar.DAY_OF_MONTH, 4);
//		cal.set(java.util.Calendar.HOUR_OF_DAY, 14);
//		cal.set(java.util.Calendar.MINUTE, 0);
//		
//		java.util.Calendar cal2 = java.util.Calendar.getInstance(timezone);
//		cal2.set(java.util.Calendar.YEAR, 2020);
//		cal2.set(java.util.Calendar.MONTH, java.util.Calendar.NOVEMBER);
//		cal2.set(java.util.Calendar.DAY_OF_MONTH, 4);
//		cal2.set(java.util.Calendar.HOUR_OF_DAY, 16);
//		cal2.set(java.util.Calendar.MINUTE, 0);
//		
//		DateTime dtStart = new DateTime(cal.getTime());
//		dtStart.setTimeZone(timezone);
//		DateTime dtEnd = new DateTime(cal2.getTime());
//		dtEnd.setTimeZone(timezone);
//		
//		String uniqueID = UUID.randomUUID().toString();
//		VEvent event = new VEvent(dtStart, dtEnd, "Subject");
//		
//		UidGenerator ug = new UidGenerator("1");
//		event.getProperties().add(ug.generateUid());
//		
//		FileOutputStream fout = new FileOutputStream("D:/mycalendar.ics");
//		
//
//		
//		
//		Calendar calendar = new Calendar();
//		calendar.getProperties().add(new ProdId("-//Tuan Anh//iCal4j 1.0//VN"));
//		calendar.getProperties().add(Version.VERSION_2_0);
//		calendar.getProperties().add(CalScale.GREGORIAN);
//		calendar.getComponents().add(event);
//		
//		CalendarOutputter outputter = new CalendarOutputter();
//		outputter.output(calendar, fout);
	}

}
