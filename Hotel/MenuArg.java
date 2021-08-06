import java.util.*;

public class MenuArg {
    private String name;
    private String description;
    private String valueName;
    private HashMap<String, MenuArg> args = new LinkedHashMap<>();

    public MenuArg(String name, String description, String valueName) {
        this.name = name;
        this.description = description;
        this.valueName = valueName;
    }

    public String getValueName() {
        return valueName;
    }

    public MenuArg() {
        this("");
    }

    public MenuArg(String name) {
        this(name, "");
    }

    public MenuArg(String name, String description) {
        this(name, description, "");
    }

    public void add(MenuArg arg) {
        this.args.put(arg.name, arg);
    }

    public void add(String name) {
        this.add(new MenuArg(name));
    }

    private String getInfo() {
        return name + (valueName.isEmpty() ? "" : " " + valueName) +
               (args.isEmpty() ? "" : " " + list("[", "]", " ")) +
               (description.isEmpty() ? "" : "\t" + description);
    }

    public String list() {
        return "---------------------\n" +
               list("", "", "\n") + "\n";
    }

    private String list(String prefix, String suffix, String delimiter) {
        StringJoiner result = new StringJoiner(delimiter);
        for (Map.Entry<String, MenuArg> entry : args.entrySet()) {
            result.add(prefix + entry.getValue().getInfo() + suffix);
        }
        return result.toString();
    }

    public MenuArg get(String name) {
        return args.get(name);
    }

    public Map<String, String> checkInput(String[] input) {
        Map<String, String> result = new HashMap<>();

        Queue<String> params = new LinkedList<>(Arrays.asList(input));

        String read = params.poll();

        if (args.containsKey(read)) {

            MenuArg main = args.get(read);

            if (!main.valueName.isEmpty()) { //if need value
                if (params.isEmpty()) {
                    return new HashMap<>();

                } else {
                    result.put("value", params.poll());
                }
            }

            result.put("command", read);

            while (!params.isEmpty()) {
                read = params.poll();
                if (!main.args.containsKey(read))
                    return new HashMap<>();

                if (!main.args.get(read).valueName.isEmpty()) { //if need value
                    if (params.isEmpty()) {
                        return new HashMap<>();

                    } else {
                        result.put(read, params.poll());
                    }
                } else {
                    result.put(read, "");
                }
            }
        }

        return result;
    }
}
