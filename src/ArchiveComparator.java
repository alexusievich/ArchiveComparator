import java.util.HashMap;
import java.util.Objects;

public class ArchiveComparator {
    private final Archive firstArchive;
    private final Archive secondArchive;


    ArchiveComparator(Archive oldArchive, Archive newArchive) {
        this.firstArchive = oldArchive;
        this.secondArchive = newArchive;
    }

    public void compareFiles() {
        this.findDeletedFiles();
        this.findNewFiles();
        this.findUpdatedFiles();
        this.findRenamedFiles();
    }

    private void findDeletedFiles() {
        for (int i = 0; i < firstArchive.getFileSize(); i++) {
            boolean flag = true;
            for (int j = 0; j < secondArchive.getFileSize(); j++) {
                if (Objects.equals(secondArchive.getFiles().get(j).getFileName(), firstArchive.getFiles().get(i).getFileName())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                firstArchive.getFiles().get(i).setFileStatus("deleted");
            }
        }
    }


    private void findNewFiles() {
        for (int i = 0; i < secondArchive.getFileSize(); i++) {
            boolean flag = true;
            for (int j = 0; j < firstArchive.getFileSize(); j++) {
                if (Objects.equals(secondArchive.getFiles().get(i).getFileName(), firstArchive.getFiles().get(j).getFileName())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                secondArchive.getFiles().get(i).setFileStatus("new");
            }
        }
    }

    private void findUpdatedFiles() {
        for (int i = 0; i < firstArchive.getFileSize(); i++) {
            for (int j = 0; j < secondArchive.getFileSize(); j++) {
                if (Objects.equals(secondArchive.getFiles().get(j).getFileName(), firstArchive.getFiles().get(i).getFileName())
                        && secondArchive.getFiles().get(j).getFileSize() != firstArchive.getFiles().get(i).getFileSize()) {
                    secondArchive.getFiles().get(j).setFileStatus("updated");
                    firstArchive.getFiles().get(i).setFileStatus("updated");
                    break;
                }
            }
        }
    }


    private void findRenamedFiles() {
        for (int i = 0; i < firstArchive.getFileSize(); i++) {
            for (int j = 0; j < secondArchive.getFileSize(); j++) {
                if (!Objects.equals(secondArchive.getFiles().get(j).getFileName(), firstArchive.getFiles().get(i).getFileName())
                        && secondArchive.getFiles().get(j).getFileSize() == firstArchive.getFiles().get(i).getFileSize()) {
                    secondArchive.getFiles().get(j).setFileStatus("renamed");
                    firstArchive.getFiles().get(i).setFileStatus("renamed");
                    break;
                }
            }
        }
    }

    public HashMap<String, String> getHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        for (FileConstructor file : firstArchive.getFiles()) {
            if (file.getFileStatus() == "renamed") {
                for (FileConstructor file1 : secondArchive.getFiles()) {
                    if (file.getFileSize() == file1.getFileSize()) {
                        hashMap.put(file.getFileStatus(), file.getFileName() + '/' + file1.getFileName());
                        break;
                    }
                }
            } else {
                hashMap.put(file.getFileStatus(), file.getFileName());
            }
        }
        for (FileConstructor file : secondArchive.getFiles()) {
            if (Objects.equals(file.getFileStatus(), "new")) {
                hashMap.put(file.getFileStatus(), file.getFileName());
            }
        }
        return hashMap;
    }
}