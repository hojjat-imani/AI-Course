package algorithms;

import problems.Problem;

import java.util.List;

/**
 * Created by hojjatimani on 12/16/2016 AD.
 */
public class SimulatedAnnealing {
    Problem problem; //the problem to solve
    Schedule schedule; //a mapping between time and temperature
    Problem.State currentState;
    int visitedNodes;
    int expandedNodes;

    //stuff used fro printing progress
    float startT;
    int progress;

    public interface Schedule {
        float getTemperature(int time);
    }

    public SimulatedAnnealing(Problem problem, Schedule schedule) {
        this.problem = problem;
        this.schedule = schedule;
    }

    public void solve() {
        float T;
        int deltaE;
        Problem.State nextState;
        List<? extends Problem.State> successors;
        currentState = problem.getInitialState();
        printStart();
        for (int time = 1; true; time++) {
            T = schedule.getTemperature(time);
            printProgress(T);
            if (T <= 0)
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

    private void printStart() {
        System.out.println("Initial Value = " + problem.getValue(currentState) + "\n" + currentState);
        System.out.println("Running...");
        for (int i = 0; i < 50; i++)
            System.out.print("\"");
        System.out.println();
        startT = schedule.getTemperature(1);
    }

    private void printProgress(float t) {
        if ((int) ((1 - t / startT) * 50) > progress) {
            for (int i = 0; i < (int) ((1 - t / startT) * 50) - progress; i++)
                System.out.print("\"");
            progress = (int) ((1 - t / startT) * 50);
        }
    }

    private double getProbability(float T, int deltaE) {
        return Math.exp(deltaE / T);
    }

    private <T> T random(List<T> items) {
        return items.get((int) (Math.random() * items.size()));
    }

    private boolean random(double probability) {
        return Math.random() < probability;
    }

    public void printResult() {
        printProgress(0);
        System.out.println("\n\n");
        System.out.println("#VisitedNodes     =    " + visitedNodes);
        System.out.println("#ExpandedNodes    =     " + expandedNodes);
        System.out.println("solution value:" + problem.getValue(currentState) + "\n" + currentState);
    }

    public static class StabilizerSchedule implements Schedule {
        float stabilizer = 35f;
        float stabilizeFactor = 1.005f;
        float T = 35;
        float coolingFactor = 0.05f;

        public StabilizerSchedule() {
        }

        public StabilizerSchedule(float stabilizer, float stabilizeFactor, float t, float coolingFactor) {
            this.stabilizer = stabilizer;
            this.stabilizeFactor = stabilizeFactor;
            T = t;
            this.coolingFactor = coolingFactor;
        }

        @Override
        public float getTemperature(int time) {
            if (time % (int) stabilizer == 0) {
                T -= coolingFactor;
                stabilizer *= stabilizeFactor;
            }
            return T;
        }
    }
}