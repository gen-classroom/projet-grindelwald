package ch.heig.gen.commands;

import picocli.CommandLine;

@CommandLine.Command(
        name = "serve",
        description = "Open site in default web browser",
        mixinStandardHelpOptions = true,
        version = "1")
public class ServeCommand implements Runnable {
    @CommandLine.Parameters(description = "The path to the site in order to be opened in web browser ")
    @Override
    public void run() {
        System.out.println("'serve' command not implemented");
    }
}