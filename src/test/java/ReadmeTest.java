import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

import com.github.mrdimosthenis.synapses.Attribute;
import com.github.mrdimosthenis.synapses.Codec;
import com.github.mrdimosthenis.synapses.Fun;
import com.github.mrdimosthenis.synapses.Net;
import com.github.mrdimosthenis.synapses.Stats;

import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @hidden
 */
public class ReadmeTest {

    @Test
    public void readme() {

        Net randNet = new Net(new int[]{2, 3, 1});

        randNet.json();
        // System.out.println(randNet.json());

        Net net = new Net(
                """
                        [[{"activationF" : "sigmoid", "weights" : [-0.5,0.1,0.8]},
                          {"activationF" : "sigmoid", "weights" : [0.7,0.6,-0.1]},
                          {"activationF" : "sigmoid", "weights" : [-0.8,-0.1,-0.7]}],
                         [{"activationF" : "sigmoid", "weights" : [0.5,-0.3,-0.4,-0.5]}]]
                        """);

        net.predict(new double[]{0.2, 0.6});
        // System.out.println(Arrays.toString(net.predict(new double[]{0.2, 0.6})));

        net.fit(0.1, new double[]{0.2, 0.6}, new double[]{0.9});

        new Net(new int[]{2, 3, 1}, 1000L);

        IntFunction<Fun> activationF = layerIndex ->
                switch (layerIndex) {
                    case 0 -> Fun.IDENTITY;
                    case 1 -> Fun.SIGMOID;
                    case 2 -> Fun.LEAKY_RE_LU;
                    default -> Fun.TANH;
                };

        IntFunction<Double> weightInitF = _layerIndex ->
                1.0 - 2.0 * new Random().nextDouble();

        Net customNet = new Net(new int[]{4, 6, 8, 5, 3}, activationF, weightInitF);

        customNet.svg();

        Supplier<Stream<double[][]>> expAndPredVals = () -> Arrays.stream(
                new double[][][]{
                        {{0.0, 0.0, 1.0}, {0.0, 0.1, 0.9}},
                        {{0.0, 1.0, 0.0}, {0.8, 0.2, 0.0}},
                        {{1.0, 0.0, 0.0}, {0.7, 0.1, 0.2}},
                        {{1.0, 0.0, 0.0}, {0.3, 0.3, 0.4}},
                        {{0.0, 0.0, 1.0}, {0.2, 0.2, 0.6}}
                }
        );

        Stats.rmse(expAndPredVals.get());
        // System.out.println(Stats.rmse(expAndPredVals.get()));

        Stats.score(expAndPredVals.get());
        // System.out.println(Stats.score(expAndPredVals.get()));

        Map<String, String> setosa = Map.of(
                "petal_length", "1.5",
                "petal_width", "0.1",
                "sepal_length", "4.9",
                "sepal_width", "3.1",
                "species", "setosa"
        );

        Map<String, String> versicolor = Map.of(
                "petal_length", "3.8",
                "petal_width", "1.1",
                "sepal_length", "5.5",
                "sepal_width", "2.4",
                "species", "versicolor"
        );

        Map<String, String> virginica = Map.of(
                "petal_length", "6.0",
                "petal_width", "2.2",
                "sepal_length", "5.0",
                "sepal_width", "1.5",
                "species", "virginica"
        );

        Stream dataset = Arrays.stream(
                new Map[]{setosa, versicolor, virginica}
        );

        Attribute[] attributes = {
                new Attribute("petal_length", false),
                new Attribute("petal_width", false),
                new Attribute("sepal_length", false),
                new Attribute("sepal_width", false),
                new Attribute("species", true)
        };

        Codec codec = new Codec(attributes, dataset);

        String codecJson = codec.json();
        // System.out.println(codecJson);

        new Codec(codecJson);

        double[] encodedSetosa = codec.encode(setosa);
        // System.out.println(Arrays.toString(encodedSetosa));

        codec.decode(encodedSetosa);
        // System.out.println(codec.decode(encodedSetosa));

        assertTrue(true);
    }

}
