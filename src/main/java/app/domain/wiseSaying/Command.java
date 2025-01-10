package app.domain.wiseSaying;

import java.util.HashMap;
import java.util.Map;

public class Command {
    private String command;
    private Map<String, String> map;

    public Command(String input) {
        String[] split = input.split("\\?", 2);
        this.command = split[0];
        this.map = new HashMap<>();

        if (split.length > 1) {
            String[] paramPairs = split[1].split("&");
            for (String pair : paramPairs) {
                String[] keyValue = pair.split("=", 2);
                if (keyValue.length == 2) {
                    this.map.put(keyValue[0], keyValue[1]);
                }
            }
        }
    }
    public String getCommand() {
        return command;
    }

    public String getParam(String key) {
        return map.get(key);
    }

    public boolean hasParam(String key) {
        return map.containsKey(key);
    }

}

