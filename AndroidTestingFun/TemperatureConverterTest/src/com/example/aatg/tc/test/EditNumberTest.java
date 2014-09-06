package com.example.aatg.tc.test;

import com.example.aatg.tc.EditNumber;

import android.test.AndroidTestCase;

public class EditNumberTest extends AndroidTestCase{
	
	private EditNumber mEditNumber;
	
	public EditNumberTest(){
		this("EditNumberTest");
	}

	public EditNumberTest(String name) {
		setName(name);
	}
	protected void setUp() throws Exception{
		super.setUp();
		
		mEditNumber = new EditNumber(mContext);
		mEditNumber.setFocusable(true);
	}
	protected void tearDown() throws Exception{
		super.tearDown();
	}
	public final void testEditNumberContext(){
		assertNotNull(mEditNumber);
	}
	
}
