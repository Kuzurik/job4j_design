package ru.job4j.io.scanner;

public class Args {

    private String path;
    private String delimiter;
    private String out;
    private String filter;

    public void of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        this.path = parseArg(args[0]);
        this.delimiter = parseArg(args[1]);
        this.out = parseArg(args[2]);
        this.filter = parseArg(args[3]);
    }

    private String parseArg(String arg) {
        String[] value = arg.split("=");
        if (value.length <= 1) {
            throw new IllegalArgumentException();
        }
        return value[1];
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
