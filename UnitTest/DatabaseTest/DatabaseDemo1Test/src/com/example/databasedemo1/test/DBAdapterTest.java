package com.example.databasedemo1.test;

import com.example.databasedemo1.DBAdapter;

import android.test.AndroidTestCase;

public class DBAdapterTest extends AndroidTestCase{
	
	private DBAdapter dbAdapter;
	
	protected void setUp() throws Exception{
		dbAdapter = new DBAdapter(getContext());
	}
	protected void tearDown() throws Exception{
		dbAdapter.close();
	}
//	public void testCount() throws Exception{
//		assertEquals(6, dbAdapter.getCount());
//	}
	public void testInsert() throws Exception{
		dbAdapter.open();
		boolean result = dbAdapter.insertContact("789", "789@youku.com");
		assertTrue(result);
		assertEquals(9, dbAdapter.getCount());
	}

}
