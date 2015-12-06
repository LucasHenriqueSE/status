package br.com.fornax.corporativo.status.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFormat {
	
	public String formatCalendar(String pattern,Calendar calendar){
		
		SimpleDateFormat format1 = new SimpleDateFormat(pattern);
		String novaData = format1.format(calendar.getTime());

		return novaData;
	}

}
