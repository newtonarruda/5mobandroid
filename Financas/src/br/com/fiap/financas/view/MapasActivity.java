package br.com.fiap.financas.view;

import br.com.fiap.financas.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.Menu;
import android.view.MenuItem;

public class MapasActivity extends Activity{

	static final LatLng FIAP = new LatLng(-23.573978,-46.623272);
	   private GoogleMap googleMap;
	   
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_mapas);
	      
	      
	            if (googleMap == null) {
	               googleMap = ((MapFragment) getFragmentManager().
	               findFragmentById(R.id.activity_mapas)).getMap();
	            }
	         googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	         Marker TP = googleMap.addMarker(new MarkerOptions().
	         position(FIAP).title("FIAP"));

	         googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(FIAP, 15));

	         googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

	   }
	  
	   @Override
	   public boolean onOptionsItemSelected(MenuItem item) {
	      switch (item.getItemId()) {
	         // Respond to the action bar's Up/Home button
	         case android.R.id.home:
	         NavUtils.navigateUpFromSameTask(this);
	         return true;
	      }
	      return super.onOptionsItemSelected(item);
	   }
	   @Override
	   public void onBackPressed() {
	      moveTaskToBack(true); 
	      MapasActivity.this.finish();
	   }
}
