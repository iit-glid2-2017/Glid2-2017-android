package com.iit.glid2017.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EDIT_TEXT_KEY = "edit_text_key";
    private EditText mEditText;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_relative);

        mHandler = new Handler();

        initViews();
    }

    private void initViews() {

        Button okButton = (Button) findViewById(R.id.main_activity_ok);
        okButton.setOnClickListener(this);
        mEditText = (EditText) findViewById(R.id.edit_text);

    }

    public void mainActivityClick(View view) {

        switch (view.getId()) {
            case R.id.main_activity_ok:
                openActivity2();

                break;
            default:
                break;
        }
    }

    private void openActivity2() {
//        Intent intent = new Intent(getApplicationContext(), Activity2.class);
//        String str = mEditText.getText().toString();
//        Bundle bundle = new Bundle();
//        bundle.putString(EDIT_TEXT_KEY, str);
//        intent.putExtras(bundle);
//        startActivity(intent);

//        SomeThread myThread = new SomeThread();
//        myThread.start();

        SomeAsyncTask someAsyncTask1 = new SomeAsyncTask(1);
        SomeAsyncTask someAsyncTask2 = new SomeAsyncTask(2);
        SomeAsyncTask someAsyncTask3 = new SomeAsyncTask(3);
        SomeAsyncTask someAsyncTask4 = new SomeAsyncTask(4);
        someAsyncTask1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        someAsyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        someAsyncTask3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        someAsyncTask4.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        int sharedValue = sharedPreferences.getInt("my_pref", 0);
        Log.v("shared", String.valueOf(sharedValue));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("my_pref", 5);
        editor.apply();

    }

    private void openListActivity() {
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);
    }

    private void callChuckNorris() {
        ChuckNorrisAsyncTask chuckNorrisAsyncTask = new ChuckNorrisAsyncTask();
        chuckNorrisAsyncTask.execute();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_activity_ok:
                //openListActivity();
                //openActivity2();
                callChuckNorris();
                break;
            default:
                break;
        }
    }


    private class SomeThread extends Thread {
        @Override
        public void run() {
            //opeartion
            final long timestamp = Calendar.getInstance().getTimeInMillis();

            //update UI
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mEditText.setText(String.valueOf(timestamp));
                }
            }, 1000);

        }
    }

    private class SomeAsyncTask extends AsyncTask<Void, Void, Long> {

        private int mVar;

        public SomeAsyncTask(int var) {
            mVar = var;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            Log.v("async", "var = " + mVar);
            return Calendar.getInstance().getTimeInMillis();
        }

        @Override
        protected void onPostExecute(Long timestamp) {
            Log.v("async", "timestamp in " + mVar + " = " + timestamp);
            mEditText.setText(String.valueOf(timestamp));
        }
    }

    private class ChuckNorrisAsyncTask extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... voids) {


            String result = "";


            HttpURLConnection connection = null;


            try {
                URL url = new URL("http://api.icndb.com/jokes/random");
                connection = (HttpURLConnection) url.openConnection();
                // Timeout for reading InputStream arbitrarily set to 3000ms.
                connection.setReadTimeout(3000);
                // Timeout for connection.connect() arbitrarily set to 3000ms.
                connection.setConnectTimeout(3000);
                // For this use case, set HTTP method to GET.
                connection.setRequestMethod("GET");
                // Already true by default but setting just in case; needs to be true since this request
                // is carrying an input (response) body.
                connection.setDoInput(true);
                // Open communications link (network traffic occurs here).
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {

                    // read the response
                    StringBuffer buffer = new StringBuffer();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        buffer.append(line + "\r\n");
                    }

                    inputStream.close();

                    result = buffer.toString();

                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(String message) {

            Gson gson = new GsonBuilder().create();
            JokeWrapper jokeWrapper = gson.fromJson(message,
                    JokeWrapper.class);
            String text = "id = " + jokeWrapper.getJokeId() + " - " + jokeWrapper.getJoke();
            mEditText.setText(text);
        }
    }


}
