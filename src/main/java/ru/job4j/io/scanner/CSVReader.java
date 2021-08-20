package ru.job4j.io.scanner;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    private final String[] args;

    public CSVReader(String[] args) {
        this.args = args;
    }

    private List<String> parseArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        List<String> rsl = new ArrayList<>();
        for (String str : args) {
            String[] value = str.split("=");
            if (value.length <= 1) {
                throw new IllegalArgumentException();
            }
            rsl.add(value[1]);
        }
        return rsl;
    }

    public List<String> parseStr(String line, String delimiter) {
        List<String> rsl = new ArrayList<>();
        var scanner = new Scanner(new ByteArrayInputStream(line.getBytes()))
                .useDelimiter(delimiter);
        while (scanner.hasNext()) {
            rsl.add(scanner.next());
        }
        return rsl;
    }


    public List<String> readFile(Path path) {
        List<String> rsl = new ArrayList<>();
        try(Scanner scanner = new Scanner(path)) {
            while (scanner.hasNext()) {
                rsl.add(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsl;
    }

    public List<Integer> findIndex(String line, String[] filters, String delimiter) {
        List<Integer> indexes = new ArrayList<>();
        List<String> values = parseStr(line, delimiter);
        for (String filter : filters) {
            indexes.add(values.indexOf(filter));
        }
        return indexes;
    }

    public StringBuilder parseColumn(List<String> content, List<Integer> indexes, String delimiter) {
        StringBuilder rsl = new StringBuilder();
        for (String line : content) {
            List<String> values = parseStr(line, delimiter);
            for (Integer index : indexes) {
                rsl.append(values.get(index))
                        .append(delimiter);
            }
            rsl.append(System.lineSeparator());
        }

        return rsl;
    }

    public void writer(StringBuilder stringBuilder, String out) {
        if (out.equals("stdout")) {
            System.out.println(stringBuilder);
        } else {
            try (PrintWriter outer = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(out)
                    ))) {
                outer.print(stringBuilder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void execute() {
        List<String> args = parseArgs(this.args);
        String delimiter = args.get(1);
        List<String> rsl = this.readFile(Path.of(args.get(0)));
        List<Integer> indexes = findIndex(rsl.get(0), args.get(3).split(","), delimiter);
        StringBuilder stringBuilder = this.parseColumn(rsl, indexes, delimiter);
        this.writer(stringBuilder, args.get(2));
    }



    public static void main(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Illegal argument. Usage java -jar dir.jar -path=file path -delimiter=\"delimiter\"" +
                    "  -out=stdout -filter=column name.");
        }
        new CSVReader(args).execute();
    }
}
