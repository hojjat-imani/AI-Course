package algorithms;

import problems.Problem;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hojjatimani on 12/19/2016 AD.
 */
public class StochasticHillClimbing {
    Problem problem;
    Problem.State currentState;
    LinkedList<Problem.State> path;
    int visitedNodes = 1;
    int expandedNodes = 1;

    public StochasticHillClimbing(Problem problem) {
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
            List<? extends Problem.State> successors = problem.getSuccessors(currentState);
            visitedNodes += successors.size();
            Problem.State next = getRandomUphillNode(problem.getValue(currentState), successors);
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

    private Problem.State getRandomUphillNode(int currentValue, List<? extends Problem.State> successors) {
        Iterator<? extends Problem.State> itr = successors.iterator();
        while (itr.hasNext()) {
            if (problem.getValue(itr.next()) <= currentValue)
                itr.remove();
        }
        if (successors.size() == 0)
            return null;
        return successors.get((int) (Math.random() * successors.size()));
    }
}
