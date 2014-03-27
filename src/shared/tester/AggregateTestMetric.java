package shared.tester;

import shared.Instance;

/**
 * Adds results to an array of test metrics.
 */
public class AggregateTestMetric extends TestMetric {

    private TestMetric[] testMetrics;

    /**
     * Ingests all the relevant tests to keep track of.
     *
     * @param testMetrics
     */
    public AggregateTestMetric(TestMetric[] testMetrics) {
        this.testMetrics = testMetrics;
    }

    @Override
    public void addResult(Instance expected, Instance actual) {
        for (TestMetric metric : testMetrics) {
            metric.addResult(expected, actual);
        }
    }

    /**
     * Prints result to System.out.
     */
    public void printResults() {
        //only report results if there were any results to report.
        for (TestMetric metric : testMetrics) {
            metric.printResults();
        }
    }
}
