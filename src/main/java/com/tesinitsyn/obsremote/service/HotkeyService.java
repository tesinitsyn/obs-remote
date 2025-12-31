package com.tesinitsyn.obsremote.service;

import org.springframework.stereotype.Service;

@Service
public class HotkeyService {

    public void makeClip() {
        try {
            new ProcessBuilder("cmd", "/c", "C:\\obs-remote\\idk.ahk").start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

