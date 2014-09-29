package com.example.databasedemo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
	
	static final String KEY_ROWID = "_id";
	static final String KEY_NAME = "name";
	static final String KEY_EMAIL = "email";
	static final String DATABASE_TABLE = "contacts";
	
	SQLiteDatabase db;
	DBHelper dbHelper;
	final Context context;
	
	public DBAdapter(Context ctx){
		this.context = ctx;
		dbHelper = new DBHelper(context); 
	}
	
	public DBAdapter open(){
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public boolean insertContact(String name, String email){
		ContentValues initValues = new ContentValues();
		initValues.put(KEY_NAME, name);
		initValues.put(KEY_EMAIL, email);
		long id = db.insert(DATABASE_TABLE, null, initValues);
		if (id != 0){
			return true;
		}
		return false;
	}
	public Cursor getAllContacts(){
		return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, KEY_EMAIL}, null, null, null, null, null);
	}
	public long getCount(){
		db = dbHelper.getWritableDatabase();
		return DatabaseUtils.queryNumEntries(db, DATABASE_TABLE);
	}

}
