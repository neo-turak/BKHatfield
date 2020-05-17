package cn.nurasoft.bkhatfield;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SandwichesActivity extends AppCompatActivity {

    Adapter adapter;
    ListView view;
    Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwiches);
        context=this;
        view=findViewById(R.id.sandwich_list);


        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sandwiches));

        view.setAdapter((ListAdapter) adapter);
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(context,SandwichViewActivity.class);
                intent.putExtra("ID",id+"");
                Log.e("ID",id+"");
                startActivity(intent);
            }
        });
    }
}
