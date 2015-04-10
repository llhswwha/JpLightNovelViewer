package oneway.NovelViewer.Data;

import android.text.format.Time;
import android.widget.TimePicker;

public class MTime {
	public int hour, minute;
	public MTime(TimePicker tp)
	{
		hour=tp.getCurrentHour();
		minute=tp.getCurrentMinute();
	}
	public MTime()
	{
		hour=0;
		minute=0;
	}
    public MTime(String time)
    {
        if (time == "")
        {
            Time today = new Time();
            today.setToNow();
            hour = today.hour;
            minute = today.minute;
        }
        else if (time.contains(":"))
        {
            String[] parts = time.split(":");
            hour = ParseInt(parts[0].trim());
            minute = ParseInt(parts[1].trim());
        }
        else
        {
            hour = Integer.parseInt(time.substring(0, 2));
            minute = Integer.parseInt(time.substring(2));
        }
    }
    int ParseInt(String str)
    {
    	char[] cs=str.toCharArray();
    	if(cs.length==3)
    	{
    		str=str.substring(1);
    	}
    	return Integer.parseInt(str);
    }
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return String.format("%2d:%2d", hour,minute);
		String text="";
		if(hour<10)
			text+="0"+hour;
		else
			text+=hour;
		text+=":";
		if(minute<10)
			text+="0"+minute;
		else
			text+=minute;
		return text;
	}
    public static MTime Sub(MTime t1, MTime t2)
    {
        MTime t3 = new MTime();
        int m1 = t1.minute; int m2 = t2.minute;
        int h1 = t1.hour; int h2 = t2.hour;
        int m3 = m1 - m2;
        
        if (m3 < 0)
        {
            m3 += 60;
            h2 += 1;
        }
        int h3 = h1 - h2;
        if (h3 < 0)
        {
            h3+=24;
        }
        t3.minute=m3;t3.hour=h3;
        return t3;
    }
    public int GetTotalMinute()
    {
    	return hour * 60 + minute;
    }
    public static String GetTodayFileName(String exetension)
    {
 
    	String date=GetTodayText();
        String fileName=FileNames.GetFileName(date, exetension);
        return fileName;
    }
    public static String GetTodayText()
    {
       	Time today=new Time();
    	today.setToNow();
    	
        int year = today.year;
        int month = today.month+1;
        int day = today.monthDay;
        String date = String.format("%d/%d/%d",year, month, day);
        return date;
    }
    
}
