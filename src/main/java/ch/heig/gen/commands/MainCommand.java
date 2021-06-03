package ch.heig.gen.commands;

import picocli.CommandLine;

@CommandLine.Command(
        name = "arancia",
        subcommands = {NewCommand.class, CleanCommand.class, BuildCommand.class, ServeCommand.class},
        description = "Description stub.",
        mixinStandardHelpOptions = true,
        version = "1")
public class MainCommand {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new MainCommand()).execute(args);
        if(exitCode != 0) {
            System.exit(exitCode);
        }
    }
}