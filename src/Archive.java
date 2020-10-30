import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Archive {

    private List<FileConstructor> files;
    private int fileSize;


    public Archive(ZipFile zipFile) throws IOException {

        Enumeration zipFiles = zipFile.entries();
        files = new ArrayList<>();
        while (zipFiles.hasMoreElements()) {
            ZipEntry element = (ZipEntry) zipFiles.nextElement();
            this.files.add(new FileConstructor(element.getName(), element.getSize()));

        }
        this.fileSize = this.files.size();

        zipFile.close();
    }

    public List<FileConstructor> getFiles() { return files; }

    public int getFileSize() { return fileSize; }


}

