package com.example.lesson5.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.example.lesson5.ReceiverActivity;
import com.example.lesson5.SenderActivity;
import com.example.lesson5.R;

public class ReceiverActivityTest extends ActivityInstrumentationTestCase2<ReceiverActivity>{
	
	private ReceiverActivity mReceiverActivity;
	private TextView singleTextView;

	public ReceiverActivityTest() {
		super(ReceiverActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	protected void setUp() throws Exception{
		super.setUp();
		mReceiverActivity = getActivity();
		singleTextView = (TextView)mReceiverActivity.findViewById(R.id.single_text_view);
	}
	
	public void testPreconditions(){
		assertNotNull("mSenderActivity is null", mReceiverActivity);
	}
	public void testTextinTextView(){
		assertEquals(mReceiverActivity.getString(R.string.receiver_text_view),singleTextView.getText().toString());
	}
	
}
