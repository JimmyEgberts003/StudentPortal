package com.egberts.jimmy.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPortal extends AppCompatActivity {
    public EditText mUrl;
    public EditText mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);

        mUrl = findViewById(R.id.URITextInput);
        mTitle = findViewById(R.id.nameTextInput);
        Button addPortalButton = findViewById(R.id.addPortalButton);

        addPortalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitle.getText().toString();
                String url = mUrl.getText().toString();

                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(url)) {
                    Portal portal = new Portal(title, url);
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.PORTAL_CODE, portal);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(AddPortal.this, R.string.empty_field_error, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
