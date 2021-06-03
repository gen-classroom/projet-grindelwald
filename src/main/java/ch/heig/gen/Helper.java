package ch.heig.gen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Helper {
    public static void deleteDirectory(Path path) throws IOException {
        List<Path> paths = Files.walk(path).collect(Collectors.toList());

        for (ListIterator<Path> it = paths.listIterator(paths.size()); it.hasPrevious(); ) {
            Path a = it.previous();
            Files.delete(a);
        }
    }
}