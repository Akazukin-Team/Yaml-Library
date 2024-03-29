package net.akazukin.yaml;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A class representing an element of YAML. It could either be a {@link YamlObject}, a
 * {@link YamlArray}, a {@link YamlPrimitive} or a {@link YamlNull}.
 *
 * @author Akazukin Team
 */
public abstract class YamlElement {
    /**
     * @deprecated Creating custom {@code YamlElement} subclasses is highly discouraged
     * and can lead to undefined behavior.<br>
     * This constructor is only kept for backward compatibility.
     */
    @Deprecated
    public YamlElement() {
    }

    /**
     * Returns a deep copy of this element. Immutable elements like primitives
     * and nulls are not copied.
     *
     * @since 1.0.0
     */
    public abstract YamlElement deepCopy();

    /**
     * Provides a check for verifying if this element is a YAML array or not.
     *
     * @return true if this element is of type {@link YamlArray}, false otherwise.
     */
    public boolean isYamlArray() {
        return this instanceof YamlArray;
    }

    /**
     * Provides a check for verifying if this element is a YAML object or not.
     *
     * @return true if this element is of type {@link YamlObject}, false otherwise.
     */
    public boolean isYamlObject() {
        return this instanceof YamlObject;
    }

    /**
     * Provides a check for verifying if this element is a primitive or not.
     *
     * @return true if this element is of type {@link YamlPrimitive}, false otherwise.
     */
    public boolean isYamlPrimitive() {
        return this instanceof YamlPrimitive;
    }

    /**
     * Provides a check for verifying if this element represents a null value or not.
     *
     * @return true if this element is of type {@link YamlNull}, false otherwise.
     * @since 1.0.0
     */
    public boolean isYamlNull() {
        return this instanceof YamlNull;
    }

    /**
     * Convenience method to get this element as a {@link YamlObject}. If this element is of some
     * other type, an {@link IllegalStateException} will result. Hence it is best to use this method
     * after ensuring that this element is of the desired type by calling {@link #isYamlObject()}
     * first.
     *
     * @return this element as a {@link YamlObject}.
     * @throws IllegalStateException if this element is of another type.
     */
    public YamlObject getAsYamlObject() {
        if (isYamlObject()) {
            return (YamlObject) this;
        }
        throw new IllegalStateException("Not a YAML Object: " + this);
    }

    /**
     * Convenience method to get this element as a {@link YamlArray}. If this element is of some
     * other type, an {@link IllegalStateException} will result. Hence it is best to use this method
     * after ensuring that this element is of the desired type by calling {@link #isYamlArray()}
     * first.
     *
     * @return this element as a {@link YamlArray}.
     * @throws IllegalStateException if this element is of another type.
     */
    public YamlArray getAsYamlArray() {
        if (isYamlArray()) {
            return (YamlArray) this;
        }
        throw new IllegalStateException("Not a YAML Array: " + this);
    }

    /**
     * Convenience method to get this element as a {@link YamlPrimitive}. If this element is of some
     * other type, an {@link IllegalStateException} will result. Hence it is best to use this method
     * after ensuring that this element is of the desired type by calling {@link #isYamlPrimitive()}
     * first.
     *
     * @return this element as a {@link YamlPrimitive}.
     * @throws IllegalStateException if this element is of another type.
     */
    public YamlPrimitive getAsYamlPrimitive() {
        if (isYamlPrimitive()) {
            return (YamlPrimitive) this;
        }
        throw new IllegalStateException("Not a YAML Primitive: " + this);
    }

    /**
     * Convenience method to get this element as a {@link YamlNull}. If this element is of some
     * other type, an {@link IllegalStateException} will result. Hence it is best to use this method
     * after ensuring that this element is of the desired type by calling {@link #isYamlNull()}
     * first.
     *
     * @return this element as a {@link YamlNull}.
     * @throws IllegalStateException if this element is of another type.
     * @since 1.0.0
     */
    public YamlNull getAsYamlNull() {
        if (isYamlNull()) {
            return (YamlNull) this;
        }
        throw new IllegalStateException("Not a YAML Null: " + this);
    }

