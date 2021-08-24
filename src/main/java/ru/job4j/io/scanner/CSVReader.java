package ru.job4j.io.scanner;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

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
        try (var scanner = new Scanner(new ByteArrayInputStream(line.getBytes()))
                .useDelimiter(delimiter)) {
            while (scanner.hasNext()) {
                rsl.add(scanner.next());
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

    public String parseColumn(List<String> content, List<Integer> indexes, String delimiter) {
       StringBuilder rsl = new StringBuilder();
            for (Integer index : indexes) {
                rsl.append(content.get(index))
                        .append(delimiter);
            }

        return rsl.toString();
    }

    public void execute(String[] args) {
        List<String> argsParse = this.parseArgs(args);
        String delimiter = argsParse.get(1);
        try (Scanner input = new Scanner(Path.of(argsParse.get(0)));
            PrintWriter outer = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(argsParse.get(2))))) {
            List<Integer> indexes = findIndex(input.nextLine(), argsParse.get(3).split(","), delimiter);
            while (input.hasNext()) {
                List<String> line = this.parseStr(input.nextLine(), delimiter);
                String str = this.parseColumn(line, indexes, delimiter);
                if (argsParse.get(2).equals("stdout")) {
                    System.out.println(str);
                } else {
                        outer.println(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Illegal argument. Usage java -jar dir.jar -path=file path -delimiter=\"delimiter\""
                    + "  -out=stdout -filter=column name.");
        }
        new CSVReader().execute(args);
    }
}
