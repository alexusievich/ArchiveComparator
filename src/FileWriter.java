import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.DataFormatException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class FileWriter {

    private String firstArchiveName;
    private String secondArchiveName;
    private HashMap<String, String> stringHashMap;


    public void writeToFile() throws FileNotFoundException {
        PrintWriter resultWriter = new PrintWriter("src\\compareResult.txt");
        resultWriter.printf("%-30.30s|%-30.30s\n", firstArchiveName.substring(firstArchiveName.lastIndexOf('/') + 1),
                secondArchiveName.substring(secondArchiveName.lastIndexOf('/') + 1));

        String stringBuild = "-".repeat(30) +
                '+' +
                "-".repeat(30) +
                '\n';
        resultWriter.print(stringBuild);

        for (Map.Entry<String, String> entry : stringHashMap.entrySet()) {
            switch (entry.getKey()) {
                case "new" -> resultWriter.printf("%-30.30s|+ %-28.28s\n", "", entry.getValue());
                case "deleted" -> resultWriter.printf("- %-28.28s|%-30.30s\n", entry.getValue(), "");
                case "renamed" -> resultWriter.printf("? %-28.28s|? %-28.28s\n",
                        entry.getValue().substring(0, entry.getValue().indexOf('/')),
                        entry.getValue().substring(entry.getValue().indexOf('/') + 1));
                case "updated" -> resultWriter.printf("* %-18.18s|* %-18.18s\n", entry.getValue(), entry.getValue());
            }
        }
        resultWriter.close();
    }

    public FileWriter(File oldFile, File newFile) {

        this.firstArchiveName = newFile.getName();
        this.secondArchiveName = oldFile.getName();
    }

    public void fileWrite(File oldFile, File newFile) {

        try {
            ZipFile zipNewFile = new ZipFile(newFile);
            ZipFile zipOldFile = new ZipFile(oldFile);
            Archive a = new Archive(zipNewFile);
            Archive a1 = new Archive(zipOldFile);
            zipNewFile.close();
            zipOldFile.close();
            ArchiveComparator archiveComparator = new ArchiveComparator(a, a1);
            archiveComparator.compareFiles();
            this.stringHashMap = archiveComparator.getHashMap();
            this.writeToFile();
            System.out.println("File \"compareResult.txt\" created in project's directory.");
        } catch (IOException e) {
            System.out.println("Input-output operation error! Please try to choose correct files again!");
            Frame frame = new Frame();
            firstArchiveName = frame.getFirstFile().getName();
            secondArchiveName = frame.getSecondFile().getName();
            fileWrite(frame.getFirstFile(),frame.getSecondFile());
        }
    }

}
