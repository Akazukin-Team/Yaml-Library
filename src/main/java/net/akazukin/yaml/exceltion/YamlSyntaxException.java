package net.akazukin.yaml.exceltion;

public class YamlSyntaxException extends YamlParseException {
    public YamlSyntaxException(String msg) {
        super(msg);
    }

    public YamlSyntaxException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public YamlSyntaxException(Throwable cause) {
        super(cause);
    }
}
