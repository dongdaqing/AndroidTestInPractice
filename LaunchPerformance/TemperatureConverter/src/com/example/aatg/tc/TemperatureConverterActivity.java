/**
 * Copyright (C) 2010-2011 Diego Torres Milano
 */
package com.example.aatg.tc;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.aatg.tc.TemperatureConverter.OP;

public class TemperatureConverterActivity extends Activity {

    public static final String TAG = "TemperatureConverterActivity";
    
	private static final boolean BENCHMARK_TEMPERATURE_CONVERSION = true;
	

	/**
	 * Changes fields values when text changes applying the corresponding method.
	 *
	 */
	public class TemperatureChangedWatcher implements TextWatcher {
		

		private final EditNumber mSource;
		private final EditNumber mDest;
		private OP mOp;

		/**
		 * @param mDest
		 * @param convert
		 * @throws NoSuchMethodException 
		 * @throws SecurityException 
		 */
		public TemperatureChangedWatcher(TemperatureConverter.OP op) {
			if ( op == OP.C2F ) {
				this.mSource = mCelsius;
				this.mDest = mFahrenheit;
			}
			else {
				this.mSource = mFahrenheit;
				this.mDest = mCelsius;
			}
			this.mOp = op;
		}

		/* (non-Javadoc)
		 * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
		 */
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence, int, int, int)
		 */
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence, int, int, int)
		 */
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (!mDest.hasWindowFocus() || mDest.hasFocus() || s == null ) {
				return;
			}
				
			final String str = s.toString();
			if ( "".equals(str) ) {
				mDest.setText("");
				return;
			}

            final long t0;
			if ( BENCHMARK_TEMPERATURE_CONVERSION ) {
				t0 = System.currentTimeMillis();
				Debug.startMethodTracing();
			}
			
			try {
				final double temp = Double.parseDouble(str);
				final double result = (mOp == OP.C2F) ? TemperatureConverter.celsiusToFahrenheit(temp) :
					TemperatureConverter.fahrenheitToCelsius(temp);
				final String resultString = String.format("%.2f", result);
				mDest.setNumber(result);
				mDest.setSelection(resultString.length());
			} catch (NumberFormatException e) {
				// WARNING
				// this is generated while a number is entered,
				// for example just a '-'
				// so we don't want to show the error
			} catch (Exception e) {
				mSource.setError("ERROR: " + e.getLocalizedMessage());
			}
			
			if ( BENCHMARK_TEMPERATURE_CONVERSION ) {
				Debug.stopMethodTracing();
				long t = System.currentTimeMillis() - t0;
				Log.i(TAG, "TemperatureConversion took " + t + " ms to complete.");
			}
		}

	}

	private EditNumber mCelsius;
	private EditNumber mFahrenheit;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mCelsius = (EditNumber) findViewById(R.id.celsius);
        mFahrenheit = (EditNumber) findViewById(R.id.fahrenheit);

        mCelsius.addTextChangedListener(new TemperatureChangedWatcher(OP.C2F));
        mFahrenheit.addTextChangedListener(new TemperatureChangedWatcher(OP.F2C));
    }

}