package ch.heig.gen;

import picocli.CommandLine;

@CommandLine.Command(
        name = "arancia",
        subcommands = {NewCommand.class, CleanCommand.class, BuildCommand.class, ServeCommand.class},
        description = "Program allowing the generation of static site from a simple Markdown document\n",
        mixinStandardHelpOptions = true,
        version = "1")
public class MainCommand {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new MainCommand()).execute(args);
        System.exit(exitCode);
    }
}
