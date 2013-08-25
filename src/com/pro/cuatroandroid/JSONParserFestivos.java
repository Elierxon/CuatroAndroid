package com.pro.cuatroandroid;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.ImageView;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class JSONParserFestivos {
	
	private Activity activity;
	private JSONObject jObject; 
	private ProgressDialog progressDialog = null;
	private Runnable runReadAndParseJSON;
	private Festivo f = new Festivo();
	
	ImageView[] ims = new ImageView[7];
    ImageView[] ims2 = new ImageView[7];
    ImageView[] ims3 = new ImageView[7];
    ImageView[] ims4 = new ImageView[7];
    ImageView[] ims5 = new ImageView[7];
    ImageView[] ims6 = new ImageView[7];

	
	private static final String TAG = "MyActivity";
	
	
	public JSONParserFestivos(Activity a){
	   activity = a;
	}

	public void readAndParseJSONFestivos() throws JSONException{
	   runReadAndParseJSON = new Runnable() {
	      @Override
	      public void run() {
	       try{
	          readJSONFestivos();
	       } catch(Exception e){}
	      }
	   };
	   Thread thread = new Thread(null, runReadAndParseJSON,"bgReadJSONFestivos");
	   thread.start();
	   progressDialog = ProgressDialog.show(activity, "Descargando información", "Por favor espere",true);
	}

	private void readJSONFestivos() throws JSONException{
	   jObject = JSONManager.getJSONfromURL("http://10.230.174.58/serpro/index.php?lat=28.068951&lng=-15.455067");
	   if(jObject != null)
	          parseJSONFestivos(jObject);
	   activity.runOnUiThread(returnRes);
	}

	private void parseJSONFestivos(JSONObject festivos) throws JSONException{
	  /*for(int i = 0; i < festivosArray.length(); i++){
	     Festivo f = new Festivo();
	     f.setpais(festivosArray.getJSONObject(i).getString("id"));
	     f.setlocalizacion(festivosArray.getJSONObject(i).getString("libro"));
	     f.setsublocalizacion(festivosArray.getJSONObject(i).getString("isbn"));
	     //f.save(activity);
	  }*/
		     f.setpais(festivos.getString("pais"));
		     //Log.i(TAG, "pais2=" + festivos.getString("pais"));
		     f.setlocalizacion(festivos.getString("localizacion"));
		     f.setsublocalizacion(festivos.getString("sublocalizacion"));
		     //f.save(activity);
		     
		     ////
		     /*for(int i=0; i<7;i++){
		        	CuatroAndroidActivity.ims[i].setVisibility(flags[i]);
		        	ims2[i].setVisibility(flags[i+7]);
		        	ims3[i].setVisibility(flags[i+14]);
		        	ims4[i].setVisibility(flags[i+21]);
		        	ims5[i].setVisibility(flags[i+28]);
		        	ims6[i].setVisibility(flags[i+35]);        	
		      }*/
		     //visibilidades de marca del mes
		     //if(CuatroAndroidActivity.getmonth()==2){}
		     //pasa el metodo a u usar lode dia del mes y convertirdeformato-de31dias-a6semanas???
		     //podria mover las imagenes en oncreate en vez de cambiar de formato
		     
		     //desvisualizar todos los marcadores, leer cada festivo, coger el dia y visualizar la imagen
		     
		     //CuatroAndroidActivity.resetflags();//
		     //o si se mueven imagenes//
		     boolean tflags[] = new boolean[32];//o int
		     for(int i=0;i<32;i++){
		    	 tflags[i] = false;
		     }
		     
		     JSONArray fpais = festivos.getJSONArray("arraypais");
		     
		     Log.i(TAG, "inidias");
		     
		     for(int i=0;i<fpais.length();i++){
		    	 JSONObject dfest = fpais.getJSONObject(i);
		    	 String sdfest = dfest.getString("dia");
		    	 Log.i(TAG, "dia=" + sdfest);
		    	 Log.i(TAG, "dia=" + sdfest.substring(8,10));
		    	 int dvalue = Integer.parseInt(sdfest.substring(8,10));
		    	 tflags[15]=true;
		     }
		     
		     /*for(int i=0;i<32;i++){
		    	 if(tflags[i])
		    		 CuatroAndroidActivity.setvis(0,i);
		    	 else
		    		 CuatroAndroidActivity.setvis(4,i);
		     }*/
		     /*CuatroAndroidActivity.setvis(0,0);
		     CuatroAndroidActivity.setvis(4,1);
		     CuatroAndroidActivity.setvis(4,2);
		     CuatroAndroidActivity.setvis(0,3);
		     CuatroAndroidActivity.setvis(0,4);
		     CuatroAndroidActivity.setvis(4,5);
		     CuatroAndroidActivity.setvis(4,6);*/
		     int[] b = {0,0,0,4,4,4,4,
		    			4,4,4,4,4,4,4,
		    			4,4,4,4,4,4,4,
		    			4,4,4,4,4,4,4,
		    			4,4,4,4,4,4,4,
		    			4,4,4,4,0,0,0};
		     CuatroAndroidActivity.setvis(b);
		     

	}

	private Runnable returnRes = new Runnable(){ 
	 @Override 
	 public void run() {
	  progressDialog.dismiss();
	 }
	};
	
	public String getpais(){
		//String aa = new String("cosa");
		//return aa;
		return f.getpais();
	}
	
	public String getlocalizacion(){
		
		return f.getlocalizacion();
	}
	
	public String getsublocalizacion(){
		
		return f.getsublocalizacion();
	}

}
