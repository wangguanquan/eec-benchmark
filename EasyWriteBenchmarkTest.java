
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.nio.file.Path;

public class EasyWriteBenchmarkTest {

    public static void main(String[] args) {
        w1k(); w5k(); w1w(); w5w(); w10w(); w50w(); w100w();
    }

    public static void w1k() {
        easyWrite(1000, RandomDataProvider.outPath.resolve("easy-1k.xlsx"));
    }

    public static void w5k() {
        easyWrite(5000, RandomDataProvider.outPath.resolve("easy-5k.xlsx"));
    }

    public static void w1w() {
        easyWrite(10000, RandomDataProvider.outPath.resolve("easy-10k.xlsx"));
    }

    public static void w5w() {
        easyWrite(50000, RandomDataProvider.outPath.resolve("easy-50k.xlsx"));
    }

    public static void w10w() {
        easyWrite(100000, RandomDataProvider.outPath.resolve("easy-100k.xlsx"));
    }

    public static void w50w() {
        easyWrite(500000, RandomDataProvider.outPath.resolve("easy-50k.xlsx"));
    }

    public static void w100w() {
        easyWrite(1000000, RandomDataProvider.outPath.resolve("easy-1000k.xlsx"));
    }

    private static void easyWrite(int n, Path subPath) {
        long start = System.currentTimeMillis();
        ExcelWriter excelWriter = EasyExcel.write(subPath.toFile()).withTemplate(RandomDataProvider.outPath.getParent().resolve("fill.xlsx").toFile()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        for (int j = 0, p = 1000, c = n / p; j < c; j++) {
            excelWriter.fill(RandomDataProvider.randomData(p), writeSheet);
        }
        excelWriter.finish();
        RandomDataProvider.println("[Easyexcel] write \"" + subPath.getFileName() + "\" finished. Rows: " + n + " Cost(ms): " + (System.currentTimeMillis() - start));
    }
}
