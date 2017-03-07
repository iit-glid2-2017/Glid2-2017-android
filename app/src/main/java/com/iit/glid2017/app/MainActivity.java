package com.iit.glid2017.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EDIT_TEXT_KEY = "edit_text_key";
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_relative);

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
        Intent intent = new Intent(getApplicationContext(), Activity2.class);
        String str = mEditText.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EDIT_TEXT_KEY, str);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void openListActivity() {
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_activity_ok:
                openListActivity();
                break;
            default:
                break;
        }
    }


}
