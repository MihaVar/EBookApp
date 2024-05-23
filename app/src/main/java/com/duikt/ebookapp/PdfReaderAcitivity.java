package com.duikt.ebookapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.barteksc.pdfviewer.PDFView;

import org.w3c.dom.Text;

public class PdfReaderAcitivity extends AppCompatActivity {

    PDFView pdfView;
    TextView chapterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pdf_reader_acitivity);

        pdfView = findViewById(R.id.pdfView);
        chapterName = findViewById(R.id.chapterNames);

        int position = getIntent().getIntExtra("position", 0);
        String chapter = getIntent().getStringExtra("name");

        switch(position) {
            case 0:
                pdfView.fromAsset("kaydasheva_simya.pdf").load();
                chapterName.setText(chapter);
                break;
            case 1:
                pdfView.fromAsset("tyhrolovy.pdf").load();
                chapterName.setText(chapter);
                break;
        }
    }
}