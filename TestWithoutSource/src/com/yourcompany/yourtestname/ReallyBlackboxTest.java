package com.yourcompany.yourtestname;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

@SuppressWarnings("unchecked")
public class ReallyBlackboxTest extends ActivityInstrumentationTestCase2 {

	private static final String TARGET_PACKAGE_ID = "com.newsrob";
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.newsrob.DashboardListActivity";

	private static Class<?> launcherActivityClass;
	static{
		try {
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ReallyBlackboxTest() throws ClassNotFoundException {
		super(TARGET_PACKAGE_ID, launcherActivityClass);
	}
	
	private Solo solo;
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testActivityAppears(){
		solo.assertCurrentActivity("Correct activity did not appear.", launcherActivityClass);
	}
	
	public void testCanOpenSettings(){
		solo.pressMenuItem(0);
	}

	public void testCanOpenSettingsSynchronization(){
		testCanOpenSettings();
		solo.clickOnText("Synchronization");
	}

}
