package com.tesinitsyn.obsremote.service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import okhttp3.*;

import java.util.concurrent.TimeUnit;

@Service
public class ObsWebSocketService {

    private static final String OBS_WS_URL = "ws://192.168.0.11:4455";

    private WebSocket webSocket;
    private boolean identified = false;

    public ObsWebSocketService() {
        connect();
    }

    private void connect() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build();

        Request request = new Request.Builder()
                .url(OBS_WS_URL)
                .build();

        webSocket = client.newWebSocket(request, new WebSocketListener() {

            @Override
            public void onOpen(WebSocket ws, Response response) {
                System.out.println("🔌 Connected to OBS WebSocket");
            }

            @Override
            public void onMessage(WebSocket ws, String text) {
                System.out.println("⬅ OBS: " + text);

                if (text.contains("\"op\":0")) {
                    // Hello → Identify
                    identify();
                }

                if (text.contains("\"op\":2")) {
                    identified = true;
                    System.out.println("✅ Identified with OBS");
                }
            }

            @Override
            public void onFailure(WebSocket ws, Throwable t, Response response) {
                System.err.println("❌ OBS WebSocket error: " + t.getMessage());
            }
        });
    }

    private void identify() {
        String identifyPayload = """
        {
          "op": 1,
          "d": {
            "rpcVersion": 1
          }
        }
        """;

        webSocket.send(identifyPayload);
    }

    public void saveReplay() {
        if (!identified) {
            System.out.println("⏳ OBS not identified yet");
            return;
        }

        String payload = """
        {
          "op": 6,
          "d": {
            "requestType": "SaveReplayBuffer",
            "requestId": "clip-1"
          }
        }
        """;

        webSocket.send(payload);
        System.out.println("🎬 SaveReplayBuffer sent");
    }
}
