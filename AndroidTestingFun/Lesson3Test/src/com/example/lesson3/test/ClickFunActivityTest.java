package com.example.lesson3.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.lesson3.ClickFunActivity;
import com.example.lesson3.R;

public class ClickFunActivityTest extends ActivityInstrumentationTestCase2<ClickFunActivity>{
	
	private ClickFunActivity mClickFunActivity;
	private Button mClickMeButton;
	private TextView mInfoTextView;
	
	public ClickFunActivityTest(){
		super(ClickFunActivity.class);
	}
	/**
     * Sets up the test fixture for this test case. This method is always called before every test
     * run.
     */
	protected void setUp() throws Exception{
		super.setUp();
		//Sets the initial touch mode for the Activity under test. This must be called before
        //getActivity()
		setActivityInitialTouchMode(true);
		//Get a reference to the Activity under test, starting it if necessary.
		mClickFunActivity = getActivity();
		//Get references to all views
		mClickMeButton = (Button)mClickFunActivity.findViewById(R.id.launch_next_activity_button);
		mInfoTextView = (TextView)mClickFunActivity.findViewById(R.id.info_text_view);
	}
	/**
     * Tests the preconditions of this test fixture.
     */
	public void testPreconditions(){
		assertNotNull("mClickFunActivity is null", mClickFunActivity);
		assertNotNull("mClickMeButton is null", mClickMeButton);
		assertNotNull("mInfoTextView is null", mInfoTextView);
	}
	public void testClickMeButton_layout(){
		//Retrieve the top-level window decor view
		final View decorView = mClickFunActivity.getWindow().getDecorView();
		//Verify that the mClickMeButton is on screen
		ViewAsserts.assertOnScreen(decorView, mClickMeButton);
		//Verify width and heights
		final ViewGroup.LayoutParams layoutParams = mClickMeButton.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	}
	public void testClickMeButton_labelText(){
		//Verify that mClickMeButton uses the correct string resource
		final String expectedButtonText = mClickFunActivity.getString(R.string.label_click_me);
		final String actualButtonText = mClickMeButton.getText().toString();
		assertEquals(expectedButtonText, actualButtonText);
	}
	public void testInfoTextView_layout(){
		//Retrieve the top-level window decor view
		final View decorView = mClickFunActivity.getWindow().getDecorView();
		//Verify that the mInfoTextView is on screen and is not visible
		ViewAsserts.assertOnScreen(decorView, mInfoTextView);
		assertTrue(View.GONE == mInfoTextView.getVisibility());
	}
	public void testInfoTextViewText_isEmpty(){
		//Verify that the mInfoTextView is initialized with the correct default value
		assertEquals("",mInfoTextView.getText());
	}
	public void testClickMeButton_clickButtonAndExpectInfoText(){
		String expectText = mClickFunActivity.getString(R.string.info_text);
		//Perform a click on mClickMeButton
		TouchUtils.clickView(this, mClickMeButton);
		//Verify the that mClickMeButton was clicked. mInfoTextView is visible and contains
        //the correct text.
		assertTrue(View.VISIBLE == mInfoTextView.getVisibility());
		assertEquals(expectText, mInfoTextView.getText());
	}
	
}
