import java.nio.file.Path;
import java.util.List;

public class TextFile {

    private Path file;

    private List<String> content;

    public TextFile(Path file, List<String> content) {
        this.file = file;
        this.content = content;
    }

    public Path getFile() {
        return file;
    }

    public void setFile(Path nfile){
        this.file = nfile;
    }
    public List<String> getContent() {
        return content;
    }
}