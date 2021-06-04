package ch.heig.gen.commands;

import ch.heig.gen.Helper;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@CommandLine.Command(
        name = "build",
        description = "Not implemented",
        mixinStandardHelpOptions = true,
        version = "1")

public class BuildCommand implements Runnable {
    @CommandLine.Parameters(description = "The path to the site to build")
    Path pathToSite;

    private void ConvertFile(Path path) {
        try {
            Path temp = pathToSite.relativize(path);
            if (temp.startsWith("Build"))
                return;

            Path result = pathToSite.resolve("Build").resolve(temp);
            if (path.toString().endsWith(".md")) {
                result = Helper.changeExtension(result, "md", "html");
                Helper.cleanPath(result);
                Files.writeString(result, ExtractContent(Files.readString(path)), StandardOpenOption.CREATE);
            } else if (path.toString().endsWith(".png") || path.toString().endsWith(".png")) {
                Helper.cleanPath(result);
                Files.copy(path, result);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private String ExtractContent(String source) {
        String[] result = source.split("---" + System.lineSeparator(), 2);
        String content = "";
        String title = null;

        if (result.length == 1) {
            content = result[0];
        }

        if (result.length == 2) {
            String[] params = result[0].split(System.lineSeparator());

            for (String param : params) {
                String[] paramDetail = param.split(":", 2);

                // Pour l'instant, seul le titre du projet est supporté mais la structure du code permet d'
                // ajouter d'autres paramètres facilement
                switch (paramDetail[0]) {
                    case "titre" -> title = paramDetail[1];
                }
            }

            content = result[1];
        }

        StringBuilder sb = new StringBuilder("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator());

        if (title != null) {
            sb.append("<title>").append(title).append("</title>").append(System.lineSeparator());
        }

        sb.append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append(Helper.convertToHTML(content))
                .append("</body>").append(System.lineSeparator())
                .append("</html>");

        return sb.toString();
    }


    @Override
    public void run() {
        try {
            if (Files.isDirectory(pathToSite)) {
                Files.walk(pathToSite).forEach(this::ConvertFile);
            }
        } catch (IOException exception) {
            System.out.println("An error occured during the build of the site : " + exception);
        }
    }
}