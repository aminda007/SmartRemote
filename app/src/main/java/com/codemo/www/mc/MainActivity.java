package com.codemo.www.mc;

import android.content.Intent;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static String ip="";
    public static DatabaseController dbc;
    public static MainActivity activity;
    Handler messageHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        dbc = new DatabaseController(this,null,null,5);
        ip=dbc.getIP();
        Button onLightBtn = (Button)findViewById(R.id.onLightBtn);
        Button offLightBtn = (Button)findViewById(R.id.offLightBtn);
        Button onFanBtn = (Button)findViewById(R.id.onFanBtn);
        Button offFanBtn = (Button)findViewById(R.id.offFanBtn);
        Button onACBtn = (Button)findViewById(R.id.onACBtn);
//        Button offACBtn = (Button)findViewById(R.id.offACBtn);
        Button addTempBtn = (Button)findViewById(R.id.plusACBtn);
        Button minusTempBtn = (Button)findViewById(R.id.minusACBtn);
        onLightBtn.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        buttonClicked("a");
                    }
                }
        );
        offLightBtn.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        buttonClicked("b");
                    }
                }
        );
        onFanBtn.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        buttonClicked("f");
                    }
                }
        );
        offFanBtn.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        buttonClicked("g");
                    }
                }
        );
        onACBtn.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        buttonClicked("c");
                    }
                }
        );
//        offACBtn.setOnClickListener(
//                new Button.OnClickListener(){
//                    @Override
//                    public void onClick(View v) {
//                        buttonClicked("f");
//                    }
//                }
//        );
        addTempBtn.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        buttonClicked("d");
                    }
                }
        );
        minusTempBtn.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        buttonClicked("e");
                    }
                }
        );
        if(ip==""){
            Toast.makeText(this,"set ip", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"current ip is "+ip, Toast.LENGTH_SHORT).show();
        }
        messageHandler = new Handler();

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        if(ip==""){
            Toast.makeText(this,"set ip", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"current ip is "+ip, Toast.LENGTH_SHORT).show();
        }

    }

    public void buttonClicked(final String opp){
        final Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {

                    //Your code goes here
                    if(MainActivity.ip==""){
                        Toast.makeText(getApplicationContext(),"set ip first", Toast.LENGTH_SHORT).show();
                    }else{
                        URL url=null;
                        HttpURLConnection urlConnection = null;
                        try {
                            url = new URL("http://"+ip+"/"+opp);
//                            url = new URL("http://www.google.com");

                            urlConnection = (HttpURLConnection) url.openConnection();

                            InputStream in = urlConnection.getInputStream();

                            InputStreamReader isw = new InputStreamReader(in);

                            int data = isw.read();
                            while (data != -1) {
                                char current = (char) data;
                                data = isw.read();
                                System.out.print(current);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (urlConnection != null) {
                                Toast.makeText(getApplicationContext(),"connected to "+url.toString(), Toast.LENGTH_SHORT).show();
                                urlConnection.disconnect();
                            }else{
                                Toast.makeText(getApplicationContext(),"not connected to "+url.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        messageHandler.post(thread);
        thread.start();

//        network.execute("String1","String2");
    }
    public void onSettingClick(View view){
        Intent i = new Intent(this,SettingsActivity.class);
        startActivity(i);
        Toast.makeText(this,"settings", Toast.LENGTH_SHORT).show();
    }
}
