package cn.nurasoft.bkhatfield;

import android.os.Bundle;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class WeeklyActivity extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weekly);
        txt = findViewById(R.id.text);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://www.nurasoft.cn";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txt.setText("Response is:" + response.substring(0, 500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txt.setText("That did not work");
            }
        });
        queue.add(stringRequest);
    }
}
