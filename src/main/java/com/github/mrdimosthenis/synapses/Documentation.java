package com.github.mrdimosthenis.synapses;

/**
 * <strong>Synapses-java</strong> is a <strong>neural networks</strong> library for <strong>Java</strong>!
 * <p>
 * <h2>Basic usage</h2>
 * <p>
 * <h3>Install synapses-java</h3>
 *
 * <pre>
 * {@code
 * <!-- https://mvnrepository.com/artifact/com.github.mrdimosthenis/synapses-java -->
 * <dependency>
 *     <groupId>com.github.mrdimosthenis</groupId>
 *     <artifactId>synapses-java</artifactId>
 *     <version>1.0.0</version>
 * </dependency>
 * }
 * </pre>
 * <p>
 * <h3>Import the <code>Net</code> class</h3>
 *
 * <pre>
 * {@code
 * import com.github.mrdimosthenis.synapses.Fun;
 * }
 * </pre>
 * <p>
 * <h3>Create a random neural network by providing its layer sizes</h3>
 *
 * <pre>
 * {@code
 * Net randNet = new Net(new int[]{2, 3, 1});
 * }
 * </pre>
 * <p>
 * <ul>
 * <li>Input layer: the first layer of the network has 2 nodes.</li>
 * <li>Hidden layer: the second layer has 3 neurons.</li>
 * <li>Output layer: the third layer has 1 neuron.</li>
 * </ul>
 * <p>
 * <h3>Get the json of the random neural network</h3>
 *
 * <pre>
 * {@code
 * randNet.json();
 * // """
 * // [[{"activationF" : "sigmoid", "weights" : [-0.5,0.1,0.8]},
 * //   {"activationF" : "sigmoid", "weights" : [0.7,0.6,-0.1]},
 * //   {"activationF" : "sigmoid", "weights" : [-0.8,-0.1,-0.7]}],
 * //  [{"activationF" : "sigmoid", "weights" : [0.5,-0.3,-0.4,-0.5]}]]
 * // """
 * }
 * </pre>
 * <p>
 * <h3>Create a neural network by providing its json</h3>
 *
 * <pre>
 * {@code
 * Net net = new Net(
 *     """
 *     [[{"activationF" : "sigmoid", "weights" : [-0.5,0.1,0.8]},
 *       {"activationF" : "sigmoid", "weights" : [0.7,0.6,-0.1]},
 *       {"activationF" : "sigmoid", "weights" : [-0.8,-0.1,-0.7]}],
 *      [{"activationF" : "sigmoid", "weights" : [0.5,-0.3,-0.4,-0.5]}]]
 *     """
 * );
 * }
 * </pre>
 * <p>
 * <h3>Make a prediction</h3>
 *
 * <pre>
 * {@code
 * net.predict(new double[]{0.2, 0.6});
 * // [0.49131100324012494]
 * }
 * </pre>
 * <p>
 * <h3>Train a neural network</h3>
 *
 * <pre>
 * {@code
 * net.fit(0.1, new double[]{0.2, 0.6}, new double[]{0.9});
 * }
 * </pre>
 * <p>
 * The <code>fit</code> method adjusts the weights of the neural network to a single observation.
 * <p>
 * In practice, for a neural network to be fully trained, it should be fitted with multiple observations.
 * <p>
 * <h2>Advanced usage</h2>
 * <p>
 * <h3>Import the rest of the classes</h3>
 *
 * <pre>
 * {@code
 * import com.github.mrdimosthenis.synapses.Attribute;
 * import com.github.mrdimosthenis.synapses.Codec;
 * import com.github.mrdimosthenis.synapses.Net;
 * import com.github.mrdimosthenis.synapses.Stats;
 * }
 * </pre>
 * <p>
 * <h3>Boost the performance</h3>
 * <p>
 * Every function is efficient because its implementation is based on lazy list
 * and all information is obtained at a single pass.
 * <p>
 * For a neural network that has huge layers, the performance can be further improved
 * by using the parallel counterparts of <code>predict</code> and <code>fit</code>
 * (<code>parPredict</code> and <code>parFit</code>).
 * <p>
 * <h3>Create a neural network for testing</h3>
 *
 * <pre>
 * {@code
 * new Net(new int[]{2, 3, 1}, 1000L);
 * }
 * </pre>
 * <p>
 * We can provide a <code>seed</code> to create a non-random neural network.
 * This way, we can use it for testing.
 * <p>
 * <h3>Define the activation functions and the weights</h3>
 *
 * <pre>
 * {@code
 * IntFunction<Fun> activationF = layerIndex ->
 *         switch (layerIndex) {
 *             case 0 -> Fun.IDENTITY;
 *             case 1 -> Fun.SIGMOID;
 *             case 2 -> Fun.LEAKY_RE_LU;
 *             default -> Fun.TANH;
 *         };
 *
 * IntFunction<Double> weightInitF = _layerIndex ->
 *         1.0 - 2.0 * new Random().nextDouble();
 *
 * Net customNet = new Net(new int[]{4, 6, 8, 5, 3}, activationF, weightInitF);
 * }
 * </pre>
 * <p>
 * <ul>
 * <li>The <code>activationF</code> function accepts the index of a layer and returns an activation function for its neurons.</li>
 * <li>The <code>weightInitF</code> function accepts the index of a layer and returns a weight for the synapses of its neurons.</li>
 * </ul>
 * <p>
 * If we don't provide these functions, the activation function of all neurons is sigmoid,
 * and the weight distribution of the synapses is normal between -1.0 and 1.0.
 * <p>
 * <h3>Draw a neural network</h3>
 *
 * <pre>
 * {@code
 * customNet.svg();
 * }
 * </pre>
 * <p>
 * With its svg drawing, we can see what a neural network looks like.
 * The color of each neuron depends on its activation function
 * while the transparency of the synapses depends on their weight.
 * <p>
 * <h3>Measure the difference between the expected and predicted values</h3>
 *
 * <pre>
 * {@code
 * Supplier<Stream<double[][]>> expAndPredVals = () -> Arrays.stream(
 *         new double[][][]{
 *             {{0.0, 0.0, 1.0}, {0.0, 0.1, 0.9}},
 *             {{0.0, 1.0, 0.0}, {0.8, 0.2, 0.0}},
 *             {{1.0, 0.0, 0.0}, {0.7, 0.1, 0.2}},
 *             {{1.0, 0.0, 0.0}, {0.3, 0.3, 0.4}},
 *             {{0.0, 0.0, 1.0}, {0.2, 0.2, 0.6}}
 *         }
 * );
 * }
 * </pre>
 * <p>
 * <ul>
 * <li>
 * Root-mean-square error
 *
 * <pre>
 * {@code
 * Stats.rmse(expAndPredVals.get());
 * // 0.6957010852370435
 * }
 * </pre>
 * </li>
 * <li>
 * Classification accuracy score
 *
 * <pre>
 * {@code
 * Stats.score(expAndPredVals.get());
 * // 0.6
 * }
 * </pre>
 * </li>
 * </ul>
 * <p>
 * <h3>Create a <code>Codec</code> by providing the attributes and the data points</h3>
 * <p>
 * <ul>
 * <li>One hot encoding is a process that turns discrete attributes into a list of 0.0 and 1.0.</li>
 * <li>Minmax normalization scales continuous attributes into values between 0.0 and 1.0.</li>
 * </ul>
 * <p>
 * You can use a <code>Codec</code> to encode and decode a data point.
 *
 * <pre>
 * {@code
 * Map<String, String> setosa = Map.of(
 *     "petal_length", "1.5",
 *     "petal_width", "0.1",
 *     "sepal_length", "4.9",
 *     "sepal_width", "3.1",
 *     "species", "setosa"
 * );
 *
 * Map<String, String> versicolor = Map.of(
 *     "petal_length", "3.8",
 *     "petal_width", "1.1",
 *     "sepal_length", "5.5",
 *     "sepal_width", "2.4",
 *     "species", "versicolor"
 * );
 *
 * Map<String, String> virginica = Map.of(
 *     "petal_length", "6.0",
 *     "petal_width", "2.2",
 *     "sepal_length", "5.0",
 *     "sepal_width", "1.5",
 *     "species", "virginica"
 * );
 *
 * Stream dataset = Arrays.stream(
 *     new Map[]{setosa, versicolor, virginica}
 * );
 *
 * Attribute[] attributes = {
 *     new Attribute("petal_length", false),
 *     new Attribute("petal_width", false),
 *     new Attribute("sepal_length", false),
 *     new Attribute("sepal_width", false),
 *     new Attribute("species", true)
 * };
 *
 * Codec codec = new Codec(attributes, dataset);
 * }
 * </pre>
 * <p>
 * <ul>
 * <li>The first parameter is a list of pairs that define the name and the type (discrete or not) of each attribute.</li>
 * <li>The second parameter is an iterator that contains the data points.</li>
 * </ul>
 * <p>
 * <h3>Get the json of the codec</h3>
 *
 * <pre>
 * {@code
 * String codecJson = codec.json();
 * // codecJson: String = """[
 * //   {"Case" : "SerializableContinuous",
 * //    "Fields" : [{"key" : "petal_length","min" : 1.5,"max" : 6.0}]},
 * //   {"Case" : "SerializableContinuous",
 * //    "Fields" : [{"key" : "petal_width","min" : 0.1,"max" : 2.2}]},
 * //   {"Case" : "SerializableContinuous",
 * //    "Fields" : [{"key" : "sepal_length","min" : 4.9,"max" : 5.5}]},
 * //   {"Case" : "SerializableContinuous",
 * //    "Fields" : [{"key" : "sepal_width","min" : 1.5,"max" : 3.1}]},
 * //   {"Case" : "SerializableDiscrete",
 * //    "Fields" : [{"key" : "species","values" : ["virginica","versicolor","setosa"]}]}
 * // ]"""
 * }
 * </pre>
 * <p>
 * <h3>Create a codec by providing its json</h3>
 *
 * <pre>
 * {@code
 * new Codec(codecJson);
 * }
 * </pre>
 * <p>
 * <h3>Encode a data point</h3>
 *
 * <pre>
 * {@code
 * double[] encodedSetosa = codec.encode(setosa);
 * // [0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0]
 * }
 * </pre>
 * <p>
 * <h3>Decode a data point</h3>
 *
 * <pre>
 * {@code
 * codec.decode(encodedSetosa);
 * // {species=setosa, sepal_width=3.1, petal_width=0.1, petal_length=1.5, sepal_length=4.9}
 * }
 * </pre>
 */
public class Documentation {
}
