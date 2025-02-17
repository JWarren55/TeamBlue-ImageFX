package edu.okcu.imagefx.filters;

import edu.okcu.imagefx.ImageUtil;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class GameBoyFilter implements IFilter {
    public static Image apply(File file) throws IOException {
        BufferedImage img = ImageIO.read(file);

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x, y);
                Color color = new Color(pixel);

                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int newPixel;

                // Calculation for GrayScale
                var grayScale = (red + blue + green) / 3;
                double brightness = grayScale/ 63.75;
                if(brightness < 1) {
                    newPixel = (alpha<<24) | (15<<16) | (56<<8) | 15;
                } else if(brightness < 2) {
                    newPixel = (alpha<<24) | (48<<16) | (98<<8) | 48;
                } else if(brightness < 3) {
                    newPixel = (alpha<<24) | (139<<16) | (127<<8) | 15;
                } else {
                    newPixel = (alpha<<24) | (155<<16) | (188<<8) | 15;
                }

                // Create an Integer for the new values
                // int newPixel = (alpha<<24) | (newColor<<16) | (newColor<<8) | newColor;


                img.setRGB(x, y, newPixel);
            }
        }

        Image image = ImageUtil.convertBufferedImageToFxImage(img);
        return image;
    }

}