    /**
     * Convenience method to get this element as a boolean value.
     *
     * @return this element as a primitive boolean value.
     * @throws UnsupportedOperationException if this element is not a {@link YamlPrimitive} or {@link YamlArray}.
     * @throws IllegalStateException         if this element is of the type {@link YamlArray} but contains
     *                                       more than a single element.
     */
    public boolean getAsBoolean() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /**
     * Convenience method to get this element as a {@link Number}.
     *
     * @return this element as a {@link Number}.
     * @throws UnsupportedOperationException if this element is not a {@link YamlPrimitive} or {@link YamlArray},
     *                                       or cannot be converted to a number.
     * @throws IllegalStateException         if this element is of the type {@link YamlArray} but contains
     *                                       more than a single element.
     */
    public Number getAsNumber() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /**
     * Convenience method to get this element as a string value.
     *
     * @return this element as a string value.
     * @throws UnsupportedOperationException if this element is not a {@link YamlPrimitive} or {@link YamlArray}.
     * @throws IllegalStateException         if this element is of the type {@link YamlArray} but contains
     *                                       more than a single element.
     */
    public String getAsString() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /**
     * Convenience method to get this element as a primitive double value.
     *
     * @return this element as a primitive double value.
     * @throws UnsupportedOperationException if this element is not a {@link YamlPrimitive} or {@link YamlArray}.
     * @throws NumberFormatException         if the value contained is not a valid double.
     * @throws IllegalStateException         if this element is of the type {@link YamlArray} but contains
     *                                       more than a single element.
     */
    public double getAsDouble() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /**
     * Convenience method to get this element as a primitive float value.
     *
     * @return this element as a primitive float value.
     * @throws UnsupportedOperationException if this element is not a {@link YamlPrimitive} or {@link YamlArray}.
     * @throws NumberFormatException         if the value contained is not a valid float.
     * @throws IllegalStateException         if this element is of the type {@link YamlArray} but contains
     *                                       more than a single element.
     */
    public float getAsFloat() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /**
     * Convenience method to get this element as a primitive long value.
     *
     * @return this element as a primitive long value.
     * @throws UnsupportedOperationException if this element is not a {@link YamlPrimitive} or {@link YamlArray}.
     * @throws NumberFormatException         if the value contained is not a valid long.
     * @throws IllegalStateException         if this element is of the type {@link YamlArray} but contains
     *                                       more than a single element.
     */
    public long getAsLong() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /**
     * Convenience method to get this element as a primitive integer value.
     *
     * @return this element as a primitive integer value.
     * @throws UnsupportedOperationException if this element is not a {@link YamlPrimitive} or {@link YamlArray}.
     * @throws NumberFormatException         if the value contained is not a valid integer.
     * @throws IllegalStateException         if this element is of the type {@link YamlArray} but contains
     *                                       more than a single element.
     */
    public int getAsInt() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /**
     * Convenience method to get this element as a primitive byte value.
     *
     * @return this element as a primitive byte value.
     * @throws UnsupportedOperationException if this element is not a {@link YamlPrimitive} or {@link YamlArray}.
     * @throws NumberFormatException         if the value contained is not a valid byte.
     * @throws IllegalStateException         if this element is of the type {@link YamlArray} but contains
     *                                       more than a single element.
     * @since 1.0.0
     */
    public byte getAsByte() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /**
     * Convenience method to get the first character of the string value of this element.
     *
     * @return the first character of the string value.
     * @throws UnsupportedOperationException if this element is not a {@link YamlPrimitive} or {@link YamlArray},
     *                                       or if its string value is empty.
     * @throws IllegalStateException         if this element is of the type {@link YamlArray} but contains
     *                                       more than a single element.
     * @since 1.0.0
     * @deprecated This method is misleading, as it does not get this element as a char but rather as
     * a string's first character.
     */
    @Deprecated
    public char getAsCharacter() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /**
     * Convenience method to get this element as a {@link BigDecimal}.
     *
     * @return this element as a {@link BigDecimal}.
     * @throws UnsupportedOperationException if this element is not a {@link YamlPrimitive} or {@link YamlArray}.
     * @throws NumberFormatException         if this element is not a valid {@link BigDecimal}.
     * @throws IllegalStateException         if this element is of the type {@link YamlArray} but contains
     *                                       more than a single element.
     * @since 1.0.0
     */
    public BigDecimal getAsBigDecimal() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /**
     * Convenience method to get this element as a {@link BigInteger}.
     *
     * @return this element as a {@link BigInteger}.
     * @throws UnsupportedOperationException if this element is not a {@link YamlPrimitive} or {@link YamlArray}.
     * @throws NumberFormatException         if this element is not a valid {@link BigInteger}.
     * @throws IllegalStateException         if this element is of the type {@link YamlArray} but contains
     *                                       more than a single element.
     * @since 1.0.0
     */
    public BigInteger getAsBigInteger() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /**
     * Convenience method to get this element as a primitive short value.
     *
     * @return this element as a primitive short value.
     * @throws UnsupportedOperationException if this element is not a {@link YamlPrimitive} or {@link YamlArray}.
     * @throws NumberFormatException         if the value contained is not a valid short.
     * @throws IllegalStateException         if this element is of the type {@link YamlArray} but contains
     *                                       more than a single element.
     */
    public short getAsShort() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /**
     * Returns a String representation of this element.
     */
    @Override
    public String toString() {
        StringWriter stringWriter = new StringWriter();
        try (YamlWriter yamlWriter = new YamlWriter(stringWriter)) {
            return yamlWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Returns the native object.
     *
     * @since 1.0.0
     */
    public abstract Object getAsNativeObject();
}
