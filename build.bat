javac -encoding utf-8 -cp .;./eec-lib/eec-0.5.14-SNAPSHOT.jar;./easy-lib/easyexcel-core-3.3.2.jar LargeData.java
javac -encoding utf-8 RandomDataProvider.java
javac -encoding utf-8 Reporter.java

javac -encoding utf-8 -cp .;./eec-lib/eec-0.5.14-SNAPSHOT.jar EecBenchmarkTest.java
javac -encoding utf-8 -cp .;./fast-lib/fastexcel-reader-0.15.6.jar FastBenchmarkTest.java
javac -encoding utf-8 -cp .;./easy-lib/easyexcel-core-3.3.2.jar EasyBenchmarkTest.java

javac -encoding utf-8 -cp .;./eec-lib/eec-0.5.14-SNAPSHOT.jar EecWriteBenchmarkTest.java
javac -encoding utf-8 -cp .;./fast-lib/commons-compress-1.23.0.jar;./fast-lib/stax2-api-4.2.jar;./fast-lib/aalto-xml-1.3.2.jar;./fast-lib/fastexcel-0.15.6.jar FastWriteBenchmarkTest.java
javac -encoding utf-8 -cp .;./easy-lib/easyexcel-core-3.3.2.jar EasyWriteBenchmarkTest.java