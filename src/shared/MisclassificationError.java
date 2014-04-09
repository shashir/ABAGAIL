package shared;

public class MisclassificationError extends AbstractErrorMeasure {

    @Override
    public double value(Instance output, Instance example) {
        double sum = 0;
        Instance label = example.getLabel();
        for (int i = 0; i < output.size(); i++) {
            sum += Math.floor(Math.abs(output.getContinuous(i) - label.getContinuous(i)))
                * example.getWeight();
        }
        return sum;
    }

}
