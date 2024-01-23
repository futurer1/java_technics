package java_technics.file_process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

public class LargeFileGenerator {

    public static void main(String[] args) {
        new LargeFileGenerator().generate();
    }

    public void generate() {
        var data = prepareData(4_000_000);
        File f = new File(File.separator + "home"+ File.separator +"username" + File.separator + "temp.txt");

        int stepCount = 10;
        long total = 0;
        for (int i = 0; i < stepCount; i++) {
            System.out.printf("Begin step %s%n", i);

            long begin = System.currentTimeMillis();
            writeFileSimple(f, data);
            total += System.currentTimeMillis() - begin;
        }
        System.out.printf("Average time: %s ms%n.", total / stepCount);
    }

    public String prepareData(int lineCount) {
        var data = new StringBuilder();
        for (int i = 0; i < lineCount; i++) {
            data.append(UUID.randomUUID()).append(System.lineSeparator());
        }
        return data.toString();
    }

    public void writeFileSimple(File f, String content) { // 1697 ms
        try (var fw = new FileWriter(f)) {
            fw.write(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
