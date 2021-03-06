import algorithms.*;
import problems.EightQueens;
import problems.Equation;
import problems.Problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hojjatimani on 12/16/2016 AD.
 */
public class Main {
    /**
     * uncomment each line to run specific algorithm
     */
    public static void main(String[] args) {
//        sa1();
//        sa2();
//        sa3();
//        standardHillClimbing();
//        stochasticHillClimbing();
//        firstChoiceHillClimbing();
//        randomRestartHillClimbing();
//        genetic();
    }

    public static void sa1() {
        //linear schedule
        new SimulatedAnnealing(new EightQueens(false), time -> 100000 - time).solve();
    }

    public static void sa2() {
        // 1/x schedule
        new SimulatedAnnealing(new EightQueens(false), time -> 100000 / time).solve();
    }

    public static void sa3() {
        //stabilizer schedule
        new SimulatedAnnealing(new EightQueens(false), new SimulatedAnnealing.StabilizerSchedule()).solve();
    }

    private static void standardHillClimbing() {
        new StandardHillClimbing(new EightQueens(false)).solve();
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

    private static void genetic() {
        new Genetic(new Equation(), 20).solve();
    }
}