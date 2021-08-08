package com.github.mrdimosthenis.synapses;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import synapses.custom.AttributeWithFlag;
import synapses.jvm.CodecJ;

/**
 * The constructors and methods of codecs.
 * <p>
 * Create a codec:
 * <pre>
 * {@code
 * Map<String, String> setosa = Map.of(
 *      "petal_length", "1.5",
 *      "species", "setosa"
 * );
 *
 * Map<String, String> versicolor = Map.of(
 *      "petal_length", "3.8",
 *      "species", "versicolor"
 * );
 *
 * Stream dataset = Arrays.stream(new Map[]{setosa, versicolor});
 *
 * Attribute[] attributes = {
 *      new Attribute("petal_length", false),
 *      new Attribute("species", true)
 * };
 *
 * Codec codec = new Codec(attributes, dataset);
 * }
 * </pre>
 * <p>
 * Encode a data point:
 * <pre>
 * {@code
 * codec.encode(setosa);
 * }
 * </pre>
 * <p>
 * <p>
 * Decode a data point:
 * <pre>
 * {@code
 * codec.decode(new double[]{0.0, 1.0, 0.0});
 * }
 * </pre>
 * <p>
 * <p>
 * Get the JSON representation of the codec:
 * <pre>
 * {@code
 * codec.json();
 * }
 * </pre>
 * <p>
 */
public class Codec {

    CodecJ contents;

    /**
     * Creates a codec by consuming a stream of data points.
     *
     * @param attributes An array of attributes that defines their names and types (discrete or not).
     * @param datapoints A stream that contains the data points.
     */
    public Codec(Attribute[] attributes,
                 Stream<Map<String, String>> datapoints) {
        AttributeWithFlag[] keysWithDiscreteFlags =
                Arrays.stream(attributes)
                        .map(attribute ->
                                new AttributeWithFlag(attribute.name, attribute.flag)
                        ).toArray(AttributeWithFlag[]::new);
        contents = CodecJ.apply(keysWithDiscreteFlags, datapoints);
    }

    /**
     * Creates a codec by parsing its JSON representation.
     *
     * @param json The JSON representation of a codec.
     */
    public Codec(String json) {
        contents = CodecJ.apply(json);
    }

    /**
     * Encodes a data point.
     *
     * @param datapoint A data point as a map of strings.
     * @return The encoded data point as an array of numbers between 0.0 and 1.0.
     */
    public double[] encode(Map<String, String> datapoint) {
        return contents.encode(datapoint);
    }

    /**
     * Decodes a data point.
     *
     * @param encodedValues An encoded data point as an array of numbers between 0.0 and 1.0.
     * @return The decoded data point as a map of strings.
     */
    public Map<String, String> decode(double[] encodedValues) {
        return contents.decode(encodedValues);
    }

    /**
     * The JSON representation of the codec.
     *
     * @return The JSON representation of the codec.
     */
    public String json() {
        return contents.json();
    }

}
