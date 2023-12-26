package java_technics.shard.example;

import java.util.zip.CRC32;

public class Example {

    public static void main(String[] args) {

        String name = "IsssueName"; // на основе чего шард
        int count = 10; // размерность шарда

        CRC32 crc = new CRC32();
        crc.update(name.getBytes());
        Long shard = Math.abs(crc.getValue()) % count;

        System.out.println(shard); // вывод: 6
    }
}
