package algorithms;

import problems.Problem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hojjatimani on 12/16/2016 AD.
 */
public class SimulatedAnnealing {
    Problem problem; //the problem to solve
    Schedule schedule; //a mapping between time and temperature
    Problem.State currentState;
    LinkedList<Problem.State> path;
    int visitedNodes;
    int expandedNodes;

    public interface Schedule {
        int getTemperature(int time);
    }

    public SimulatedAnnealing(Problem problem, Schedule schedule) {
        this.problem = problem;
        this.schedule = schedule;
        path = new LinkedList<>();
    }

    public void solve() {
        int T;
        int deltaE;
        Problem.State nextState;
        List<? extends Problem.State> successors;
        nextState = currentState = problem.getInitialState();
        System.out.println("initial state:" + currentState);
        System.out.println("initial state value:" + problem.getValue(currentState));
        for (int time = 0; true; time++) {
            if (currentState == nextState)
                path.add(currentState);
            T = schedule.getTemperature(time);
            if (T == 0)
                break;
            successors = problem.getSuccessors(currentState);
            nextState = random(successors);
            expandedNodes++;
            visitedNodes += successors.size();
            deltaE = problem.getValue(nextState) - problem.getValue(currentState);
            if (deltaE >= 0) {
                currentState = nextState;
            } else {
                if (random(getProbability(T, deltaE)))
                    currentState = nextState;
            }
        }
        printResult();
    }

    private double getProbability(int T, int deltaE) {
        double d = Math.pow(Math.E, (float) deltaE / T);
//        System.out.println("T=" + T + "  de=" + deltaE + "    p=" + d);
        return d;
    }

    private <T> T random(List<T> items) {
        return items.get((int) (Math.random() * items.size()));
    }

    private boolean random(double probability) {
        return Math.random() < probability;
    }

    public void printResult() {
        System.out.println("SimulatedAnnealing Output <<");
        System.out.println("#VisitedNodes     =    " + visitedNodes);
        System.out.println("#ExpandedNodes    =     " + expandedNodes);
        System.out.println("solution: " + currentState);
        System.out.println("solution value:" + problem.getValue(currentState));
//        System.out.println("Path:" + path);
        System.out.println("SimulatedAnnealing Output >>");
    }
}