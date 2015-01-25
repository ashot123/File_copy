package item.io;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class App {

    final static Charset ENCODING = StandardCharsets.UTF_8;

    public static void main(String[] args) throws IOException {

        // 1. In root file paths with dir1 prefix
        List<String> lines = readSmallTextFile(args[0]); // root.txt
        List<String> targetLines = new ArrayList<>(lines.size());
        // read line by line and renaming
        for (String currentLine : lines) {
            // The logic of file moving

            int indexOf = currentLine.indexOf(args[1]);
            if(indexOf != -1) {
                /*int indexOfEnd = currentLine.indexOf(' ', indexOf + args[1].length() + 1);
                if(indexOfEnd == -1) {
                    indexOfEnd = currentLine.length();
                }*/

                String replacedLine = currentLine.replace(args[1], args[2]);
                targetLines.add(replacedLine);

                moveFile(args[1], args[2]);
            }


        }


        writeSmallTextFile(targetLines, args[0]);



        // 2. get file name and concatenate with target path
        // 3. copy file to target path

        /*
        * // File (or directory) with old name
    File file = new File("oldname");

    // File (or directory) with new name
    File file2 = new File("newname");
    if(file2.exists()) throw new java.io.IOException("file exists");

    // Rename file (or directory)
    boolean success = file.renameTo(file2);
    if (!success) {
        // File was not successfully renamed
    }*/
        // 4. generate new root txt, and the old renamed with "old" suffix
    }

    /**
     Note: the javadoc of Files.readAllLines says it's intended for small
     files. But its implementation uses buffering, so it's likely good
     even for fairly large files.
     */
    private static List<String>  readSmallTextFile(String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        return Files.readAllLines(path, ENCODING);
    }

    private static void writeSmallTextFile(List<String> aLines, String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        Files.write(path, aLines, ENCODING);
    }

    private static void moveFile(String sourceDir, String targetDir) throws IOException {
        Path sourceFilePath = Paths.get(sourceDir);
        Path targetFilePath = Paths.get(targetDir);
        Files.move(sourceFilePath, targetFilePath.resolve(sourceFilePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
    }




}
