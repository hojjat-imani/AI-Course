package algorithms;

import problems.GeneticProblem;
import problems.GeneticProblem.Individual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hojjatimani on 12/19/2016 AD.
 */
public class Genetic {
    GeneticProblem problem;
    int populationSize;

    public Genetic(GeneticProblem problem, int populationSize) {
        this.problem = problem;
        this.populationSize = populationSize;
    }

    public void solve() {
        List<Individual> population = createInitialPopulation();
        while (!goalFound(population)) {
            List<Individual> parents = selectParents(population);
            List<Individual> offsprings = generateOffsprings(parents);
            mutateOffsprings(offsprings);
            population = selectNewPopulation(offsprings, population);
        }
        printResult(population);
    }

    private List<Individual> selectNewPopulation(List<Individual> offsprings, List<Individual> population) {
        //rank selection
        population.addAll(offsprings);
        sort(population);
        List<Individual> newPopulation = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            int rand = (int) (Math.random() * (population.size() * population.size() + 1) / 2);
            for (int j = 1; ; j++) {
                rand -= j;
                if (rand <= 0) {
                    newPopulation.add(population.get(j - 1));
                    break;
                }
            }
        }
        return newPopulation;
    }

    private void mutateOffsprings(List<Individual> offsprings) {
        for (Individual offspring : offsprings)
            offspring.mutate();
    }

    private List<Individual> selectParents(List<Individual> population) {
        sort(population);
        return population.subList(population.size() / 2, population.size());
    }

    private List<Individual> generateOffsprings(List<Individual> parents) {
        List<Individual> res = new ArrayList<>();
        for (int i = 0; i < parents.size() - 1; i++)
            res.add(problem.crossOver(parents.get(i), parents.get(i + 1)));
        return res;
    }

    private boolean goalFound(List<Individual> population) {
        for (Individual i : population)
            if (problem.isGoal(i))
                return true;
        return false;
    }

    private List<Individual> createInitialPopulation() {
        List<Individual> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++)
            population.add(problem.randomState());
        return population;
    }

    private void sort(List<Individual> list) {
        list.sort((o1, o2) -> {
            double v1 = problem.evaluate(o1);
            double v2 = problem.evaluate(o2);
            return v1 < v2 ? 1 : (v1 == v2 ? 0 : -1);
        });
    }

    private void printResult(List<Individual> population) {
        for (Individual i : population) {
            if (problem.isGoal(i)) {
                System.out.println();
                System.out.println("Solution: " + i);
                System.out.println("Error: " + problem.evaluate(i));
            }
        }
    }
}