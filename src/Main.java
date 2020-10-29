import java.io.*;

public class Main {

    static public void main(String[] arg) {

        if (arg.length == 2) {
            File file = new File(arg[0]);
            File file1 = new File(arg[1]);
            FileWriter fileWriter = new FileWriter(file, file1);
            fileWriter.fileWrite(file, file1);
        }  else {
            Frame frame = new Frame();
            FileWriter fileWriter = new FileWriter(frame.getFirstFile(),frame.getSecondFile());
            fileWriter.fileWrite(frame.getFirstFile(),frame.getSecondFile());
        }
    }
}
