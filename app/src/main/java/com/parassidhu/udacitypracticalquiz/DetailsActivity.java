package com.parassidhu.udacitypracticalquiz;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private TextView name, email, yourself;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

        if (hasExtra("username"))
            name.setText(getIntent().getStringExtra("username"));
        else
            name.setText(sharedPreferences.getString("username",""));

        if (hasExtra("email"))
            email.setText(getIntent().getStringExtra("email"));
        else
            email.setText(sharedPreferences.getString("email",""));

        if (hasExtra("yourself"))
            yourself.setText(getIntent().getStringExtra("yourself"));
        else
            yourself.setText(sharedPreferences.getString("yourself",""));

    }

    private boolean hasExtra(String username) {
        return getIntent().hasExtra(username);
    }

    private void initViews() {
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        yourself = findViewById(R.id.yourself);
        sharedPreferences = getSharedPreferences("info",MODE_PRIVATE);
    }
}
