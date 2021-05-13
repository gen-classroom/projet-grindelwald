package ch.heig.gen.commands;

import picocli.CommandLine;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;


@CommandLine.Command(
        name = "serve",
        description = "Open site in default web browser",
        mixinStandardHelpOptions = true,
        version = "1")
public class ServeCommand implements Runnable {
    @CommandLine.Parameters(description = "The path to the site in order to be opened in web browser.")
    Path pathToSite;

    @Override
    public void run() {
        try {
            File site = pathToSite.toFile();
            if(site.isDirectory()) {
                File dirBuild = findFile("build", site);
                if(dirBuild == null) {
                    throw new IOException("The site has not yet been built.");
                }

                File dirContent = findFile("content", dirBuild);
                if(dirContent == null) {
                    throw new IOException("There was an error during the site compilation.");
                }

                ArrayList<File> htmlFiles = findHtmlFiles(dirContent);
                if(htmlFiles.isEmpty()) {
                    throw new IOException("There is no page to show");
                }

                for(File f : htmlFiles) {
                    Desktop.getDesktop().browse(f.toURI());
                }
            } else {
                throw new IOException("Incorrect path to site");
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

    private ArrayList<File> findHtmlFiles(File dir) {
        ArrayList<File> list = new ArrayList<>();
        File[] files = dir.listFiles();

        if(files != null) {
            for(File f : files) {
                String filename = f.getName();
                int lastIndexOf = filename.lastIndexOf(".");

                if(filename.substring(lastIndexOf).equals(".html")) {
                    list.add(f);
                }
            }
        }

        return list;
    }
}