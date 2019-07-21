package unsw.dungeon.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PlayerMovementBlockedBoulderTest.class, PlayerMovementDirectionTest.class, PlayerMovementDoorTest.class, PlayerMovementTest.class,
		PlayerMovementWallTest.class })
public class PlayerMovementTests {

}
