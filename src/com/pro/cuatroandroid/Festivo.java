package com.pro.cuatroandroid;

import android.app.Activity;
import android.util.Log;

public class Festivo {
	
	private String pais;
	private String localizacion;
	private String sublocalizacion;
	
	private static final String TAG = "MyActivity";
	
	public Festivo(){
		   this.pais = new String("cosa3");
		   this.localizacion = new String("");
		   this.sublocalizacion = new String("");  
	}
	
	public void setpais(String p){
		
		//pais = new String(p);
		this.pais = p;
		Log.i(TAG, "pais4=" + this.pais);
	}
	
	public String getpais(){
		//String rr = new String("cosa4");
		String rr = new String(pais);
		return rr;
	}
	
	public void setlocalizacion(String lo){
		localizacion = new String(lo);
	}
	
	public String getlocalizacion(){
		return localizacion;
	}
	
	public void setsublocalizacion(String slo){
		sublocalizacion = new String(slo);
	}
	
	public String getsublocalizacion(){
		return sublocalizacion;
	}
	

}
