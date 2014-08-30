package com.example.lesson5.test;

import com.example.lesson5.ReceiverActivity;
import com.example.lesson5.SenderActivity;
import com.example.lesson5.R;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Instrumentation;

/**
 * Functional test across multiple Activities. Tests SenderActivity and ReceiverActivity. Introduces
 * advanced Instrumentation testing practices as sending key events and interaction monitoring
 * between Activities and the system.
 */
public class SenderActivityTest extends ActivityInstrumentationTestCase2<SenderActivity>{

	private SenderActivity mSenderActivity;
	private static final int TIMEOUT_IN_MS = 5000;
	private static final String TEST_MESSAGE = "Hello Receiver";
	
	public SenderActivityTest() {
		super(SenderActivity.class);
	}
	protected void setUp() throws Exception{
		super.setUp();
		setActivityInitialTouchMode(true);
		mSenderActivity = getActivity();
	}
	/**
     * Tests the preconditions of this test fixture.
     */
	public void testPreconditions(){
		assertNotNull("mSenderActivity is null", mSenderActivity);
	}
	public void testSendMessageToReceiverActivity(){
		//Because this functional test tests interaction across multiple components these views
        //are part of the actual test method and not of the test fixture
		final Button sendToReceiverButton = (Button)mSenderActivity.findViewById(R.id.send_message_button);
		final EditText senderMessageEditText = (EditText)mSenderActivity.findViewById(R.id.message_input_edit_text);
		//Create and add an ActivityMonitor to monitor interaction between the system and the
        //ReceiverActivity
		Instrumentation.ActivityMonitor receiverActivityMonitor = getInstrumentation()
				.addMonitor(ReceiverActivity.class.getName(), null, false);
		//Request focus on the EditText field. This must be done on the UiThread because
		getInstrumentation().runOnMainSync(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				senderMessageEditText.requestFocus();
			}
			
		});
		//Wait until all events from the MainHandler's queue are processed
		getInstrumentation().waitForIdleSync();
		//Send the text message
		getInstrumentation().sendStringSync(TEST_MESSAGE);
		getInstrumentation().waitForIdleSync();
		//Click on the sendToReceiverButton to send the message to ReceiverActivity
		TouchUtils.clickView(this, sendToReceiverButton);
		//Wait until ReceiverActivity was launched and get a reference to it.
		ReceiverActivity receiverActivity = (ReceiverActivity)receiverActivityMonitor
				.waitForActivityWithTimeout(TIMEOUT_IN_MS);
		//Verify that ReceiverActivity was started
		assertNotNull("ReceiverActivity is null", receiverActivity);
		assertEquals("Monitor for ReceiverActivity has not been called",1,
				receiverActivityMonitor.getHits());
		assertEquals("Activity is of wrong type", ReceiverActivity.class,
				receiverActivity.getClass());
		//Read the message received by ReceiverActivity
		final TextView receivedMessage = (TextView)receiverActivity
				.findViewById(R.id.received_message_text_view);
		//Verify that received message is correct
		assertNotNull(receivedMessage);
		assertEquals("Wrong received message", TEST_MESSAGE, receivedMessage.getText().toString());
		//Unregister monitor for ReceiverActivity
		getInstrumentation().removeMonitor(receiverActivityMonitor);
		
	}
	
}
