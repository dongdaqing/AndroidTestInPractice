package com.example.lesson4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NextActivity extends Activity{
	
	public final static String EXTRA_PAYLOAD_KEY
		= "com.example.lesson4.EXTRA_PAYLOAD_KEY";
	
	public static Intent makeIntent(Context context, String payload){
		return new Intent(context, NextActivity.class).putExtra(EXTRA_PAYLOAD_KEY, payload);
	}
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next);
		
		final String stringPayload = getIntent().getStringExtra(EXTRA_PAYLOAD_KEY);
		if (stringPayload != null){
			((TextView)findViewById(R.id.next_activity_info_text_view)).setText(stringPayload);
		}
	}
	
}
