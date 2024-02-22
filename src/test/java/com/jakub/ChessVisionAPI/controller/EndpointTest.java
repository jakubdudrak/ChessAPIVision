//package com.jakub.ChessVisionAPI.controller;
//
//import com.jakub.ChessVisionAPI.controller.Endpoint;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.openMocks;
//
//@SpringBootTest
//public class EndpointTests {
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @InjectMocks
//    private Endpoint endpoint;
//
//    @BeforeEach
//    public void setUp() {
//        openMocks(this);
//    }
//
//    @Test
//    public void testCalculateMoveReturnsValidResponse() {
//
//        String fen = "PPPR8B2/NR8pPp1B/8pN2QR1/1P8BQ8b1/p10bPb1/PpPpqp2/nRp8nPRr/Pn8b2Q1 b Q - 7 38";
//        int depth = 2;
//        String mode = "normal";
//        String expectedResponse = "e2e4";
//
//       // when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));
//
//
//        String actualResponse = endpoint.calculateMove(fen, depth, mode);
//
//
//        assertEquals(expectedResponse, actualResponse, "The response should match the expected chess move.");
//    }
//
//
//}