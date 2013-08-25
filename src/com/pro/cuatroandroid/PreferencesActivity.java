package com.pro.cuatroandroid;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import android.widget.CheckBox;

public class PreferencesActivity extends Activity {
	
	public static final String PREFS_NAME = "MyPrefsFile";
	public static Integer l1;
	public static Integer l2;
	
	
    /** Called when the activity is first created. */
    @Override
    /*public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
    }*/
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
        
     // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean sf = settings.getBoolean("sabadosfestivos", false);
        boolean df = settings.getBoolean("domingosfestivos", false);
        l1 = settings.getInt("latitud1", 0);
        l2 = settings.getInt("longitud1", 0);

        
        final CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox2.setChecked(sf);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
        checkBox.setChecked(df);
        
        final TextView textv = (TextView) findViewById(R.id.tv3);
        String msg = "Lat:" + l1/1E6 + "Lon:" + l2/1E6;
        textv.setText(msg);



        Button next = (Button) findViewById(R.id.button9);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        
        final Button map = (Button) findViewById(R.id.button7);
        map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MapsActivity.class);
                startActivityForResult(myIntent, 1);
                //final TextView txv = (TextView) findViewById(R.id.tv3);
                //txv.setText("cambiano");
            }

        });
    }
    
    @Override
    protected void onStop(){
       super.onStop();
       
      final CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);//
      final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);//
      
      // We need an Editor object to make preference changes.
      // All objects are from android.context.Context
      SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
      SharedPreferences.Editor editor = settings.edit();
      editor.putBoolean("sabadosfestivos", checkBox2.isChecked());
      editor.putBoolean("domingosfestivos", checkBox.isChecked());

      // Commit the edits!
      editor.commit();
    }
    
    protected void onActivityResult(int requestCode, int resultCode,
            Intent dat){
    	final TextView textv = (TextView) findViewById(R.id.tv3);
        String msg = "Lat:" + l1/1E6 + "Lon:" + l2/1E6;
        textv.setText(msg);
    }
    
    //protected void onResume(){
    	//final TextView textv = (TextView) findViewById(R.id.tv3);
        //String msg = "Lat:" + lat1 + "Lon:" + lon1;
        //textv.setText("cambia2");
    //}
    
    /*public static void setViewLocation(Integer la, Integer lo){
    	final TextView textv = (TextView) findViewById(R.id.tv3);
        //String msg = "Lat:" + lat1 + "Lon:" + lon1;
        //textv.setText("cambia2", android.widget.TextView.BufferType.NORMAL);
    	textv.setText("cambia2");
    
    }*/
    
}