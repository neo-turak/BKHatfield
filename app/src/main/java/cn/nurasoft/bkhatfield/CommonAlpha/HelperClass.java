package cn.nurasoft.bkhatfield.CommonAlpha;


import android.content.Context;
import android.content.SharedPreferences;


import java.util.Calendar;


public class HelperClass {


    private static Calendar cal = Calendar.getInstance();

    private String weekName = "";

    private Context context;

  public HelperClass(Context Getcontext){

        context=Getcontext;
    }

    public  String getOSAT(){
      SharedPreferences pref=context.getSharedPreferences("config",Context.MODE_PRIVATE);
      return pref.getString("OSAT",null);
    }

    public String getRota(){
        SharedPreferences pref=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return pref.getString(weekName,null);
    }

  public String  getDay() {
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        switch (day) {
            case 2:
                weekName = "Monday";
                break;
            case 3:
                weekName = "Tuesday";
                break;
            case 4:
                weekName = "Wednesday";
                break;
            case 5:
                weekName = "Thursday";
                break;
            case 6:
                weekName = "Friday";
                break;
            case 7:
                weekName = "Saturday";
                break;
            case 8:
                weekName = "Sunday";
                break;
        }
        return weekName;
    }

}
