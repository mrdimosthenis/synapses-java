import static org.junit.Assert.*;
import org.junit.Test;
import com.github.mrdimosthenis.synapses.Net;

/**
 * @hidden
 */
public class SeedNetworkTest {

    int[] layers = {4, 6, 5, 3};

    Net neuralNetwork = new Net(layers, 1000L);

    String neuralNetworkJson = "[\n" +
            "  [\n" +
            "    {\n" +
            "      \"activationF\" : \"sigmoid\",\n" +
            "      \"weights\" : [\n" +
            "        -0.4203698112641414,\n" +
            "        -0.1496727007713341,\n" +
            "        -0.8928384189584146,\n" +
            "        0.9211880913772268,\n" +
            "        0.027180243817137795\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"activationF\" : \"sigmoid\",\n" +
            "      \"weights\" : [\n" +
            "        -0.3161167802991376,\n" +
            "        -0.9489930079469029,\n" +
            "        -0.26015677306584273,\n" +
            "        -0.6978873003833059,\n" +
            "        0.2874994065396639\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"activationF\" : \"sigmoid\",\n" +
            "      \"weights\" : [\n" +
            "        -0.21496286920606544,\n" +
            "        -0.9357227175449083,\n" +
            "        -0.9155202030305005,\n" +
            "        -0.9129309852279106,\n" +
            "        -0.6978999469719491\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"activationF\" : \"sigmoid\",\n" +
            "      \"weights\" : [\n" +
            "        0.25806133542967125,\n" +
            "        0.47588465535587576,\n" +
            "        -0.5681549645809776,\n" +
            "        0.9815364553014789,\n" +
            "        -0.6175472916357287\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"activationF\" : \"sigmoid\",\n" +
            "      \"weights\" : [\n" +
            "        0.8131999422319118,\n" +
            "        -0.18065888278217646,\n" +
            "        -0.8943132277876955,\n" +
            "        0.294939737903549,\n" +
            "        -0.13946653126709108\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"activationF\" : \"sigmoid\",\n" +
            "      \"weights\" : [\n" +
            "        0.3867614051417534,\n" +
            "        0.4437184379986423,\n" +
            "        0.2690674140331535,\n" +
            "        0.32810615530021336,\n" +
            "        0.17323902449022643\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  [\n" +
            "    {\n" +
            "      \"activationF\" : \"sigmoid\",\n" +
            "      \"weights\" : [\n" +
            "        -0.20162813099768573,\n" +
            "        -0.10075233916843396,\n" +
            "        0.7276149322065253,\n" +
            "        0.8063910194692945,\n" +
            "        0.6688055519864093,\n" +
            "        -0.975106575568284,\n" +
            "        0.09221847729601618\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"activationF\" : \"sigmoid\",\n" +
            "      \"weights\" : [\n" +
            "        -0.9446600359811137,\n" +
            "        -0.5964794872043058,\n" +
            "        0.7903102784527913,\n" +
            "        -0.9051888424151342,\n" +
            "        -0.09122610135386999,\n" +
            "        0.6535569969369026,\n" +
            "        -0.22748365874317633\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"activationF\" : \"sigmoid\",\n" +
            "      \"weights\" : [\n" +
            "        0.09017935830009893,\n" +
            "        0.6208093403826789,\n" +
            "        0.33538576542866516,\n" +
            "        0.753380386023317,\n" +
            "        -0.2055709809859254,\n" +
            "        0.9488128412675767,\n" +
            "        -0.07338684978278609\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"activationF\" : \"sigmoid\",\n" +
            "      \"weights\" : [\n" +
            "        -0.07576012821021783,\n" +
            "        -0.35625015691520523,\n" +
            "        -0.06294831714455151,\n" +
            "        -0.5599640923034961,\n" +
            "        -0.35867440290460384,\n" +
            "        0.5199823465293354,\n" +
            "        0.6691922308539804\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"activationF\" : \"sigmoid\",\n" +
            "      \"weights\" : [\n" +
            "        0.07030263961505323,\n" +
            "        -0.26545912729664556,\n" +
            "        0.37560221411197214,\n" +
            "        0.2923244847426285,\n" +
            "        -0.8896329641128919,\n" +
            "        0.5311148426965802,\n" +
            "        0.6182392962661842\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  [\n" +
            "    {\n" +
            "      \"activationF\" : \"sigmoid\",\n" +
            "      \"weights\" : [\n" +
            "        0.10852652658514339,\n" +
            "        0.45388143955720217,\n" +
            "        -0.13615987609385183,\n" +
            "        -0.7968643692136412,\n" +
            "        -0.8999692624692617,\n" +
            "        -0.20406638996276305\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"activationF\" : \"sigmoid\",\n" +
            "      \"weights\" : [\n" +
            "        -0.11406885695371116,\n" +
            "        -0.18786441034717605,\n" +
            "        -0.5055219662230839,\n" +
            "        0.04524373070462362,\n" +
            "        -0.11891872316176122,\n" +
            "        -0.9069098420319777\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"activationF\" : \"sigmoid\",\n" +
            "      \"weights\" : [\n" +
            "        -0.49189194418627435,\n" +
            "        0.9655404131465519,\n" +
            "        -0.7461112874103033,\n" +
            "        -0.2337494582987365,\n" +
            "        0.03348554207672061,\n" +
            "        0.5612844635907202\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "]";

    @Test
    public void neuralNetworkOfToJson() {
        assertEquals(neuralNetwork.json(), new Net(neuralNetwork.json()).json());
    }

    @Test
    public void neuralNetworkOfJson() {
        assertEquals(neuralNetwork.json(), new Net(neuralNetworkJson).json());
    }

    double[] inputValues = {1.0, 0.5625, 0.511111, 0.47619};

    double[] prediction = neuralNetwork.predict(inputValues);

    @Test
    public void neuralNetworkPrediction() {
        assertArrayEquals(
                new double[]{0.291094231333837, 0.2908897735203388, 0.4852741175830821},
                prediction,
                0.0001
        );
    }

    double learningRate = 0.99;

    double[] expectedOutput = new double[]{0.4, 0.05, 0.9};

    @Test
    public void neuralNetworkNormalErrors() {
        assertArrayEquals(
                new double[]{
                        -0.024340361539339307,
                        -0.013691453365878362,
                        -0.012440626526733252,
                        -0.011590636761417986
                },
                neuralNetwork.errors(inputValues, expectedOutput, false),
                0.0001
        );
    }

    @Test
    public void neuralNetworkZeroErrors() {
        assertArrayEquals(
                new double[]{0.0, 0.0, 0.0, 0.0},
                neuralNetwork.errors(inputValues, prediction, false),
                0.0001
        );
    }

    @Test
    public void fitNeuralNetworkPrediction() {
        neuralNetwork.fit(learningRate, inputValues, expectedOutput);
        assertArrayEquals(
                new double[]{0.3000848628242431, 0.26517382555679964, 0.548974781113718},
                neuralNetwork.predict(inputValues),
                0.0001
        );
    }

}
