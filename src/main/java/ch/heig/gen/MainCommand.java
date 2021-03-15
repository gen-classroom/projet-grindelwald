package ch.heig.gen;

import picocli.CommandLine;

//TODO : Donner un nom et une description a la commande et aux sous commandes
@CommandLine.Command(
        name = "testCommand",
        subcommands = {NewCommand.class, CleanCommand.class, BuildCommand.class, ServeCommand.class},
        description = "Not implemented",
        mixinStandardHelpOptions = true,
        version = "1")
public class MainCommand {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new MainCommand()).execute(args);
        System.exit(exitCode);
    }
}