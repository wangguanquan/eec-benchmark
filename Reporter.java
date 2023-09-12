import java.nio.file.Files;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reporter {

    final static String GROUP_KEY = "G:";

    public static void main(String[] args) {
        List<O> list = parseLogs();
        if (list == null) return;
        // inlineStr 统计
        List<O> inlineList = list.stream().filter(o -> o.shared == 0).collect(Collectors.toList());
        String[][] v = statistics(inlineList);

        // Sort
        sort(v);

        // SharedString 统计
        List<O> sharedList = list.stream().filter(o -> o.shared == 1).collect(Collectors.toList());
        if (!sharedList.isEmpty()) {
            String[][] v2 = statistics(sharedList);

            // Sort
            sort(v2);
            // Group: 以'G:'开头
            v2[0] = new String[] { GROUP_KEY + "↓Shared String↓" };
            // Join
            String[][] _v = new String[v.length + v2.length][];
            System.arraycopy(v, 0, _v, 0, v.length);
            System.arraycopy(v2, 0, _v, v.length, v2.length);
            v = _v;
        }

        printTable("平均耗时：读写总耗时/读写次数" + System.lineSeparator() + "Cells/s： 平均每秒处理多少单元格", v);
        System.out.println();
        printCompare(v);
    }

    static List<O> parseLogs() {
        try (Stream<String> lines = Files.lines(RandomDataProvider.outPath.resolve("1.out"))) {
            return lines.map(s -> {
                String[] ss = s.split(" ");
                if (ss.length < 5) return null;
                // 0: tool
                String v = ss[0];
                if (v.charAt(0) != '[' || v.charAt(v.length() - 1) != ']') return null;
                v = v.substring(1, v.length() - 1);
                if (!"Eec".equalsIgnoreCase(v) && !"Easy".equalsIgnoreCase(v) && !"Fast".equalsIgnoreCase(v)) return null;
                O o = new O();
                o.tool = v;

                // 1: r/w
                v = ss[1];
                if ("write".equalsIgnoreCase(v)) o.rw = 1;
                else if ("read".equalsIgnoreCase(v)) o.rw = 2;

                // 2: file name
                v = ss[2];
                if (v.charAt(0) != '"' || v.charAt(v.length() - 1) != '"') return null;
                int i = v.lastIndexOf('-'), j = i > 0 ? v.indexOf('.', i + 2) : -1;
                if (j > i) o.fn = v.substring(i + 1, j);
                if (o.fn == null || o.fn.equalsIgnoreCase("ignore.xlsx") || o.fn.equalsIgnoreCase("ignore.xls")) return null;
                o.shared = v.contains("-shared-") ? 1 : 0;

                // 5: rows
                v = ss[4].toLowerCase();
                if (v.startsWith("rows")) {
                    v = ss[5];
                    Integer iv = toInteger(v);
                    if (iv != null) o.rows = iv;
                    else o.rows = (iv = toInteger(o.fn)) != null ? iv : 0;

                    v = ss[6].toLowerCase();
                }

                if (v.startsWith("cost")) {
                    v = ss[7];
                    Integer c = toInteger(v);
                    if (c != null) o.cost = c;
                }

                return o;
            }).filter(Objects::nonNull).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static String[][] statistics(List<O> list) {
        Map<String, List<O>> map = list.stream().collect(Collectors.groupingBy(O::getTool));
        String[][] v = new String[(map.size() << 1) + 1][];
        int i = 0;
        for (Map.Entry<String, List<O>> entry : map.entrySet()) {
            List<O> sub = entry.getValue();
            String tool = entry.getKey();
            sub.sort(Comparator.comparingInt(O::getRows));
            Map<String, A> r = new LinkedHashMap<>(), w = new LinkedHashMap<>();
            long[] rows = { 0L, 0L }, cost = { 0L, 0L };
            for (O o : sub) {
                Map<String, A> t = o.rw == 1 ? w : r;
                A a = t.computeIfAbsent(o.fn, k -> new A());
                a.a += o.cost;
                a.b++;

                rows[o.rw - 1] += o.rows;
                cost[o.rw - 1] += o.cost;
            }
            int j = 1, len = !r.isEmpty() ? r.size() : w.size();
            if (i == 0) {
                String[] __ = new String[len + 2], titles = !r.isEmpty() ? r.keySet().toArray(new String[r.size()]) : w.keySet().toArray(new String[w.size()]);
                v[i++] = __;
                __[0] = "TOOLS";
                for (String s : titles) __[j++] = s;
                __[j] = "Cells/s";
            }

            if (!w.isEmpty()) {
                String[] __ = new String[w.size() + 2];
                v[i++] = __;
                __[0] = tool + "(w)";
                j = 1;
                for (A a : w.values()) {
                    long avg = (long) (1.0D * a.a / a.b + 0.5D);
                    __[j++] = String.valueOf(avg);
                }
                __[j] = String.valueOf((long) (rows[0] * 19.0 / cost[0] * 1000 + 0.5));
            }

            if (!r.isEmpty()) {
                String[] __ = new String[r.size() + 2];
                v[i++] = __;
                __[0] = tool + "(r)";
                j = 1;
                for (A a : r.values()) {
                    long avg = (long) (1.0D * a.a / a.b + 0.5D);
                    __[j++] = String.valueOf(avg);
                }
                __[j] = String.valueOf((long) (rows[1] * 19.0 / cost[1] * 1000 + 0.5));
            }
        }
        return v;
    }

    static String[] sortTable = { "Eec(w)", "Fast(w)", "Easy(w)", "Eec(r)", "Fast(r)", "Easy(r)" };
    static void sort(String[][] v) {
        for (int i, j = 0, _j = j + 1; j < sortTable.length - 1; j++, _j++) {
            i = firstCellIgnoreCase(v, sortTable[j], _j);
            if (i >= 0 && i != _j) {
                String[] t = v[_j];
                v[_j] = v[i];
                v[i] = t;
            }
        }
    }

    static void printTable(String message, String[][] v) {
        int[] lenIndex = new int[v[0].length];
        for (int j = 0, len; j < lenIndex.length; j++) {
            for (String[] ss : v) {
                len = ss != null && j < ss.length && ss[j] != null && !ss[j].startsWith(GROUP_KEY) ? stringWidth(ss[j]) : 0;
                if (len > lenIndex[j]) lenIndex[j] = len;
            }
            lenIndex[j] += (lenIndex[j] & 1) + 2;
        }

        if (message != null && !message.isEmpty()) System.out.println(message);

        for (int j = 0, h = 0, r = 0, len = v.length; j < len; j++, h = 0, r++) {
            String[] x = v[j];
            if (x == null) continue;
            boolean firstRow = r == 0;
            if (firstRow) printLines(lenIndex);
            // Group line
            if (x[0].startsWith(GROUP_KEY)) {
                String groupName = x[0].substring(2);
                printLines(lenIndex);
                System.out.print("|");
                int append = Arrays.stream(lenIndex).sum() + lenIndex.length - stringWidth(groupName) - 1, right = append >> 1, left = append - right;
                for (int c = 0; c < right; c++) System.out.print(' ');
                System.out.print(groupName);
                for (int c = 0; c < left; c++) System.out.print(' ');
                System.out.print('|');
                System.out.println();
                printLines(lenIndex);
                continue;
            }
            System.out.print('|');
            for (String s : x) {
                boolean notNull = s != null;
                int append = lenIndex[h++] - (notNull ? stringWidth(s) : 0);
                if (notNull) {
                    if (firstRow) {
                        int right = append >> 1, left = append - right;
                        for (int c = 0; c < right; c++) System.out.print(' ');
                        System.out.print(s);
                        for (int c = 0; c < left; c++) System.out.print(' ');
                    } else {
                        for (int c = 0, n = append - 1; c < n; c++) System.out.print(' ');
                        System.out.print(s);
                        System.out.print(' ');
                    }
                } else for (int c = 0; c < append; c++) System.out.print(' ');
                System.out.print('|');
            }
            System.out.println();

            if (firstRow) printLines(lenIndex);
        }
        printLines(lenIndex);
    }

    static void printCompare(String[][] v) {
        String[][] vv = {};
        int i = 1, j = i;
        for (; j < v.length; ) {
            for (; j < v.length && (v[j] == null || !v[j][0].startsWith(GROUP_KEY)); j++) ;
            // TODO 多个分组可能导致数据无法对齐的问题
            String[][] _v = groupCompare(v, i, j);
            if (vv.length > 0) _v[0] = v[i - 1];
            String[][] newV = new String[vv.length + _v.length][];
            System.arraycopy(vv, 0, newV, 0, vv.length);
            System.arraycopy(_v, 0, newV, vv.length, _v.length);
            vv = newV;
            i = ++j;
        }

        printTable("性能比较：每秒处理单元格数量比较[A vs B = (A - B) / B]", vv);
    }

    static String[][] groupCompare(String[][] v, int from, int to) {
        Map<String, Integer> r = new LinkedHashMap<>(), w = new LinkedHashMap<>();
        for (int i = from; i < to; i++) {
            String[] x = v[i];
            if (x == null) continue;
            String fn = x[0], a = fn.substring(0, fn.length() - 3);
            if (fn.charAt(fn.length() - 2) == 'r') {
                r.put(a, toInteger(x[x.length - 1]));
            } else {
                w.put(a, toInteger(x[x.length - 1]));
            }
        }

        v = new String[3][];

        int limit = c2(Math.max(w.size(), r.size())) + 1;
        if (!w.isEmpty()) {
            String[] keys = w.keySet().toArray(new String[w.size()]);
            v[0] = new String[limit];
            v[1] = new String[limit];
            v[0][0] = ""; v[1][0] = "Write";
            int k = 1;
            for (int i = 0; i < keys.length - 1; i++) {
                for (int j = i + 1; j < keys.length; j++) {
                    v[0][k] = keys[i] + " vs " + keys[j];
                    v[1][k] = percentage(w.get(keys[i]), w.get(keys[j]));
                    k++;
                }
            }
        }

        if (!r.isEmpty()) {
            String[] keys = r.keySet().toArray(new String[r.size()]);
            if (v[0] != null) {
                v[2] = new String[limit];
                v[2][0] = "Read";
                int a = c2(w.size()) + 1;
                for (int i = 0; i < keys.length - 1; i++) {
                    for (int j = i + 1; j < keys.length; j++) {
                        String t = keys[i] + " vs " + keys[j];
                        int k = indexOf(v[0], t, 1, a);
                        if (k >= 0) v[2][k] = percentage(r.get(keys[i]), r.get(keys[j]));
                        else {
                            v[0][a] = t;
                            v[2][a] = percentage(r.get(keys[i]), r.get(keys[j]));
                            a++;
                        }
                    }
                }
            } else {
                v[0] = new String[limit];
                v[1] = new String[limit];
                v[0][0] = ""; v[1][0] = "Read";
                int k = 1;
                for (int i = 0; i < keys.length - 1; i++) {
                    for (int j = i + 1; j < keys.length; j++) {
                        v[0][k] = keys[i] + " vs " + keys[j];
                        v[1][k] = percentage(r.get(keys[i]), r.get(keys[j]));
                        k++;
                    }
                }
            }
        }
        return v;
    }

    static void printLines(int[] lenIndex) {
        System.out.print('+');
        for (int b = 0; b < lenIndex.length; b++) {
            for (int c = 0; c < lenIndex[b]; c++) System.out.print('-');
            System.out.print("+");
        }
        System.out.println();
    }

    static Integer toInteger(String v) {
        try {
            return Integer.valueOf(v);
        } catch (NumberFormatException e) {
            int len = v.length(), a = v.charAt(len - 1);
            if (a == 'k' || a == 'm') {
                int p = Integer.parseInt(v.substring(0, len - 1));
                return p * (a == 'k' ? 1000 : 10000);
            }
        }
        return null;
    }

    static int c2(int u) {
        return u > 2 ? u * (u - 1) >> 1 : 1;
    }

    static int indexOf(String[] v, String s, int from, int to) {
        if (from < to) {
            for (; from < to; from++) {
                if (s.equals(v[from])) return from;
            }
        }
        return -1;
    }

    static int stringWidth(String v) {
//        int bl = v.getBytes(StandardCharsets.UTF_8).length, cl = v.length();
//        return cl + ((bl - cl + 1) >> 1);
        return v.length();
    }

    static String percentage(Integer v1, Integer v2) {
        if (v1 == null || v2 == null || v2.equals(0)) return "-";
        double v = ((int) (10000.0 * (v1 - v2) / v2 + 0.5)) / 100.0;
        return v + "%" + (v > 0 ? '↑' : '↓');
    }

    static int firstCellIgnoreCase(String[][] array, String v, int from) {
        int i;
        for(i = from; i < array.length; ++i) {
            if (array[i] != null && v.equalsIgnoreCase(array[i][0])) return i;
        }
        return -1;
    }

    static class O {
        String tool, fn;
        int rows, cost;
        // 1: write
        // 2: read
        int rw, shared = 0;

        public String getTool() {
            return tool;
        }

        public String getFn() {
            return fn;
        }

        public int getRows() {
            return rows;
        }

        @Override
        public String toString() {
            return tool + " " + fn + " " + rows + " " + cost + " " + (rw == 1 ? "w" : "r");
        }
    }

    static class A {
        long a, b, c, d;
    }
}