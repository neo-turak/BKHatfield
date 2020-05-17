package cn.nurasoft.bkhatfield;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;
import java.util.Locale;


public class WelcomeActivity extends AppCompatActivity {

    DatabaseHelperClass dbhelper;
    SQLiteDatabase db;
    TextView status;
    ProgressBar progressBar;

    String language;
    Context context;
    public static final int CODE = 1;
    private String[] list = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        context = this;
        status = findViewById(R.id.status);
        progressBar = findViewById(R.id.progress);
        /*
        create channel for REV.
         */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("regular", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, list, CODE);
        } else {
            Intent intent = new Intent();
            intent.setClass(context, MainActivity.class);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(50);
                        status.setText("Initializing.");
                        progressBar.setProgress(10);
                        Thread.sleep(50);
                        status.setText("Initializing..");
                        progressBar.setProgress(20);
                        Thread.sleep(50);
                        status.setText("Initializing...");
                        progressBar.setProgress(30);

                        Thread.sleep(50);
                        status.setText("loading Resources.");
                        progressBar.setProgress(40);
                        Thread.sleep(100);
                        status.setText("loading Resources..");
                        progressBar.setProgress(50);
                        Thread.sleep(100);
                        status.setText("loading Resources...");
                        progressBar.setProgress(60);
                        Copy();
                        Thread.sleep(300);
                        status.setText("Done!");
                        progressBar.setProgress(100);

                        //Do something after 100ms

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }).start();
            startActivity(intent);
        }
    }

    private void Copy() {
        dbhelper = new DatabaseHelperClass(WelcomeActivity.this);
        dbhelper.CheckDir(context, new File("/data/data/" + context.getPackageName()));
        language = Locale.getDefault().getDisplayLanguage();
        db = dbhelper.getReadableDatabase("");
        db.close();
        dbhelper.onUpgrade(db, 1, 2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, list, CODE);
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                        status.setText("Initializing...");
                        progressBar.setProgress(10);
                    /*
                    create notification channel
                     */
                        Thread.sleep(200);
                        status.setText("Permission checking");
                        progressBar.setProgress(30);

                        int permissionCheck = ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(WelcomeActivity.this, new String[]{
                                    Manifest.permission.INTERNET,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            }, CODE);
                            Copy();
                        } else {
                            Thread.sleep(50);
                            status.setText("Permission checking");
                            progressBar.setProgress(70);
                            Copy();
                        }
                        Thread.sleep(200);
                        status.setText("Complished!");
                        progressBar.setProgress(90);

                        Thread.sleep(100);
                        status.setText("Go!");
                        progressBar.setProgress(100);
                        //Do something after 100ms
                        Intent intent = new Intent();
                        intent.setClass(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        Log.e("Exception:", e.getMessage());
                    }
                }
            }).start();
        }
    }
}

