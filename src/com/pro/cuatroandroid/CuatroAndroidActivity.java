package com.pro.cuatroandroid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;

import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

//import com.exina.android.calendar.R;

import android.widget.CalendarView.OnDateChangeListener;

import android.widget.ImageButton;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;



public class CuatroAndroidActivity extends Activity {
    /** Called when the activity is first created. */
    
    CalendarView mv1 = null;
    CalendarView mv2 = null;
    MonthDisplayHelper mHelper, mHelper2, mHelper3, mHelperTemp;
    static Calendar mRightNow = null;
    Calendar mNotNow = null;  
    Calendar pri = null;
    static ImageView[] ims = new ImageView[7];
    static ImageView[] ims2 = new ImageView[7];
    static ImageView[] ims3 = new ImageView[7];
    static ImageView[] ims4 = new ImageView[7];
    static ImageView[] ims5 = new ImageView[7];
    static ImageView[] ims6 = new ImageView[7];
    //ImageView[] ims7 = new ImageView[7];
    //JSONParserFestivos json = new JSONParserFestivos(this);
    
    private static final String TAG = "MyActivity";
    
    private JSONObject jObject;//
    
    
    //public static final String PREFS_NAME = "MyPrefsFile";

    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
               
        mv1 = (CalendarView)findViewById(R.id.calendarView1);
        mv2 = (CalendarView)findViewById(R.id.calendarView2);
        
        
        int[] ivs = {R.id.imageView1,R.id.imageView2,R.id.imageView3,
        		R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7};
        int[] ivs2 = {R.id.imageView21,R.id.imageView22,R.id.imageView23,
        		R.id.imageView24,R.id.imageView25,R.id.imageView26,R.id.imageView27};
        int[] ivs3 = {R.id.imageView31,R.id.imageView32,R.id.imageView33,
        		R.id.imageView34,R.id.imageView35,R.id.imageView36,R.id.imageView37};
        int[] ivs4 = {R.id.imageView41,R.id.imageView42,R.id.imageView43,
        		R.id.imageView44,R.id.imageView45,R.id.imageView46,R.id.imageView47};
        int[] ivs5 = {R.id.imageView51,R.id.imageView52,R.id.imageView53,
        		R.id.imageView54,R.id.imageView55,R.id.imageView56,R.id.imageView57};
        int[] ivs6 = {R.id.imageView61,R.id.imageView62,R.id.imageView63,
        		R.id.imageView64,R.id.imageView65,R.id.imageView66,R.id.imageView67};
        
        //
        for(int i=0;i<7;i++){
        	ims[i] = (ImageView)findViewById(ivs[i]);
        	ims2[i] = (ImageView)findViewById(ivs2[i]);
        	ims3[i] = (ImageView)findViewById(ivs3[i]);
        	ims4[i] = (ImageView)findViewById(ivs4[i]);
        	ims5[i] = (ImageView)findViewById(ivs5[i]);
        	ims6[i] = (ImageView)findViewById(ivs6[i]);
        }
        
        
        //im1.setPadding(left, top, right, bottom)
        //
 
        //mv1.getWidth();
        ims[0].setPadding(34, 56, 0, 0);
        for (int i=1;i<7;i++){
        	ims[i].setPadding(6, 56, 0, 0);
        }
        
        ims2[0].setPadding(34, 73, 0, 0);
        for (int i = 1;i<7;i++){
        	ims2[i].setPadding(6, 73, 0, 0);
        }

        ims3[0].setPadding(34, 91, 0, 0);
        for (int i = 1;i<7;i++){
        	ims3[i].setPadding(6, 91, 0, 0);
        }
        
        ims4[0].setPadding(34, 109, 0, 0);
        for (int i = 1;i<7;i++){
        	ims4[i].setPadding(6, 109, 0, 0);
        }

        ims5[0].setPadding(34, 127, 0, 0);
        for (int i = 1;i<7;i++){
        	ims5[i].setPadding(6, 127, 0, 0);
        }

        ims6[0].setPadding(34, 145, 0, 0);
        for (int i = 1;i<7;i++){
        	ims6[i].setPadding(6, 145, 0, 0);
        }

        /*ims7[0].setPadding(34, 197, 0, 0);
        for (int i = 1;i<7;i++){
        	ims7[i].setPadding(6, 197, 0, 0);
        }*/
        //resetflags();
        int[] flags = obtenerflags();
        //resetflags();
        
