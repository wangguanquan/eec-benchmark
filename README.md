### 编译测试实体
```text
javac -cp .;./eec-lib/eec-0.5.11-SNAPSHOT.jar;./easy-lib/easyexcel-core-3.3.2.jar LargeData.java
javac -encoding utf-8 RandomDataProvider.java
```

### 限制运行内存
运行java 命令添加参数 `-Xmx5m -Xms5m`

### 极限运行内存

     Tool | Memory
----------|--------
EEC       | 5M
FAST EXCEL| 5M
EASY EXCEL| 7M

### 编译 & 运行

## Reader 测试
### xlsx格式

================================ EEC ================================

编译 EecBenchmarkTest 测试类
```text
javac -encoding utf-8 -cp .;./eec-lib/eec-0.5.11-SNAPSHOT.jar EecBenchmarkTest.java
```

运行 EecBenchmarkTest 测试类
```text
java -cp .;./eec-lib/dom4j-2.1.3.jar;./eec-lib/slf4j-api-1.7.32.jar;./eec-lib/eec-0.5.11-SNAPSHOT.jar EecBenchmarkTest
```

============================= FAST EXCEL =============================


编译 FastBenchmarkTest 测试类
```text
javac -encoding utf-8 -cp .;./fast-lib/fastexcel-reader-0.15.6.jar FastBenchmarkTest.java
```

运行 FastBenchmarkTest 测试类
```text
java -cp .;./fast-lib/commons-compress-1.23.0.jar;./fast-lib/stax2-api-4.2.jar;./fast-lib/aalto-xml-1.3.2.jar;./fast-lib/fastexcel-reader-0.15.6.jar FastBenchmarkTest
```

============================= EASY EXCEL =============================


编译 EasyBenchmarkTest 测试类
```text
javac -encoding utf-8 -cp .;./easy-lib/easyexcel-core-3.3.2.jar EasyBenchmarkTest.java
```

运行 EasyBenchmarkTest 测试类
```text
java -cp .;./easy-lib/commons-codec-1.13.jar;./easy-lib/commons-collections4-4.4.jar;./easy-lib/commons-compress-1.19.jar;./easy-lib/commons-csv-1.8.jar;./easy-lib/commons-io-2.11.0.jar;./easy-lib/commons-math3-3.6.1.jar;./easy-lib/curvesapi-1.06.jar;./easy-lib/easyexcel-3.3.2.jar;./easy-lib/easyexcel-core-3.3.2.jar;./easy-lib/easyexcel-support-3.3.2.jar;./easy-lib/ehcache-3.9.9.jar;./easy-lib/poi-4.1.2.jar;./easy-lib/poi-ooxml-4.1.2.jar;./easy-lib/poi-ooxml-schemas-4.1.2.jar;./easy-lib/SparseBitSet-1.2.jar;./easy-lib/xmlbeans-3.1.0.jar;./easy-lib/slf4j-api-1.7.32.jar EasyBenchmarkTest
```

## Writer测试

================================ EEC ================================

编译 EecWriteBenchmarkTest 测试类
```text
javac -encoding utf-8 -cp .;./eec-lib/eec-0.5.11-SNAPSHOT.jar EecWriteBenchmarkTest.java
```

运行 EecWriteBenchmarkTest 测试类
```text
java -cp .;./eec-lib/dom4j-2.1.3.jar;./eec-lib/slf4j-api-1.7.32.jar;./eec-lib/eec-0.5.11-SNAPSHOT.jar EecWriteBenchmarkTest
```


============================= FAST EXCEL =============================

编译 FastWriteBenchmarkTest 测试类
```text
javac -encoding utf-8 -cp .;./fast-lib/commons-compress-1.23.0.jar;./fast-lib/stax2-api-4.2.jar;./fast-lib/aalto-xml-1.3.2.jar;./fast-lib/fastexcel-0.15.6.jar FastWriteBenchmarkTest.java
```

运行 FastWriteBenchmarkTest 测试类
```text
java -cp .;./fast-lib/commons-compress-1.23.0.jar;./fast-lib/stax2-api-4.2.jar;./fast-lib/aalto-xml-1.3.2.jar;./fast-lib/fastexcel-0.15.6.jar;./fast-lib/opczip-1.2.0.jar FastWriteBenchmarkTest
```

============================= EASY EXCEL =============================

编译 EasyWriteBenchmarkTest 测试类
```text
javac -encoding utf-8 -cp .;./easy-lib/easyexcel-core-3.3.2.jar EasyWriteBenchmarkTest.java
```

运行 EasyWriteBenchmarkTest 测试类
```text
java -cp .;./easy-lib/commons-codec-1.13.jar;./easy-lib/commons-collections4-4.4.jar;./easy-lib/commons-compress-1.19.jar;./easy-lib/commons-csv-1.8.jar;./easy-lib/commons-io-2.11.0.jar;./easy-lib/commons-math3-3.6.1.jar;./easy-lib/curvesapi-1.06.jar;./easy-lib/easyexcel-3.3.2.jar;./easy-lib/easyexcel-core-3.3.2.jar;./easy-lib/easyexcel-support-3.3.2.jar;./easy-lib/ehcache-3.9.9.jar;./easy-lib/poi-4.1.2.jar;./easy-lib/poi-ooxml-4.1.2.jar;./easy-lib/poi-ooxml-schemas-4.1.2.jar;./easy-lib/SparseBitSet-1.2.jar;./easy-lib/xmlbeans-3.1.0.jar;./easy-lib/slf4j-api-1.7.32.jar EasyWriteBenchmarkTest
```

## 说明

Fast使用`SharedString`模式，该模式速度不及`innerStr`模式，所以测试得到的Writer速度并不能说明fastexcel写性能不及前两者。EEC也支持`SharedString`模式，只需要在LargeData.java的ExcelColumn注解后面加上`share=true`即可，实际生产环境可以在一些枚举值上开启share可以启到压缩数据的作用。这里的枚举值可以是省市区，状态值，男、女等固定范围的值