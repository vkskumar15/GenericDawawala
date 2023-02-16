package com.example.genericdawawalauser.fragments.profiles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.genericdawawalauser.R;
import com.example.genericdawawalauser.activities.LabTestActivity;
import com.example.genericdawawalauser.databinding.ActivityPdfViewerBinding;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PdfViewerActivity extends AppCompatActivity {
    ActivityPdfViewerBinding binding;
    String urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        urls = getIntent().getStringExtra("url");

        binding.back.setOnClickListener(v -> {

            onBackPressed();

        });

        binding.medButton.setOnClickListener(v -> {


        });

        binding.labButton.setOnClickListener(v -> {
            startActivity(new Intent(PdfViewerActivity.this, LabTestActivity.class));

        });

        new RetrivePdfStream().execute(urls);
    }

    class RetrivePdfStream extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {

                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                return null;
            }

            return inputStream;
        }

        @Override
        // Here load the pdf and dismiss the dialog box
        protected void onPostExecute(InputStream inputStream) {
            binding.abc.fromStream(inputStream).load();
        }
    }

}