        /*for(int i=0; i<7;i++){
        	ims[i].setVisibility(flags[i]);
        	ims2[i].setVisibility(flags[i+7]);
        	ims3[i].setVisibility(flags[i+14]);
        	ims4[i].setVisibility(flags[i+21]);
        	ims5[i].setVisibility(flags[i+28]);
        	ims6[i].setVisibility(flags[i+35]);        	
        }*/
          
        
        
        //mv1.setFirstDayOfWeek(2);//MONDAY
        //mv2.setFirstDayOfWeek(2);//MONDAY
        
               
        mRightNow = Calendar.getInstance();
        mRightNow.setFirstDayOfWeek(Calendar.MONDAY);
                
        mv1.setDate(mRightNow.getTime().getTime());
                
        mNotNow = Calendar.getInstance();
        mNotNow.setFirstDayOfWeek(Calendar.MONDAY);

        messiguiente(mNotNow);

        mHelper3 = new MonthDisplayHelper(mNotNow.get(Calendar.YEAR),mNotNow.get(Calendar.MONTH));
        if(mRightNow.get(Calendar.DAY_OF_MONTH) > mHelper3.getNumberOfDaysInMonth()){
        	mNotNow.set(Calendar.DAY_OF_MONTH, mHelper3.getNumberOfDaysInMonth());
        }
        mv2.setDate(mNotNow.getTime().getTime());
        
        
        //anticp2
        //poner solo los dias del mes, bloquear scroll
        pri = Calendar.getInstance();
        pri.setFirstDayOfWeek(Calendar.MONDAY);
        
        int restosemana=restosemana(pri);
        
        mesanterior(pri);
        
        //pri.set(Calendar.DAY_OF_MONTH, 1);
        mHelperTemp = new MonthDisplayHelper(pri.get(Calendar.YEAR),pri.get(Calendar.MONTH));
        pri.set(Calendar.DAY_OF_MONTH, mHelperTemp.getNumberOfDaysInMonth()-restosemana);//
        mv1.setMinDate(pri.getTime().getTime());
        pri = Calendar.getInstance();//
        mHelper = new MonthDisplayHelper(pri.get(Calendar.YEAR),pri.get(Calendar.MONTH));
        pri.set(Calendar.DAY_OF_MONTH, mHelper.getNumberOfDaysInMonth());
        //
        int faltasemana = 7-pri.get(Calendar.DAY_OF_WEEK);
        if(pri.get(Calendar.DAY_OF_WEEK)==1){
        	faltasemana=7-0;//
        }
        pri.set(Calendar.DAY_OF_MONTH, mHelper.getNumberOfDaysInMonth()+faltasemana+1);
        //
        
        mv1.setMaxDate(pri.getTime().getTime());
        
        
        //2ºmes
        pri = (Calendar)mNotNow.clone();
        
        pri.set(Calendar.DAY_OF_MONTH, 1);//
        restosemana = restosemana(pri);
        
        mesanterior(pri);
        
        mHelperTemp = new MonthDisplayHelper(pri.get(Calendar.YEAR),pri.get(Calendar.MONTH));
        pri.set(Calendar.DAY_OF_MONTH, mHelperTemp.getNumberOfDaysInMonth()-restosemana);//
        mv2.setMinDate(pri.getTime().getTime());
        pri = (Calendar)mNotNow.clone();//
        mHelper2 = new MonthDisplayHelper(pri.get(Calendar.YEAR),pri.get(Calendar.MONTH));
        pri.set(Calendar.DAY_OF_MONTH, mHelper2.getNumberOfDaysInMonth());
        //
        faltasemana = 7-pri.get(Calendar.DAY_OF_WEEK);
        if(pri.get(Calendar.DAY_OF_WEEK)==1){
        	faltasemana=7-0;//
        }
        pri.set(Calendar.DAY_OF_MONTH, mHelper.getNumberOfDaysInMonth()+faltasemana+1);
        //
        mv2.setMaxDate(pri.getTime().getTime());
        
