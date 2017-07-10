package com.janaspandana.translationdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText toTranslate;
    Button buttonTranslate;
    public static String API_KEY = "AIzaSyAKqybXNlXIxqfDkOBuQMdqUtKgLE-M1gM";
    GoogleTranslate translator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toTranslate = (EditText) findViewById(R.id.ed_toTranslate);
        buttonTranslate = (Button)findViewById(R.id.button_translate);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        buttonTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    new EnglishToTagalog().execute();
            }
        });
    }

    private class EnglishToTagalog extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        protected void onError(Exception ex) {

        }
        @Override
        protected Void doInBackground(Void... params) {

            try {
                translator = new GoogleTranslate(API_KEY);

                Thread.sleep(1000);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;

        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPreExecute() {
            //start the progress dialog
            progress = ProgressDialog.show(MainActivity.this, null, "Translating...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Void result) {
            progress.dismiss();

            super.onPostExecute(result);
            translated();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    public void translated(){
        String translatetotagalog = toTranslate.getText().toString();//get the value of text
        String text = translator.translte(translatetotagalog, "en", "ru");
//        translatabletext = (TextView) findViewById(R.id.translatabletext);
//        translatabletext.setText(text);
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}
