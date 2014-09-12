package com.youku.phone.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;

import com.robotium.solo.Solo;

import dalvik.system.DexClassLoader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

public class MainActivityTest extends ActivityInstrumentationTestCase2{
	
	public Solo solo;
	private static Class<?> launchActivityClass;
	private static String mainActivity = "com.youku.phone.ActivityWelcome";
	String Tag = "dongdaqing";
	private static boolean isLoadDex;
	private Context mContext;
	
	static{
		try{
			launchActivityClass=Class.forName(mainActivity);
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}
	
	public MainActivityTest(){
		super(launchActivityClass);
	}
	
	protected void setUp() throws Exception{
		mContext = getInstrumentation().getTargetContext().getApplicationContext();
		solo=new Solo(getInstrumentation(),getActivity());
	}
	
	public void tearDown() throws Exception{
		solo.finishOpenedActivities();
	}
	
	public void testLaunch(){
		Log.i(Tag, "Test Processding");
		assertTrue(Tag,true);
	}
	
	@SuppressLint("NewApi")
	private void dexTool() {
		if (!isLoadDex) {
			isLoadDex = true;
			final String SECONDARY_DEX_NAME = "libs.apk";
			Log.d(Tag, "enter");
			final File dexDir = mContext.getDir("dex", Context.MODE_PRIVATE);
			dexDir.mkdir();
			final File dexFile = new File(dexDir, SECONDARY_DEX_NAME);
			final File dexOpt = mContext.getDir("dexopt", Context.MODE_PRIVATE);
			boolean hasDexFile = true;
			try {
				final InputStream ins = mContext.getAssets().open(SECONDARY_DEX_NAME);
				if (dexFile.length() != ins.available()) {
					FileOutputStream fos = new FileOutputStream(dexFile);
					byte[] buf = new byte[4096];
					int l;
					while ((l = ins.read(buf)) != -1) {
						fos.write(buf, 0, l);
					}
					fos.close();
				}
				ins.close();
			} catch (Exception e) {
				hasDexFile = false;
				Log.e("dex", e.toString());
			}

			if (hasDexFile) {
				final ApplicationInfo ai = mContext.getApplicationInfo();
				String nativeLibraryDir = null;
				if (Build.VERSION.SDK_INT > 8) {
					nativeLibraryDir = ai.nativeLibraryDir;
				} else {
					nativeLibraryDir = "/data/data/" + ai.packageName + "/lib/";
				}
				final ClassLoader cl = mContext.getClassLoader();
				final DexClassLoader dcl = new DexClassLoader(
						dexFile.getAbsolutePath(), dexOpt.getAbsolutePath(),
						nativeLibraryDir, cl.getParent());
				try {
					final Field f = ClassLoader.class
							.getDeclaredField("parent");
					f.setAccessible(true);
					f.set(cl, dcl);
				} catch (Exception e) {
					Log.e("dex", e.toString());
				}
			}
		}
	}

}
