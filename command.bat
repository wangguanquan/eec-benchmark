javac -cp .;./eec-lib/eec-0.5.11-SNAPSHOT.jar;./easy-lib/easyexcel-core-3.3.2.jar LargeData.java
javac -encoding utf-8 RandomDataProvider.java

javac -encoding utf-8 -cp .;./eec-lib/eec-0.5.11-SNAPSHOT.jar EecBenchmarkTest.java
javac -encoding utf-8 -cp .;./fast-lib/fastexcel-reader-0.15.6.jar FastBenchmarkTest.java
javac -encoding utf-8 -cp .;./easy-lib/easyexcel-core-3.3.2.jar EasyBenchmarkTest.java

@rem for /l %%i in (1,1,5) do inner1

@rem do reader
reader