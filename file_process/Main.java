package readfile;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Example.example1();

        // разделитель при указании путей к файлу, использующийся в файловой системе данной ОС
        String separator = File.separator; 
      
        String p = String.format(
                "java_technics%sfile_process%s",
                separator,
                separator
        );
      
        Example.example2(p, "log1.log");
        Example.example3(p, "log2.log");
        Example.example4(p, "log3.log");
        Example.example5(p, "data.bin");
        Example.example6(p, "data.bin");
    }
}
