package ru.job4j.io.scanner;

public class Args {

    private final String path;
    private final String delimiter;
    private final String out;
    private final String filter;

    public Args(String path, String delimiter, String out, String filter) {
        this.path = path;
        this.delimiter = delimiter;
        this.out = out;
        this.filter = filter;
    }

    public String getPath() {
        return path;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getOut() {
        return out;
    }

    public String getFilter() {
        return filter;
    }
}
