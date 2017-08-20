package com.estats.test1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.RelativeLayout;

public class GraphBar extends Activity {

	public ArrayList<Float> MakeNormal(ArrayList<String> _data) {
		
	//vars
		ArrayList<Float> fdata, undermean, overmean;
	//init
		fdata = new ArrayList<Float>();
		
		
	//fill fdata
	    for (int i = 0; i < _data.size()-1; i++) {
	    	fdata.add(Float.valueOf(_data.get(i)));
			}
        
        
		
		return fdata; 
		
	    };
   
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_graph_bar);
		
		Bundle extras = getIntent().getExtras();
		ArrayList<String> data = extras.getStringArrayList("data");
	    float mean = extras.getFloat("mean");
		
	    GraphViewData[] graph_data = new GraphView.GraphViewData[data.size()-1];
			   
	    for (int i=0; i<data.size()-1; i++) {
	      	graph_data[i] = new GraphViewData(i, Double.valueOf(data.get(i)));
	    }
	    GraphViewSeries series_graph_data = new GraphViewSeries(graph_data);
	    
	    			 
			GraphView graphView = new LineGraphView(
			      this // context
			      , "GraphViewDemo" // heading
			);
			graphView.addSeries(series_graph_data); // data
			 
			
			RelativeLayout layout = (RelativeLayout) findViewById(R.id.graph1);
			layout.addView(graphView);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.graph_bar, menu);
		return true;
	}

}
