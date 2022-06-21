package com.example.pairingnumbergame;


import android.app.ActionBar.LayoutParams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivitypairingnumbergame extends Activity implements OnClickListener {
	
	/*note: the reason why there is two arraylist is because the first version of this only make random number per row
	 * but i did not remove the extra array for me to not forget the two solution i made thanks for understanding.
	 * */
	
	ArrayList<Integer> arr = new ArrayList<Integer>();
	ArrayList<Integer> arr2 = new ArrayList<Integer>();
	String num1="",num2="";
	int x = 0, score=0;
	LinearLayout gridLinearLayout, mainLinearLayout;
	TextView scoreTextView, timerTextView;
	Button restartButton,buttonOldGlobal,buttonCurrentGlobal;
	long millisUntilFinishedGlobal = 30000;
	int counter = 0;
	boolean isDelay = false, isGameFinished = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		restartButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				isGameFinished = false;
				scoreTextView.setText("Score: ");
				cancelTimer();
				millisUntilFinishedGlobal = 30000;	
				startTimer();	
				num1= num2= "";
				int set=0, k=0, j=0;
				score = 0;
//				setArr();
				setArr2();
				for(int i=0; i<100; i++) {
			        	LinearLayout linearLayout = (LinearLayout) gridLinearLayout.getChildAt(j);
			        	Button button = (Button) linearLayout.getChildAt(k);
			        	button.setTag(arr2.get(i));
			        	button.setText("");
			        	 if((i%2 == 0 && j%2 == 0) || (i%2 == 1 && j%2 == 1))
			        		button.setBackgroundColor(Color.WHITE);
			             else             
			        		button.setBackgroundColor(Color.GRAY);
			        	set = k +=1;	
			        	if(set == 10) {
			        		j++;
			        		set = k = 0;
//			        		setArr();
			        	}    
			    }
			}
		});
		
		//create 10 horizontal linear layout first 
		for(int i=0; i< 10; i++) {
			LinearLayout linearLayout = new LinearLayout(this);			//width				//heigth
			linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,80));
    		linearLayout.setTag("linearLayout"+i);
    		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
    		linearLayout.setWeightSum(10);   
    		gridLinearLayout.addView(linearLayout);
		}
		
		int set=0, j=0, k=0;
	   	//create buttons 10 buttons and add it per linear index by j
        for (int i=0; i < 100; i++) {       
            Button button = new Button(this);   
            button.setLayoutParams(new LinearLayout.LayoutParams(0,LayoutParams.WRAP_CONTENT, 1));                        
            button.setId(i);  
            button.setOnClickListener(this);
            button.setTextSize(11);
            if((i%2 == 0 && j%2 == 0) || (i%2 == 1 && j%2 == 1))
            	button.setBackgroundColor(Color.WHITE);
            else
            	button.setBackgroundColor(Color.GRAY);
        	LinearLayout linearLayout = (LinearLayout) gridLinearLayout.getChildAt(j);
        	linearLayout.addView(button);    		 
        	set++;
        	if(set == 10) {
        		j++;
        		set = 0;
        	}
        }
        j=k=0;   
//        setArr();
        setArr2();
        //set tag for every button
        for(int i=0; i<=100-1; i++) {
        	LinearLayout linearLayout = (LinearLayout) gridLinearLayout.getChildAt(j);
        	Button button = (Button) linearLayout.getChildAt(k);
        	button.setTag(arr2.get(i));	
        	set = k +=1;	
        	if(set == 10) {
        		j++;
        		set = 0;
        		k = 0;
//        		setArr();
        	}    
        }
        startTimer();
        
	}
	
	//Declare timer
	CountDownTimer cTimer = null;
	//start timer function
	void startTimer() {
	    cTimer = new CountDownTimer(millisUntilFinishedGlobal, 1000) {
	        public void onTick(long millisUntilFinished) {
	        	millisUntilFinishedGlobal = millisUntilFinished;
	        	timerTextView.setText((millisUntilFinished / 1000)+"s");
	        }
	        public void onFinish() {
	        	isGameFinished = true;
	        	timerTextView.setText("done!");
	        	cTimer.cancel();
	        	if(score!=50) {
	        		timerTextView.setText("loser!");
					int set=0, j=0, k=0;
        			for(int i=0; i<=100-1; i++) {
        	        	LinearLayout linearLayout = (LinearLayout) gridLinearLayout.getChildAt(j);
        	        	Button button = (Button) linearLayout.getChildAt(k);
        	        	button.setText(button.getTag().toString());	
        	        	set = k +=1;	
        	        	if(set == 10) {
        	        		j++;
        	        		set = 0;
        	        		k = 0;
        	        	}    
        	        }
	        	}
	        }
	    };
	    cTimer.start();
	}

	//cancel timer
	void cancelTimer() {
	    if(cTimer!=null) {
	        cTimer.cancel();
	    }
	}
	
