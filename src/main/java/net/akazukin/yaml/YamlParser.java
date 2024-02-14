package net.akazukin.yaml;

import net.akazukin.yaml.exceltion.MalformedYamlException;
import net.akazukin.yaml.exceltion.YamlIOException;
import net.akazukin.yaml.exceltion.YamlParseException;
import net.akazukin.yaml.exceltion.YamlSyntaxException;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

/**
 * A parser to parse YAML into a parse tree of {@link YamlElement}s.
 *
 * @author Akazukin Team
 * @since 1.0.0
 */
public final class YamlParser {
    private static final Yaml yaml = new Yaml();

    /**
     * Parses the specified YAML string into a parse tree.
     * An exception is thrown if the YAML string has multiple top-level YAML elements,
     * or if there is trailing data.
     *
     * <p>The YAML string is parsed in {@linkplain Yaml#loadAs(Reader, Class)}.
     *
     * @param yaml YAML text
     * @return a parse tree of {@link YamlElement}s corresponding to the specified YAML
     * @throws YamlParseException if the specified text is not valid YAML
     * @since 1.0.0
     */
    public static YamlElement parseString(final String yaml) throws YamlSyntaxException {
        return parseReader(new StringReader(yaml));
    }

    /**
     * Parses the complete YAML string provided by the reader into a parse tree.
     * An exception is thrown if the YAML string has multiple top-level YAML elements,
     * or if there is trailing data.
     *
     * <p>The YAML data is parsed in {@linkplain Yaml#loadAs(Reader, Class)}.
     *
     * @param reader YAML text
     * @return a parse tree of {@link YamlElement}s corresponding to the specified YAML
     * @throws YamlParseException if there is an IOException or if the specified
     *                            text is not valid YAML
     * @since 1.0.0
     */
    public static YamlElement parseReader(final Reader reader) throws YamlIOException, YamlSyntaxException {
        try {
            final YamlElement element = parseObject(yaml.loadAs(reader, Object.class));

            if (element.isYamlNull()) {
                throw new YamlSyntaxException("Did not consume the entire document.");
            }
            return element;
        } catch (final StackOverflowError | OutOfMemoryError e) {
            throw new YamlParseException("Failed parsing YAML source: " + reader + " to Yaml", e);
        } catch (final MalformedYamlException | NumberFormatException e) {
            throw new YamlSyntaxException(e);
        } catch (final IOException e) {
            throw new YamlIOException(e);
        }
    }

    private static YamlElement parseObject(final Object obj) throws MalformedYamlException, IOException {
        if (obj == null) {
            return YamlNull.INSTANCE;
        } else if (obj instanceof Map) {
            final YamlObject yamlObject = new YamlObject();
            for (final Map.Entry<?, ?> entry : ((Map<?, ?>) obj).entrySet()) {
                yamlObject.add((String) entry.getKey(), parseObject(entry.getValue()));
            }
            return yamlObject;
        } else if (obj instanceof Number) {
            return new YamlPrimitive((Number) obj);
        } else if (obj instanceof Boolean) {
            return new YamlPrimitive((Boolean) obj);
        } else if (obj instanceof String) {
            return new YamlPrimitive((String) obj);
        } else if (obj instanceof Character) {
            return new YamlPrimitive((Character) obj);
        }
        throw new MalformedYamlException("Invalid object type: " + obj);
    }
}