      /*  
     // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean df = settings.getBoolean("domingosfestivos", false);

      */  
        
        
        // aki? o fuera de onCreate?
        //al seleccionar un dia en el calendario1 , selecciona el mes siguiente en calendario2
        /*mv1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
			
			//@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				Calendar chan = null;
				chan = Calendar.getInstance();
				chan.set(year,month,dayOfMonth);
				//mes siguiente
		        if(chan.get(Calendar.MONTH)==11){
		        	chan.set(Calendar.MONTH, 0);
		        	chan.set(Calendar.YEAR, chan.get(Calendar.YEAR)+1);
		        }else{
		        	chan.set(Calendar.MONTH, chan.get(Calendar.MONTH)+1);
		        }
				mv2.setDate(chan.getTime().getTime());
				
			}
		});
        
        mv2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
			
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				Calendar chan = null;
				chan = Calendar.getInstance();
				chan.set(year,month,dayOfMonth);
				//mes anterior
		        if(chan.get(Calendar.MONTH)==0){
		        	chan.set(Calendar.MONTH, 11);
		        	chan.set(Calendar.YEAR, chan.get(Calendar.YEAR)-1);
		        }else{
		        	chan.set(Calendar.MONTH, chan.get(Calendar.MONTH)-1);
		        }
				mv1.setDate(chan.getTime().getTime());
				
			}
		});*/
               
        final Button button_1 = (Button) findViewById(R.id.button1);
        button_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //
				Calendar chan, chan2 = null;
				chan = Calendar.getInstance();
				chan.setFirstDayOfWeek(Calendar.MONDAY);
				chan.setTimeInMillis(mv1.getDate());
	        				
				mesanterior(chan);
		        
		        chan2 = (Calendar)chan.clone();		        
		        mesanterior(chan2);

		        chan2.set(Calendar.DAY_OF_MONTH, 28);//
		        mv1.setMinDate(chan2.getTime().getTime());
		        
		        chan.set(Calendar.DAY_OF_MONTH, 15);//
		        mv1.setDate(chan.getTime().getTime());

		        cuadrarmes(chan,mv1);
		        
		        //cambiodeflagsmes1
		        //resetflags();
		        int[] flags = obtenerflags();
		        /*int[] flags = {4,4,4,0,0,0,0,
		    			0,0,0,0,0,0,0,
		    			0,4,0,4,4,0,0,
		    			0,0,0,4,0,0,0,
		    			0,4,0,4,0,0,0,
		    			0,4,4,0,0,4,4};*/
		        /*for(int i=0; i<7;i++){
		        	ims[i].setVisibility(flags[i]);
		        	ims2[i].setVisibility(flags[i+7]);
		        	ims3[i].setVisibility(flags[i+14]);
		        	ims4[i].setVisibility(flags[i+21]);
		        	ims5[i].setVisibility(flags[i+28]);
		        	ims6[i].setVisibility(flags[i+35]);        	
		        }*/
		        
		        
		        //segundo mes
				chan.setTimeInMillis(mv2.getDate());

				mesanterior(chan);       
		        
		        chan2 = (Calendar)chan.clone();
		        mesanterior(chan2);
		        chan2.set(Calendar.DAY_OF_MONTH, 28);//
		        mv2.setMinDate(chan2.getTime().getTime());
		        
		        chan.set(Calendar.DAY_OF_MONTH, 15);//
		        mv2.setDate(chan.getTime().getTime());
		        
