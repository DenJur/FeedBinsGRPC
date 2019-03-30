package bin;

import picocli.CommandLine;

public class BinMain {
    public static void main(String[] args) {
        CommandLine.run(new CLI(), args);
    }
}
