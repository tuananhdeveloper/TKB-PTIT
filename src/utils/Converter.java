package utils;
import com.tuananh.WeekDay;

public class Converter {
	
	public static WeekDay[] convert(String weekDay[]) {
		
		WeekDay wD[] = new WeekDay[weekDay.length];
		for(int i = 0; i < weekDay.length; i++) {
			wD[i] = convert(weekDay[i]);
		}
		return wD;
	}
	
	public static int[] convertInts(String str[]) {
		int newArr[] = new int[str.length];
		for(int i = 0; i < str.length; i++) {
			newArr[i] = Integer.parseInt(str[i]);
		}
		return newArr;
	}
	
	
	public static WeekDay convert(String weekDay) {
		switch (weekDay) {
		case "Hai":
			return WeekDay.MONDAY;
		case "Ba": 
			return WeekDay.TUESDAY;
		case "Tư":
			return WeekDay.WEDNESDAY;
		case "Năm":
			return WeekDay.THURSDAY;
		case "Sáu":
			return WeekDay.FRIDAY;
		case "Bảy":
			return WeekDay.SATURDAY;
		case "CN":
			return WeekDay.SUNDAY;
		default:
			return WeekDay.MONDAY;
		}
	}
}
