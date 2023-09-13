out_path=./out

if [ ! -d "$out_path" ];then
  mkdir $out_path
fi

javac -encoding utf-8 -d $out_path -cp .:./out:./eec-lib/eec-0.5.11.jar:./easy-lib/easyexcel-core-3.3.2.jar LargeData.java
javac -encoding utf-8 -d $out_path -cp .:./out RandomDataProvider.java
javac -encoding utf-8 -d $out_path -cp .:./out Reporter.java
javac -encoding utf-8 -d $out_path -cp .:./out:./eec-lib/eec-0.5.11.jar Warmup.java

javac -encoding utf-8 -d $out_path -cp .:./out:./eec-lib/eec-0.5.11.jar EecBenchmarkTest.java
javac -encoding utf-8 -d $out_path -cp .:./out:./fast-lib/fastexcel-reader-0.15.6.jar FastBenchmarkTest.java
javac -encoding utf-8 -d $out_path -cp .:./out:./easy-lib/easyexcel-core-3.3.2.jar EasyBenchmarkTest.java

javac -encoding utf-8 -d $out_path -cp .:./out:./eec-lib/eec-0.5.11.jar EecWriteBenchmarkTest.java
javac -encoding utf-8 -d $out_path -cp .:./out:./fast-lib/commons-compress-1.23.0.jar:./fast-lib/stax2-api-4.2.jar:./fast-lib/aalto-xml-1.3.2.jar:./fast-lib/fastexcel-0.15.6.jar FastWriteBenchmarkTest.java
javac -encoding utf-8 -d $out_path -cp .:./out:./easy-lib/easyexcel-core-3.3.2.jar EasyWriteBenchmarkTest.java
