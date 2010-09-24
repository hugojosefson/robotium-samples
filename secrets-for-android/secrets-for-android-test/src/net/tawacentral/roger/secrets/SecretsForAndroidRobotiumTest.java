package net.tawacentral.roger.secrets;

import android.test.ActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;
import junit.framework.Assert;

public class SecretsForAndroidRobotiumTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	private static final String MASTER_PASSWORD = "Some password :)";
	private Solo solo;

	public SecretsForAndroidRobotiumTest() throws ClassNotFoundException {
		super("net.tawacentral.roger.secrets", LoginActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testLoginActivityAppears(){
		solo.assertCurrentActivity("Login activity did not appear.", LoginActivity.class);
		Assert.assertTrue(solo.searchText("Welcome to your Secrets"));
	}

	private void loginOrCreateMasterPassword(){
		if (solo.searchText("Create a password to protect your secrets.")){
			// Create password
			solo.enterText(0, MASTER_PASSWORD);
			solo.sendKey(Solo.ENTER);

			Assert.assertTrue(solo.searchText("Validate your new password"));
			solo.enterText(0, MASTER_PASSWORD);
			solo.sendKey(Solo.ENTER);
		}else {
			// Login
			Assert.assertFalse(solo.searchText("Validate your new password"));
			solo.enterText(0, MASTER_PASSWORD);
			solo.sendKey(Solo.ENTER);
		}
	}

}
