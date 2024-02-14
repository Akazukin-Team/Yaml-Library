package net.akazukin.yaml;

/**
 * A class representing a YAML {@code null} value.
 *
 * @author Akazukin Team
 * @since 1.0.0
 */
public final class YamlNull extends YamlElement {
    /**
     * Singleton for {@code YamlNull}.
     *
     * @since 1.0.0
     */
    public static final YamlNull INSTANCE = new YamlNull();

    /**
     * Creates a new {@code YamlNull} object.
     *
     * @deprecated Deprecated since Gson version 1.8, use {@link #INSTANCE} instead.
     */
    @Deprecated
    public YamlNull() {
        // Do nothing
    }

    /**
     * Returns the same instance since it is an immutable value.
     *
     * @since 1.0.0
     */
    @Override
    public YamlNull deepCopy() {
        return INSTANCE;
    }

    /**
     * All instances of {@code YamlNull} have the same hash code since they are indistinguishable.
     */
    @Override
    public int hashCode() {
        return YamlNull.class.hashCode();
    }

    /**
     * All instances of {@code YamlNull} are considered equal.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof YamlNull;
    }

    /**
     * Returns the native object.
     *
     * @since 1.0.0
     */
    @Override
    public Object getAsNativeObject() {
        return null;
    }
}
