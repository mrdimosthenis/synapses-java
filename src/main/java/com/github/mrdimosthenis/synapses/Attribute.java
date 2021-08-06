package com.github.mrdimosthenis.synapses;

/**
 * The attribute a codec can have.
 * <p>
 * They can be used in the arguments of codec's constructor.
 */
public class Attribute {

    /**
     * The name of the attribute.
     */
    public final String name;

    /**
     * The flag of the attribute defines whether it is discrete or not.
     */
    public final boolean flag;

    public Attribute(String name, boolean flag) {
        this.name = name;
        this.flag = flag;
    }
}
