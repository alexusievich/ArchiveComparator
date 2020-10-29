import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

class Frame extends JFrame {
    private File firstFile;
    private File secondFile;

    Frame() {
        JFileChooser fileChooser = new JFileChooser();
        int a1 = fileChooser.showDialog(null, "Open the first archive");
        if (a1 == JFileChooser.APPROVE_OPTION) {
            firstFile = fileChooser.getSelectedFile();
        }
        int a2 = fileChooser.showDialog(null, "Open the second archive");
        if (a2 == JFileChooser.APPROVE_OPTION) {
            secondFile = fileChooser.getSelectedFile();
        }
    }

    public File getFirstFile() {
        return firstFile;
    }

    public File getSecondFile() {
        return secondFile;
    }
}
