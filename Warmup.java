import org.ttzero.excel.entity.EmptySheet;
import org.ttzero.excel.entity.Workbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Warmup {
    public static void main(String[] args) {
        Path path = RandomDataProvider.outPath.resolve("ignore.xlsx");
        if (!Files.exists(path)) {
            try {
                new Workbook().addSheet(new EmptySheet()).writeTo(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
