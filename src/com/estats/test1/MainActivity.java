package com.estats.test1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button button1;
	Button button2;
	ListView listView1;
	EditText editText1;
	TextView textView2, textView3, textView4, textView5;
	int maxcount = 20, count = 0;
	ArrayList<String> data;
	BigDecimal med1 = new BigDecimal("0.0");
	
	
	public float CalcSD(ArrayList<String> _data) {
		float _count = 0.0f, S_radical = 0f, Sigma = 0.0f, V = 0.0f;
		
		BigDecimal summ1 = new BigDecimal("0.0");
		
		
		
		for (int i = 0; i < count; i++) {
			 
			summ1 = summ1.add(new BigDecimal(_data.get(i)));	
				
		}
		
		_count = count;
		
		BigDecimal __count = new BigDecimal(String.valueOf(_count));
		med1 = summ1.divide(__count, 5, RoundingMode.HALF_UP); //rounding scale was 2
		textView4.setText("N = " + String.valueOf(__count.intValue())); 
		
		textView3.setText("Среднее = "+med1.toString());
		
		for (int i = 0; i < count; i++) {
			
			S_radical = S_radical + (Float.valueOf(_data.get(i)) - med1.floatValue()) * (Float.valueOf(_data.get(i)) - med1.floatValue()); 
		}
		
		
	    S_radical = (float) (1 / (double)count)*S_radical;
		
	    Sigma = ((float) Math.sqrt(S_radical));// * 100;
		
		textView5.setText("СКО = "+String.valueOf(Sigma));
			
		V = 100 * Sigma / med1.floatValue();
		
		textView2.setText("Коэф. вар. V = " + String.valueOf(V) + " %");
			
		return Sigma;
		
		
	    };




	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		listView1 = (ListView) findViewById(R.id.listView1);
		listView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		editText1 = (EditText) findViewById(R.id.editText1);
		textView2 = (TextView) findViewById(R.id.textView2);
		textView3 = (TextView) findViewById(R.id.textView3);
		textView4 = (TextView) findViewById(R.id.textView4);
		textView5 = (TextView) findViewById(R.id.textView5);
		
		
		//final ArrayList<String> data = new ArrayList<String>();
		data = new ArrayList<String>();
		
		// Создаём адаптер ArrayAdapter, чтобы привязать массив к ListView
		final ArrayAdapter<String> adapter;
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, data);
	//	adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, data);
		// Привяжем массив через адаптер к ListView
		listView1.setAdapter(adapter);
		
		
		
		
		
		OnClickListener oclButton1 = new OnClickListener() {
		       @Override
		       public void onClick(View v) {
		         
		    	   	if (count < maxcount) {
		    	   		if (!(editText1.getText().toString().isEmpty())) {
		    	   	
		    	   			count++;
		    	   			data.add(0, editText1.getText().toString());
		    	   			adapter.notifyDataSetChanged();
		    	   			editText1.setText("");
		    	   			float _summ = CalcSD(data);
					
							    	   	
		    	   	    } 
		    	   		else {
		    	   			Toast toast = Toast.makeText(getApplicationContext(),"Пожалуйста, введите значение.", Toast.LENGTH_SHORT);
		    	   			toast.setGravity(Gravity.CENTER, 0, 0);
		    	   			toast.show();
		    	   			}
		    	   	}
		    	   	else {
		    	   		Toast toast = Toast.makeText(getApplicationContext(),"Превышен предел количества значений 20 шт.",Toast.LENGTH_SHORT);
		    	   		toast.setGravity(Gravity.CENTER, 0, 0);
		    	   		toast.show();
		    	   		}
		    	   
		      }
		};
		   
		
		OnClickListener oclButton2 = new OnClickListener() {
			       @Override
			       public void onClick(View v) {
			         
			    //   Toast toast = Toast.makeText(getApplicationContext(),String.valueOf(listView1.getCheckedItemPosition()),Toast.LENGTH_SHORT);
		    	//   toast.setGravity(Gravity.CENTER, 0, 0);
		    	//  toast.show();
			    	   
			       
		    	   if (data.isEmpty() != true) {
		    		   if ((listView1.getCheckedItemPosition() == -1) || (count == 1)) {
			    	   data.clear();
			    	   count = 0;
			    	   listView1.invalidateViews();
			    	   
			    	   textView4.setText("N = "); 
			   		   textView3.setText("Среднее = ");
			   		   textView5.setText("СКО = ");
			   		   textView2.setText("Коэф. вар. V = ");
			   		   editText1.setText("");
			          } else {
			        	  data.remove(listView1.getCheckedItemPosition());
			        	  count--;
			        	  listView1.invalidateViews();
			        	  CalcSD(data);
			                 }
		    	   }
			       }
			       
		};
		
		button1.setOnClickListener(oclButton1);
		
		button2.setOnClickListener(oclButton2);
		
		
		
	    
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}





	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case R.id.action_graph:
			Intent intent = new Intent(MainActivity.this, GraphBar.class);
			intent.putStringArrayListExtra("data", data);
			intent.putExtra("mean", med1.floatValue());
		    startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
						
	}
	
	

	
}
