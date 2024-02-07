package com.jakub.ChessVisionAPI.controller;

import com.jakub.ChessVisionAPI.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class Endpoint {
    private final RestTemplate restTemplate;
    @Autowired
    public Endpoint(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // PPPR8B2/NR8pPp1B/8pN2QR1/1P8BQ8b1/p10bPb1/PpPpqp2/nRp8nPRr/Pn8b2Q1 b Q - 7 38
    @GetMapping("/calculateMove")
    public String calculateMove(@RequestParam(name = "fen") String fen,
                                @RequestParam(name = "depth") int depth,
                                @RequestParam(name = "mode") String mode) {
        String url = "https://stockfish.online/api/stockfish.php/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("fen", fen)
                .queryParam("depth", depth)
                .queryParam("mode", mode);

        String urlWithParams = builder.toUriString();
//        if (!isValidChessMove(move)) {
//            return "Invalid move. Please use standard algebraic notation.";
//        }
        ResponseEntity<String> response = restTemplate.getForEntity(urlWithParams, String.class);
        return response.getBody();
    }

    private boolean isValidChessMove(String move) {
        String regex = "^(?:[KQRBN][a-h]?[1-8]?x?[a-h][1-8]|[a-h]x?[a-h][1-8]|O-O(?:-O)?)$";
        return move.matches(regex);
    }
}
