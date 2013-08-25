package com.pro.cuatroandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class OverlayMapa extends Overlay {
    //private Double latitud = 37.40*1E6;
    //private Double longitud = -5.99*1E6;
    public Integer latitud = MapsActivity.lat1;
    public Integer longitud = MapsActivity.lon1;
    //private Integer kk= MapsActivity.lon1;
    //latitud = MapsActivity.lon1.doubleValue();
    
    
    //public static final String PREFS_NAME = "MyPrefsFile";
    
    //SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
    
    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow)
    {    	
        Projection projection = mapView.getProjection();
        GeoPoint geoPoint =
            new GeoPoint(latitud, longitud);
 
        if (shadow == false)
        {
            Point centro = new Point();
            projection.toPixels(geoPoint, centro);
 
            //Definimos el pincel de dibujo
            Paint p = new Paint();
            p.setColor(Color.BLUE);
 
            //Marca Ejemplo 1: Círculo y Texto
            //canvas.drawCircle(centro.x, centro.y, 5, p);
            //canvas.drawText("Sevilla", centro.x+10, centro.y+5, p);
            
          //Marca Ejemplo 2: Bitmap
            Bitmap bm = BitmapFactory.decodeResource(
                    mapView.getResources(),
                    R.drawable.marcador_google_maps);
            
             
            /*canvas.drawBitmap(bm, centro.x - bm.getWidth(),
                    centro.y - bm.getHeight(), p);*/
            canvas.drawBitmap(bm, centro.x - (bm.getWidth()/2), centro.y - bm.getHeight(), p);
        }
    }
    
    @Override
    public boolean onTap(GeoPoint point, MapView mapView)
    {
        Context contexto = mapView.getContext();
        String msg = "Lat: " + point.getLatitudeE6()/1E6 + " - " +
            "Lon: " + point.getLongitudeE6()/1E6;
        
        //latitud = point.getLatitudeE6()/1E6;
        //longitud = point.getLongitudeE6()/1E6;
        latitud = point.getLatitudeE6();
        longitud = point.getLongitudeE6();
        //MapsActivity.lat1 = latitud.intValue();
        //MapsActivity.lon1 = longitud.intValue();
        
        //MapsActivity.cambiolatitud(latitud.intValue());
        //MapsActivity.cambiolongitud(longitud.intValue());
        MapsActivity.cambiolatitud(point.getLatitudeE6());
        MapsActivity.cambiolongitud(point.getLongitudeE6());
        PreferencesActivity.l1 = point.getLatitudeE6();
        PreferencesActivity.l2 = point.getLongitudeE6();
     
        Toast toast = Toast.makeText(contexto, msg, Toast.LENGTH_SHORT);
        toast.show();
     
        return true;
    }
    
}
