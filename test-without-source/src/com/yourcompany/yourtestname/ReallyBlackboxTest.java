package com.yourcompany.yourtestname;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

@SuppressWarnings("unchecked")
public class ReallyBlackboxTest extends ActivityInstrumentationTestCase2 {

	private static final String TARGET_PACKAGE_ID = "com.metago.astro";
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.metago.astro.FileManagerActivity";

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
		if (solo.searchText("License Agreement", 1, false)){
			solo.clickOnButton("Accept");
		}
	}
	
	public void testActivityAppears(){
		solo.assertCurrentActivity("Correct activity did not appear.", launcherActivityClass);
	}

	public void testGoHome(){
		solo.clickOnText("Home");
		Assert.assertTrue(solo.searchText("/sdcard"));
		Assert.assertFalse(solo.searchText("/sdcard/"));
	}

	/**
	 * Remove "IGNORE_" from method name to enable this test.
	 * Not everyone has AnkiDroid installed :)
	 */
	public void IGNORE_testOpenNewsRobDirectory(){
		testGoHome();
		solo.clickOnText("AnkiDroid");
		Assert.assertTrue(solo.searchText("/sdcard/AnkiDroid"));
		Assert.assertTrue(solo.searchText("country-capitals"));
	}
	
	public void testListFileExtensions(){
		solo.clickOnMenuItem("Preferences", true);
		solo.clickOnText("Edit File Extensions");
		Assert.assertTrue(solo.searchText("7z"));
	}
	
	public void testEditFileExtension(){
		testListFileExtensions();
		
		solo.clickOnText("3gp");
		solo.clearEditText(2);
		solo.enterText(2, "3ghugo");
		solo.clickOnButton("Save");
		Assert.assertTrue(solo.searchText("video/3ghugo"));
		
		solo.clickOnText("3gp");
		solo.clearEditText(2);
		solo.enterText(2, "3gp");
		solo.clickOnButton("Save");
		Assert.assertTrue(solo.searchText("video/3gp"));
	}

}
