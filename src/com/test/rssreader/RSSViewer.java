package com.test.rssreader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RSSViewer extends Activity{
	private TextView tvTitle;
	private TextView tvContent;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        
        this.tvTitle = (TextView) findViewById(R.id.tvTitle);
        this.tvContent = (TextView) findViewById(R.id.tvContent);
        
        RSSItem selectedRSSItem = RSSReaderActivity.selectedRssItem;
        
        String title = "\n" + selectedRSSItem.getTitle() + " )\n\n";
        String content = selectedRSSItem.getDescription() + "\n"+ selectedRSSItem.getLink();
        
        this.tvTitle.setText(title);
        this.tvContent.setText(content);
    }


}
