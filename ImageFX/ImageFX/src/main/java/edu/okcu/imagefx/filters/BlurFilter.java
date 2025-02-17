package edu.okcu.imagefx.filters;

import edu.okcu.imagefx.ImageUtil;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class BlurFilter implements IFilter {
    public static Image apply(File file) throws IOException {
        BufferedImage img = ImageIO.read(file);
        BufferedImage blurredImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        int radius = 2;

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int red = 0, green = 0, blue = 0, alpha = 0, count = 0;

                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        int nx = x + dx;
                        int ny = y + dy;

                        if (nx >= 0 && nx < img.getWidth() && ny >= 0 && ny < img.getHeight()) {
                            int pixel = img.getRGB(nx, ny);
                            Color color = new Color(pixel, true);

                            alpha += color.getAlpha();
                            red += color.getRed();
                            green += color.getGreen();
                            blue += color.getBlue();
                            count++;
                        }
                    }
                }

                alpha /= count;
                red /= count;
                green /= count;
                blue /= count;

                int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
                blurredImg.setRGB(x, y, newPixel);
            }
        }

        Image image = ImageUtil.convertBufferedImageToFxImage(blurredImg);
        return image;
    }
}