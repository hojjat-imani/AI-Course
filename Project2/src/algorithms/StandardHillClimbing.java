package algorithms;

import problems.Problem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hojjatimani on 12/17/2016 AD.
 */
public class StandardHillClimbing {
    Problem problem;
    Problem.State currentState;
    int visitedNodes = 1;
    int expandedNodes = 1;

    public StandardHillClimbing(Problem problem) {
        this.problem = problem;
    }

    public void solve() {
        currentState = problem.getInitialState();
        System.out.println("initial state= " + currentState);
        System.out.println("initial value=" + problem.getValue(currentState));
        while (true) {
            List<? extends Problem.State> successors = problem.getSuccessors(currentState);
            visitedNodes += successors.size();
            Problem.State best = getBest(successors);
            if (best == null || problem.getValue(best) <= problem.getValue(currentState))
                break;
            currentState = best;
            expandedNodes++;
        }
        printResult();
    }

    private void printResult() {
        System.out.println("#VisitedNodes     =    " + visitedNodes);
        System.out.println("#ExpandedNodes    =     " + expandedNodes);
        System.out.println("solution: " + currentState);
        System.out.println("solution value:" + problem.getValue(currentState));
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
