package unsw.dungeon;

import java.util.ArrayList;

public class ComplexGoal implements GoalExpression {

	private Goal goal;
	private ArrayList<GoalExpression> subGoals;
	
	public ComplexGoal(Goal goal) {
		this.goal = goal;
		subGoals = new ArrayList<>();
	}

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
	
	@Override
	public void setComplete(Goal goal, boolean completed) {
		boolean nonExitCompleted = true;
		if (goal == Goal.EXIT && this.goal == Goal.AND) {
			if (containsExit()) {
				GoalExpression exit = null;
				for (GoalExpression g : subGoals) {
					if (g.getGoal() == Goal.EXIT) {
						exit = g;
					} else {
						g.setComplete(goal,  completed);
						nonExitCompleted = nonExitCompleted && g.isComplete();
					}
				}
				if (exit != null) {
					exit.setComplete(goal, nonExitCompleted);
				}
			}
		} else {
			for (GoalExpression g : subGoals) {
				g.setComplete(goal, completed);
			}
		}
	}
	
	@Override
	public Goal getGoal() {
		return this.goal;
	}
	
	@Override
	public void addSubGoal(GoalExpression subGoal) {
		subGoals.add(subGoal);
	}
	
	public boolean containsExit() {
		for (GoalExpression g : subGoals) {
			if (g.getGoal() == Goal.EXIT) {
				return true;
			}
		}
		return false;
	}

}
