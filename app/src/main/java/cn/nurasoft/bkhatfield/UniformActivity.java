package cn.nurasoft.bkhatfield;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class UniformActivity extends AppCompatActivity {

    PDFView pdf;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sandwiches_view);

        pdf=findViewById(R.id.pdfView);
        pdf.fromAsset("uniform.pdf")
                .pages(0,1)
                .swipeHorizontal(true)
                .defaultPage(0)
                .enableDoubletap(true)
                .load();
        Toast.makeText(this,"Swipe horizontal for more.",Toast.LENGTH_LONG).show();
    }

}