		        cuadrarmes(chan,mv2);

            }
        });
        
        final Button button_2 = (Button) findViewById(R.id.button2);
        button_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {        	
            	Calendar chan, chan2 = null;
				chan = Calendar.getInstance();
				chan.setFirstDayOfWeek(Calendar.MONDAY);
				chan.setTimeInMillis(mv1.getDate());

				messiguiente(chan);
		        
		        chan2 = (Calendar)chan.clone();
		        mesanterior(chan2);
		        //diferencia
		        chan.set(Calendar.DAY_OF_MONTH, 28);//
		        mv1.setMaxDate(chan.getTime().getTime());
		        
		        chan.set(Calendar.DAY_OF_MONTH, 15);//
		        mv1.setDate(chan.getTime().getTime());
		        
		        cuadrarmes(chan, mv1);
		        
		      //cambiodeflagsmes1
		        //resetflags();
		        int[] flags = obtenerflags();		        
		        /*for(int i=0; i<7;i++){
		        	ims[i].setVisibility(flags[i]);
		        	ims2[i].setVisibility(flags[i+7]);
		        	ims3[i].setVisibility(flags[i+14]);
		        	//
		        	//ColorDrawable cd = new ColorDrawable(0xffff0000);
		        	//ims3[i].setImageDrawable(cd);
		        	//
		        	ims4[i].setVisibility(flags[i+21]);
		        	ims5[i].setVisibility(flags[i+28]);
		        	ims6[i].setVisibility(flags[i+35]);        	
		        }*/
		        
		        //segundo mes
				chan.setTimeInMillis(mv2.getDate());

				messiguiente(chan);	        
		        
		        chan2 = (Calendar)chan.clone();
		        mesanterior(chan2);
		        //diferencia
		        chan.set(Calendar.DAY_OF_MONTH, 28);//
		        mv2.setMaxDate(chan.getTime().getTime());
		        
		        chan.set(Calendar.DAY_OF_MONTH, 15);//
		        mv2.setDate(chan.getTime().getTime());
		        		        
		        cuadrarmes(chan,mv2);	

            }
        });
        
        /*final Button button_p = (Button) findViewById(R.id.imageButton1);
        button_p.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            	Intent intent = new Intent(CuatroAndroidActivity.this, PreferencesActivity.class);
            	startActivity(intent);

            }
        });*/
        
        final ImageButton pref = (ImageButton) findViewById(R.id.imageButton1);
        pref.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), PreferencesActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });
        
    }
   /* public void onClick(View v) {
    	Intent intent = new Intent(CuatroAndroidActivity.this, PreferencesActivity.class);
    	startActivity(intent);
    }*/
    
 /*   @Override
    protected void onStop(){
       super.onStop();

      // We need an Editor object to make preference changes.
      // All objects are from android.context.Context
      SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
      SharedPreferences.Editor editor = settings.edit();
      editor.putBoolean("domingosfestivos", true);

      // Commit the edits!
      editor.commit();
    }*/

    
    //FUNCIONES AUXILIARES
    private void mesanterior(Calendar cal){
    	if(cal.get(Calendar.MONTH)==0){
    		cal.set(Calendar.MONTH, 11);
    		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-1);
    	}else{
    		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
    	}
    }
    private void messiguiente(Calendar cal){
    	if(cal.get(Calendar.MONTH)==11){
        	cal.set(Calendar.MONTH, 0);
        	cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)+1);
        }else{
        	cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
        }
    }
    private int restosemana(Calendar cal){
    	Calendar cal2 = (Calendar)cal.clone();
    	cal2.set(Calendar.DAY_OF_MONTH, 1);//
    	return (7+cal2.get(Calendar.DAY_OF_WEEK)-2)%7;
    }
    private int faltasemana(Calendar cal){
    	Calendar cal2 = (Calendar)cal.clone();
    	MonthDisplayHelper mHelperaux;
    	mHelperaux = new MonthDisplayHelper(cal2.get(Calendar.YEAR),cal2.get(Calendar.MONTH));
        cal2.set(Calendar.DAY_OF_MONTH, mHelperaux.getNumberOfDaysInMonth());
        //modulo 7 por el cambio del primer dia de la semana
        return (8-cal2.get(Calendar.DAY_OF_WEEK))%7;
    }
    private void cuadrarmes(Calendar cal, CalendarView mv){
    	Calendar cal2 = (Calendar)cal.clone();
    	MonthDisplayHelper mHelperaux, mHelperaux2;
        int restosemana = restosemana(cal2);
        mesanterior(cal2);
        
        mHelperaux = new MonthDisplayHelper(cal2.get(Calendar.YEAR),cal2.get(Calendar.MONTH));
        cal2.set(Calendar.DAY_OF_MONTH, mHelperaux.getNumberOfDaysInMonth()-restosemana);//
        mv.setMinDate(cal2.getTime().getTime());
        cal2 = (Calendar)cal.clone();
        mHelperaux2 = new MonthDisplayHelper(cal2.get(Calendar.YEAR),cal2.get(Calendar.MONTH));
        cal2.set(Calendar.DAY_OF_MONTH, mHelperaux2.getNumberOfDaysInMonth());

        int faltasemana = faltasemana(cal2);
        cal2.set(Calendar.DAY_OF_MONTH, mHelperaux2.getNumberOfDaysInMonth()+faltasemana);

        mv.setMaxDate(cal2.getTime().getTime());
    }
    
    //debe devolver el array de dias festivos
    //segun progrese la funcion se debe añadir la fecha...
    //...añadir la localizacion
    //otro dato a aportar serian las preferencias de los dias festivos
    //parece k la mejor opcion es meterlo las preferencias dentro dela funcion...
    //..ya k de momento las opciones son sabado y domingo festivo
    private int[] obtenerflags(){
    	//JSONParserFestivos json = new JSONParserFestivos(this);
    	try {
    		JSONParserFestivos json = new JSONParserFestivos(this);
			json.readAndParseJSONFestivos();
			//Log.i(TAG, "pais=" + json.getpais());
    		//
    		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	/*jObject = JSONManager.getJSONfromURL("http://10.230.24.19/serpro/index.php?lat=28.068951&lng=-15.455067");
    	if(jObject != null){Log.i(TAG, "pkpkpk");}
    	else {Log.i(TAG, "pkpkpk2");}//
    	*/
    	/*InputStream is = null;
	     String result = "";
	     JSONObject json = null;
	      try{
	         HttpClient httpclient = new DefaultHttpClient();
	         Log.i(TAG, "pksi11");
	         HttpPost httppost = new HttpPost("http://10.230.24.19/serpro/index.php?lat=28.068951&lng=-15.455067");
	         Log.i(TAG, "pksi12");
	         HttpResponse response = httpclient.execute(httppost);
	         Log.i(TAG, "pksi13");
	         HttpEntity entity = response.getEntity();
	         Log.i(TAG, "pksi14");
	         is = entity.getContent();
	         Log.i(TAG, "pksi15");
	     }catch(Exception e){Log.i(TAG, "pkno1");}

	      try{
	         BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	         StringBuilder sb = new StringBuilder();
	         String line = null;
	         while ((line = reader.readLine()) != null) {
	             sb.append(line + "\n");
	         }
	         is.close();
	         result=sb.toString();
	     } catch(Exception e){Log.i(TAG, "pkno2");}

	     try{
	         json = new JSONObject(result);
	         Log.i(TAG, "pais0=" + json.getString("pais"));
	     }catch(JSONException e){Log.i(TAG, "pkno3");}*/
	     /*try {
			Log.i(TAG, "pais0=" + json.getString("pais"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	     //json.getString("pais");

    	
    	//habra k hacer una consulta de datos
    	//se podria pasar o los flags del mes, o los de las 6 semanas de la vista
    	//0-ImageView.VISIBLE, 4-ImageView.INVISIBLE
    	int[] b = {0,0,0,4,4,4,4,
    			4,4,4,4,4,4,4,
    			4,4,4,4,4,4,4,
    			4,4,4,4,4,4,4,
    			4,4,4,4,4,4,4,
    			4,4,4,4,0,0,0};
    	int[] b2 = {0,0,0,4,4,4,4,
    			0,0,0,0,0,0,0,
    			0,0,0,0,0,0,0,
    			0,0,0,0,0,0,0,
    			0,0,0,0,0,0,0,
    			4,4,4,4,0,0,0};
    	//Log.i(TAG, "pais=" + json.getpais());
    	/*if(json.getpais().equals("Spain")){
    		return b2;
    	}*/
    	return b;
    }
    
    //public static void setvis(int a, int n){
    public static void setvis(int[] n){
    	/*if(n<7)
        	ims[n].setVisibility(a);
    	else if(n<14)
        	ims2[n].setVisibility(a);
    	else if(n<21)
        	ims3[n].setVisibility(a);
    	else if(n<28)
        	ims4[n].setVisibility(a);
    	else if(n<35)
        	ims5[n].setVisibility(a);*/
        	//ims6[n].setVisibility(a);
    	for(int i=0; i<7;i++){
    	ims[i].setVisibility(n[i]);
    	ims2[i].setVisibility(n[i+7]);
    	ims3[i].setVisibility(n[i+14]);
    	ims4[i].setVisibility(n[i+21]);
    	ims5[i].setVisibility(n[i+28]);
    	ims6[i].setVisibility(n[i+35]);
    	}
    }
    
    public static int getmonth(){
    	
    	return mRightNow.get(Calendar.MONTH);
    }
    
    public static void resetflags(){
    	for(int i=0; i<7;i++){
        	ims[i].setVisibility(0);
        	ims2[i].setVisibility(0);
        	ims3[i].setVisibility(0);
        	ims4[i].setVisibility(4);
        	ims5[i].setVisibility(4);
        	ims6[i].setVisibility(4);        	
        }
    }
}