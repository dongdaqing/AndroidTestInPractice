package com.example.lesson4.test;

import com.example.lesson4.LaunchActivity;
import com.example.lesson4.NextActivity;
import com.example.lesson4.R;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

/**
 * Tests LaunchActivity in isolation from the system.
 */
public class LaunchActivityTest extends ActivityUnitTestCase<LaunchActivity>{
	
	private Intent mLaunchIntent;
	
	public LaunchActivityTest(){
		super(LaunchActivity.class);
	}
	
	protected void setUp() throws Exception{
		super.setUp();
		//Create an intent to launch target Activity
		mLaunchIntent = new Intent(getInstrumentation().getTargetContext(),
				LaunchActivity.class);
	}
	/**
     * Tests the preconditions of this test fixture.
     */
	public void testPreconditions(){
		//Start the activity under test in isolation, without values for savedInstanceState and
        //lastNonConfigurationInstance
		startActivity(mLaunchIntent, null, null);
		final Button launchNextButton = (Button)getActivity().findViewById(R.id.launch_next_activity_button);
		assertNotNull("mLaunchActivity is null", getActivity());
        assertNotNull("mLaunchNextButton is null", launchNextButton);
	}
	public void testLaunchNextActivityButton_labelText(){
		startActivity(mLaunchIntent,null,null);
		final Button launchNextButton = (Button)getActivity().findViewById(R.id.launch_next_activity_button);
		final String expectButtonText = getActivity().getString(R.string.label_launch_next);
		assertEquals(expectButtonText,launchNextButton.getText().toString());
	}
	public void testNextActivityWasLaunchedWithIntent(){
		startActivity(mLaunchIntent,null,null);
		final Button launchNextButton = (Button)getActivity().findViewById(R.id.launch_next_activity_button);
		//Because this is an isolated ActivityUnitTestCase we have to directly click the
        //button from code
		launchNextButton.performClick();
		// Get the intent for the next started activity
		final Intent launchIntent = getStartedActivityIntent();
		//Verify the intent was not null.
		assertNotNull("Intent was null", launchIntent);
		//Verify that LaunchActivity was finished after button click
		assertTrue(isFinishCalled());
		
		final String payload = launchIntent.getStringExtra(NextActivity.EXTRA_PAYLOAD_KEY);
        //Verify that payload data was added to the intent
        assertEquals("Payload is empty", LaunchActivity.STRING_PAYLOAD
                , payload);
	}
}
