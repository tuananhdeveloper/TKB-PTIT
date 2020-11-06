package utils;

import com.tuananh.WeekDay;

public class TimeFactory {
	static int[] timeBegin = {7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19};
	static int[] timeEnd = {8, 9, 10, 11, 1, 14, 15, 16, 17, 18, 19, 20};
	
	public static int getTimeBegin(int begin) {
		return timeBegin[begin-1];
	}
	
	public static int getTimeEnd(int begin) {
		return timeEnd[begin-1];
	}
	
	public static String getWeekDay(WeekDay weekDay) {
		switch (weekDay) {
		case MONDAY:
			return "Thứ Hai";
		case TUESDAY:
			return "Thứ Ba";
		case WEDNESDAY:
			return "Thứ Tư";
		case THURSDAY:
			return "Thứ Năm";
		case FRIDAY:
			return "Thứ Sáu";
		case SATURDAY:
			return "Thứ Bảy";
		case SUNDAY:
			return "Chủ Nhật";
		}
		return null;
	}
}
