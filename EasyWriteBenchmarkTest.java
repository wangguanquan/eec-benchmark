
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.nio.file.Path;

public class EasyWriteBenchmarkTest {

    public static void main(String[] args) {
        warmup(); w1k(); w5k(); w10k(); w50k(); w100k(); w500k(); w1000k();
    }

    public static void warmup() {
        for (int i = 0; i < 5; i++)
            easyWrite(10, RandomDataProvider.outPath.resolve("ignore.xlsx"));
    }

    public static void w1k() {
        easyWrite(1000, RandomDataProvider.outPath.resolve("easy-1k.xlsx"));
    }

    public static void w5k() {
        easyWrite(5000, RandomDataProvider.outPath.resolve("easy-5k.xlsx"));
    }

    public static void w10k() {
        easyWrite(10000, RandomDataProvider.outPath.resolve("easy-10k.xlsx"));
    }

    public static void w50k() {
        easyWrite(50000, RandomDataProvider.outPath.resolve("easy-50k.xlsx"));
    }

    public static void w100k() {
        easyWrite(100000, RandomDataProvider.outPath.resolve("easy-100k.xlsx"));
    }

    public static void w500k() {
        easyWrite(500000, RandomDataProvider.outPath.resolve("easy-500k.xlsx"));
    }

    public static void w1000k() {
        easyWrite(1000000, RandomDataProvider.outPath.resolve("easy-1000k.xlsx"));
    }

    private static void easyWrite(int n, Path subPath) {
        long start = System.currentTimeMillis();
        ExcelWriter excelWriter = EasyExcel.write(subPath.toFile()).withTemplate(RandomDataProvider.outPath.getParent().resolve("fill.xlsx").toFile()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        for (int j = n, p; j > 0; j -= p) {
            excelWriter.fill(RandomDataProvider.randomData(p = Math.min(100, j)), writeSheet);
        }
        excelWriter.finish();
        RandomDataProvider.println("[Easy] write \"" + subPath.getFileName() + "\" finished. Rows: " + n + " Cost(ms): " + (System.currentTimeMillis() - start));
    }
}