//	public void setArr() {
//		arr.clear();
//		Random random = new Random(); 
//		for(int i=0; i<=10-1; i++) {
//			int num = (random.nextInt(11 - 1)+1);	
//			while(arr.contains(num)) {
//				num = (random.nextInt(11 - 1)+1);				
//			}
//			arr.add(num);
//		}
//	}
	
	public void setArr2() {
		arr2.clear();
		Random random = new Random(); 
		for(int i=0; i<=100-1; i++) {
			int num = (random.nextInt(11 - 1)+1);	
			while(Collections.frequency(arr2, num)==10) {
				num = (random.nextInt(11 - 1)+1);				
			}
			arr2.add(num);
		}
	}
	
	 public void onClick(View buttonPressed) {
		 	// show a message with the button's ID
			Button buttonLocal = (Button)buttonPressed;
			if(!(buttonLocal.getText().toString().equals("")) || isDelay || isGameFinished)
				return;
			String tag = buttonPressed.getTag().toString();
			Button button = (Button)findViewById(buttonPressed.getId());	
			button.setText(tag);		
			if(num1.equals("")) {
				num1 = tag;
				x = buttonPressed.getId();
				return;
			}
			//comparing two button block
			else{
				num2 = tag;
				if(num1.equals(num2)) {			
					Button buttonOld = (Button)findViewById(x);
					buttonOld.setBackgroundColor(Color.GREEN);
					button.setBackgroundColor(Color.GREEN);
					millisUntilFinishedGlobal += 5000;
					cancelTimer();
					startTimer();
					score += 1;
					scoreTextView.setText("Score: "+score);
					if(score == 50) {			
						isGameFinished = true;
						millisUntilFinishedGlobal = 0;
						cTimer.cancel();
						timerTextView.setText("You win!");
						Toast.makeText(getApplicationContext(),"Congrats you win!",Toast.LENGTH_SHORT).show();						
					}		
				}else{						
					Button buttonOld = (Button)findViewById(x);
					buttonOldGlobal = buttonOld;
					buttonCurrentGlobal = button;
					startTimer2();
					isDelay = true;	
					x=0;
					}
				num1=num2="";		
				}
     }
	 
		CountDownTimer cTimer2 = null;
		void startTimer2() {
		    cTimer2 = new CountDownTimer(100, 1000) {
		        public void onTick(long millisUntilFinished) {
		        	//do nothing
		        }
		        public void onFinish() {		
		        	isDelay = false;
		        	buttonOldGlobal.setText("");
		        	buttonCurrentGlobal.setText("");	
		        	cTimer2.cancel();	        	
		        }
		    };
		    cTimer2.start();
		}
	
	public void init() {
		restartButton = (Button) findViewById(R.id.restartButton);
		mainLinearLayout = (LinearLayout) findViewById(R.id.mainLinearLayout);
		gridLinearLayout = (LinearLayout) findViewById(R.id.gridLinearLayout);
		scoreTextView = (TextView) findViewById(R.id.scoreTextView);
		timerTextView = (TextView) findViewById(R.id.timerTextView);
	}
}


/* 	code reserved
new CountDownTimer(millisUntilFinishedGlobal, 1000) {
public void onTick(long millisUntilFinished) {
	timerTextView.setText((millisUntilFinished / 1000)+"s");
}

public void onFinish() {
	timerTextView.setText("done!");
}
}.start();
*/

/*this is how to convert arraylist to string
 * Toast.makeText(getApplicationContext(),Arrays.toString(arr.toArray()), Toast.LENGTH_SHORT).show();		
 * 
 * 
 * 
 * change button color: 
 * Button.setBackgroundColor(Color.RED);
 * Button.setBackgroundColor(0xFFFF0000);
 * button.setBackgroundResource(android.R.drawable.btn_default);	
 * 	
 * */

