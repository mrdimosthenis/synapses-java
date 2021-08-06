package com.github.mrdimosthenis.synapses;

import synapses.lib.Fun$package.Fun$;
import synapses.model.net_elems.activation.Activation;

/**
 * The activation functions a neuron can have.
 * <p>
 * They can be used in the arguments of neural network's constructor.
 */
public class Fun {

    Activation contents;

    Fun(Activation _contents) {
        contents = _contents;
    }

    /**
     * Sigmoid takes any real value as input and outputs values in the range of 0 to 1.
     *
     * <pre>
     * {@code
     * x -> 1.0 / (1.0 + Math.exp(-x))
     * }
     * </pre>
     */
    public static final Fun SIGMOID = new Fun(Fun$.MODULE$.sigmoid());

    /**
     * Identity is a linear function where the output is equal to the input.
     *
     * <pre>
     * {@code
     * x -> x
     * }
     * </pre>
     */
    public static final Fun IDENTITY = new Fun(Fun$.MODULE$.identity());

    /**
     * Tanh is similar to Sigmoid, but outputs values in the range of -1 and 1.
     *
     * <pre>
     * {@code
     * x -> Math.tanh(x)
     * }
     * </pre>
     */
    public static final Fun TANH = new Fun(Fun$.MODULE$.tanh());

    /**
     * LeakyReLU gives a small proportion of x if x is negative and x otherwise.
     *
     * <pre>
     * {@code
     * x -> x < 0.0 ? 0.01 * x : x
     * }
     * </pre>
     */
    public static final Fun LEAKY_RE_LU = new Fun(Fun$.MODULE$.leakyReLU());

}
