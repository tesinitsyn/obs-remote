package com.tesinitsyn.obsremote.controller;

import com.tesinitsyn.obsremote.service.HotkeyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClipController {

    private final HotkeyService hotkeyService;

    public ClipController(HotkeyService hotkeyService) {
        this.hotkeyService = hotkeyService;
    }

    @PostMapping("/clip")
    public String makeClip() {
        hotkeyService.makeClip();
        return "CLIP OK";
    }
}
