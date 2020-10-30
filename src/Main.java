import java.io.*;

public class Main {

    static public void main(String[] arg) {

        if (arg.length == 2) {
            File firstFile = new File(arg[0]);
            File secondFile = new File(arg[1]);
            FileWriter fileWriter = new FileWriter(firstFile, secondFile);
            fileWriter.fileWrite(firstFile, secondFile);
        }  else {
            Frame frame = new Frame();
            FileWriter fileWriter = new FileWriter(frame.getFirstFile(),frame.getSecondFile());
            fileWriter.fileWrite(frame.getFirstFile(),frame.getSecondFile());
        }
    }
}
