package com.yourcompany.yourtestname;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

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
	}
	
	public void testActivityAppears(){
		solo.assertCurrentActivity("Correct activity did not appear.", launcherActivityClass);
	}

	public void testGoHome(){
		solo.clickOnText("Home");
		Assert.assertTrue(solo.searchText("/sdcard"));
		Assert.assertFalse(solo.searchText("/sdcard/"));
	}
	
	public void testOpenNewsRobDirectory(){
		testGoHome();
		solo.clickOnText("newsrob");
		Assert.assertTrue(solo.searchText("/sdcard/newsrob"));
		Assert.assertTrue(solo.searchText("feed_preferences"));
	}
	
	public void testListFileExtensions(){
		solo.pressMenuItem(5);
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);
		solo.clickOnText("Edit File Extensions");
		Assert.assertTrue(solo.searchText("7z"));
	}
	
	public void testEditFileExtension(){
		testListFileExtensions();
		
		solo.clickOnText("3gp");
		solo.clearEditText(2);
		solo.enterText(2, "3ghugo");
		solo.goBack();
		solo.clickOnButton("Save");
		Assert.assertTrue(solo.searchText("video/3ghugo"));
		
		solo.clickOnText("3gp");
		solo.clearEditText(2);
		solo.enterText(2, "3gp");
		solo.goBack();
		solo.clickOnButton("Save");
		Assert.assertTrue(solo.searchText("video/3gp"));
	}

}
