package net.akazukin.yaml;

import net.akazukin.yaml.object.LinkedTreeMap;

import java.util.Map;
import java.util.Set;

/**
 * A class representing an object type in Yaml. An object consists of name-value pairs where names
 * are strings, and values are any other type of {@link YamlElement}. This allows for a creating a
 * tree of YamlElements. The member elements of this object are maintained in order they were added.
 * This class does not support {@code null} values. If {@code null} is provided as value argument
 * to any of the methods, it is converted to a {@link YamlNull}.
 *
 * <p>{@code YamlObject} does not implement the {@link Map} interface, but a {@code Map} view
 * of it can be obtained with {@link #asMap()}.
 *
 * @author Akazukin Team
 */
public final class YamlObject extends YamlElement {
    private final LinkedTreeMap<String, YamlElement> members = new LinkedTreeMap<>(false);

    /**
     * Creates an empty YamlObject.
     */
    @SuppressWarnings("deprecation") // superclass constructor
    public YamlObject() {
    }

    /**
     * Creates a deep copy of this element and all its children.
     *
     * @since 1.0.0
     */
    @Override
    public YamlObject deepCopy() {
        YamlObject result = new YamlObject();
        for (Map.Entry<String, YamlElement> entry : members.entrySet()) {
            result.add(entry.getKey(), entry.getValue().deepCopy());
        }
        return result;
    }

    /**
     * Adds a member, which is a name-value pair, to self. The name must be a String, but the value
     * can be an arbitrary {@link YamlElement}, thereby allowing you to build a full tree of YamlElements
     * rooted at this node.
     *
     * @param property name of the member.
     * @param value    the member object.
     */
    public void add(String property, YamlElement value) {
        members.put(property, value == null ? YamlNull.INSTANCE : value);
    }

    /**
     * Removes the {@code property} from this object.
     *
     * @param property name of the member that should be removed.
     * @return the {@link YamlElement} object that is being removed, or {@code null} if no
     * member with this name exists.
     * @since 1.0.0
     */
    public YamlElement remove(String property) {
        return members.remove(property);
    }

    /**
     * Convenience method to add a string member. The specified value is converted to a
     * {@link YamlPrimitive} of String.
     *
     * @param property name of the member.
     * @param value    the string value associated with the member.
     */
    public void addProperty(String property, String value) {
        add(property, value == null ? YamlNull.INSTANCE : new YamlPrimitive(value));
    }

    /**
     * Convenience method to add a number member. The specified value is converted to a
     * {@link YamlPrimitive} of Number.
     *
     * @param property name of the member.
     * @param value    the number value associated with the member.
     */
    public void addProperty(String property, Number value) {
        add(property, value == null ? YamlNull.INSTANCE : new YamlPrimitive(value));
    }

    /**
     * Convenience method to add a boolean member. The specified value is converted to a
     * {@link YamlPrimitive} of Boolean.
     *
     * @param property name of the member.
     * @param value    the boolean value associated with the member.
     */
    public void addProperty(String property, Boolean value) {
        add(property, value == null ? YamlNull.INSTANCE : new YamlPrimitive(value));
    }

    /**
     * Convenience method to add a char member. The specified value is converted to a
     * {@link YamlPrimitive} of Character.
     *
     * @param property name of the member.
     * @param value    the char value associated with the member.
     */
    public void addProperty(String property, Character value) {
        add(property, value == null ? YamlNull.INSTANCE : new YamlPrimitive(value));
    }

    /**
     * Returns a set of members of this object. The set is ordered, and the order is in which the
     * elements were added.
     *
     * @return a set of members of this object.
     */
    public Set<Map.Entry<String, YamlElement>> entrySet() {
        return members.entrySet();
    }

    /**
     * Returns a set of members key values.
     *
     * @return a set of member keys as Strings
     * @since 1.0.0
     */
    public Set<String> keySet() {
        return members.keySet();
    }

    /**
     * Returns the number of key/value pairs in the object.
     *
     * @return the number of key/value pairs in the object.
     * @since 1.0.0
     */
    public int size() {
        return members.size();
    }

    /**
     * Returns true if the number of key/value pairs in the object is zero.
     *
     * @return true if the number of key/value pairs in the object is zero.
     * @since 1.0.0
     */
    public boolean isEmpty() {
        return members.size() == 0;
    }

    /**
     * Convenience method to check if a member with the specified name is present in this object.
     *
     * @param memberName name of the member that is being checked for presence.
     * @return true if there is a member with the specified name, false otherwise.
     */
    public boolean has(String memberName) {
        return members.containsKey(memberName);
    }

    /**
     * Returns the member with the specified name.
     *
     * @param memberName name of the member that is being requested.
     * @return the member matching the name, or {@code null} if no such member exists.
     */
    public YamlElement get(String memberName) {
        return members.get(memberName);
    }

    /**
     * Convenience method to get the specified member as a {@link YamlPrimitive}.
     *
     * @param memberName name of the member being requested.
     * @return the {@code YamlPrimitive} corresponding to the specified member, or {@code null} if no
     * member with this name exists.
     * @throws ClassCastException if the member is not of type {@code YamlPrimitive}.
     */
    public YamlPrimitive getAsYamlPrimitive(String memberName) {
        return (YamlPrimitive) members.get(memberName);
    }

    /**
     * Convenience method to get the specified member as a {@link YamlArray}.
     *
     * @param memberName name of the member being requested.
     * @return the {@code YamlArray} corresponding to the specified member, or {@code null} if no
     * member with this name exists.
     * @throws ClassCastException if the member is not of type {@code YamlArray}.
     */
    public YamlArray getAsYamlArray(String memberName) {
        return (YamlArray) members.get(memberName);
    }

    /**
     * Convenience method to get the specified member as a {@link YamlObject}.
     *
     * @param memberName name of the member being requested.
     * @return the {@code YamlObject} corresponding to the specified member, or {@code null} if no
     * member with this name exists.
     * @throws ClassCastException if the member is not of type {@code YamlObject}.
     */
    public YamlObject getAsYamlObject(String memberName) {
        return (YamlObject) members.get(memberName);
    }

    /**
     * Returns a mutable {@link Map} view of this {@code YamlObject}. Changes to the {@code Map}
     * are visible in this {@code YamlObject} and the other way around.
     *
     * <p>The {@code Map} does not permit {@code null} keys or values. Unlike {@code YamlObject}'s
     * {@code null} handling, a {@link NullPointerException} is thrown when trying to add {@code null}.
     * Use {@link YamlNull} for YAML null values.
     *
     * @return mutable {@code Map} view
     * @since 1.0.0
     */
    public Map<String, YamlElement> asMap() {
        // It is safe to expose the underlying map because it disallows null keys and values
        return members;
    }

    /**
     * Returns whether the other object is equal to this. This method only considers
     * the other object to be equal if it is an instance of {@code YamlObject} and has
     * equal members, ignoring order.
     */
    @Override
    public boolean equals(Object o) {
        return (o == this) || (o instanceof YamlObject
                && ((YamlObject) o).members.equals(members));
    }

    /**
     * Returns the hash code of this object. This method calculates the hash code based
     * on the members of this object, ignoring order.
     */
    @Override
    public int hashCode() {
        return members.hashCode();
    }

    /**
     * Returns the native object.
     *
     * @since 1.0.0
     */
    @Override
    public Map<String, Object> getAsNativeObject() {
        Map<String, Object> members2 = new LinkedTreeMap<>();
        for (Map.Entry<String, YamlElement> entry : members.entrySet()) {
            members2.put(entry.getKey(), entry.getValue().getAsNativeObject());
        }
        return members2;
    }
}
