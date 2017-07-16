package com.codemo.www.mc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    EditText ipAddress;
    TextView currentip;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        spinner = (Spinner) findViewById(R.id.spinner);
        ipAddress = (EditText) findViewById(R.id.ipAddress);
        currentip=(TextView)findViewById(R.id.currentIP);
        currentip.setText(MainActivity.ip);
        ArrayAdapter arrayAdapter = new ArrayAdapter(SettingsActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

    }
    public void onClick(View view){
        if(MainActivity.ip==""){
            MainActivity.dbc.addIP(ipAddress.getText().toString());
            MainActivity.ip=ipAddress.getText().toString();
            currentip.setText(MainActivity.ip);
        }else {
            if(ipAddress.getText().toString().length()<6){
                Toast.makeText(this,"not valid", Toast.LENGTH_SHORT).show();
            }else{
                MainActivity.dbc.updateIP(ipAddress.getText().toString());
                MainActivity.ip=ipAddress.getText().toString();
                currentip.setText(MainActivity.ip);
            }
        }
        Toast.makeText(this,"saved"+ipAddress.getText(), Toast.LENGTH_SHORT).show();
    }
    public void onHomeClick(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        Toast.makeText(this,"Home", Toast.LENGTH_SHORT).show();
    }
    public void onTypeClick(View view){

        int id = spinner.getSelectedItemPosition();
        if(id==0){
            MainActivity.activity.buttonClicked("1");
        }
        else if(id==1){
            MainActivity.activity.buttonClicked("2");
        }
        else if(id==2){
            MainActivity.activity.buttonClicked("3");
        }
        else if(id==3){
            MainActivity.activity.buttonClicked("4");
        }
//        else if(id==4){
//            MainActivity.activity.buttonClicked("t");
//        }
//        else if(id==5){
//            MainActivity.activity.buttonClicked("u");
//        }
//        else if(id==6){
//            MainActivity.activity.buttonClicked("v");
//        }
//        else if(id==7){
//            MainActivity.activity.buttonClicked("w");
//        }
//        else if(id==8){
//            MainActivity.activity.buttonClicked("x");
//        }
//        else if(id==9){
//            MainActivity.activity.buttonClicked("y");
//        }
//        else if(id==10){
//            MainActivity.activity.buttonClicked("z");
//        }
//        else{
//            MainActivity.activity.buttonClicked("m");
//        }
    }

}

