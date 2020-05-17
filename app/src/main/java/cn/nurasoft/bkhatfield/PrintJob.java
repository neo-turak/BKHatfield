package cn.nurasoft.bkhatfield;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class PrintJob extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Button print;
    CheckBox safe,holiday,waste,daily,job,cash;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_job);
        context=this;
        print = findViewById(R.id.print);

        AssetManager manager = this.getAssets();

        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent PrintIntent = new Intent(PrintJob.this, PrintDialogActivity.class);
                PrintIntent.setDataAndType(Uri.fromFile(new File("/data/data/"+context.getPackageName()+"/databases/webpwd.pdf")), "application/pdf");
                PrintIntent.putExtra("Printer", "application/pdf");
                startActivity(PrintIntent);
            }
        });

        safe = findViewById(R.id.Safe_Counting);
        safe.setOnCheckedChangeListener(this);
        holiday = findViewById(R.id.Holiday_requst);
        holiday.setOnCheckedChangeListener(this);
        waste = findViewById(R.id.Waste_sheet);
        waste.setOnCheckedChangeListener(this);
        daily = findViewById(R.id.Daily_Counting);
        daily.setOnCheckedChangeListener(this);
        job = findViewById(R.id.Job_Application);
        job.setOnCheckedChangeListener(this);
        cash = findViewById(R.id.Cash_responsibility);
        cash.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.isChecked()){
            buttonView.setTextColor(Color.parseColor("#ffffff"));
        }else {
            buttonView.setTextColor(Color.parseColor("#008577"));
        }
    }
}
