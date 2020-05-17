package cn.nurasoft.bkhatfield;

import android.content.Intent;
import android.os.Bundle;

import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.*;

public class SandwichViewActivity extends AppCompatActivity {

    PDFView pdf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sandwiches_view);

        Intent intent=getIntent();
        String id=intent.getStringExtra("ID");
        int ID=Integer.parseInt(id);
        pdf=findViewById(R.id.pdfView);

        pdf.fromAsset("Sandwiches.pdf")
                .pages(ID).
                swipeHorizontal(false)
                .enableDoubletap(true)
                .load();
    }
}
