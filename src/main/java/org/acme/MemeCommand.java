package org.acme;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "meme", mixinStandardHelpOptions = true)
public class MemeCommand implements Runnable {
    @Inject
    MemeService memeService;

    @Parameters(paramLabel = "<top>", defaultValue = "hello",
            description = "Top text")
    String top;

    @Parameters(paramLabel = "<bottom>", defaultValue = "world",
            description = "Bottom text")
    String bottom;

    @Override
    public void run() {
        String path = memeService.generateMeme(top, bottom);
        System.out.printf("Wrote meme to %s\n", path);
    }

}
