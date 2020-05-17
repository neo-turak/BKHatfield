package cn.nurasoft.bkhatfield;

import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ServerManager {

    public void Upload() {


        FTPClient con = null;
        try {
            con = new FTPClient();
            con.connect("82.30.231.205");

            if (con.login("pi", "root")) {
                con.enterLocalPassiveMode(); // important!
                con.setFileType(FTP.BINARY_FILE_TYPE);
                String data = "/sdcard/vivekm4a.m4a";

                FileInputStream in = new FileInputStream(new File(data));
                boolean result = con.storeFile("/vivekm4a.m4a", in);
                in.close();
                if (result) Log.v("upload result", "succeeded");
                con.logout();
                con.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Download(){
        FTPClient con = null;

        try
        {
            con = new FTPClient();
            con.connect("82.30.231.205");

            if (con.login("pi", "root"))
            {
                con.enterLocalPassiveMode(); // important!
                con.setFileType(FTP.BINARY_FILE_TYPE);
                String data = "/sdcard/vivekm4a.m4a";

                OutputStream out = new FileOutputStream(new File(data));
                boolean result = con.retrieveFile("vivekm4a.m4a", out);
                out.close();
                if (result) Log.v("download result", "succeeded");
                con.logout();
                con.disconnect();
            }
        }
        catch (Exception e)
        {
            Log.v("download result","failed");
            e.printStackTrace();
        }



    }
}

