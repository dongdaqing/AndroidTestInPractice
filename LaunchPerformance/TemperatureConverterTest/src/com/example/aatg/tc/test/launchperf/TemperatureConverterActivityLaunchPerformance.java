package com.example.aatg.tc.test.launchperf;

import android.app.Activity;
import android.os.Bundle;

public class TemperatureConverterActivityLaunchPerformance extends LaunchPerformanceBase{
	/**
	 * @Title: TemperatureConverterActivityLaunchPerformance.java
	 * @Package com.example.aatg.tc.test.launchperf
	 * @Description: TODO(用一句话描述该文件做什么)
	 * @author dongdaqing
	 * @date 2014-8-26 下午3:40:18
	 * @version V1.0
	 */
	/**
     * Constructor.
     */
    public TemperatureConverterActivityLaunchPerformance() {
        super();
    }

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);

        mIntent.setClassName("com.example.aatg.tc",
                 "com.example.aatg.tc.TemperatureConverterActivity");
        start();
    }

    /**
     * Calls LaunchApp and finish.
     */
    @Override
    public void onStart() {
        super.onStart();
        LaunchApp();
        finish(Activity.RESULT_OK, mResults);
    }
}
