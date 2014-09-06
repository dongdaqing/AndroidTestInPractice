/**
 * Copyright (C) 2010-2011 Diego Torres Milano
 */
package com.example.aatg.tc;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * @author diego
 *
 */
public class TemperatureConverterApplication extends Application {
	private static final String TAG = "TemperatureConverterApplication";
	
	public static final int DECIMAL_PLACES_DEFAULT = 2;
	// step 2
	public static final String DECIMAL_PLACES = "decimalPlaces";
	
	// step 1
	// TODO: use SharedPreferences
	//private int decimalPlaces = DECIMAL_PLACES_DEFAULT;
	
	// step 2
	private SharedPreferences mSharedPreferences;
	
	/**
	 * 
	 */
	public TemperatureConverterApplication() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// step 2
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	}
	
	public void setDecimalPlaces(int d) {
		// step 1
		//decimalPlaces = d;
		// step 2
		final Editor editor = mSharedPreferences.edit();
		editor.putString(DECIMAL_PLACES, Integer.toString(d));
		editor.commit();
	}

	public int getDecimalPlaces() {
		// step 1
		//return decimalPlaces;
		// step 2
		return Integer.parseInt(mSharedPreferences.getString(DECIMAL_PLACES, Integer.toString(DECIMAL_PLACES_DEFAULT)));
	}

}
