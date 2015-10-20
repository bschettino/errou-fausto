package schettino.bruno.errrroooou;

import schettino.bruno.errrroooou.R;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements AccelerometerListener{
	MediaPlayer mediaPlayerErrrou;
	MediaPlayer mediaPlayerFogo;
	ImageView imgErro;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayerErrrou = MediaPlayer.create(MainActivity.this, R.raw.erro);
        mediaPlayerFogo = MediaPlayer.create(MainActivity.this, R.raw.fogo);
        mediaPlayerErrrou.start();
        imgErro = (ImageView) findViewById(R.id.imgErro);

        imgErro.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            @Override
            public void onSwipeLeft() {
            	mediaPlayerFogo.seekTo(0);
            	mediaPlayerFogo.start();
            }
            @Override
            public void onSwipeRight() {
            	mediaPlayerFogo.seekTo(0);
            	mediaPlayerFogo.start();
            }
        });
        
    }
    
    public void onAccelerationChanged(float x, float y, float z) {
        // TODO Auto-generated method stub
         
    }
 
    public void onShake(float force) {  
    	if (force > 30){
    			mediaPlayerErrrou.seekTo(0);
    	        mediaPlayerErrrou.start();
    	}
    }
 
    @Override
    public void onResume() {
            super.onResume();
            listenToAccelerometer();
    }
    @Override
    public void onPause(){
    	super.onPause();
    	stoplisteningToAccelerometer();
    }
     
    @Override
    public void onStop() {
            super.onStop();
            stoplisteningToAccelerometer();      
    }
     
    @Override
    public void onDestroy() {
        super.onDestroy();
        stoplisteningToAccelerometer();       
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//    
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
    
    

    
    public void listenToAccelerometer(){
    	 if (AccelerometerManager.isSupported(this)) {
             AccelerometerManager.startListening(this);
         }
    }
    
    public void stoplisteningToAccelerometer(){
    	if (AccelerometerManager.isListening()) {
            AccelerometerManager.stopListening();
        }
    }
 
}
