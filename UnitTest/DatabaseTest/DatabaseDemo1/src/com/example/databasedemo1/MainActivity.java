package com.example.databasedemo1;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	DBAdapter db;
	Button add;
	Button query;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db = new DBAdapter(this);
		
		add = (Button)findViewById(R.id.add);
		query = (Button)findViewById(R.id.query);
		tv = (TextView)findViewById(R.id.tv);
		add.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddContact();
			}
			
		});
		
		query.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetContacts();
			}
			
		});
	}
	
	public void AddContact(){
		db.open();
		if (db.insertContact("123", "123@youku.com")){
			Toast.makeText(this, "Add Sucessful:123", Toast.LENGTH_LONG).show();
		}
		if (db.insertContact("456", "456@youku.com")){
			Toast.makeText(this, "Add Sucessful:456", Toast.LENGTH_LONG).show();
		}
		db.close();
	}
	
	public void GetContacts(){
		db.open();
		Cursor c = db.getAllContacts();
		if (c.moveToFirst()){
			do{
				DisplayContact(c);
			}while(c.moveToNext());
		}
		System.out.println("Total Contacts Number: " + db.getCount());
		db.close();
	}
	
	public void DisplayContact(Cursor c){
//		tv.setText("id: "+c.getString(0)+"\n"+"Name: "+c.getString(1)+"\n"
//				+"Email: "+c.getString(2)+"\n");
		System.out.println("id: "+c.getString(0)+"\n"+"Name: "+c.getString(1)+"\n"
				+"Email: "+c.getString(2));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
