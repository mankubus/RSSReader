package com.test.rssreader;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class RSSReaderActivity extends Activity implements OnClickListener{
	private	ListView lvFeeds;
	private Button btnGo;
	private EditText edAddr;
	public	static RSSItem selectedRssItem = null;
	
	private	String feedUrl = "";
	private ArrayList<RSSItem> rssItems = new ArrayList<RSSItem>();
	private	ArrayAdapter<RSSItem> arrAdapter = null;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    	this.btnGo = (Button) findViewById(R.id.btnGo);
    	this.edAddr = (EditText) findViewById(R.id.edAddr);
    	this.lvFeeds = (ListView) findViewById(R.id.lvFeeds);
    	
    	this.feedUrl = edAddr.getText().toString();
    	btnGo.setOnClickListener(this);
    	
    	lvFeeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    	      //@Override
    	      public void onItemClick(AdapterView<?> av, View view, int index, long arg3) {
    	    	  selectedRssItem = rssItems.get(index);
    	    	  Intent intent = new Intent( "com.test.rssreader.RSSViewerAct");
    	    	  startActivity(intent);
    	      }
    	 });
    	
    	arrAdapter = new ArrayAdapter<RSSItem>(this, R.layout.listitem, rssItems);
    	lvFeeds.setAdapter(arrAdapter);

    	refreshRSS();
    }
    
    public void onClick(View v) {
    	arrAdapter.notifyDataSetChanged();
    	refreshRSS();
    }

    private void refreshRSS() {
        ArrayList<RSSItem> newItems = RSSItem.getRssItems(feedUrl);
        rssItems.clear();
        rssItems.addAll(newItems);
        arrAdapter.notifyDataSetChanged();
    }
}