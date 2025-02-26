package edu.okcu.imagefx;

import edu.okcu.imagefx.filters.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class ImageFXController {
    GrayScaleFilter grayScaleFilter = new GrayScaleFilter();
    BlurFilter blurFilter = new BlurFilter();
    GameBoyFilter gameBoyFilter = new GameBoyFilter();
    MirrorFilter mirrorFilter = new MirrorFilter();
    SepiaToneFilter sepiaToneFilter = new SepiaToneFilter();

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
    }

    String [] filterList = {"Gray Scale", "Blur", "Mirror", "Sepia Tone", "Game Boy"};
    
    // in the initialize method, we generate the options for the combo box.
    public void initialize() {
        ObservableList<String> filters = FXCollections.observableArrayList(filterList);
        filterComboBox.setItems(filters);
    }

    // this method is used when the a selection is made in the combo box. It dictates what filter is applied to the image.
    public void onComboBoxSelection(javafx.event.ActionEvent actionEvent) throws IOException {
        if (filterComboBox.getValue().equals("Gray Scale")) {
            imgNewPicture.setImage(GrayScaleFilter.apply(selectedFile));
        } else if (filterComboBox.getValue().equals("Blur")) {
            imgNewPicture.setImage(BlurFilter.apply(selectedFile));
        } else if (filterComboBox.getValue().equals("Mirror")) {
            imgNewPicture.setImage(mirrorFilter.applyMirrorEffect(selectedFile));
        } else if (filterComboBox.getValue().equals("Sepia Tone")) {
            imgNewPicture.setImage(SepiaToneFilter.apply(selectedFile));
        } else {
            imgNewPicture.setImage(GameBoyFilter.apply(selectedFile));
        }
    }
}
