package problems;

/**
 * Created by hojjatimani on 1/15/2017 AD.
 */
public interface GeneticProblem {
    interface Individual {
        void mutate();
    }

    Individual crossOver(Individual i1, Individual i2);

    Individual randomState();

    double evaluate(Individual i);

    boolean isGoal(Individual i);
}