
import org.ttzero.excel.entity.ListSheet;
import org.ttzero.excel.entity.Workbook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public class EecWriteBenchmarkTest {

    public static void main(String[] args) {
		w1k(); w5k(); w10k(); w50k(); w100k(); w500k(); w1000k();
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

        RandomDataProvider.println("[EEC] write \"" + path.getFileName() + "\" finished. Rows: " + n + " Cost(ms): " + (System.currentTimeMillis() - start));
    }
}
