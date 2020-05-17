package cn.nurasoft.bkhatfield;


import android.app.ActivityOptions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import cn.nurasoft.bkhatfield.CriticalsAlpha.ActivityCriticals;
import cn.nurasoft.bkhatfield.CommonAlpha.HelperClass;
import cn.nurasoft.bkhatfield.TemperatureAlpha.TemperatureActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView OSAT;
    Context context;

    HelperClass helper=new HelperClass(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        OSAT = findViewById(R.id.osat);

        TextView day_rota = findViewById(R.id.day_rota);
        TextView day_rota_Details = findViewById(R.id.day_rota_details);

         day_rota.setText(helper.getDay());

         day_rota_Details.setText(helper.getRota());
         if (day_rota_Details.getText().equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("Rota Error")
                    .setMessage("Please set up rota in Settings")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent();
                            intent.setClass(context,SettingsActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(android.R.string.no,null)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
         }
         if (day_rota_Details.getText().toString().contains("OFF")){
             day_rota_Details.setText("OFF");
         }

        try {
            OSAT.setText("OSAT Score:"+helper.getOSAT());
        }catch (Exception e){
            Log.e("Exception:",e.getMessage());
        }


        OSAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OSAT.setText("OSAT Score:"+helper.getOSAT());
                Toast.makeText(MainActivity.this, "Use Settings for update the OSAT", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NotNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_links) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    getWindow().setExitTransition(new Explode());
                    Intent intent=new Intent();
                    intent.setClass(MainActivity.this,UsefulLinksActivity.class);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                }


        } else if (id == R.id.nav_hand_wash) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setExitTransition(new Explode());
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HandWashActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }
        }else if (id == R.id.nav_uniform) {

            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                getWindow().setExitTransition(new Explode());
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,UniformActivity.class);
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }

        } else if (id == R.id.nav_sandwich) {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                getWindow().setExitTransition(new Explode());
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,SandwichesActivity.class);
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }

        }else if (id == R.id.nav_label){
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                getWindow().setExitTransition(new Explode());
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,LabelMakerActivity.class);
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }
        }else if (id == R.id.nav_critical){
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                getWindow().setExitTransition(new Explode());
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, ActivityCriticals.class);
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }

        }else if (id == R.id.nav_temperature) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setExitTransition(new Explode());
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TemperatureActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }
        }else if(id==R.id.nav_rota) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setExitTransition(new Explode());
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DailyRotaActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }
        }else if (id==R.id.nav_tools){
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                getWindow().setExitTransition(new Explode());
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,PrintDialogActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }
        }else if (id==R.id.nav_license){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setExitTransition(new Explode());
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HelpActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


   }

