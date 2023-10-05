import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class EasyBenchmarkTest {

   public static void main(String[] args) {
       easyRead(RandomDataProvider.outPath.resolve("ignore.xlsx").toFile());
       easyRead(RandomDataProvider.outPath.resolve("ignore.xlsx").toFile());
       easyRead(RandomDataProvider.outPath.resolve("ignore.xlsx").toFile());
       easyRead(RandomDataProvider.outPath.resolve("ignore.xlsx").toFile());
       if (args.length == 0) {
           try (Stream<Path> stream = Files.list(RandomDataProvider.outPath)) {
               stream.map(Path::toFile).filter(p -> {
                   int i = p.getName().lastIndexOf(".xls"), n = p.getName().length();
                   return i == n - 4 || i == n - 5;
               })
                   .sorted(Comparator.comparingLong(File::length))
                   .forEach(EasyBenchmarkTest::easyRead);
           } catch (IOException e) {
               e.printStackTrace();
           }
       } else {
           for (String file : args) {
               easyRead(RandomDataProvider.outPath.resolve(file).toFile());
           }
       }
   }

    /**
     * 读取并转对象，输出总行数（不包含表头）
     */
    private static void easyRead(File file) {
        long start = System.currentTimeMillis();
        int[] rows = { 0 };
        EasyExcel.read(file, LargeData.class, new ReadListener<LargeData>() {

            @Override
            public void invoke(LargeData o, AnalysisContext analysisContext) { }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                rows[0] = analysisContext.readRowHolder() != null ? analysisContext.readRowHolder().getRowIndex() : 0;
            }
        }).headRowNumber(1).doReadAll();
        RandomDataProvider.println("[Easy] read \"" + file.getName() + "\" finished. Rows: " + rows[0] + " Cost(ms): " + (System.currentTimeMillis() - start));
    }
}
