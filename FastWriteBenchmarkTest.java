
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FastWriteBenchmarkTest {

    public static void main(String[] args) {
        w5k(); w1w(); w5w(); w10w(); w50w(); w100w();
    }

    public static void w5k() {
        fastWrite(5000, RandomDataProvider.outPath.resolve("fast-5k.xlsx"));
    }

    public static void w1w() {
        fastWrite(10000, RandomDataProvider.outPath.resolve("fast-1w.xlsx"));
    }

    public static void w5w() {
        fastWrite(50000, RandomDataProvider.outPath.resolve("fast-5w.xlsx"));
    }

    public static void w10w() {
        fastWrite(100000, RandomDataProvider.outPath.resolve("fast-10w.xlsx"));
    }

    public static void w50w() {
        fastWrite(500000, RandomDataProvider.outPath.resolve("fast-50w.xlsx"));
    }

    public static void w100w() {
        fastWrite(1000000, RandomDataProvider.outPath.resolve("fast-100w.xlsx"));
    }

    private static void fastWrite(int n, Path path) {
        long start = System.currentTimeMillis();
        try (Workbook wb = new Workbook(Files.newOutputStream(path), path.getFileName().toString(), "0.0")) {
            Worksheet ws = wb.newWorksheet("Sheet 1");
			int i = 0, c = 0;
			ws.value(i, c++, "nv");
            ws.value(i, c++, "lv");
            ws.value(i, c++, "dv");
            ws.value(i, c++, "av");
            ws.value(i, c++, "str1");
            ws.value(i, c++, "str2");
            ws.value(i, c++, "str3");
            ws.value(i, c++, "str4");
            ws.value(i, c++, "str5");
            ws.value(i, c++, "str6");
            ws.value(i, c++, "str7");
            ws.value(i, c++, "str8");
            ws.value(i, c++, "str9");
            ws.value(i, c++, "str10");
            ws.value(i, c++, "str11");
            ws.value(i, c++, "str12");
            ws.value(i, c++, "str13");
            ws.value(i, c++, "str14");
            ws.value(i, c, "str15");
            c = 0;
            for (; ++i <= n;  c = 0) {
                LargeData o = RandomDataProvider.randomOne();
                ws.value(i, c++, o.getNv());
                ws.value(i, c++, o.getLv());
                ws.value(i, c++, o.getDv());
                ws.value(i, c++, o.getAv());
                ws.value(i, c++, o.getStr1());
                ws.value(i, c++, o.getStr2());
                ws.value(i, c++, o.getStr3());
                ws.value(i, c++, o.getStr4());
                ws.value(i, c++, o.getStr5());
                ws.value(i, c++, o.getStr6());
                ws.value(i, c++, o.getStr7());
                ws.value(i, c++, o.getStr8());
                ws.value(i, c++, o.getStr9());
                ws.value(i, c++, o.getStr10());
                ws.value(i, c++, o.getStr11());
                ws.value(i, c++, o.getStr12());
                ws.value(i, c++, o.getStr13());
                ws.value(i, c++, o.getStr14());
                ws.value(i, c, o.getStr15());
            }
            ws.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        RandomDataProvider.println("[Fastexcel] write \"" + path.getFileName() + "\" finished. Cost(ms): " + (System.currentTimeMillis() - start));
    }

}
