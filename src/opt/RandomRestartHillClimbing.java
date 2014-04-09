package opt;

import shared.Instance;

/**
 * A randomized hill climbing algorithm
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class RandomRestartHillClimbing extends OptimizationAlgorithm {
    
    /**
     * The current optimization data
     */
    private Instance cur;

    /**
     * The pocketed optimal.
     */
    private Instance pocketCur;

    /**
     * Pocket cur error.
     */
    private double pocketCurError = Double.MAX_VALUE;

    /**
     * The current value of the data
     */
    private double curVal;

    /**
     * Make a new randomized hill climbing
     */
    public RandomRestartHillClimbing(HillClimbingProblem hcp) {
        super(hcp);
        cur = hcp.random();
        curVal = hcp.value(cur);
        pocketCur = cur;
    }

    /**
     * @see shared.Trainer#train()
     */
    public double train() {
        HillClimbingProblem hcp = (HillClimbingProblem) getOptimizationProblem();
        Instance neigh = hcp.neighbor(cur);
        double neighVal = hcp.value(neigh);
        if (neighVal > curVal) {
            curVal = neighVal;
            cur = neigh;
        }
        return curVal;
    }

    public void restart(double error) {
        if (error < pocketCurError) {
            pocketCur = cur;
            pocketCurError = error;
        }
        cur = super.getOptimizationProblem().random();
        curVal = super.getOptimizationProblem().value(cur);
    }

    /**
     * @see opt.OptimizationAlgorithm#getOptimalData()
     */
    public Instance getOptimal() {
        return pocketCur;
    }

}
