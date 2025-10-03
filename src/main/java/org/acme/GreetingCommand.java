package org.acme;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "greeting", mixinStandardHelpOptions = true)
public class GreetingCommand implements Runnable {
    @Inject
    MemeService memeService;

    @Parameters(paramLabel = "<name>", defaultValue = "picocli",
            description = "Your name.")
    String name;

    @Override
    public void run() {
        String path = memeService.generateMeme("hello", "world");
        System.out.printf("Wrote meme at %s", path);
    }

}
