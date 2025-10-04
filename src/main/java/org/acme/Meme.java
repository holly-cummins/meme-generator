package org.acme;

public record Meme(String fileName, String text, boolean append) {
    public static final Meme BOROMIR = new Meme("boromir.png", "one does not simply", false);
    public static final Meme CAT = new Meme("cat.png", "it was awful", true);
    
}

