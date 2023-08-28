import com.alibaba.excel.EasyExcel;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EasyBenchmarkTest {

   public static void main(String[] args) {
		r1w(); r5w(); r10w(); r50w(); r100w();
    }

    static Path path = Paths.get("./excel-simple");

    public static void r1w() {
        easyRead(path.resolve("eec-1w.xlsx"));
    }

    public static void r5w() {
        easyRead(path.resolve("eec-5w.xlsx"));
    }

    public static void r10w() {
        easyRead(path.resolve("eec-10w.xlsx"));
    }

    public static void r50w() {
        easyRead(path.resolve("eec-50w.xlsx"));
    }

    public static void r100w() {
        easyRead(path.resolve("eec-100w.xlsx"));
    }

    private static void easyRead(Path path) {
        long start = System.currentTimeMillis();
        EasyExcel.read(path.toFile(), LargeData.class, null).headRowNumber(1).doReadAll();
        System.out.println(path.getFileName() + " [Easyexcel] read finished. 耗时(ms): " + (System.currentTimeMillis() - start));
    }
}
