package com.example.lesson5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class ReceiverActivity extends Activity{
	
	private static final String EXTRA_SENDER_MESSAGE_TEXT
		= "com.example.lesson5.extra.sender.message.text";
	
	public static Intent makeIntent(Context context, CharSequence message){
		return new Intent(context, ReceiverActivity.class)
			.putExtra(EXTRA_SENDER_MESSAGE_TEXT, message);
	}
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receiver);
		final CharSequence senderMessage = getIntent()
				.getCharSequenceExtra(EXTRA_SENDER_MESSAGE_TEXT);
		final TextView receiverTextView = (TextView)findViewById(R.id.received_message_text_view);
		if(!TextUtils.isEmpty(senderMessage)){
			receiverTextView.setText(senderMessage);
		}
	}
	
}
