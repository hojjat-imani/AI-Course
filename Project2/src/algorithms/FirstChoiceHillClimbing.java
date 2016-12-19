package algorithms;

import problems.Problem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hojjatimani on 12/19/2016 AD.
 */
public class FirstChoiceHillClimbing {
    Problem problem;
    Problem.State currentState;
    LinkedList<Problem.State> path;
    int visitedNodes = 1;
    int expandedNodes = 1;

    public FirstChoiceHillClimbing(Problem problem) {
        this.problem = problem;
        this.path = new LinkedList<>();
    }

    public void solve() {
        currentState = problem.getInitialState();
        System.out.println("initial state= " + currentState);
        System.out.println("initial value=" + problem.getValue(currentState));
        while (true) {
            path.add(currentState);
            System.out.println("val=" + problem.getValue(currentState));

            //for simplicity i generate all nodes once and check their
            //value randomly and choose the first uphill move, however
            //I had better actually generate nodes randomly

            List<? extends Problem.State> successors = problem.getSuccessors(currentState);
            Problem.State next = null;
            int currValue = problem.getValue(currentState);
            while (next == null && successors.size() > 0) {
                next = random(successors);
                visitedNodes++;
                if (problem.getValue(next) <= currValue) {
                    successors.remove(next);
                    next = null;
                }
            }
            if (next == null)
                break;
            currentState = next;
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

    private Problem.State random(List<? extends Problem.State> items) {
        return items.get((int) (Math.random() * items.size()));
    }

    private Problem.State getFirstChoiceNextRandomNode() {
        List<? extends Problem.State> successors = problem.getSuccessors(currentState);
        Problem.State next = null;
        int currValue = problem.getValue(currentState);
        while (next == null && successors.size() > 0) {
            next = random(successors);
            if (problem.getValue(next) <= currValue) {
                successors.remove(next);
                next = null;
            }
        }
        return next;
    }
}
