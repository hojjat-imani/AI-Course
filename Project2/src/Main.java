import algorithms.FirstChoiceHillClimbing;
import algorithms.SimulatedAnnealing;
import algorithms.StochasticHillClimbing;
import problems.EightQueens;

/**
 * Created by hojjatimani on 12/16/2016 AD.
 */
public class Main {
    public static void main(String[] args) {
//        sa3();
//        stochasticHillClimbing();
        firstChoiceHillClimbing();
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
}