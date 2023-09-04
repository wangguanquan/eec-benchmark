
import org.ttzero.excel.entity.ListSheet;
import org.ttzero.excel.entity.Workbook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public class EecWriteBenchmarkTest {

    public static void main(String[] args) {
		w5k(); w1w(); w5w(); w10w(); w50w(); w100w();
    }

    public static void w5k() {
        eecWrite(5000, RandomDataProvider.outPath.resolve("eec-5k.xlsx"));
    }

    public static void w1w() {
        eecWrite(10000, RandomDataProvider.outPath.resolve("eec-1w.xlsx"));
    }

    public static void w5w() {
        eecWrite(50000, RandomDataProvider.outPath.resolve("eec-5w.xlsx"));
    }

    public static void w10w() {
        eecWrite(100000, RandomDataProvider.outPath.resolve("eec-10w.xlsx"));
    }

    public static void w50w() {
        eecWrite(500000, RandomDataProvider.outPath.resolve("eec-50w.xlsx"));
    }

    public static void w100w() {
        eecWrite(1000000, RandomDataProvider.outPath.resolve("eec-100w.xlsx"));
    }

    private static void eecWrite(final int n, final Path path) {
        long start = System.currentTimeMillis();
        try {
            new Workbook().addSheet(new ListSheet<LargeData>() {
                int i = 0;
                final int p = 1000, c = n / p;

                @Override
                protected List<LargeData> more() {
                    return i++ < c ? RandomDataProvider.randomData(p) : null;
                }
            }).writeTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RandomDataProvider.println("[EEC] write \"" + path.getFileName() + "\" finished. Rows: " + n + " cost(ms): " + (System.currentTimeMillis() - start));
    }
}
