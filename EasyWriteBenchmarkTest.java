
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class EasyWriteBenchmarkTest {

    public static void main(String[] args) {
        w1w(); w5w(); w10w(); w50w(); w100w();
    }

    static Path path = Paths.get("./excel-simple");

    public static void w1w() {
        easyWrite(10000, path.resolve("easy-1w.xlsx"));
    }

    public static void w5w() {
        easyWrite(50000, path.resolve("easy-5w.xlsx"));
    }

    public static void w10w() {
        easyWrite(100000, path.resolve("easy-10w.xlsx"));
    }

    public static void w50w() {
        easyWrite(500000, path.resolve("easy-50w.xlsx"));
    }

    public static void w100w() {
        easyWrite(1000000, path.resolve("easy-100w.xlsx"));
    }
    private static void easyWrite(int n, Path path) {
        long start = System.currentTimeMillis();
        ExcelWriter excelWriter = EasyExcel.write(path.toFile()).withTemplate(path.resolve("../../fill.xlsx").toFile()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        for (int j = 0, p = 1000, c = n / p; j < c; j++) {
            excelWriter.fill(RandomDataProvider.randomData(p), writeSheet);
        }
        excelWriter.finish();
        System.out.println(path.getFileName() + " [Easyexcel] write finished. 耗时(ms): " + (System.currentTimeMillis() - start));
    }
}
