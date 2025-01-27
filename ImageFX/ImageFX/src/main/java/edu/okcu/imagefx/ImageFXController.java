package edu.okcu.imagefx;

import edu.okcu.imagefx.filters.BlurFilter;
import edu.okcu.imagefx.filters.GrayScaleFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;

public class ImageFXController {
    GrayScaleFilter grayScaleFilter = new GrayScaleFilter();
    BlurFilter blurFilter = new BlurFilter();
    @FXML
    private ImageView imgPicture;
    @FXML
    private ImageView imgNewPicture;
    @FXML
    private ComboBox<String> filterComboBox;

    private File selectedFile;


    @FXML
    protected void onLoadButtonClick() throws IOException {
        FileChooser chooser = new FileChooser();
        selectedFile = chooser.showOpenDialog(null);

        Image image = new Image(selectedFile.toURI().toString());

        imgPicture.setImage(image);
        imgNewPicture.setImage(BlurFilter.apply(selectedFile));
    }

    @FXML
    protected void onFilterSelection() throws IOException {
        String selectedFilter = filterComboBox.getValue();

        if (selectedFile == null || imgPicture.getImage() == null) {
            return;
        }

        switch (selectedFilter) {
            case "Grey Scale" -> {imgNewPicture.setImage(GrayScaleFilter.apply(selectedFile));}
            case "Sepia Tone" -> {}
            case "Blur" -> {imgNewPicture.setImage(BlurFilter.apply(selectedFile));}
            case "Mirror" -> {}
            case "Gameboy" -> {}
            default -> {}
        }
    }
}