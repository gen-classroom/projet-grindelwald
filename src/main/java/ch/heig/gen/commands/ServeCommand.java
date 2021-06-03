package ch.heig.gen.commands;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;


@CommandLine.Command(
        name = "serve",
        description = "Open site in default web browser",
        mixinStandardHelpOptions = true,
        version = "1")
public class ServeCommand implements Runnable {
    @CommandLine.Parameters(paramLabel = "SITE", description = "Path to the site to be opened in web browser.")
    Path pathToSite;

    static final String INDEX_HTML = "index.html";

    @Override
    public void run() {
        try {
            File site = pathToSite.toFile();
            site.getAbsolutePath();
            if(site.exists() && site.isDirectory()) {
                Path pathBuild = pathToSite.resolve("build");
                File dirBuild = pathBuild.toFile();
                if(!dirBuild.exists()) {
                    throw new IOException("The site has not yet been built.");
                }

                // Serve the site
                Javalin server = Javalin.create(javalinConfig -> {
                    javalinConfig.addStaticFiles(dirBuild.getAbsolutePath(),
                            Location.EXTERNAL);
                }).start(8080);

                Path pathIndex = pathBuild.resolve(INDEX_HTML);
                File index = pathIndex.toFile();
                if(!index.exists()) {
                    server.stop();
                    throw new IOException("The site is not correctly built.");
                }
                openUriInBrowser(index.toURI());

            } else {
                throw new IOException("Incorrect path to site.");
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private File findFile(String filename, File dir) throws IOException {
        File[] files = dir.listFiles();

        if(files != null) {
            for(File f : files) {
                if(filename.equalsIgnoreCase(f.getName())) {
                    return f;
                }
            }
        }

        return null;
    }

    private void openUriInBrowser(URI uri) {
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();

        try {

            if (os.contains("win")) {

                // this doesn't support showing urls in the form of "page.html#nameLink"
                rt.exec( "rundll32 url.dll,FileProtocolHandler " + uri);

            } else if (os.contains("mac")) {

                rt.exec( "open " + uri);

            } else if (os.contains("nix") || os.contains("nux")) {

                // Do a best guess on unix until we get a platform independent way
                // Build a list of browsers to try, in this order.
                String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
                        "netscape","opera","links","lynx"};

                // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
                StringBuilder cmd = new StringBuilder();
                for (int i=0; i<browsers.length; i++) {
                    cmd.append(i == 0 ? "" : " || ").append(browsers[i]).append(" \"").append(uri).append("\" ");
                }

                rt.exec(new String[] { "sh", "-c", cmd.toString() });

            } else {
                throw new Exception("The program doesn't support the OS.");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}