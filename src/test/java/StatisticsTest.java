import static org.junit.Assert.*;
import org.junit.Test;
import com.github.mrdimosthenis.synapses.Stats;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @hidden
 */
public class StatisticsTest {

    @Test
    public void rootMeanSquareError() {
        double[][][] expectedWithOutputValuesArr = {
                {{0.0, 0.0, 1.0}, {0.0, 0.0, 1.0}},
                {{0.0, 0.0, 1.0}, {0.0, 1.0, 1.0}}
        };

        Stream<double[][]> expectedWithOutputValuesStream =
                Arrays.stream(expectedWithOutputValuesArr);

        assertEquals(
                0.7071067811865476,
                Stats.rmse(expectedWithOutputValuesStream),
                0.0);
    }

    @Test
    public void accuracy() {
        double[][][] expectedWithOutputValuesArr = {
                {{0.0, 0.0, 1.0}, {0.0, 0.1, 0.9}},
                {{0.0, 1.0, 0.0}, {0.8, 0.2, 0.0}},
                {{1.0, 0.0, 0.0}, {0.7, 0.1, 0.2}},
                {{1.0, 0.0, 0.0}, {0.3, 0.3, 0.4}},
                {{0.0, 0.0, 1.0}, {0.2, 0.2, 0.6}}
        };

        Stream<double[][]> expectedWithOutputValuesStream =
                Arrays.stream(expectedWithOutputValuesArr);

        assertEquals(
                0.6,
                Stats.score(expectedWithOutputValuesStream),
                0.0);
    }

}
