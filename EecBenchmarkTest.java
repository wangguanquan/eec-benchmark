import org.ttzero.excel.reader.ExcelReader;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

public class EecBenchmarkTest {
    public static void main(String[] args) {
		r1w(); r5w(); r10w(); r50w(); r100w();
    }

    static Path path = Paths.get("./excel-simple");

    public static void r1w() {
        eecRead(path.resolve("eec-1w.xlsx"));
    }

    public static void r5w() {
        eecRead(path.resolve("eec-5w.xlsx"));
    }

    public static void r10w() {
        eecRead(path.resolve("eec-10w.xlsx"));
    }

    public static void r50w() {
        eecRead(path.resolve("eec-50w.xlsx"));
    }

    public static void r100w() {
        eecRead(path.resolve("eec-100w.xlsx"));
    }

    private static void eecRead(Path path) {
        long start = System.currentTimeMillis(), n = 0;
        try (ExcelReader reader = ExcelReader.read(path)) {
            n = reader.sheet(0).header(1).bind(LargeData.class).rows().map(org.ttzero.excel.reader.Row::get).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(path.getFileName() + " [EEC] read finished. 行: " + n + " 耗时(ms): " + (System.currentTimeMillis() - start));
    }
}