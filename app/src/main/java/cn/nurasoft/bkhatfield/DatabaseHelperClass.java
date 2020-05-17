package cn.nurasoft.bkhatfield;


import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import net.sqlcipher.Cursor;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteException;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import cn.nurasoft.bkhatfield.TemperatureAlpha.TemperatureStructure;

public class DatabaseHelperClass extends SQLiteOpenHelper {
    private static final String fileName = "bkhatfield.db";
    private String TAG = "TAG";
    private final static String pwd = "";
    private static final int DB_VERSION = 1;   // 数据库版本
    private static String DB_PATH = "";
    private static SQLiteDatabase sqLiteDatabase;

    private String[] arg = new String[12];

   public DatabaseHelperClass(Context context) {

        super(context, fileName, null, DB_VERSION);
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        Log.e("HARD", DB_PATH);
        SQLiteDatabase.loadLibs(context);
    }


   public void openDataBase() throws SQLException {
        String myPath = DB_PATH + fileName;
        sqLiteDatabase = SQLiteDatabase.openDatabase(myPath, pwd, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
        super.close();
    }

    public int getTotal() {
        int total = 0;
        String query = "select count(id) from ptd";
        try {
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                total = cursor.getInt(0);
            }
            cursor.close();
            close();
        } catch (SQLiteException ex) {
            Log.e("ERRPR:", ex.getMessage());
        }
        return total;
    }

    String[] Get_Label(String CName) {
        arg[0] = null;
        arg[1] = null;
        arg[2] = null;
        arg[3] = null;
        arg[4] = null;
        String query = "select * from ptd where name='" + CName + "'";
        Log.e(TAG, query);
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    arg[0] = cursor.getString(1); //name
                    arg[1] = cursor.getString(2); //thaw_hour
                    arg[2] = cursor.getString(3); //P_discard_day
                    arg[3] = cursor.getString(4);//thaw_hor
                    arg[4] = cursor.getString(5); //B_discard_day

                }
                while (cursor.moveToNext());
            }
            cursor.close();
            close();
        }
        return arg;
    }

    public String[] getStore_details(String store_name) {
        Cursor cursor;
        String[] result = new String[5];
        String query = "select store_name,store_address,post_code,Tell_number,resturant_manager from store_contact where store_name='" + store_name + "' ";
        cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getColumnCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    result[0] = cursor.getString(0);
                    result[1] = cursor.getString(1);
                    result[2] = cursor.getString(2);
                    result[3] = cursor.getString(3);
                    result[4] = cursor.getString(4);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        close();
        return result;
    }

    public TemperatureStructure getTemprature(String name){
       TemperatureStructure data=null;
       String query="Select name,type,min,max from temps where name='"+name+"'";
       Cursor cursor=sqLiteDatabase.rawQuery(query,null);
       if (cursor.getColumnCount()>0){
           if (cursor.moveToFirst()){
               do {
                   data=new TemperatureStructure(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
               }while (cursor.moveToNext());
           }
       }
       cursor.close();
       close();
    return data;
    }

    public int totalTemprature(){
       int total=0;
       Cursor cursor;
       String query = "select count(name) from temps";
       cursor=sqLiteDatabase.rawQuery(query,null);
       if (cursor.moveToFirst()){
           total=cursor.getInt(0);
       }
       cursor.close();
       close();
       return total;
    }

    public String[] getTempratureList(int total) {
        String[] list=new String[total];
        Cursor cursor;
        String query = "select name from temps";
        cursor = sqLiteDatabase.rawQuery(query, null);
        int cout = 0;
        if (cursor.getColumnCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    list[cout] = cursor.getString(0);
                    cout++;
                } while (cursor.moveToNext());
            }
            cursor.close();
            close();
        }
        return list;
    }


    public String[] getStoreList() {
        Cursor cursor;
        String[] list = new String[83];
        String query = "select store_name from store_contact";
        cursor = sqLiteDatabase.rawQuery(query, null);
        int count = 0;
        if (cursor.getColumnCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    list[count] = cursor.getString(0);
                    count++;
                } while (cursor.moveToNext());
            }
            cursor.close();
            close();
        }
        return list;
    }

    String[] Get_Name(int total) {
        Cursor cursor;
        String[] result = new String[total];

        String query = "select name from ptd";
        cursor = sqLiteDatabase.rawQuery(query, null);
        int i = 0;
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    result[i] = cursor.getString(0);
                    i++;
                }
                while (cursor.moveToNext());
            }
            cursor.close();
            close();

        }
        return result;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int ii) {

    }

    public void CheckDir(Context context, File path) {

        if (!path.exists()) {

            Log.e("检查:", String.valueOf(path.mkdirs()));

            path.mkdirs();

            CopyFile(context, path);

            Log.e("目录：", path.toString());

        } else {
            CopyFile(context, path);
            Log.e(TAG, "存在！" + path);
        }
    }

    private void CopyFile(Context context, File Path) {

        AssetManager assetManager = context.getAssets();
        try {
            InputStream in = assetManager.open(fileName);

            OutputStream out = new FileOutputStream(Path + "/databases/" + fileName);


            Log.e(TAG, "复制文件：" + Path.toString());

            byte[] buffer = new byte[1024];

            int read = in.read(buffer);

            while (read != -1) {
                out.write(buffer, 0, read);
                read = in.read(buffer);
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }
}
