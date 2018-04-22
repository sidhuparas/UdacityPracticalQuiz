package com.parassidhu.udacitypracticalquiz;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    private MaterialEditText username, email, yourself;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

        if (savedInstanceState!=null){
            username.setText(getBundleData(savedInstanceState,"username"));
            email.setText(getBundleData(savedInstanceState,"email"));
            yourself.setText(getBundleData(savedInstanceState,"yourself"));
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("username", username.getText().toString());
                editor.putString("email", email.getText().toString());
                editor.putString("yourself", yourself.getText().toString());
                editor.apply();

                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("username", username.getText().toString());
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("yourself", yourself.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void initViews(){
        username =  findViewById(R.id.username);
        email = findViewById(R.id.email);
        yourself = findViewById(R.id.yourself);
        next = findViewById(R.id.next);

        sharedPreferences = getSharedPreferences("info",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private String getBundleData(Bundle bundle, String key){
        return bundle.getString(key,"");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("username", username.getText().toString());
        outState.putString("email", email.getText().toString());
        outState.putString("yourself", yourself.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent openActivity = new Intent(this, DetailsActivity.class);
            startActivity(openActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
