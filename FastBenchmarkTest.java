import org.dhatim.fastexcel.reader.ReadableWorkbook;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.stream.Stream;

public class FastBenchmarkTest {

    public static void main(String[] args) {
        fastExcelRead(RandomDataProvider.outPath.resolve("ignore.xlsx").toFile());
        fastExcelRead(RandomDataProvider.outPath.resolve("ignore.xlsx").toFile());
        try (Stream<Path> stream = Files.list(RandomDataProvider.outPath)) {
            stream.map(Path::toFile).filter(p -> {int i = p.getName().lastIndexOf(".xls"), n = p.getName().length(); return i == n - 4 || i == n - 5;})
                .sorted(Comparator.comparingLong(File::length))
                .forEach(FastBenchmarkTest::fastExcelRead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取并转对象，输出总行数（不包含头）
     */
    private static void fastExcelRead(File file) {
        long start = System.currentTimeMillis(), n = 0;

        try (ReadableWorkbook wb = new ReadableWorkbook(file)) {
            try (Stream<org.dhatim.fastexcel.reader.Row> rows = wb.getFirstSheet().openStream()) {
                n = rows.filter(row -> row.getRowNum() > 1).map(row -> {
                    LargeData t = new LargeData();
                    t.setNv(row.getCellAsNumber(0).orElse(BigDecimal.ZERO).intValue());
                    t.setLv(row.getCellAsNumber(1).orElse(BigDecimal.ZERO).longValue());
                    t.setDv(row.getCellAsNumber(2).orElse(BigDecimal.ZERO).doubleValue());
                    t.setAv(Timestamp.valueOf(row.getCellAsDate(3).orElse(LocalDateTime.now())));
                    t.setStr1(row.getCellText(4));
                    t.setStr2(row.getCellText(5));
                    t.setStr3(row.getCellText(6));
                    t.setStr4(row.getCellText(7));
                    t.setStr5(row.getCellText(8));
                    t.setStr6(row.getCellText(9));
                    t.setStr7(row.getCellText(10));
                    t.setStr8(row.getCellText(11));
                    t.setStr9(row.getCellText(12));
                    t.setStr10(row.getCellText(13));
                    t.setStr11(row.getCellText(14));
                    t.setStr12(row.getCellText(15));
                    t.setStr13(row.getCellText(16));
                    t.setStr14(row.getCellText(17));
                    t.setStr15(row.getCellText(18));
                    return t;
                }).count();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        RandomDataProvider.println("[Fast] read \"" + file.getName() + "\" finished. Rows: " + n + " Cost(ms): " + (System.currentTimeMillis() - start));
    }
}
