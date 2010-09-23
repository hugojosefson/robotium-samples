package sr.player.test;

import android.test.ActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;
import junit.framework.Assert;
import sr.player.SRPlayer;

public class SRPlayerRobotiumTest extends ActivityInstrumentationTestCase2<SRPlayer> {

	private Solo solo;
	
	@SuppressWarnings("unchecked")
	public SRPlayerRobotiumTest() throws ClassNotFoundException {
		super("sr.player", SRPlayer.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testActivityAppears(){
		solo.assertCurrentActivity("Correct activity did not appear.", SRPlayer.class);
	}
	
	public void testFavouritesAppear(){
		solo.clickOnText("Favoriter");
		Assert.assertTrue(solo.searchText("Offline avsnitt"));
	}

}
