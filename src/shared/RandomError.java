package shared;

import java.util.Random;

public class RandomError extends AbstractErrorMeasure {

    Random rand = new Random();
    @Override
    public double value(Instance output, Instance example) {
        // TODO Auto-generated method stub
        return rand.nextDouble()*100;
    }

}
