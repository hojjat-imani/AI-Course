import algorithms.SimulatedAnnealing;
import problems.EightQueens;

/**
 * Created by hojjatimani on 12/16/2016 AD.
 */
public class Main {
    public static void main(String[] args) {
        sa3();

//        new SimulatedAnnealing(new EightQueens(), time -> {
//            if (time == 0)
//                time++;
//            return (int) (10000 / Math.pow(time, 2));
//        }).solve();


//        new SimulatedAnnealing(new EightQueens(), time -> (int) (100 / (1 + Math.pow(1 + time, 0.35)))).solve();

//        new SimulatedAnnealing(new EightQueens(), new SimulatedAnnealing.Schedule() {
//            @Override
//            public int getTemperature(int time) {
//                return (int) Math.abs((1000000000 * Math.sin(time + 1) / time + 1));
//            }
//        }).solve();

//        new StandardHillClimbing(new EightQueens()).solve();
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
}