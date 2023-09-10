
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomDataProvider {
    public static Random random = new Random();
    public static char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890🍦⚽🛒😂😊😢😉😎😯❤不逢北国之秋，已将近十余年了。在南方每年到了秋天，总要想起陶然亭（1）的芦花，钓鱼台（2）的柳影，西山（3）的虫唱，玉泉（4）的夜月，潭柘寺（5）的钟声。在北平即使不出门去吧，就是在皇城人海之中，租人家一椽（6）破屋来住着，早晨起来，泡一碗浓茶，向院子一坐，你也能看得到很高很高的碧绿的天色，听得到青天下驯鸽的飞声。从槐树叶底，朝东细数着一丝一丝漏下来的日光，或在破壁腰中，静对着像喇叭似的牵牛花（朝荣）的蓝朵，自然而然地也能够感觉到十分的秋意。说到了牵牛花，我以为以蓝色或白色者为佳，紫黑色次之，淡红色最下。最好，还要在牵牛花底，叫长着几根疏疏落落的尖细且长的秋草，使作陪衬。".toCharArray();
    public static Path outPath = Paths.get("./excel-simple");
    private static final char[] cache = new char[50];
    public static String getRandomString() {
        int n = random.nextInt(cache.length) + 1, size = charArray.length;
        for (int i = 0, j; i < n; i++) {
            cache[i] = charArray[j = random.nextInt(size)];
            if (Character.isSurrogate(cache[i])) cache[i++] = charArray[j + 1];
        }
        return new String(cache, 0, n);
    }
	
	public static String getRandomAssicString() {
        int n = random.nextInt(20) + 1, size = charArray.length;
        for (int i = 0; i < n; i++) {
            cache[i] = charArray[random.nextInt(size)];
        }
        return new String(cache, 0, n);
    }

    public static List<LargeData> randomData(int n) {
        List<LargeData> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            LargeData largeData = new LargeData();
            list.add(largeData);
            largeData.setNv(random.nextInt());
            largeData.setLv(random.nextLong());
            largeData.setDv(random.nextDouble());
            largeData.setAv(new Date(System.currentTimeMillis() - random.nextInt(9999)));
            largeData.setStr1(getRandomAssicString());
            largeData.setStr2(getRandomAssicString());
            largeData.setStr3(getRandomAssicString());
            largeData.setStr4(getRandomAssicString());
            largeData.setStr5(getRandomAssicString());
            largeData.setStr6(getRandomAssicString());
            largeData.setStr7(getRandomString());
            largeData.setStr8(getRandomString());
            largeData.setStr9(getRandomString());
            largeData.setStr10(getRandomString());
            largeData.setStr11(getRandomString());
            largeData.setStr12(getRandomString());
            largeData.setStr13(getRandomString());
            largeData.setStr14(getRandomString());
            largeData.setStr15(getRandomString());
        }
        return list;
    }

	public static LargeData randomOne() {
        LargeData largeData = new LargeData();
        largeData.setNv(random.nextInt());
        largeData.setLv(random.nextLong());
        largeData.setDv(random.nextDouble());
        largeData.setAv(new Date(System.currentTimeMillis() - random.nextInt(9999)));
        largeData.setStr1(getRandomAssicString());
        largeData.setStr2(getRandomAssicString());
        largeData.setStr3(getRandomAssicString());
        largeData.setStr4(getRandomAssicString());
        largeData.setStr5(getRandomAssicString());
        largeData.setStr6(getRandomAssicString());
        largeData.setStr7(getRandomString());
        largeData.setStr8(getRandomString());
        largeData.setStr9(getRandomString());
        largeData.setStr10(getRandomString());
        largeData.setStr11(getRandomString());
        largeData.setStr12(getRandomString());
        largeData.setStr13(getRandomString());
        largeData.setStr14(getRandomString());
        largeData.setStr15(getRandomString());
        return largeData;
    }

    static BufferedWriter logWriter;
    static {
        try {
            logWriter = Files.newBufferedWriter(outPath.resolve("1.out"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void println(String msg) {
        System.out.println(msg);
        try {
            logWriter.write(msg);
            logWriter.newLine();
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
