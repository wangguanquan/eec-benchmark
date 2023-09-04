import org.ttzero.excel.reader.ExcelReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.stream.Stream;

public class EecBenchmarkTest {
    public static void main(String[] args) {
        try (Stream<Path> stream = Files.list(RandomDataProvider.outPath)) {
            stream.filter(p -> {int i = p.getFileName().toString().lastIndexOf(".xls"), n = p.getFileName().toString().length(); return i == n - 4 || i == n - 5;})
                .forEach(EecBenchmarkTest::eecRead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void eecRead(Path path) {
        long start = System.currentTimeMillis(), n = 0;
        try (ExcelReader reader = ExcelReader.read(path)) {
            n = reader.sheet(0).header(1).bind(LargeData.class).rows().map(org.ttzero.excel.reader.Row::get).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RandomDataProvider.println("[EEC] read \"" + path.getFileName() + "\" finished. Rows: " + n + " cost(ms): " + (System.currentTimeMillis() - start));
    }
}