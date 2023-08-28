
import org.ttzero.excel.entity.ListSheet;
import org.ttzero.excel.entity.Workbook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class EecWriteBenchmarkTest {

    public static void main(String[] args) throws IOException {
		w1w(); w5w(); w10w(); w50w(); w100w();
    }
	
    static Path path = Paths.get("./excel-simple");

    public static void w1w() throws IOException {
        eecWrite(10000, path.resolve("eec-1w.xlsx"));
    }

    public static void w5w() throws IOException {
        eecWrite(50000, path.resolve("eec-5w.xlsx"));
    }

    public static void w10w() throws IOException {
        eecWrite(100000, path.resolve("eec-10w.xlsx"));
    }

    public static void w50w() throws IOException {
        eecWrite(500000, path.resolve("eec-50w.xlsx"));
    }

    public static void w100w() throws IOException {
        eecWrite(1000000, path.resolve("eec-100w.xlsx"));
    }

    private static void eecWrite(final int n, final Path path) throws IOException {
        long start = System.currentTimeMillis();
        new Workbook().addSheet(new ListSheet<LargeData>() {
            int i = 0;
            final int p = 1000, c = n / p;
            @Override
            protected List<LargeData> more() {
                return i++ < c ? RandomDataProvider.randomData(p) : null;
            }
        }).writeTo(path);

        System.out.println(path.getFileName() + " [EEC] write finished. 行: " + n + " 耗时(ms): " + (System.currentTimeMillis() - start));
    }
}
