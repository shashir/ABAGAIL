package shared.tester;

import shared.DataSet;
import shared.Instance;

/**
 * This interface defines an API for testers, which run test data through
 * a classifier and accumulate the results.  How the classifier, test metrics,
 * and supporting objects are injected is left up to the implementation.
 * 
 * @author Jesse Rosalia (https://www.github.com/theJenix)
 * @date 2013-03-05
 */
public abstract class Tester {

    /**
     * Test a classifier using the instances passed in.  Note that these can
     * also be your training instances, to test with your training set.
     * 
     * @param instances
     */
    public abstract void test(Instance[] instances);

    /**
     * Test a classifier using the instances passed in.  Note that these can
     * also be your training instances, to test with your training set.
     * 
     * @param instances data set.
     */
    public void test(DataSet instances) {
        this.test(instances.getInstances());
    }
}
