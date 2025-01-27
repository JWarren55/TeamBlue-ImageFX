
package edu.okcu.imagefx.filters;
import javafx.scene.image.Image;
import java.io.File;
import java.io.IOException;

public interface IFilter {
    static Image apply(File img) throws IOException {
        return null;
    }
}
