package cn.nurasoft.bkhatfield;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;



public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final String url = "http://dylike.nurasoft.cn/rota/";

    private EditText json;
    private EditText id;
    private Spinner mon,tue,wed,thur,fri,sat,sun;
    private Spinner mon_end,tue_end,wed_end,thur_end,fri_end,sat_end,sun_end;

    private ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        final Calendar c = Calendar.getInstance();
        Button apply = findViewById(R.id.apply);

        id = findViewById(R.id.payroll);

        SharedPreferences share=getSharedPreferences("config",MODE_PRIVATE);
        id.setText(share.getString("OSAT", null)+"");

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("config", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Date", c.getTime().toString());
                    editor.putString("OSAT", id.getText()+"");
                    if (mon.getSelectedItem().toString().equals("OFF")){
                        editor.putString("Monday", "OFF");
                    }else {
                        editor.putString("Monday", mon.getSelectedItem().toString()+"-"+mon_end.getSelectedItem().toString());
                    }

                    if (tue.getSelectedItem().toString().equals("OFF")){
                        editor.putString("Tuesday","OFF");
                    }else {
                        editor.putString("Tuesday", tue.getSelectedItem().toString()+"-"+tue_end.getSelectedItem().toString());
                    }

                    if (wed.getSelectedItem().toString().equals("OFF")){
                        editor.putString("Wednesday","OFF");
                    }else {
                        editor.putString("Wednesday", wed.getSelectedItem().toString()+"-"+wed_end.getSelectedItem().toString());
                    }

                    if (thur.getSelectedItem().toString().equals("OFF")){
                        editor.putString("Thursday","OFF");
                    }else {
                        editor.putString("Thursday", thur.getSelectedItem().toString()+"-"+thur_end.getSelectedItem().toString());
                    }

                    if (fri.getSelectedItem().toString().equals("OFF")){
                        editor.putString("Friday","OFF");
                    }else {
                        editor.putString("Friday", fri.getSelectedItem().toString()+"-"+fri_end.getSelectedItem().toString());
                    }

                    if (sat.getSelectedItem().toString().equals("OFF")){
                       editor.putString("Saturday","OFF") ;
                    }else {
                        editor.putString("Saturday", sat.getSelectedItem().toString()+"-"+sat_end.getSelectedItem().toString());
                    }

                   if (sun.getSelectedItem().toString().equals("OFF")){
                       editor.putString("Sunday","OFF");
                   }else {
                       editor.putString("Sunday", sun.getSelectedItem().toString()+"-"+sun_end.getSelectedItem().toString());
                   }
                   if (id.getText().equals("")){
                       Toast.makeText(getApplicationContext(), "OSAT score must be entered!", Toast.LENGTH_LONG).show();
                   }else {
                       editor.apply();
                       Toast.makeText(getApplicationContext(), "Applied successfully!", Toast.LENGTH_LONG).show();
                   }
            }
        });


        mon=findViewById(R.id.S_Monday);
        tue=findViewById(R.id.S_Tuesday);
        wed=findViewById(R.id.S_Wednesday);
        thur=findViewById(R.id.S_Thursday);
        fri=findViewById(R.id.S_Friday);
        sat=findViewById(R.id.S_Saturday);
        sun=findViewById(R.id.S_Sunday);

        mon_end=findViewById(R.id.E_Monday);
        tue_end=findViewById(R.id.E_Tuesday);
        wed_end=findViewById(R.id.E_Wednesday);
        thur_end=findViewById(R.id.E_Thursday);
        fri_end=findViewById(R.id.E_Friday);
        sat_end=findViewById(R.id.E_Saturday);
        sun_end=findViewById(R.id.E_Sunday);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner


        mon.setAdapter(adapter);
        mon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if(parent.getItemAtPosition(position).equals("OFF")){
                   mon_end.setVisibility(View.INVISIBLE);
               }else {
                   mon_end.setVisibility(View.VISIBLE);
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tue.setAdapter(adapter);
        tue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("OFF")){
                    tue_end.setVisibility(View.INVISIBLE);
                }else {
                    tue_end.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        wed.setAdapter(adapter);
        wed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("OFF")){
                    wed_end.setVisibility(View.INVISIBLE);
                }else {
                    wed_end.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        thur.setAdapter(adapter);
        thur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("OFF")){
                    thur_end.setVisibility(View.INVISIBLE);
                }else {
                    thur_end.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fri.setAdapter(adapter);
        fri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("OFF")){
                    fri_end.setVisibility(View.INVISIBLE);
                }else {
                    fri_end.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sat.setAdapter(adapter);
        sat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("OFF")){
                    sat_end.setVisibility(View.INVISIBLE);
                }else {
                    sat_end.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sun.setAdapter(adapter);
        sun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("OFF")){
                    sun_end.setVisibility(View.INVISIBLE);
                }else {
                    sun_end.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mon_end.setAdapter(adapter);
        tue_end.setAdapter(adapter);
        wed_end.setAdapter(adapter);
        thur_end.setAdapter(adapter);
        fri_end.setAdapter(adapter);
        sat_end.setAdapter(adapter);
        sun_end.setAdapter(adapter);

        mon.setOnTouchListener(touchListener);
        tue.setOnTouchListener(touchListener);
        wed.setOnTouchListener(touchListener);
        thur.setOnTouchListener(touchListener);
        fri.setOnTouchListener(touchListener);
        sat.setOnTouchListener(touchListener);
        sun.setOnTouchListener(touchListener);

        mon_end.setOnTouchListener(touchListener);
        tue_end.setOnTouchListener(touchListener);
        wed_end.setOnTouchListener(touchListener);
        thur_end.setOnTouchListener(touchListener);
        fri_end.setOnTouchListener(touchListener);
        sat_end.setOnTouchListener(touchListener);
        sun_end.setOnTouchListener(touchListener);

    }

    private Spinner.OnTouchListener touchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            InputMethodManager imm= ((InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE));
            imm.hideSoftInputFromWindow(id.getWindowToken(), 0);
            return false;
        }
    };

}
