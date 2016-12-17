import algorithms.SimulatedAnnealing;
import problems.EightQueens;

/**
 * Created by hojjatimani on 12/16/2016 AD.
 */
public class Main {
    public static void main(String[] args) {
//        new SimulatedAnnealing(new EightQueens(), time -> 500 - time).solve();

//        new SimulatedAnnealing(new EightQueens(), time -> {
//            if (time == 0)
//                time++;
//            return (int) (10000 / Math.pow(time, 2));
//        }).solve();

//        new SimulatedAnnealing(new EightQueens(), time -> 35 - (int) (time * 0.05)).solve();
        new SimulatedAnnealing(new EightQueens(), time -> (int) (100 / (1 + Math.pow(1 + time, 0.35)))).solve();
    }
}