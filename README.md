## 介绍

本项目用于Eec性能测试，分别将以1k, 5k, 10k, 50k, 100k, 500k, 1000k 7个数量级进行读写测试

除 [Eec](https://github.com/wangguanquan/eec) 外还加入了 [Fastexcel](https://github.com/dhatim/fastexcel) 和 [Easyexcel](https://github.com/alibaba/easyexcel) 进行对比测试

测试包均为当前最新版本(2023-09)

- eec: 0.5.11
- fastexcel: 0.15.6
- easyexcel: 3.3.2

测试文件统一保存在excel-simple目录下，请不要随意改变日志输出格式，否则将导致Reporter运行时异常。

### 运行时必要依赖包比较

只比较运行时必要依赖，所有依赖包均放到各自*-lib下可自行比较

```
+------+------+--------+
| Eec  | Fast |  Easy  |
+------+------+--------+
|  <1m | 1.7m |  21.5m |
+------+------+--------+
```

### 编译

执行build脚本

### Writer比较

执行writer脚本，其中warmup是一个预热方法，统计时将被忽略。如果对某个工具感兴趣也可以复制脚本中的某行来执行

测试代码均使用`inlineStr`模式，测试数据包含int, long, date和15个字符串，其中有10列包含中文字符

### Reader比较

执行reader脚本，将行数据转对象统计行数，这里并不会收集对象放到内存，所以不影响内存使用。

### 一些测试统计结果

执行完测试后使用report脚本查看统计结果，性能比较是按每秒处理多少单元格来计算，单元格是excel最小单位用它计算比单纯按行比较更精准

```
平均耗时：读写总耗时/读写次数
Cells/s： 平均每秒处理多少单元格
+----------+------+------+------+------+--------+--------+--------+----------+
|  TOOLS   |  1k  |  5k  | 10k  | 50k  |  100k  |  500k  | 1000k  | Cells/s  |
+----------+------+------+------+------+--------+--------+--------+----------+
|   Eec(w) |  177 |  366 |  692 | 3342 |   6597 |  47136 |  83332 |   223479 |
|  Fast(w) |  255 |  836 | 1379 | 5892 |  11915 |  74304 | 122643 |   145721 |
|  Easy(w) |  917 | 1573 | 1776 | 8774 |  14679 |  73038 | 139568 |   131713 |
|   Eec(r) |   68 |  108 |  203 | 1107 |   2320 |  13708 |  26112 |   725571 |
|  Fast(r) |  132 |  349 |  400 | 1670 |   3322 |  15829 |  32405 |   585046 |
|  Easy(r) |  332 |  469 |  647 | 2663 |   5244 |  30218 |  52716 |   342985 |
+----------+------+------+------+------+--------+--------+--------+----------+

性能比较：每秒处理单元格数量比较[A vs B = (A - B) / B]
+--------+--------------+--------------+--------------+
|        | Eec vs Fast  | Eec vs Easy  | Fast vs Easy |
+--------+--------------+--------------+--------------+
|  Write |      53.36%↑ |      69.67%↑ |      10.64%↑ |
|   Read |      24.02%↑ |     111.55%↑ |      70.57%↑ |
+--------+--------------+--------------+--------------+
```

### 极限运行内存

运行java 命令添加参数 `-Xmx10m -Xms10m`

innerStr模式下使用内存都很低都可以在10M以内完成测试

```
+----------+------+------+------+------+------+--------+--------+
|  TOOLS   |  1k  |  5k  | 10k  | 50k  | 100k |  500k  | 1000k  |
+----------+------+------+------+------+------+--------+--------+
|   Eec(w) |   6m |   6m |   6m |  10m |  10m |    10m |    10m |
|  Fast(w) |  32M |  32M |  32M | 128M | 350m |  1500m |  3500m |
|  Easy(w) |  10m |  16m |  16m |  16m |  16m |    16m |    16m |
|   Eec(r) |   6m |   6m |   6m |   6m |   6m |     6m |     6m |
|  Fast(r) |   6m |   6m |   6m |   6m |   6m |     6m |     6m |
|  Easy(r) |   6m |   6m |   6m |   6m |   6m |     6m |     7m |
+----------+------+------+------+------+------+--------+--------+
```

SharedStrings模式下FastExcel读取占用内存最多，EEC和Easyexcel均可以在10M以内完成测试

| Rows | Eec | Fast | Easy |
|-----:|:---:|:---------:|:---------:|
| 1w   | 5M  | 32M       | 7M        |
| 5w   | 5M  | 28M       | 7M        |
| 10w  | 5M  | 256M      | 7M        |
| 50w  | 5M  | 800M      | 7M        |
| 100w | 5M  | 1500M     | 7M        |

## 说明

1. 运行Reader测试之前请先执行Writer生成测试文件
2. Reader测试类需要指定同一个工具生产的文件，由于每个工具写文件的结果不太相同，所以在测试读的时候需要指定同一工具生产的文件
