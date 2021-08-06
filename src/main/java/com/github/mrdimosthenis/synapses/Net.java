package com.github.mrdimosthenis.synapses;

import java.util.function.IntFunction;

import synapses.jvm.NetJ;

/**
 * The constructors and methods of neural networks.
 * <p>
 * Create a neural network:
 * <pre>
 * {@code
 * Net net = new Net(new int[]{3, 4, 5});
 * }
 * </pre>
 * <p>
 * Get the prediction for an input:
 * <pre>
 * {@code
 * net.predict(new double[]{0.4, 0.05, 0.2});
 * }
 * </pre>
 * <p>
 * Fit network to a single observation:
 * <pre>
 * {@code
 * net.fit(0.1, new double[]{0.4, 0.05, 0.2}, new double[]{0.03. 0.8});
 * }
 * </pre>
 * <p>
 * Get the JSON representation of the network:
 * <pre>
 * {@code
 * net.json();
 * }
 * </pre>
 */
public class Net {

    NetJ contents;

    /**
     * Creates a neural network.
     *
     * @param layerSizes  The size of each layer.
     * @param activationF A function that accepts the index of a layer and returns an activation function for its neurons.
     * @param weightInitF A function that accepts the index of a layer and returns a weight for the synapses of its neurons.
     */
    public Net(int[] layerSizes,
               IntFunction<Fun> activationF,
               IntFunction<Double> weightInitF) {
        contents = NetJ.apply(
                layerSizes,
                (i) -> activationF.apply(i).contents,
                weightInitF::apply
        );
    }

    /**
     * Creates a random neural network.
     * <p>
     * The activation function of all neurons is sigmoid.
     * The weight distribution of the synapses is normal between -1.0 and 1.0.
     *
     * @param layerSizes The size of each layer.
     *                   The first number in the list defines the size of the input layer.
     *                   The last number in the list defines the size of the output layer.
     *                   In order for a neural network to be deep, the list should contain more than two numbers.
     */
    public Net(int[] layerSizes) {
        contents = NetJ.apply(layerSizes);
    }

    /**
     * Creates a non-random neural network.
     * <p>
     * Calling this function with the same parameters multiple times, should always return the same neural network.
     * The activation function of the nodes is sigmoid.
     * The weight distribution of the synapses is normal between -1.0 and 1.0.
     *
     * @param layerSizes The size of each layer.
     * @param seed       A number used to initialize the internal pseudorandom number generator.
     */
    public Net(int[] layerSizes, Long seed) {
        contents = NetJ.apply(layerSizes, seed);
    }

    /**
     * Cretaes a neural network by parsing its JSON representation.
     *
     * @param json The JSON representation of a neural network.
     */
    public Net(String json) {
        contents = NetJ.apply(json);
    }

    /**
     * Makes a prediction for the provided input.
     *
     * @param inputValues The values of the features. Their size should be equal to the size of the input layer.
     * @return The prediction. It's size should be equal to the size of the output layer.
     */
    public double[] predict(double[] inputValues) {
        return contents.predict(inputValues);
    }

    /**
     * Makes a prediction for the provided input.
     * <p>
     * The calculation is performed in parallel.
     * When the neural network has huge layers, the parallel calculation boosts the performance.
     *
     * @param inputValues The values of the features. Their size should be equal to the size of the input layer.
     * @return The prediction. It's size should be equal to the size of the output layer.
     */
    public double[] parPredict(double[] inputValues) {
        return contents.parPredict(inputValues);
    }

    public double[] errors(double[] inputValues, double[] expectedOutput, boolean inParallel) {
        return contents.errors(inputValues, expectedOutput, inParallel);
    }

    /**
     * Adjust the weights of the neural network to the provided observation.
     * <p>
     * In order for it to be trained, it should fit with multiple observations.
     *
     * @param learningRate   A number that controls how much the weights are adjusted to the observation.
     * @param inputValues    The feature values of the observation.
     * @param expectedOutput The expected output of the observation.
     *                       It's size should be equal to the size of the output layer.
     */
    public void fit(double learningRate, double[] inputValues, double[] expectedOutput) {
        contents = contents.fit(learningRate, inputValues, expectedOutput);
    }

    /**
     * Adjust the weights of the neural network to the provided observation.
     * <p>
     * The calculation is performed in parallel.
     * When the neural network has huge layers, the parallel calculation boosts the performance.
     *
     * @param learningRate   A number that controls how much the weights are adjusted to the observation.
     * @param inputValues    The feature values of the observation.
     * @param expectedOutput The expected output of the observation.
     *                       It's size should be equal to the size of the output layer.
     */
    public void fitPar(double learningRate, double[] inputValues, double[] expectedOutput) {
        contents = contents.fitPar(learningRate, inputValues, expectedOutput);
    }

    /**
     * The JSON representation of the neural network.
     *
     * @return The JSON representation of the neural network.
     */
    public String json() {
        return contents.json();
    }

    /**
     * An SVG representation of the neural network.
     *
     * @return The SVG representation of the neural network.
     * The color of each neuron depends on its activation function
     * while the transparency of the synapses depends on their weight.
     */
    public String svg() {
        return contents.svg();
    }

}
