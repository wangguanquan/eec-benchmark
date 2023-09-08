## 介绍

本项目用于EEC性能测试，分别将以1k, 5k, 10k, 50k, 100k, 500k, 1000k 7个数量级进行读写测试

除EEC外还加入了[Fastexcel](https://github.com/dhatim/fastexcel) 和[Easyexcel](https://github.com/alibaba/easyexcel) 进行对比测试

测试文件统一保存在excel-simple目录下

### 运行时必要依赖包比较

只比较运行时必要依赖，所有依赖包均放到各自*-lib下可自行比较

| EEC | FastExcel | Easyexcel |
|:---:|:---------:|:---------:|
| <1M | 1.7M      | 21.5M     |

### 编译

执行build脚本

### Writer比较

执行writer脚本，如果对某个工具感兴趣也可以复制脚本中的某行来执行

测试代码均使用`inlineStr`模式，测试数据包含int, long, date和15个字符串，其中有10列包含中文字符

### Reader比较

执行Reader脚本，如果对某个工具感兴趣也可以复制脚本中的某行来执行

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
