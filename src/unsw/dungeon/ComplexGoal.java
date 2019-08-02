package unsw.dungeon;

import java.util.ArrayList;

/**
 * The Class ComplexGoal.
 */
public class ComplexGoal implements GoalExpression {

	/** The goal. */
	private Goal goal;
	
	/** The sub goals. */
	private ArrayList<GoalExpression> subGoals;
	
	/**
	 * Instantiates a new complex goal.
	 *
	 * @param goal 		The goal
	 */
	public ComplexGoal(Goal goal) {
		this.goal = goal;
		subGoals = new ArrayList<>();
	}

	/**
	 * Checks if goal is complete.
	 *
	 * @return true, if it is complete
	 */
	@Override
	public boolean isComplete() {
		boolean completed;
		
		switch (goal) {
		case AND:
			completed = true;
			for (GoalExpression g : subGoals) {
				completed = completed && g.isComplete();
			}
			break;
		default:
			completed = false;
			for (GoalExpression g : subGoals) {
				completed = completed || g.isComplete();
			}
			break;
		}
		return completed;
	}
	
	/**
	 * Sets whether the goal is complete or not.
	 *
	 * @param goal 			The goal
	 * @param completed 	The boolean value of whether goal is completed or not
	 */
	@Override
	public void setComplete(Goal goal, boolean completed) {
		for (GoalExpression g : subGoals) {
			g.setComplete(goal, completed);
		}
	}
	
	/**
	 * Gets the goal.
	 *
	 * @return the goal
	 */
	@Override
	public Goal getGoal() {
		return this.goal;
	}
	
	
	
	/**
	 * Adds a sub goal to the goal
	 *
	 * @param subGoal 	The sub goal
	 */
	@Override
	public void addSubGoal(GoalExpression subGoal) {
		subGoals.add(subGoal);
	}
	
	/**
	 * Determines if goal contains exit.
	 *
	 * @return true, if it contains exit
	 */
	public boolean containsExit() {
		for (GoalExpression g : subGoals) {
			if (g.getGoal() == Goal.EXIT) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the subGoals.
	 *
	 * @return 			The subGoals
	 */
	public ArrayList<GoalExpression> getSubGoals() {
		return subGoals;
	}

}
