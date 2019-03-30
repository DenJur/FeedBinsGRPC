package controller;

import picocli.CommandLine;

public class ControllerMain {
    public static void main(String[] args) {
        CommandLine.run(new CLI(), args);
    }
}