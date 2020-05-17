package cn.nurasoft.bkhatfield;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_view);

        WebView view=findViewById(R.id.web_help);
        view.loadUrl("file:///android_asset/index.html");
    }
}
