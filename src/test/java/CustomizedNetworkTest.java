import static org.junit.Assert.*;

import org.junit.Test;
import com.github.mrdimosthenis.synapses.Net;
import com.github.mrdimosthenis.synapses.Fun;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 * @hidden
 */
public class CustomizedNetworkTest {

    static String readFile(String path)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.US_ASCII);
    }

    public CustomizedNetworkTest() throws IOException {
    }

    int[] layers = {4, 6, 5, 3};

    Fun activationF(int layerIndex) {
        switch(layerIndex) {
            case 0:
                return Fun.SIGMOID;
            case 1:
                return Fun.IDENTITY;
            case 2:
                return Fun.LEAKY_RE_LU;
            default:
                return Fun.TANH;
        }
    }

    double weightInitF(int _layerIndex) {
        return 1.0 - 2.0 * new Random().nextDouble();
    }

    Net justCreatedNet = new Net(layers, this::activationF, this::weightInitF);

    String justCreatedNetJson = justCreatedNet.json();

    @Test
    public void neuralNetworkOfToJson() {
        Net netJson = new Net(justCreatedNetJson);
        assertEquals(justCreatedNetJson, netJson.json());
    }

    String neuralNetworkJson = readFile("test-resources/network.json");

    String neuralNetworkSvg = readFile("test-resources/drawing.svg");

    Net neuralNetwork = new Net(neuralNetworkJson);

    double[] inputValues = {1.0, 0.5625, 0.511111, 0.47619};

    double[] expectedOutput = {0.4, 0.05, 0.2};

    double[] prediction = neuralNetwork.predict(inputValues);

    @Test
    public void neuralNetworkPrediction() {
        assertArrayEquals(
                new double[]{-0.013959435951885571, -0.16770539176070537, 0.6127887629040738},
                prediction,
                0.0001
        );
    }

    double learningRate = 0.01;

    @Test
    public void neuralNetworkNormalErrors() {
        assertArrayEquals(
                new double[]{-0.18229373795952453, -0.10254022760223255, -0.09317233470223055, -0.086806455078946},
                neuralNetwork.errors(inputValues, expectedOutput, false),
                0.0001
        );
    }

    @Test
    public void neuralNetworkZeroErrors() {
        assertArrayEquals(
                new double[]{0.0, 0.0, 0.0, 0.0},
                neuralNetwork.errors(inputValues, prediction, true),
                0.0001
        );
    }

    @Test
    public void fitNeuralNetworkPrediction() {
        neuralNetwork.fit(learningRate, inputValues, expectedOutput);
        assertArrayEquals(
                new double[]{-0.006109464554743645, -0.1770428172237149, 0.6087944183600162},
                neuralNetwork.predict(inputValues),
                0.0001
        );
    }

    @Test
    public void neuralNetworkSvg() {
        assertEquals(neuralNetworkSvg, neuralNetwork.svg());
    }

}
