package com.example.emmademo.test;

import com.example.emmademo.MainActivity;
import com.example.emmademo.R;

import android.content.Intent;
import android.os.SystemClock;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityTest extends InstrumentationTestCase{
	/**
	 * @Title: MainActivityTest.java
	 * @Package com.example.emmademo.test
	 * @Description: TODO(用一句话描述该文件做什么)
	 * @author dongdaqing
	 * @date 2014-8-28 下午8:07:46
	 * @version V1.0
	 */
	private MainActivity mainActivity = null;
	private Button btn = null;
	private TextView tv = null;
	
	protected void setUp(){
		try {
			super.setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent intent = new Intent();
        intent.setClassName("com.example.emmademo", MainActivity.class.getName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mainActivity = (MainActivity) getInstrumentation().startActivitySync(intent);
        tv = (TextView) mainActivity.findViewById(R.id.textView);
        btn = (Button) mainActivity.findViewById(R.id.button);
	}
	protected void tearDown(){
		mainActivity.finish();
		try {
			super.tearDown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public void testActivity() throws Exception {
        Log.v("testActivity", "test the Activity");
        SystemClock.sleep(1500);
        getInstrumentation().runOnMainSync(new PerformClick(btn));
        SystemClock.sleep(3000);
        assertEquals("Hello Android", tv.getText().toString());
    }
	private class PerformClick implements Runnable {
        Button btn;
        public PerformClick(Button button) {
            btn = button;
        }
  
        public void run() {
            btn.performClick();
        }
    }
	public void testAdd() throws Exception{
        String tag = "testAdd";
        Log.v(tag, "test the method");
        int test = mainActivity.add(1, 1);
        assertEquals(2, test);
    }
}
