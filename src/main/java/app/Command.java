package app;

public class Command {
    private final String command;
    private final int number;

    public Command(String command, int number) {
        this.command = command;
        this.number = number;
    }

    public String getCommand() {
        return command;
    }

    public int getNumber() {
        return number;
    }

    public static Command parse(String input) {
        String[] parts = input.split("\\?");
        String commandString = parts[0];
        int id = 0;
        if (parts.length > 1) {
            id = Integer.parseInt(parts[1].substring(3));
        }
        return new Command(commandString, id);
    }
}


