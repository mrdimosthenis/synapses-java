package com.github.mrdimosthenis.synapses;

import java.util.stream.Stream;

import synapses.jvm.StatsJ;

/**
 * Measure the difference between the values predicted by a neural network and the observed values.
 * <pre>
 * {@code
 * Supplier<Stream<double[][]>> expAndPredVals = () -> Arrays.stream(
 *         new double[][][]{
 *                 {{0.0, 0.0, 1.0}, {0.0, 0.1, 0.9}},
 *                 {{0.0, 1.0, 0.0}, {0.8, 0.2, 0.0}},
 *                 {{1.0, 0.0, 0.0}, {0.7, 0.1, 0.2}},
 *                 {{1.0, 0.0, 0.0}, {0.3, 0.3, 0.4}},
 *                 {{0.0, 0.0, 1.0}, {0.2, 0.2, 0.6}}
 *         }
 * );
 * }
 * </pre>
 * <p>
 * Calculate the root mean square error:
 * <pre>
 * {@code
 *  Stats.rmse(expAndPredVals.get());
 * }
 * </pre>
 * <p>
 * Calculate the score of the classification accuracy:
 * <pre>
 * {@code
 *  Stats.score(expAndPredVals.get());
 * }
 * </pre>
 */
public class Stats {

    /**
     * Root Mean Square Error.
     * <p>
     * RMSE is the standard deviation of the prediction errors.
     *
     * @param outputPairs A stream of array-pairs that contain the expected and predicted values.
     * @return The value of the RMSE metric.
     */
    public static double rmse(Stream<double[][]> outputPairs) {
        return StatsJ.rmse(outputPairs);
    }

    /**
     * Classification Accuracy.
     * <p>
     * The ratio of number of correct predictions to the total number of provided pairs.
     * For a prediction to be considered as correct, the index of its maximum expected value
     * needs to be the same with the index of its maximum predicted value.
     *
     * @param outputPairs A stream of array-pairs that contain the expected and predicted values.
     * @return The score of the classification accuracy.
     */
    public static double score(Stream<double[][]> outputPairs) {
        return StatsJ.score(outputPairs);
    }

}
