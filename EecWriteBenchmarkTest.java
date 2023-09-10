
import org.ttzero.excel.entity.Column;
import org.ttzero.excel.entity.ListSheet;
import org.ttzero.excel.entity.Workbook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public class EecWriteBenchmarkTest {

    public static void main(String[] args) {
        // inlineStr模式
		warmup(); w1k(); w5k(); w10k(); w50k(); w100k(); w500k(); w1000k();
		// SharedStrings模式
		warmup(); ws1k(); ws5k(); ws10k(); ws50k(); ws100k(); ws500k(); ws1000k();
    }

    public static void warmup() {
        for (int i = 0; i < 3; i++)
            eecWrite(10, RandomDataProvider.outPath.resolve("ignore.xlsx"));
    }

	public static void w1k() {
        eecWrite(1000, RandomDataProvider.outPath.resolve("eec-1k.xlsx"));
    }

    public static void w5k() {
        eecWrite(5000, RandomDataProvider.outPath.resolve("eec-5k.xlsx"));
    }

    public static void w10k() {
        eecWrite(10000, RandomDataProvider.outPath.resolve("eec-10k.xlsx"));
    }

    public static void w50k() {
        eecWrite(50000, RandomDataProvider.outPath.resolve("eec-50k.xlsx"));
    }

    public static void w100k() {
        eecWrite(100000, RandomDataProvider.outPath.resolve("eec-100k.xlsx"));
    }

    public static void w500k() {
        eecWrite(500000, RandomDataProvider.outPath.resolve("eec-500k.xlsx"));
    }

    public static void w1000k() {
        eecWrite(1000000, RandomDataProvider.outPath.resolve("eec-1000k.xlsx"));
    }

    private static void eecWrite(final int n, final Path path) {
        long start = System.currentTimeMillis();
        try {
            new Workbook().addSheet(new ListSheet<LargeData>() {
                int i = 0, p = Math.min(1000, n), c = n / p;

                @Override
                protected List<LargeData> more() {
                    return i++ < c ? RandomDataProvider.randomData(p) : null;
                }
            }).writeTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RandomDataProvider.println("[Eec] write \"" + path.getFileName() + "\" finished. Rows: " + n + " Cost(ms): " + (System.currentTimeMillis() - start));
    }

    public static void ws1k() {
        eecSharedWrite(1000, RandomDataProvider.outPath.resolve("eec-shared-1k.xlsx"));
    }

    public static void ws5k() {
        eecSharedWrite(5000, RandomDataProvider.outPath.resolve("eec-shared-5k.xlsx"));
    }

    public static void ws10k() {
        eecSharedWrite(10000, RandomDataProvider.outPath.resolve("eec-shared-10k.xlsx"));
    }

    public static void ws50k() {
        eecSharedWrite(50000, RandomDataProvider.outPath.resolve("eec-shared-50k.xlsx"));
    }

    public static void ws100k() {
        eecSharedWrite(100000, RandomDataProvider.outPath.resolve("eec-shared-100k.xlsx"));
    }

    public static void ws500k() {
        eecSharedWrite(500000, RandomDataProvider.outPath.resolve("eec-shared-500k.xlsx"));
    }

    public static void ws1000k() {
        eecSharedWrite(1000000, RandomDataProvider.outPath.resolve("eec-shared-1000k.xlsx"));
    }


    private static void eecSharedWrite(final int n, final Path path) {
        long start = System.currentTimeMillis();
        try {
            new Workbook().addSheet(new ListSheet<LargeData>(
                new Column("nv", "nv"),
                new Column("lv", "lv"),
                new Column("dv", "dv"),
                new Column("av", "av"),
                new Column("str1", "str1").setShare(true),
                new Column("str2", "str2").setShare(true),
                new Column("str3", "str3").setShare(true),
                new Column("str4", "str4").setShare(true),
                new Column("str5", "str5").setShare(true),
                new Column("str6", "str6").setShare(true),
                new Column("str7", "str7").setShare(true),
                new Column("str8", "str8").setShare(true),
                new Column("str9", "str9").setShare(true),
                new Column("str10", "str10").setShare(true),
                new Column("str11", "str11").setShare(true),
                new Column("str12", "str12").setShare(true),
                new Column("str13", "str13").setShare(true),
                new Column("str14", "str14").setShare(true),
                new Column("str15", "str15").setShare(true)) {
                int i = 0, p = Math.min(1000, n), c = n / p;

                @Override
                protected List<LargeData> more() {
                    return i++ < c ? RandomDataProvider.randomData(p) : null;
                }
            }).writeTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RandomDataProvider.println("[Eec] write \"" + path.getFileName() + "\" finished. Rows: " + n + " Cost(ms): " + (System.currentTimeMillis() - start));
    }
}
