package cn.nurasoft.bkhatfield.TemperatureAlpha;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import net.sqlcipher.database.SQLiteDatabase;

import cn.nurasoft.bkhatfield.DatabaseHelperClass;
import cn.nurasoft.bkhatfield.R;

public class TemperatureActivity extends AppCompatActivity {
    Context context;
    ListView listView;
    DatabaseHelperClass dbase;
    SQLiteDatabase base;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temprature);

        context=this;
        listView = findViewById(R.id.temp_list);

        dbase = new DatabaseHelperClass(TemperatureActivity.this);
        base = dbase.getReadableDatabase("");

        dbase.openDataBase();
        int total=dbase.totalTemprature();
        dbase.close();

        dbase.openDataBase();
        final String[] temps = dbase.getTempratureList(total);
        dbase.close();


        final Adapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,temps);

        listView.setAdapter((ListAdapter) adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LayoutInflater factory = LayoutInflater.from(context);
                final View deleteDialogView = factory.inflate(R.layout.activity_temprature_content, null);
                final AlertDialog deleteDialog = new AlertDialog.Builder(context).create();
                deleteDialog.setView(deleteDialogView);

                dbase.openDataBase();
                Log.e("Name:", ((String) parent.getItemAtPosition(position)));
                TemperatureStructure data=dbase.getTemprature((String)parent.getItemAtPosition(position));
                dbase.close();
                Log.e("Name:",data.getName());

                TextView name=deleteDialogView.findViewById(R.id.temp_name);
                TextView type=deleteDialogView.findViewById(R.id.temp_type);
                TextView max=deleteDialogView.findViewById(R.id.temp_max);
                TextView min=deleteDialogView.findViewById(R.id.temp_min);

                name.setText(data.getName());
                type.setText(data.getGroup());
                max.setText(data.getMax());
                min.setText(data.getMin());
                dbase.close();

                deleteDialogView.findViewById(R.id.temp_OK).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.dismiss();
                    }
                });
                deleteDialog.show();
            }

        });

    }
}

