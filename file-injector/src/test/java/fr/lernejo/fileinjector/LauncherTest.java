package fr.lernejo.fileinjector;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LauncherTest {

    @Test
    void main_terminates_before_5_sec() {
        assertTimeoutPreemptively(
            Duration.ofSeconds(5L),
            () -> Launcher.main(new String[]{"src/test/resources/games.json"}));
    }
    @Test
    void main_without_args() {
        Launcher.main(new String[]{});
    }
}
