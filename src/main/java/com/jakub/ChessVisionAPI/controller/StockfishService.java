package com.jakub.ChessVisionAPI.controller;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StockfishService {

    private StockfishManager stockfishManager;

    public StockfishService() {
        this.stockfishManager = new StockfishManager();
        boolean isStarted = stockfishManager.startEngine("stockfish/stockfish-windows-x86-64-avx2.exe");
        if (!isStarted) {
            throw new RuntimeException("Failed to start Stockfish engine.");
        }
    }

    public String getBestMove(String fen, int depth) throws IOException, InterruptedException {
        stockfishManager.sendCommand("uci");
        Thread.sleep(2000);
        stockfishManager.sendCommand("position fen " + fen);
        stockfishManager.sendCommand("go depth " + depth);

        Thread.sleep(1000);
        String output = stockfishManager.readOutput(1000);
        return parseBestMove(output);
    }

    private String parseBestMove(String output) {
        if (output.contains("bestmove")) {
            String move = output.split("bestmove ")[1].split(" ")[0];
            return move.trim();
        }
        return null;
    }
}