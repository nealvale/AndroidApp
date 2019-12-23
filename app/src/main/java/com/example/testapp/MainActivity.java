package com.example.testapp;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button saveButton;
    private TextView resultTextView;
    private EditText enterMessage;
    private SharedPreferences sharedPrefs;
    private static final String PREFS_NAME="prefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        enterMessage = (EditText) findViewById(R.id.enterName);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        saveButton= (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             sharedPrefs =getSharedPreferences(PREFS_NAME, 0);
             SharedPreferences.Editor editor=sharedPrefs.edit();
             editor.putString("message", enterMessage.getText().toString());
             editor.commit();

            }
        });

        //Get data
        SharedPreferences getPrefs =getSharedPreferences(PREFS_NAME, 0);
        if(getPrefs.contains("message")){
            String message = getPrefs.getString("message", "not found");
            resultTextView.setText("Message: "+message);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
