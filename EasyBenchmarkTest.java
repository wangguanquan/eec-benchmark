import com.alibaba.excel.EasyExcel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class EasyBenchmarkTest {

   public static void main(String[] args) {
       try (Stream<Path> stream = Files.list(path)) {
            stream.forEach(EasyBenchmarkTest::easyRead);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    static Path path = Paths.get("./excel-simple");

    private static void easyRead(Path path) {
        long start = System.currentTimeMillis();
        EasyExcel.read(path.toFile(), LargeData.class, null).headRowNumber(1).doReadAll();
        System.out.println(path.getFileName() + " [Easyexcel] read finished. 耗时(ms): " + (System.currentTimeMillis() - start));
    }
}
