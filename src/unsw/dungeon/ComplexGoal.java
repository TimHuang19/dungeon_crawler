package unsw.dungeon;

import java.util.ArrayList;

public class ComplexGoal implements GoalExpression {

	private Operator operator;
	private Goal goal;
	ArrayList<GoalExpression> subGoals;
	
	public ComplexGoal(Operator operator) {
		this.operator = operator;
		this.goal = Goal.COMPLEX;
		subGoals = new ArrayList<>();
	}

	@Override
	public boolean isComplete() {
		Boolean completed;
		
		switch (operator) {
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
	public void setComplete(Goal goal, Boolean completed) {
		Boolean nonExitCompleted = true;
		if (goal == Goal.EXIT && operator == Operator.AND) {
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

	public Operator getOperator() {
		return this.operator;
	}
	
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
