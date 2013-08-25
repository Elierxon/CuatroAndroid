package com.pro.cuatroandroid;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MapsActivity extends MapActivity {
	public static Double latt1 = 30.0*1E6;//
    public static Double lonn1 = -10.0*1E6;//
    public static Integer lat1 = latt1.intValue();
    public static Integer lon1 = lonn1.intValue();
    List<Overlay> capas;
    OverlayMapa om;
    //TextView txv;
	
	public static final String PREFS_NAME = "MyPrefsFile";
	
    /** Called when the activity is first created. */
    @Override
    /*public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
    }*/
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        //txv = (TextView) findViewById(R.id.tv3);
        //txv.setText("cambiano");
        
     // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        lat1 = settings.getInt("latitud1", lat1);
        lon1 = settings.getInt("longitud1", lon1);
        //boolean df = settings.getBoolean("domingosfestivos", false);

        
        
        //parte de la nueva capa
      	//Añadimos la capa de marcadores
        //List<Overlay> capas = mapView.getOverlays();
        capas = mapView.getOverlays();
        //OverlayMapa om = new OverlayMapa();
        om = new OverlayMapa();
        capas.add(om);
        mapView.postInvalidate();
        
        
        Button next2 = (Button) findViewById(R.id.button8);
        next2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        
        
        
    }
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
    
    @Override
    protected void onStop(){
       super.onStop();
       
      //final CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);//
      //final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);//
       
      
      // We need an Editor object to make preference changes.
      // All objects are from android.context.Context
      SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
      SharedPreferences.Editor editor = settings.edit();
      
      //
      //OverlayMapa om = (OverlayMapa) capas.get(0);
      lat1 = om.latitud;
      lon1 = om.longitud;
      //
      
      //
      //PreferencesActivity.l1 = lat1;
      //PreferencesActivity.l2 = lon1;
      //
      
      //PreferencesActivity.setViewLocation(lat1, lon1);
      //Revisar esto
      //final TextView txv = (TextView) findViewById(R.id.tv3);
      //String msg = "Lat:" + lat1 + "Lon:" + lon1;
      //txv.setText("cambia2");
      //
      
      editor.putInt("latitud1", lat1);
      editor.putInt("longitud1", lon1);

      // Commit the edits!
      editor.commit();
    }
    
    public static void cambiolatitud(Integer la){
    	lat1 = la;
    }
    
    public static void cambiolongitud(Integer lo){
    	lon1 = lo;
    }
    
    
}