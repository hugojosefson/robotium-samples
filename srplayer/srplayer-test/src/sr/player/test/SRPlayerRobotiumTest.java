package sr.player.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;
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

    public void testFavouritesProgramme(){
        solo.clickOnText("Program A-Ö");
        Assert.assertTrue(solo.waitForText("Program A-Ö", 2, 90000, false));
        solo.clickLongOnText("Barnradion i P4");
        solo.clickOnText("Lägg till.*");

        solo.clickOnText("Favoriter");
        solo.clickOnText("1 favorit är tillagd");
        Assert.assertTrue(solo.searchText("Favoriter - Program"));
        Assert.assertTrue(solo.searchText("Barnradion i P4"));
        solo.clickLongOnText("Barnradion i P4");
        solo.clickOnText("Ta bort.*");
    }

}
