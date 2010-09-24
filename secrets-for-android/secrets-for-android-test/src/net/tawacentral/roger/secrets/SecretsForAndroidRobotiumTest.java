package net.tawacentral.roger.secrets;

import android.test.ActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;
import junit.framework.Assert;

public class SecretsForAndroidRobotiumTest extends ActivityInstrumentationTestCase2 {

	private static Class loginActivityClass;
	static{
		try {
			loginActivityClass = Class.forName("net.tawacentral.roger.secrets.LoginActivity");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private Solo solo;

	@SuppressWarnings("unchecked")
	public SecretsForAndroidRobotiumTest() throws ClassNotFoundException {
		super("net.tawacentral.roger.secrets", loginActivityClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testLoginActivityAppears(){
		//TODO
	}

}
