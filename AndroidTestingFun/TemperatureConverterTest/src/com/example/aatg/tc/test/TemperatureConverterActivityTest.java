package com.example.aatg.tc.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.example.aatg.tc.EditNumber;
import com.example.aatg.tc.TemperatureConverterActivity;
import com.example.aatg.tc.R;

public class TemperatureConverterActivityTest extends ActivityInstrumentationTestCase2<TemperatureConverterActivity>{
	
	private TemperatureConverterActivity mActivity;
	private EditNumber mCelsius;
	private EditNumber mFahrenheit;
	private TextView mCelsiusLabel;
	private TextView mFahrenheitLabel;
	
	public TemperatureConverterActivityTest(){
		super(TemperatureConverterActivity.class);
	}
	protected void setUp() throws Exception{
		super.setUp();
		mActivity = getActivity();
		mCelsiusLabel = (TextView)mActivity.findViewById(R.id.celsius_label);
		mCelsius = (EditNumber)mActivity.findViewById(R.id.celsius);
		mFahrenheitLabel = (TextView)mActivity.findViewById(R.id.fahrenheit_label);
		mFahrenheit = (EditNumber)mActivity.findViewById(R.id.fahrenheit);
	}
	protected void tearDown() throws Exception{
		super.tearDown();
	}
	public final void testPreconditions(){
		assertNotNull(mActivity);
	}
	
}
