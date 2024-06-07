package com.duikt.ebookapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

public class PdfReaderAcitivity extends AppCompatActivity {

    PDFView pdfView;
    TextView chapterName;
    TextView currentPageTextView;
    int savedPage;
    int position;
    String chapter;
    String pdfFileName;

    private static final String PREFS_NAME = "PdfPreferences";

    private void storePreferences() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String keyPage = "page_" + pdfFileName;
        String keyPdf = "pdfFile_" + position;
        editor.putInt(keyPage, savedPage);
        editor.putString(keyPdf, pdfFileName);
        editor.apply();
    }

    private void getPreferences() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String keyPage = "page_" + pdfFileName;
        String keyPdf = "pdfFile_" + position;
        savedPage = pref.getInt(keyPage, 0);
        pdfFileName = pref.getString(keyPdf, getPdfFileName(position));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_reader_acitivity);

        position = getIntent().getIntExtra("position", 0);
        chapter = getIntent().getStringExtra("name");
        pdfFileName = getPdfFileName(position); // Initialize with default PDF file name

        getPreferences(); // Retrieve saved preferences
        EdgeToEdge.enable(this);

        pdfView = findViewById(R.id.pdfView);
        chapterName = findViewById(R.id.chapterNames);
        currentPageTextView = findViewById(R.id.currentPageTextView);

        pdfView.fromAsset(pdfFileName)
                .defaultPage(savedPage)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        savedPage = page;
                        storePreferences(); // Save preferences every time the page changes
                        currentPageTextView.setText("Ст. " + (page + 1));
                    }
                })
                .load();

        chapterName.setText(chapter);

        // Set up the OnBackPressedCallback
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                storePreferences();
                finish(); // Close the activity
            }
        };

        // Add the callback to the OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private String getPdfFileName(int position) {
        switch (position) {
            case 0:
                return "kaydasheva_simya.pdf";
            case 1:
                return "tyhrolovy.pdf";
            case 2:
                return "kaminnyy_khrest.pdf";
            case 3:
                return "chorna_rada.pdf";
            case 4:
                return "haydamaky.pdf";
            case 5:
                return "eneyida.pdf";
            case 6:
                return "toreadory_z_vasiukivky.pdf";
            case 7:
                return "ya_romantyka.pdf";
            case 8:
                return "mayster_korablia.pdf";
            case 9:
                return "tini_zabutykh_predkiv.pdf";
            default:
                return "";
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        storePreferences(); // Save preferences when the activity is paused
    }

    @Override
    protected void onStop() {
        super.onStop();
        storePreferences(); // Save preferences when the activity is stopped
    }
}
