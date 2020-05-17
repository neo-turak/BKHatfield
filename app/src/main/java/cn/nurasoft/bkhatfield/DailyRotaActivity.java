package cn.nurasoft.bkhatfield;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.nurasoft.bkhatfield.CommonAlpha.HelperClass;


public class DailyRotaActivity extends AppCompatActivity {

    ImageView im_mon,im_tue,im_wed,im_thur,im_fri,im_sat,im_sun;
    TextView mon, tue, wed, thur, fri, sat, sun;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_rota);
        SharedPreferences pref=getApplicationContext().getSharedPreferences("config",MODE_PRIVATE);


        mon = findViewById(R.id.Monday);
        mon.setText(pref.getString("Monday","0"));
        tue = findViewById(R.id.Tuesday);
        tue.setText(pref.getString("Tuesday","0"));
        wed = findViewById(R.id.Wednesday);
        wed.setText(pref.getString("Wednesday","0"));
        thur = findViewById(R.id.Thursday);
        thur.setText(pref.getString("Thursday","0"));
        fri = findViewById(R.id.Friday);
        fri.setText(pref.getString("Friday","0"));
        sat = findViewById(R.id.Saturday);
        sat.setText(pref.getString("Saturday","0"));
        sun = findViewById(R.id.Sunday);
        sun.setText(pref.getString("Sunday","0"));

        im_mon=findViewById(R.id.img_mon);
        im_tue=findViewById(R.id.img_tue);
        im_wed=findViewById(R.id.img_wed);
        im_thur=findViewById(R.id.img_thur);
        im_fri=findViewById(R.id.img_fri);
        im_sat=findViewById(R.id.img_sat);
        im_sun=findViewById(R.id.img_sun);


        HelperClass help =new HelperClass(this);
        String name=help.getDay();

        switch (name){
            case "Monday":
                im_mon.setImageResource(R.drawable.arrow);
                break;
            case "Tuesday":
                im_tue.setImageResource(R.drawable.arrow);
                break;
            case "Wednesday":
                im_wed.setImageResource(R.drawable.arrow);
                break;
            case "Thursday":
                im_thur.setImageResource(R.drawable.arrow);
                break;
            case "Friday":
                im_fri.setImageResource(R.drawable.arrow);
                break;
            case "Saturday":
                im_sat.setImageResource(R.drawable.arrow);
                break;
            case "Sunday":
                im_sun.setImageResource(R.drawable.arrow);
                break;
                default:
                    im_mon.setImageResource(0);
                    im_tue.setImageResource(0);
                    im_wed.setImageResource(0);
                    im_thur.setImageResource(0);
                    im_fri.setImageResource(0);
                    im_sat.setImageResource(0);
                    im_sun.setImageResource(0);
                    break;
        }

        if (mon.getText().toString().contains("OFF")) {
            mon.setText("OFF");
            mon.setTextColor(Color.parseColor("#12F008"));  }

        if (tue.getText().toString().contains("OFF")) {
            tue.setText("OFF");
            tue.setTextColor(Color.parseColor("#12F008"));  }

        if (wed.getText().toString().contains("OFF")) {
            wed.setText("OFF");
            wed.setTextColor(Color.parseColor("#12F008"));  }

        if (thur.getText().toString().contains("OFF")) {
            thur.setText("OFF");
            thur.setTextColor(Color.parseColor("#12F008")); }

        if (fri.getText().toString().contains("OFF")){
            fri.setText("OFF");
            fri.setTextColor(Color.parseColor("#12F008"));  }

        if (sat.getText().toString().contains("OFF")) {
            sat.setText("OFF");
            sat.setTextColor(Color.parseColor("#12F008"));  }

        if (sun.getText().toString().contains("OFF")) {
            sun.setText("OFF");
            sun.setTextColor(Color.parseColor("#12F008"));  }
        }


}



