package com.example.kickshot;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
 

public class MainActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
//        System.out.print("here\n");	//print to cmd line

      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      // I want to set the background to white to match main_menu_rpp!! 
      
      
      Button button_play = (Button) findViewById(R.id.button1);

      button_play.setOnClickListener(new Button.OnClickListener() {
    	    public void onClick(View v) {
    	    	Intent i = new Intent(MainActivity.this, GameBoardLayout.class);
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



