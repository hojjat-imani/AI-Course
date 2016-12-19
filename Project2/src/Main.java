import algorithms.FirstChoiceHillClimbing;
import algorithms.RandomRestartHillClimbing;
import algorithms.SimulatedAnnealing;
import algorithms.StochasticHillClimbing;
import problems.EightQueens;
import problems.Problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hojjatimani on 12/16/2016 AD.
 */
public class Main {
    public static void main(String[] args) {
//        sa3();
//        stochasticHillClimbing();
//        firstChoiceHillClimbing();
//        randomRestartHillClimbing();
    }

    public static void sa1() {
        //linear schedule
        new SimulatedAnnealing(new EightQueens(false), time -> 10000 - time).solve();
    }

    public static void sa2() {
        //linear schedule
        new SimulatedAnnealing(new EightQueens(false), time -> 10000 / time).solve();
    }

    public static void sa3() {
        //stabilizer schedule
        new SimulatedAnnealing(new EightQueens(false), new SimulatedAnnealing.StabilizerSchedule()).solve();
    }

    public static void sa4() {
    }

    public static void sa5() {
    }

    private static void stochasticHillClimbing() {
        new StochasticHillClimbing(new EightQueens(false)).solve();
    }

    private static void firstChoiceHillClimbing() {
        new FirstChoiceHillClimbing(new EightQueens(false)).solve();
    }

    private static void randomRestartHillClimbing() {
        new RandomRestartHillClimbing(new EightQueens(false)).solve();
    }
}