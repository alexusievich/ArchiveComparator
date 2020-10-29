public class FileConstructor {

    private final String fileName;
    private final long fileSize;
    private String fileStatus;

    public FileConstructor(String fileName, long fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileStatus = "default";
    }

    public String getFileStatus() { return fileStatus; }


    public String getFileName() { return fileName; }

    public void setFileStatus(String fileStatus) { this.fileStatus = fileStatus; }

    public long getFileSize() { return fileSize; }


}
