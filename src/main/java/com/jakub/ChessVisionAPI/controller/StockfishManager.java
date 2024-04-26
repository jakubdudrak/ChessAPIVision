package com.jakub.ChessVisionAPI.controller;
import java.io.*;

public class StockfishManager {
    private Process engineProcess;
    private BufferedReader reader;
    private BufferedWriter writer;

    public boolean startEngine(String pathToExecutable) {
        try {
            ProcessBuilder builder = new ProcessBuilder(pathToExecutable);
            builder.redirectErrorStream(true);
            this.engineProcess = builder.start();
            this.reader = new BufferedReader(new InputStreamReader(engineProcess.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(engineProcess.getOutputStream()));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendCommand(String command) {
        try {
            this.writer.write(command + "\n");
            this.writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readOutput(long waitTimeMillis) {
        StringBuilder output = new StringBuilder();
        try {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < waitTimeMillis) {
                while (reader.ready()) {
                    int c = reader.read();
                    if (c == -1) return output.toString();
                    output.append((char) c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public void close() {
        try {
            sendCommand("quit");
            reader.close();
            writer.close();
            engineProcess.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}