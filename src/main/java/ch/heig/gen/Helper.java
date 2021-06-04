package ch.heig.gen;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

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

    public static Path changeExtension(Path source, String oldExtension, String newExtension) {
        String fileName = source.getFileName().toString();
        return source.resolveSibling(fileName.substring(0, fileName.length() - oldExtension.length()) + newExtension);
    }

    public static String convertToHTML(String source) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(source);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    public static void cleanPath(Path path) throws IOException {
        if (Files.exists(path))
            Files.delete(path);
        if (!Files.exists(path.getParent()))
            Files.createDirectory(path.getParent());
    }
}