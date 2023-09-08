import com.alibaba.excel.EasyExcel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class EasyBenchmarkTest {

   public static void main(String[] args) {
       try (Stream<Path> stream = Files.list(RandomDataProvider.outPath)) {
           stream.map(Path::toFile).filter(p -> {int i = p.getName().lastIndexOf(".xls"), n = p.getName().length(); return i == n - 4 || i == n - 5;})
               .sorted(Comparator.comparingLong(File::length))
               .forEach(EasyBenchmarkTest::easyRead);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    /**
     * 读取并转对象，输出总行数（不包含头）
     */
    private static void easyRead(File file) {
        long start = System.currentTimeMillis();
        EasyExcel.read(file, LargeData.class, null).headRowNumber(1).doReadAll();
        RandomDataProvider.println("[Easyexcel] read \"" + file.getName() + "\" finished. Cost(ms): " + (System.currentTimeMillis() - start));
    }
}
