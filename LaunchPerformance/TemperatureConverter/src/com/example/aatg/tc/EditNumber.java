/**
 * Copyright (C) 2010-2011 Diego Torres Milano
 */
package com.example.aatg.tc;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/**
 * @author diego
 *
 */
public class EditNumber extends EditText {

	private static final String DEFAULT_FORMAT = "%.2f";

	/**
	 * @param context
	 */
	public EditNumber(Context context) {
		super(context);
		init();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public EditNumber(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public EditNumber(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	/**
	 * Initialization.
	 * Set filter.
	 *
	 */
	private void init() {
	    // DigistKeyListener.getInstance(true, true) returns an
	    // instance that accepts digits, sign and decimal point
	    final InputFilter[] filters = new InputFilter[]
	        { DigitsKeyListener.getInstance(true, true) };
	    setFilters(filters);
	}
	
	public void clear() {
		setText("");
	}

	public void setNumber(double f) {
		super.setText(String.format(DEFAULT_FORMAT, f));
	}

	public double getNumber() {
		Log.d("EditNumber", "getNumber() returning value of '" + getText().toString() + "'");
		return Double.valueOf(getText().toString());
	}

}
