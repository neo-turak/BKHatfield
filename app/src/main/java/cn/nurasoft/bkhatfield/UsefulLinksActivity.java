package cn.nurasoft.bkhatfield;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import net.sqlcipher.database.SQLiteDatabase;

import org.w3c.dom.Text;

public class UsefulLinksActivity extends AppCompatActivity {

    ListView lists;
    DatabaseHelperClass dbase;
    SQLiteDatabase db;
    String[] store_name;
    TextView v_name,v_post_code,v_address,v_tell,v_resturant_manager;
    Button v_ok;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            setContentView(R.layout.activity_usefull_link);

            lists=findViewById(R.id.story_list);

            dbase =new DatabaseHelperClass(this);
            db= dbase.getReadableDatabase("");
            /*
            get store name from database;
             */
            dbase.openDataBase();
            store_name= dbase.getStoreList();
            dbase.close();

        Adapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,store_name);

        lists.setAdapter((ListAdapter)adapter);

        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=(String)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent();
//                intent.setClass(getApplicationContext(),UsefulLinksActivity.class);
//                intent.putExtra("name",lists.getItemIdAtPosition(position)+"");
//                startActivity(intent);
                dbase.openDataBase();
                String[] result=dbase.getStore_details(name);
                dbase.close();
                LayoutInflater factory = LayoutInflater.from(getApplicationContext());
                final View DialogView = factory.inflate(R.layout.links_view,null);
                final AlertDialog Dialog = new AlertDialog.Builder(UsefulLinksActivity.this).create();
                Dialog.setView(DialogView);

                 v_name=DialogView.findViewById(R.id.view_name);
                 v_address=DialogView.findViewById(R.id.view_address);
                 v_post_code=DialogView.findViewById(R.id.view_post_code);
                 v_resturant_manager=DialogView.findViewById(R.id.view_store_manager);
                 v_tell=DialogView.findViewById(R.id.view_Tell);

                 v_name.setText(result[0]);
                 v_address.setText(result[1]);
                 v_post_code.setText(result[2]);
                 v_tell.setText(result[3]);
                 v_resturant_manager.setText(result[4]);

                 DialogView.findViewById(R.id.view_ok).setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Dialog.dismiss();
                     }
                 });
               Dialog.show();
            }
        });



    }
}
