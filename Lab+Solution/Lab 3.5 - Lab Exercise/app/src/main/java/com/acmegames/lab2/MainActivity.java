package com.acmegames.lab2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    //code for part 2
//    private Button leftButton;
//    private Button rightButton;
//    private String buttonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //code for part 1
//        setContentView(R.layout.layout_part_1);

        //code for part 2
//        setContentView(R.layout.layout_part_2);
//        leftButton = (Button) findViewById(R.id.btnLeft);
//        rightButton = (Button) findViewById(R.id.btnRight);
//        buttonText = getString(R.string.btn_not_pressed_lbl);

        //code for part 3
        setContentView(R.layout.layout_part_3);
    }

    //code for part 2
//    public void buttonClick(View view){
//        //first clear text
//        ((Button)view).setText("");
//        if(view.getId() == R.id.btnLeft){
//            rightButton.setText(buttonText);
//        }
//        else{
//            leftButton.setText(buttonText);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_layout1, menu);
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
