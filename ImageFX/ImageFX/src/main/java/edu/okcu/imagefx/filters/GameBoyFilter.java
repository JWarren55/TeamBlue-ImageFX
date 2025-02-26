package edu.okcu.imagefx.filters;

import edu.okcu.imagefx.ImageUtil;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
/*
    This filter changes the image to use 4 shades of green to look like the original gameboy colors
    All pixels are are catogrised in 4 groups then assigned a new value of green
*/
public class GameBoyFilter implements IFilter {
    public static Image apply(File file) throws IOException {
        BufferedImage img = ImageIO.read(file);

        //Preform calculations on all pixels
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x, y);
                Color color = new Color(pixel);

                //gat value of pixel componets 
                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int newPixel;

                // Find britness for pixel
                var grayScale = (red + blue + green) / 3;
                
                //sort pixel in 4 catogory of britness
                double brightness = grayScale/ 63.75;
                
                //gives each pixel 1 or 4 green according to brytness
                if(brightness < 1) {
                    newPixel = (alpha<<24) | (15<<16) | (56<<8) | 15;
                } else if(brightness < 2) {
                    newPixel = (alpha<<24) | (48<<16) | (98<<8) | 48;
                } else if(brightness < 3) {
                    newPixel = (alpha<<24) | (139<<16) | (127<<8) | 15;
                } else {
                    newPixel = (alpha<<24) | (155<<16) | (188<<8) | 15;
                }

                
                img.setRGB(x, y, newPixel);
            }
        }

        Image image = ImageUtil.convertBufferedImageToFxImage(img);
        return image;
    }

}
