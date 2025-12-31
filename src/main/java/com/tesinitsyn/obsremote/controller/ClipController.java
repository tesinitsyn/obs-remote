package com.tesinitsyn.obsremote.controller;

import com.tesinitsyn.obsremote.service.HotkeyService;
import com.tesinitsyn.obsremote.service.ObsWebSocketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClipController {

    private final ObsWebSocketService obs;

    public ClipController(ObsWebSocketService obs) {
        this.obs = obs;
    }

    @PostMapping("/clip")
    public void clip() {
        obs.saveReplay();
    }
}
