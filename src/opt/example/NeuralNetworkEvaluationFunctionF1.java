package opt.example;

import util.linalg.Vector;
import func.nn.NeuralNetwork;
import opt.EvaluationFunction;
import shared.DataSet;
import shared.ErrorMeasure;
import shared.Instance;

/**
 * An evaluation function that uses a neural network
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class NeuralNetworkEvaluationFunctionF1 implements EvaluationFunction {
    /**
     * The network
     */
    private NeuralNetwork network;
    /**
     * The examples
     */
    private DataSet examples;
    /**
     * The error measure
     */
    private ErrorMeasure measure;
    
    /**
     * Make a new neural network evaluation function
     * @param network the network
     * @param examples the examples
     * @param measure the error measure
     */
    public NeuralNetworkEvaluationFunctionF1(NeuralNetwork network,
            DataSet examples) {
        this.network = network;
        this.examples = examples;
        this.measure = measure;
    }

    /**
     * @see opt.OptimizationProblem#value(opt.OptimizationData)
     */
    public double value(Instance d) {
        // set the links
        Vector weights = d.getData();
        network.setWeights(weights);
        // calculate the error
        int tp = 0, fp = 0, fn = 0, tn = 0;
        for (int i = 0; i < examples.size(); i++) {
            network.setInputValues(examples.get(i).getData());
            network.run();
            int target = examples.getLabelDataSet().get(0).getDiscrete();
            int cand = (int) Math.round(network.getOutputValues().get(0) + .0000001);
            if (target == 1) {
                if (cand == 1) {
                    tp++;
                } else {
                    fn++;
                }
            } else {
                if (cand == 1) {
                    fp++;
                } else {
                    tn++;
                }
            }
        }
        double prec = tp / ((double) (tp + fp));
        double rec = tp / ((double) (tp + fn));
        double f1 = 2. * (prec * rec)/(prec + rec);
        // the fitness is 1 / error
        return f1;
    }

}
