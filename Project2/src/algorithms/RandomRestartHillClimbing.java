package algorithms;

import problems.Problem;

import java.util.List;

/**
 * Created by hojjatimani on 12/19/2016 AD.
 */
public class RandomRestartHillClimbing {
    Problem problem;
    Problem.State bestAnswer;
    int visitedNodes = 1;
    int expandedNodes = 1;
    Integer steps;

    public RandomRestartHillClimbing(Problem problem, int maxSteps) {
        if (maxSteps < 1) {
            throw new IllegalArgumentException("maxSteps must be more than zero");
        }
        this.problem = problem;
        this.steps = maxSteps;
    }

    public RandomRestartHillClimbing(Problem problem) {
        this.problem = problem;
    }

    public void solve() {
        for (int i = 0; steps == null || i < steps; i++) {
            Problem.State currentState = problem.getInitialState();
            while (true) {
                List<? extends Problem.State> successors = problem.getSuccessors(currentState);
                visitedNodes += successors.size();
                Problem.State best = getBest(successors);
                if (best == null || problem.getValue(best) <= problem.getValue(currentState))
                    break;
                currentState = best;
                expandedNodes++;
            }
            if (bestAnswer == null || problem.getValue(currentState) > problem.getValue(bestAnswer))
                bestAnswer = currentState;
            if (problem.isGoal(bestAnswer))
                break;
        }
        printResult();
    }

    private void printResult() {
        System.out.println("#VisitedNodes     =    " + visitedNodes);
        System.out.println("#ExpandedNodes    =     " + expandedNodes);
        System.out.println("solution: " + bestAnswer);
        System.out.println("solution value:" + problem.getValue(bestAnswer));
//        System.out.println("Path:" + path);
    }

    private Problem.State getBest(List<? extends Problem.State> successors) {
        if (successors.size() == 0)
            return null;
        Problem.State best = successors.get(0);
        int bestVal = problem.getValue(best);
        for (Problem.State s : successors) {
            if (problem.getValue(s) > bestVal)
                best = s;
        }
        return best;
    }
}
