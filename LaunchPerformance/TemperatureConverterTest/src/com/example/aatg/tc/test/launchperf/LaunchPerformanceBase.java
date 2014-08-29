package com.example.aatg.tc.test.launchperf;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class LaunchPerformanceBase extends Instrumentation{
	/**
	 * @Title: LaunchPerformanceBase.java
	 * @Package com.example.aatg.tc.test.launchperf
	 * @Description: TODO(用一句话描述该文件做什么)
	 * @author dongdaqing
	 * @date 2014-8-26 上午10:50:58
	 * @version V1.0
	 */
	public static final String TAG = "";
	protected Bundle mResults;
	protected Intent mIntent;
	
	public LaunchPerformanceBase(){
		mResults = new Bundle();
		mIntent = new Intent(Intent.ACTION_MAIN);
		mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		setAutomaticPerformanceSnapshots();
	}
	/**
     * Launches intent {@link #mIntent}, and waits for idle before
     * returning.
     */
    protected void LaunchApp() {
        startActivitySync(mIntent);
        waitForIdleSync();
    }

	@Override
	public void finish(int resultCode, Bundle results) {
		Log.v(TAG, "Test reults = " + results);
		super.finish(resultCode, results);
	}
}
