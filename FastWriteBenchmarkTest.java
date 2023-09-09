
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FastWriteBenchmarkTest {

    public static void main(String[] args) {
        warmup(); w1k(); w5k(); w10k(); w50k(); w100k(); w500k(); w1000k();
    }

    public static void warmup() {
        fastWrite(10, RandomDataProvider.outPath.resolve("ignore.xlsx"));
    }

    public static void w1k() {
        fastWrite(1000, RandomDataProvider.outPath.resolve("fast-1k.xlsx"));
    }

    public static void w5k() {
        fastWrite(5000, RandomDataProvider.outPath.resolve("fast-5k.xlsx"));
    }

    public static void w10k() {
        fastWrite(10000, RandomDataProvider.outPath.resolve("fast-10k.xlsx"));
    }

    public static void w50k() {
        fastWrite(50000, RandomDataProvider.outPath.resolve("fast-50k.xlsx"));
    }

    public static void w100k() {
        fastWrite(100000, RandomDataProvider.outPath.resolve("fast-100k.xlsx"));
    }

    public static void w500k() {
        fastWrite(500000, RandomDataProvider.outPath.resolve("fast-500k.xlsx"));
    }

    public static void w1000k() {
        fastWrite(1000000, RandomDataProvider.outPath.resolve("fast-1000k.xlsx"));
    }

    private static void fastWrite(int n, Path path) {
        long start = System.currentTimeMillis();
        try (Workbook wb = new Workbook(Files.newOutputStream(path), path.getFileName().toString(), "0.0")) {
            Worksheet ws = wb.newWorksheet("Sheet 1");
			int i = 0, c = 0;
			ws.inlineString(i, c++, "nv");
            ws.inlineString(i, c++, "lv");
            ws.inlineString(i, c++, "dv");
            ws.inlineString(i, c++, "av");
            ws.inlineString(i, c++, "str1");
            ws.inlineString(i, c++, "str2");
            ws.inlineString(i, c++, "str3");
            ws.inlineString(i, c++, "str4");
            ws.inlineString(i, c++, "str5");
            ws.inlineString(i, c++, "str6");
            ws.inlineString(i, c++, "str7");
            ws.inlineString(i, c++, "str8");
            ws.inlineString(i, c++, "str9");
            ws.inlineString(i, c++, "str10");
            ws.inlineString(i, c++, "str11");
            ws.inlineString(i, c++, "str12");
            ws.inlineString(i, c++, "str13");
            ws.inlineString(i, c++, "str14");
            ws.inlineString(i, c, "str15");
            c = 0;
            for (; ++i <= n;  c = 0) {
                LargeData o = RandomDataProvider.randomOne();
                ws.value(i, c++, o.getNv());
                ws.value(i, c++, o.getLv());
                ws.value(i, c++, o.getDv());
                ws.value(i, c++, o.getAv());
                ws.inlineString(i, c++, o.getStr1());
                ws.inlineString(i, c++, o.getStr2());
                ws.inlineString(i, c++, o.getStr3());
                ws.inlineString(i, c++, o.getStr4());
                ws.inlineString(i, c++, o.getStr5());
                ws.inlineString(i, c++, o.getStr6());
                ws.inlineString(i, c++, o.getStr7());
                ws.inlineString(i, c++, o.getStr8());
                ws.inlineString(i, c++, o.getStr9());
                ws.inlineString(i, c++, o.getStr10());
                ws.inlineString(i, c++, o.getStr11());
                ws.inlineString(i, c++, o.getStr12());
                ws.inlineString(i, c++, o.getStr13());
                ws.inlineString(i, c++, o.getStr14());
                ws.inlineString(i, c, o.getStr15());
            }
            ws.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        RandomDataProvider.println("[Fast] write \"" + path.getFileName() + "\" finished. Rows: " + n + " Cost(ms): " + (System.currentTimeMillis() - start));
    }

}
