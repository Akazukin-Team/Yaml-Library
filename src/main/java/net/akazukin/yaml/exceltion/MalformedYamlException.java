package net.akazukin.yaml.exceltion;

import java.io.IOException;

public class MalformedYamlException extends IOException {
    public MalformedYamlException(String msg) {
        super(msg);
    }
}
