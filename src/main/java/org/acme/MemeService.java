package org.acme;

import jakarta.enterprise.context.ApplicationScoped;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@ApplicationScoped
public class MemeService {

    private final Font memeFont;

    // Load the font from resources
    // Ensure the font file is placed in src/main/resources/Impact.ttf
    public MemeService() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("Impact.ttf")) {
            if (is == null)
                throw new IllegalStateException("Font not found");
            memeFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(120f);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load font", e);
        }
    }

    // Generates a meme image with the specified top and bottom text
    // This method reads a base image (boromir.png) from resources,
    // draws the specified text on it, and returns the image as a byte array.
    public String generateMeme(String topText, String bottomText) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("META-INF/resources/boromir.png");
            if (is == null) {
                throw new IllegalStateException("Image not found");
            }
            BufferedImage image = ImageIO.read(is);

            Graphics2D g = image.createGraphics();
            g.setFont(memeFont);
            g.setColor(Color.WHITE);

            drawText(g, topText, image.getWidth(), image.getHeight(), true);
            drawText(g, bottomText, image.getWidth(), image.getHeight(), false);
            g.dispose();

            File file = new File("meme.jpg");
            FileOutputStream baos = new FileOutputStream(file);
            ImageIO.write(image, "jpg", baos);

            baos.close();
            return file.getAbsolutePath();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate meme", e);
        }
    }

    // Draws the specified text on the image at the top or bottom
    private void drawText(Graphics2D g, String text, int width, int height, boolean isTop) {
        if (text == null || text.isEmpty()) {
            return;
        }

        // How fat the outline is
        int outlineOffset = 4;
        // How far off the top and bottom edges the text is
        double positionOffset = 1.4;

        FontMetrics metrics = g.getFontMetrics();
        int x = (width - metrics.stringWidth(text)) / 2;
        int y = isTop ? (int) (positionOffset * metrics.getHeight()) : height - (int) ((positionOffset - 0.3) * metrics.getHeight());

        // Outline
        g.setColor(Color.BLACK);
        g.drawString(text.toUpperCase(), x - outlineOffset, y - outlineOffset);
        g.drawString(text.toUpperCase(), x + outlineOffset, y - outlineOffset);
        g.drawString(text.toUpperCase(), x - outlineOffset, y + outlineOffset);
        g.drawString(text.toUpperCase(), x + outlineOffset, y + outlineOffset);

        // Main text
        g.setColor(Color.WHITE);
        g.drawString(text.toUpperCase(), x, y);
    }
}