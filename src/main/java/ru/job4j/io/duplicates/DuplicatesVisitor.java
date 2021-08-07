package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, Path> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(Files.size(file), file.getFileName().toString());
        if (files.containsKey(fileProperty)) {
            System.out.println("Find the duplicate: " + file.toAbsolutePath());
        } else {
            files.put(fileProperty, file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
