package unsw.dungeon.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LitBombDestroyTest.class, PlayerDropBombTest.class, PlayerMoveBoulderTest.class,
		PlayerOpenDoorWithKeyTest.class, PlayerPickUpBombTest.class, PlayerPickUpKeyTest.class,
		PlayerPickUpPotionTest.class, PlayerPickUpSwordTest.class, PlayerPickUpTreasureTest.class,
		PlayerUseSwordTest.class })
public class PlayerInteractionTests {

}
