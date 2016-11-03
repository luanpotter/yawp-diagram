package io.yawp.extras.diagram.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class GraphicalPrinter {

    public static byte[] generate(List<Model> generate) {
        BufferedImage image = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        draw((Graphics2D) image.getGraphics(), generate);
        return toBytes(image);
    }

    private static byte[] toBytes(BufferedImage image) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "PNG", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void draw(Graphics2D g, List<Model> generate) {
        g.setFont(Font.getFont("Times New Roman, 24"));
        g.setColor(Color.CYAN);
        g.drawString("hey : you have about " + generate.size() + " models...", 100, 50);
    }
}
