package unsw.dungeon.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EnemyBehaviourTests.class, PlayerInteractionTests.class, PlayerMovementTests.class, GoalsTests.class })
public class AllTests {

}
