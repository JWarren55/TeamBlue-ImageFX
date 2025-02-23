package edu.okcu.imagefx.filters;

import edu.okcu.imagefx.ImageUtil;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class BlurFilter implements IFilter {

    // Applies a blur effect to the provided image file and returns a JavaFX Image.
    public static Image apply(File file) throws IOException {
        // Read the input image file into a BufferedImage.
        BufferedImage img = ImageIO.read(file);
        // Create a new BufferedImage to store the blurred image,
        // using the same dimensions and image type as the original.
        BufferedImage blurredImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        // Define the radius for the blur effect.
        int radius = 2;

        // Loop through every pixel in the original image.
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                // Initialize the color channels at 0 and a counter for averaging.
                int red = 0, green = 0, blue = 0, alpha = 0, count = 0;

                // Loop over the neighborhood of pixels defined by the blur radius.
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        // Calculate the neighbor pixel's coordinates.
                        int nx = x + dx;
                        int ny = y + dy;

                        // Ensure the neighbor coordinates are within the image boundaries.
                        if (nx >= 0 && nx < img.getWidth() && ny >= 0 && ny < img.getHeight()) {
                            // Get the RGB value of the neighboring pixel.
                            int pixel = img.getRGB(nx, ny);
                            // Create a Color object from the pixel value (including alpha).
                            Color color = new Color(pixel, true);

                            // Accumulate the color components.
                            alpha += color.getAlpha();
                            red += color.getRed();
                            green += color.getGreen();
                            blue += color.getBlue();
                            // Increment the count of valid neighboring pixels.
                            count++;
                        }
                    }
                }

                // Compute the average value for each color channel.
                alpha /= count;
                red /= count;
                green /= count;
                blue /= count;

                // Combine the averaged color channels back into a single pixel.
                int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
                // Set the pixel in the blurred image.
                blurredImg.setRGB(x, y, newPixel);
            }
        }

        // Convert the blurred BufferedImage to a JavaFX Image using a utility method.
        Image image = ImageUtil.convertBufferedImageToFxImage(blurredImg);
        // Return the final blurred JavaFX Image.
        return image;
    }
}
