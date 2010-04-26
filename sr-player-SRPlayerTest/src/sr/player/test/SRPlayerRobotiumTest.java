package sr.player.test;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class SRPlayerRobotiumTest extends ActivityInstrumentationTestCase2 {

	private static Class srPlayerClass;
	static{
		try {
			srPlayerClass = Class.forName("sr.player.SRPlayer");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Solo solo;
	
	@SuppressWarnings("unchecked")
	public SRPlayerRobotiumTest() throws ClassNotFoundException {
		super("sr.player", srPlayerClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testActivityAppears(){
		solo.assertCurrentActivity("Correct activity did not appear.", srPlayerClass);
	}
	
	public void testFavouritesAppear(){
		solo.clickOnText("Favoriter");
		Assert.assertTrue(solo.searchText("Offline avsnitt"));
	}

}
