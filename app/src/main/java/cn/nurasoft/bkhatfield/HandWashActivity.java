package cn.nurasoft.bkhatfield;

import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;


public class HandWashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sandwiches_view);

        PDFView pdf=findViewById(R.id.pdfView);
        pdf.fromAsset("Handwash.pdf")
                .defaultPage(0)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .pages(0)
                .load();
    }

}
