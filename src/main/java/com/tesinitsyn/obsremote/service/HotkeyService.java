package com.tesinitsyn.obsremote.service;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.event.KeyEvent;

@Service
public class HotkeyService {

    public void makeClip() {
        try {
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_C);

            robot.delay(100);

            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_CONTROL);

        } catch (AWTException e) {
            throw new RuntimeException("Cannot press hotkeys", e);
        }
    }
}
