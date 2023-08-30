## 介绍

本项目用于EEC性能测试，分别将以1w, 5w, 10w, 50w, 100w5个数量级进行读写测试，如需测试xls只需要将`eec-e3-support`添加进class path即可。

除EEC外还加入了[Fastexcel](https://github.com/dhatim/fastexcel)和[Easyexcel](https://github.com/alibaba/easyexcel)进行对比测试

### 运行时必要依赖比较

只比较运行时必要依赖，所有依赖包均放到各自*-lib下可自行比较

| EEC | FastExcel | Easyexcel |
|:---:|:---------:|:---------:|
| <1M | 1.7M      | 21.5M     |

### 编译运行

windows平台使用命令行运行`run`，*unx平台命令行运行`sh ./run.sh`，也可以单独运行`writer`和`reader`测试读写

### 一些测试结果


### 极限运行内存

运行java 命令添加参数 `-Xmx5m -Xms5m`

innerStr模式下使用内存都很低都可以在10M以内完成测试

| EEC | FastExcel | Easyexcel |
|:---:|:---------:|:---------:|
| 5M  | 5M        | 7M        |

SharedStrings模式下FastExcel读取最快但占用内存最多，EEC和Easyexcel均可以在10M以内完成测试，其中EEC需要传入参数`ExcelReader.read(path, 64, 64, 0)`指定批量大小为64

| Rows | EEC | FastExcel | Easyexcel |
|-----:|:---:|:---------:|:---------:|
| 1w   | 5M  | 32M       | 7M        |
| 5w   | 5M  | 28M       | 7M        |
| 10w  | 5M  | 256M      | 7M        |
| 50w  | 5M  | 800M      | 7M        |
| 100w | 5M  | 1500M     | 7M        |

## 说明

1. 运行Reader测试之前请先执行Writer生成测试文件
2. Reader测试类需要指定同一个工具生产的文件，由于每个工具写文件的结果不太相同，所以在测试读的时候需要指定同一工具生产的文件
3. Fast使用`SharedStrings`模式，该模式速度不及`innerStr`模式，所以测试得到的Writer速度并不能说明fastexcel写性能不及前两者。EEC也支持`SharedString`模式，只需要在LargeData.java的ExcelColumn注解后面加上`share=true`即可，实际生产环境可以在一些枚举值上开启share可以启到压缩数据的作用。这里的枚举值可以是省市区，状态值，男、女等固定范围的值
