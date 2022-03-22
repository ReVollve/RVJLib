package de.revollve.lib.config;

import java.util.Arrays;
import java.util.HashMap;

public class RVArgs {
    private HashMap<String, String> map = new HashMap<>();

    /**
     * Creates the class to access all start arguments. <br>
     * Example: <br><br>
     * <code>javafile.class -testtoken argument -testtoken2 argument2</code> <br><sub>
     * javafile.class   = executible <br>
     * -testtoken       = token with - as prefix <br>
     * argument         = information <br></sub>
     * @param args Args from <code>public static void main(String[] <strong>args</strong>)</code>
     * @param prefix Token to look for when reading the args
     */
    public RVArgs(String[] args, String prefix) {
        if (Arrays.stream(args).count() == 0) throw new IllegalArgumentException("No args");
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if(arg.startsWith(prefix) && i + 1 < args.length)
                map.put(arg.substring(prefix.length()), args[i + 1]);
            else System.out.println("Parameter \"" + arg + "\" has no argument");
        }
    }

    /**
     * Gets the results from the start arguments.
     * @param token Token to find the result
     * @return argument
     */
    public String get(String token) {
        return map.get(token);
    }
}
