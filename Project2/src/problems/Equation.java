package problems;

/**
 * Created by hojjatimani on 12/19/2016 AD.
 */
public class Equation implements GeneticProblem {
    GaussianMutate mutateFunc;
    double min = 0.2;
    double max = 3.14;
    double percision = 0.000000001;
    //equation: sin(x) = x^2 - x

    public Equation() {
        mutateFunc = new GaussianMutate(min, max, 0.05);
    }

    @Override
    public GeneticProblem.Individual crossOver(GeneticProblem.Individual s1, GeneticProblem.Individual s2) {
        Individual i = new Individual();
        i.value = (((Individual) s1).value + ((Individual) s2).value) / 2;
        return i;
    }

    @Override
    public GeneticProblem.Individual randomState() {
        Individual i = new Individual();
        i.value = Math.random() * (max - min) + min;
        return i;
    }

    @Override
    public double evaluate(GeneticProblem.Individual i) {
        double d = ((Individual) i).value;
        return Math.abs(Math.sin(d) - Math.pow(d, 2) + d);
    }

    @Override
    public boolean isGoal(GeneticProblem.Individual i) {
        return evaluate(i) < percision;
    }

    public class Individual implements GeneticProblem.Individual {
        double value;

        @Override
        public void mutate() {
            this.value = mutateFunc.mutate(value);
        }

        @Override
        public String toString() {
            return "" + evaluate(this);
        }
    }

    static class GaussianMutate {
        private final double min;
        private final double max;
        private final double sigma;
        private final double mutateChance;

        public GaussianMutate(double min, double max, double mutateChance) {
            this.min = min;
            this.max = max;
            this.mutateChance = mutateChance;
            this.sigma = (max - min) / 6d;
        }

        public double mutate(double val) {
//            if (Math.random() > mutateChance)
//                return val;
            double x1 = Math.random();
            double x2 = Math.random();

            // The method requires sampling from a uniform random of (0,1]
            // but Random.NextDouble() returns a sample of [0,1).
            // Thanks to Colin Green for catching this.
            if (x1 == 0)
                x1 = 1;
            if (x2 == 0)
                x2 = 1;

            double y1 = Math.sqrt(-2.0 * Math.log(x1)) * Math.cos(2.0 * Math.PI * x2);
            return clamp(y1 * sigma + val);
        }

        private double clamp(double val) {
            if (val >= max)
                return max;
            if (val <= min)
                return min;
            return val;
        }
    }
}