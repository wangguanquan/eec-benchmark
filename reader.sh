#!/usr/bin/env sh

java -cp .:./eec-lib/dom4j-2.1.3.jar:./eec-lib/slf4j-api-1.7.32.jar:./eec-lib/eec-0.5.11-SNAPSHOT.jar EecBenchmarkTest
java -cp .:./fast-lib/commons-compress-1.23.0.jar:./fast-lib/stax2-api-4.2.jar:./fast-lib/aalto-xml-1.3.2.jar:./fast-lib/fastexcel-reader-0.15.6.jar FastBenchmarkTest
java -cp .:./easy-lib/commons-codec-1.13.jar:./easy-lib/commons-collections4-4.4.jar:./easy-lib/commons-compress-1.19.jar:./easy-lib/commons-csv-1.8.jar:./easy-lib/commons-io-2.11.0.jar:./easy-lib/commons-math3-3.6.1.jar:./easy-lib/curvesapi-1.06.jar:./easy-lib/easyexcel-3.3.2.jar:./easy-lib/easyexcel-core-3.3.2.jar:./easy-lib/easyexcel-support-3.3.2.jar:./easy-lib/ehcache-3.9.9.jar:./easy-lib/poi-4.1.2.jar:./easy-lib/poi-ooxml-4.1.2.jar:./easy-lib/poi-ooxml-schemas-4.1.2.jar:./easy-lib/SparseBitSet-1.2.jar:./easy-lib/xmlbeans-3.1.0.jar:./easy-lib/slf4j-api-1.7.32.jar EasyBenchmarkTest
