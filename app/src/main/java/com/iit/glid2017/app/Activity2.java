package com.iit.glid2017.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2_layout);
        mEditText = (EditText) findViewById(R.id.edit_text);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String str = bundle.getString(MainActivity.EDIT_TEXT_KEY);
            mEditText.setText(str);
        }
    }
}
