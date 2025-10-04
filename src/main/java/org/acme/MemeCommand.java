package org.acme;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "meme", mixinStandardHelpOptions = true)
public class MemeCommand implements Runnable {
    @Inject
    MemeService memeService;

    @Parameters(paramLabel = "<text>", defaultValue = "say hello world",
            description = "Text")
    String text;

    @Parameters(paramLabel = "<meme>", defaultValue = "boromir",
            description = "The base image")
    Base base;

    @Override
    public void run() {
        Meme meme;
        switch (base) {
            case cat:
                meme = Meme.CAT;
                break;
            default:
                meme = Meme.BOROMIR;
        }

        String path = memeService.generateMeme(meme, text);
        System.out.printf("Wrote meme to %s\n", path);
    }

}
