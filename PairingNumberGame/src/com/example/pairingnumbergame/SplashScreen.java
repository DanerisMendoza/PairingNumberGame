package com.example.pairingnumbergame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					Intent callSomething = new Intent(".MainActivitypairingnumbergame");
					overridePendingTransition(0, 0);
					startActivity(callSomething);
					overridePendingTransition(0, 0);
					finish();				
				}
			}
		};
		timer.start();
	}
	
}
