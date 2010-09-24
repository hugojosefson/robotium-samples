package net.tawacentral.roger.secrets;

import android.test.ActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;
import junit.framework.Assert;

public class SecretsForAndroidRobotiumTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;

	public SecretsForAndroidRobotiumTest() throws ClassNotFoundException {
		super("net.tawacentral.roger.secrets", LoginActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testLoginActivityAppears(){
		//TODO
	}

}
