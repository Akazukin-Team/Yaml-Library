package net.akazukin.yaml.exceltion;

public class YamlIOException extends YamlParseException {
    public YamlIOException(String msg) {
        super(msg);
    }

    public YamlIOException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public YamlIOException(Throwable cause) {
        super(cause);
    }
}
