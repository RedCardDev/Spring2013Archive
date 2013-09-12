package com.example.kickshot;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class KickShot extends Activity {
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      

      Button button_play = (Button) findViewById(R.id.button1);
      
      button_play.setOnClickListener(new Button.OnClickListener() {
    	    public void onClick(View v) {
    	    	Intent i = new Intent(KickShot.this, GameBoardLayout.class);
    	    	startActivity(i);
    	    }
      });  
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}



