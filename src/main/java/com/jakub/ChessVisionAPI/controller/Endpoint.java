package com.jakub.ChessVisionAPI.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

@RestController
@RequestMapping("/api")
public class Endpoint {

    @Autowired
    private StockfishService stockfishService;

    @GetMapping("/calculateMove")
    public ResponseEntity<?> calculateMove(
            @RequestParam String fen,
            @RequestParam(required = false, defaultValue = "bestmove") String mode,
            @RequestParam(required = false, defaultValue = "20") int depth) {

        try {
            String bestMove = stockfishService.getBestMove(fen, depth);
            if (bestMove == null || bestMove.equals("(none)")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"No valid move found\"}");
            }
            return ResponseEntity.ok("{\"move\": \"" + bestMove + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error processing request: " + e.getMessage() + "\"}");
        }
    }
}
