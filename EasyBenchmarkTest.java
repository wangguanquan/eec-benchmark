import com.alibaba.excel.EasyExcel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class EasyBenchmarkTest {

   public static void main(String[] args) {
       try (Stream<Path> stream = Files.list(RandomDataProvider.outPath)) {
            stream.filter(p -> {int i = p.getFileName().toString().lastIndexOf(".xls"), n = p.getFileName().toString().length(); return i == n - 4 || i == n - 5;})
                .forEach(EasyBenchmarkTest::easyRead);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    private static void easyRead(Path path) {
        long start = System.currentTimeMillis();
        EasyExcel.read(path.toFile(), LargeData.class, null).headRowNumber(1).doReadAll();
        RandomDataProvider.println(path.getFileName() + " [Easyexcel] read finished. 耗时(ms): " + (System.currentTimeMillis() - start));
    }
}
