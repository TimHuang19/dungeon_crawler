package unsw.dungeon.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GoalsEnemyTest.class, GoalsExitTest.class, GoalsLogicalCompositionTest.class, GoalsSwitchTest.class,
		GoalsTreasureTest.class })
public class GoalsTests {

}
