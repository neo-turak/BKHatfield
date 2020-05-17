package cn.nurasoft.bkhatfield;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.sqlcipher.database.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class LabelMakerActivity extends AppCompatActivity {

    ListView lbl_view;
    DatabaseHelperClass dbase;
    SQLiteDatabase db;
    Context context;

    TextView pull_date, pull_time, thaw_date, thaw_time, discard_date, discard_time;
    TextView BP_date, BP_time, BT_date, BT_time, BD_date, BD_time;
    int total;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labelmaker);
        context = this;

        dbase = new DatabaseHelperClass(LabelMakerActivity.this);
        db = dbase.getReadableDatabase("");

        dbase.openDataBase();
        total=dbase.getTotal();
        dbase.close();

        dbase.openDataBase();
        String[] name = dbase.Get_Name(total);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, name);
        dbase.close();

        lbl_view = findViewById(R.id.result);

        lbl_view.setAdapter(adapter);

        lbl_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);

                Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
                dbase.openDataBase();
                final String[] details = dbase.Get_Label(item);
                dbase.close();

                LayoutInflater factory = LayoutInflater.from(context);
                final View deleteDialogView = factory.inflate(R.layout.label_content, null);
                final AlertDialog deleteDialog = new AlertDialog.Builder(context).create();
                deleteDialog.setView(deleteDialogView);

                pull_date = deleteDialogView.findViewById(R.id.pull_date);
                pull_time = deleteDialogView.findViewById(R.id.pull_time);
                thaw_date = deleteDialogView.findViewById(R.id.thaw_date);
                thaw_time = deleteDialogView.findViewById(R.id.thaw_time);
                discard_date = deleteDialogView.findViewById(R.id.discard_date);
                discard_time = deleteDialogView.findViewById(R.id.discard_time);

                BP_date = deleteDialogView.findViewById(R.id.pull_date1);
                BP_time = deleteDialogView.findViewById(R.id.pull_time1);
                BT_date = deleteDialogView.findViewById(R.id.thaw_date1);
                BT_time = deleteDialogView.findViewById(R.id.thaw_time1);
                BD_date = deleteDialogView.findViewById(R.id.discard_date1);
                BD_time = deleteDialogView.findViewById(R.id.discard_time1);

                pull_date.setText("");
                pull_time.setText("");
                thaw_date.setText("");
                thaw_time.setText("");
                discard_date.setText("");
                discard_time.setText("");
                BP_date.setText("");
                BP_time.setText("");
                BT_date.setText("");
                BT_time.setText("");
                Calendar cal=Calendar.getInstance();

                pull_date.setText(getDate(0));
                Log.e("getDate",getDate(0));
                pull_time.setText(getTime(0));

                if(details[1].equals("0")){
                    thaw_date.setText("");
                    thaw_time.setText("");
                }else {
                    if (cal.get(Calendar.HOUR_OF_DAY)>11){
                        thaw_date.setText(getDate(1));
                        thaw_time.setText(getTime(Integer.parseInt(details[1])-24));
                    }else {
                        thaw_date.setText(getDate(0));
                        thaw_time.setText(getTime(Integer.parseInt(details[1])));
                    }
                }

                if (details[2].equals("-1")){
                    thaw_date.setText("10-03");
                    thaw_date.setVisibility(View.INVISIBLE);
                    thaw_time.setText("19:30");
                    thaw_time.setVisibility(View.INVISIBLE);
                    discard_date.setText(" BoB");
                }else {
                     if ((details[2]).equals("0.5")) {
                         discard_date.setText(getDate(0));
                         discard_time.setText(getTime(12));
                     }else if(details[2].equals("0.17")){
                             discard_time.setText(getTime(4));
                             discard_date.setText(getDate(0));
                     }else {
                         discard_date.setText(getDate(Integer.parseInt(details[2])));
                         discard_time.setText(getTime(0));
                     }
                }

                if (details[3].equals("0") && details[4].equals("0")){
                    Log.e("3:",details[3]);
                    Log.e("4:",details[4]);
                    BP_date.setText("");
                    BT_time.setText("");
                }else {
                    BP_date.setText(getDate(0));
                    BP_time.setText(getTime(0));
                }
                if (details[3].equals("0")){
                    BT_date.setText("");
                    BT_time.setText("");
                }else {
                    BT_date.setText(getDate(0));
                    BT_time.setText(getTime(Integer.parseInt(details[3])));
                }
                if ((details[4].equals("0.17") || details[4].equals("0.5"))){
                    BD_date.setText(getDate(0));
                    if (details[4].equals("0.17")) {
                        BD_time.setText(getTime(4));
                    }else {
                        BD_time.setText(getTime(Integer.parseInt(details[4])*24));
                    }
                }else if (details[4].equals("0")){
                    BD_date.setText("");
                    BD_time.setText("");
                }else {
                    BD_date.setText(getDate(Integer.parseInt(details[3])));
                    BD_time.setText(getTime(0));
                }

                if (item.equals("Smoke Bacon")){
                    int hour= Integer.parseInt(thaw_time.getText().subSequence(0,2).toString());
                    int time=Integer.parseInt(thaw_time.getText().subSequence(3,5).toString());

                    Log.e("时间：",hour+"");
                    Log.e("分钟:",time+"");

                   BP_date.setText(thaw_date.getText());
                   BP_time.setText(thaw_time.getText());
                   BT_date.setText(BP_date.getText());
                   BD_date.setText(BP_date.getText());

                   BT_time.setText((hour+1)+":"+time);
                   BD_time.setText((hour+4)+":"+time);
                }

                deleteDialogView.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //your business logic
                        deleteDialog.dismiss();
                    }
                });

                deleteDialog.show();
            }
        });
    }


    public static String getDate(int d) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM");
        c.add(Calendar.DATE, d);  // number of days to add
        /*
        this part will add the day for thaw or discard.
         */
        String end_date = df.format(c.getTime());
        return end_date;
    }

    /*
    the below for calculating to half hour or integer
     */
    public static String getTime(int add) {
        String result = "";
        Calendar c = Calendar.getInstance();
        int t = c.get(Calendar.MINUTE);
        int h=c.get(Calendar.HOUR_OF_DAY);
        if (t >= 0 && t <= 15) {
            result = h+add+":00";
        }
        if (t < 45 && t > 15) {
            result = h+add+":30";
        }
        if (t >= 45 && t <= 60) {
            result = (h+add+1)+":00";
        }
        return result;
    }
}
