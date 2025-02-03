package edu.okcu.imagefx;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class ImageFXController {

    @FXML
    private ImageView imgPicture;
    @FXML
    private ImageView imgNewPicture;
    private File selectedFile;

    // Apply the horizontal mirror effect
    private WritableImage applyMirrorEffect(File file) throws IOException {
        Image image = new Image(file.toURI().toString());
        PixelReader pixelReader = image.getPixelReader();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        WritableImage mirroredImage = new WritableImage(width, height);
        PixelWriter pixelWriter = mirroredImage.getPixelWriter();

        // Loop through each pixel and mirror horizontally
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Mirror horizontally by copying pixels from right to left
                pixelWriter.setColor(width - x - 1, y, pixelReader.getColor(x, y));
            }
        }
        return mirroredImage;
    }

    @FXML
    protected void onHelloButtonClick() throws IOException {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imgPicture.setImage(image);
            imgNewPicture.setImage(applyMirrorEffect(file)); // Apply the mirror effect
        }
    }
}
