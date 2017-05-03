package com.example.asuss.blackjack3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Glowna extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glowna);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_glowna, menu);
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
    int x = 0;
    int poziom = 0;

    public void start(View v){
        Button start = (Button) findViewById(R.id.start);
        start.setTranslationZ(50);
        if(poziom!=0){
            String poziomS = Integer.toString(poziom);
            Intent intent = new Intent(context, Zaklad.class);
            intent.putExtra("poziom", poziomS);
            startActivity(intent);
        }

    }

    public void poziom_trudnosci(View v){
        context = getApplicationContext();

        boolean checked = ((RadioButton) v).isChecked();

            switch (v.getId()) {
                case R.id.radio1:
                    if (checked) {
                        poziom = 1;
                    }
                    break;
                case R.id.radio2:
                    if (checked) {
                        poziom = 2;
                    }
                    break;
            }
        if(poziom!=0){
            Button start = (Button) findViewById(R.id.start);
            start.setVisibility(View.VISIBLE);
        }
    }


}
