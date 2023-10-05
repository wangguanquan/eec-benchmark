import org.ttzero.excel.reader.ExcelReader;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Comparator;
import java.util.stream.Stream;

public class EecBenchmarkTest {
    public static void main(String[] args) {
        eecRead(RandomDataProvider.outPath.resolve("ignore.xlsx").toFile());
        eecRead(RandomDataProvider.outPath.resolve("ignore.xlsx").toFile());
        eecRead(RandomDataProvider.outPath.resolve("ignore.xlsx").toFile());
        eecRead(RandomDataProvider.outPath.resolve("ignore.xlsx").toFile());
        try (Stream<Path> stream = Files.list(RandomDataProvider.outPath)) {
            stream.map(Path::toFile).filter(p -> {int i = p.getName().lastIndexOf(".xls"), n = p.getName().length(); return i == n - 4 || i == n - 5;})
                .sorted(Comparator.comparingLong(File::length))
                .forEach(EecBenchmarkTest::eecRead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取并转对象，输出总行数（不包含表头）
     */
    private static void eecRead(File file) {
        long start = System.currentTimeMillis(), n = 0;
        try (ExcelReader reader = ExcelReader.read(file.toPath())) {
            n = reader.sheet(0).header(1).bind(LargeData.class).rows().map(org.ttzero.excel.reader.Row::get).count();
        } catch (Exception e) {
            e.printStackTrace();
        }

        RandomDataProvider.println("[Eec] read \"" + file.getName() + "\" finished. Rows: " + n + " Cost(ms): " + (System.currentTimeMillis() - start));
    }

}