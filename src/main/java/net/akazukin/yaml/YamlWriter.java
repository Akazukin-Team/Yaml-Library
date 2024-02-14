package net.akazukin.yaml;

import lombok.AllArgsConstructor;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

@AllArgsConstructor
public class YamlWriter implements AutoCloseable {
    public static final Yaml yaml = new Yaml();

    private final Writer writer;

    public void write(Object obj) throws IOException {
        writer.write(yaml.dumpAsMap(obj));
    }

    @Override
    public String toString() {
        return writer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YamlWriter that = (YamlWriter) o;
        return Objects.equals(writer, that.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(writer);
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
