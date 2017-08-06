Run the topology in Eclipse environment.

或者命令行运行：

[storm@data-srv001 ~]$ java -cp /data/opt/storm/lib/*:kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT.jar WordCountTopology


[user001@data-srv001 ~]$  storm list

1935 [main] INFO  o.a.s.u.NimbusClient - Found leader nimbus : data-srv001:6627
Topology_name        Status     Num_tasks  Num_workers  Uptime_secs
-------------------------------------------------------------------
production-topology  ACTIVE     15         1            518976    
WordCountTopology    ACTIVE     7          2            230922    
[user001@data-srv001 ~]$ storm kill WordCountTopology

1742 [main] INFO  o.a.s.u.NimbusClient - Found leader nimbus : data-srv001:6627
1791 [main] INFO  o.a.s.c.kill-topology - Killed topology: WordCountTopology

提交topology
[user001@data-srv001 ~]$ storm jar kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT.jar WordCountTopology  WordCountTopology
Running: /data/opt/jdk1.8.0_141/bin/java -client -Ddaemon.name= -Dstorm.options= -Dstorm.home=/data/opt/storm -Dstorm.log.dir=/data/opt/storm/logs -Djava.library.path=/usr/local/lib:/opt/local/lib:/usr/lib -Dstorm.conf.file= -cp /data/opt/storm/lib/storm-core-1.1.0.jar:/data/opt/storm/lib/storm-rename-hack-1.1.0.jar:/data/opt/storm/lib/slf4j-api-1.7.21.jar:/data/opt/storm/lib/log4j-slf4j-impl-2.8.jar:/data/opt/storm/lib/kryo-3.0.3.jar:/data/opt/storm/lib/reflectasm-1.10.1.jar:/data/opt/storm/lib/log4j-core-2.8.jar:/data/opt/storm/lib/asm-5.0.3.jar:/data/opt/storm/lib/objenesis-2.1.jar:/data/opt/storm/lib/disruptor-3.3.2.jar:/data/opt/storm/lib/ring-cors-0.1.5.jar:/data/opt/storm/lib/clojure-1.7.0.jar:/data/opt/storm/lib/log4j-api-2.8.jar:/data/opt/storm/lib/servlet-api-2.5.jar:/data/opt/storm/lib/minlog-1.3.0.jar:/data/opt/storm/lib/log4j-over-slf4j-1.6.6.jar:kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT.jar:/data/opt/storm/conf:/data/opt/storm/bin -Dstorm.jar=kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT.jar -Dstorm.dependency.jars= -Dstorm.dependency.artifacts={} WordCountTopology WordCountTopology
510  [main] INFO  o.a.s.StormSubmitter - Generated ZooKeeper secret payload for MD5-digest: -6385771312314459096:-5062431522820353191
586  [main] INFO  o.a.s.u.NimbusClient - Found leader nimbus : data-srv001:6627
600  [main] INFO  o.a.s.s.a.AuthUtils - Got AutoCreds []
602  [main] INFO  o.a.s.u.NimbusClient - Found leader nimbus : data-srv001:6627
615  [main] INFO  o.a.s.StormSubmitter - Uploading dependencies - jars...
616  [main] INFO  o.a.s.StormSubmitter - Uploading dependencies - artifacts...
616  [main] INFO  o.a.s.StormSubmitter - Dependency Blob keys - jars : [] / artifacts : []
625  [main] INFO  o.a.s.StormSubmitter - Uploading topology jar kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT.jar to assigned location: /data/opt/storm/storm-local/nimbus/inbox/stormjar-31dd9202-7d31-48d6-8f4f-49c6867a674a.jar
642  [main] INFO  o.a.s.StormSubmitter - Successfully uploaded topology jar to assigned location: /data/opt/storm/storm-local/nimbus/inbox/stormjar-31dd9202-7d31-48d6-8f4f-49c6867a674a.jar
642  [main] INFO  o.a.s.StormSubmitter - Submitting topology WordCountTopology in distributed mode with conf {"storm.zookeeper.topology.auth.scheme":"digest","storm.zookeeper.topology.auth.payload":"-6385771312314459096:-5062431522820353191","topology.workers":2,"topology.debug":false}
736  [main] INFO  o.a.s.StormSubmitter - Finished submitting topology: WordCountTopology

在每台服务器上启动logviewer

[storm@data-srv003 storm]$ storm logviewer &
[1] 5970
[storm@data-srv003 storm]$ ps -ef|grep 5790Running: /opt/jdk1.8.0_141/bin/java -server -Ddaemon.name=logviewer -Dstorm.options= -Dstorm.home=/data/opt/storm -Dstorm.log.dir=/data/opt/storm/logs -Djava.library.path=/usr/local/lib:/opt/local/lib:/usr/lib -Dstorm.conf.file= -cp /data/opt/storm/lib/log4j-api-2.8.jar:/data/opt/storm/lib/log4j-slf4j-impl-2.8.jar:/data/opt/storm/lib/ring-cors-0.1.5.jar:/data/opt/storm/lib/storm-core-1.1.0.jar:/data/opt/storm/lib/slf4j-api-1.7.21.jar:/data/opt/storm/lib/objenesis-2.1.jar:/data/opt/storm/lib/asm-5.0.3.jar:/data/opt/storm/lib/storm-rename-hack-1.1.0.jar:/data/opt/storm/lib/disruptor-3.3.2.jar:/data/opt/storm/lib/kryo-3.0.3.jar:/data/opt/storm/lib/reflectasm-1.10.1.jar:/data/opt/storm/lib/log4j-over-slf4j-1.6.6.jar:/data/opt/storm/lib/minlog-1.3.0.jar:/data/opt/storm/lib/servlet-api-2.5.jar:/data/opt/storm/lib/clojure-1.7.0.jar:/data/opt/storm/lib/log4j-core-2.8.jar:/data/opt/storm:/data/opt/storm/conf -Xmx128m -Dlogfile.name=logviewer.log -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector -Dlog4j.configurationFile=/data/opt/storm/log4j2/cluster.xml org.apache.storm.daemon.logviewer

由于是在cleanup方法中输出，所以需要先杀掉job


增加storm-hbase集成后，报错。

[storm@data-srv001 ~]$ java -cp /data/opt/storm/lib/*:kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT.jar WordCountTopology  
Error: A JNI error has occurred, please check your installation and try again
Exception in thread "main" java.lang.NoClassDefFoundError: org/apache/storm/hbase/bolt/mapper/HBaseMapper
	at java.lang.Class.getDeclaredMethods0(Native Method)
	at java.lang.Class.privateGetDeclaredMethods(Class.java:2701)
	at java.lang.Class.privateGetMethodRecursive(Class.java:3048)
	at java.lang.Class.getMethod0(Class.java:3018)
	at java.lang.Class.getMethod(Class.java:1784)
	at sun.launcher.LauncherHelper.validateMainClass(LauncherHelper.java:544)
	at sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:526)
Caused by: java.lang.ClassNotFoundException: org.apache.storm.hbase.bolt.mapper.HBaseMapper
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:335)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	... 7 more

原因：storm-hbase-1.1.0.jar没有打包到jar文件中。

/Users/myang/.m2/repository/org/apache/storm/storm-hbase/1.1.0
localhost:1.1.0 myang$ ls
storm-hbase-1.1.0.jar

解决方案：修改pom.xml，把依赖文件打包

Apache Maven Assembly Plugin

A Maven plugin to create archives of your project's sources, classes, dependencies etc. from flexible assembly descriptors. 

			<plugin>
			    <groupId>org.apache.maven.plugins</groupId>
    			<artifactId>maven-shade-plugin</artifactId>
			</plugin>



6904 [Thread-20-hbase-bolt-executor[3 3]] ERROR o.a.s.util - Async loop died!
java.lang.IllegalArgumentException: HBase configuration not found using key 'hbase.conf'
	at org.apache.storm.hbase.bolt.AbstractHBaseBolt.prepare(AbstractHBaseBolt.java:61) ~[kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at org.apache.storm.hbase.bolt.HBaseBolt.prepare(HBaseBolt.java:109) ~[kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at org.apache.storm.daemon.executor$fn__5044$fn__5057.invoke(executor.clj:791) ~[kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at org.apache.storm.util$async_loop$fn__557.invoke(util.clj:482) [kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at clojure.lang.AFn.run(AFn.java:22) [kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at java.lang.Thread.run(Thread.java:748) [?:1.8.0_141]
6905 [Thread-20-hbase-bolt-executor[3 3]] ERROR o.a.s.d.executor - 
java.lang.IllegalArgumentException: HBase configuration not found using key 'hbase.conf'
	at org.apache.storm.hbase.bolt.AbstractHBaseBolt.prepare(AbstractHBaseBolt.java:61) ~[kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at org.apache.storm.hbase.bolt.HBaseBolt.prepare(HBaseBolt.java:109) ~[kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at org.apache.storm.daemon.executor$fn__5044$fn__5057.invoke(executor.clj:791) ~[kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at org.apache.storm.util$async_loop$fn__557.invoke(util.clj:482) [kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at clojure.lang.AFn.run(AFn.java:22) [kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at java.lang.Thread.run(Thread.java:748) [?:1.8.0_141]
6923 [Thread-20-hbase-bolt-executor[3 3]] ERROR o.a.s.util - Halting process: ("Worker died")
java.lang.RuntimeException: ("Worker died")
	at org.apache.storm.util$exit_process_BANG_.doInvoke(util.clj:341) [kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at clojure.lang.RestFn.invoke(RestFn.java:423) [kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at org.apache.storm.daemon.worker$fn__5642$fn__5643.invoke(worker.clj:759) [kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at org.apache.storm.daemon.executor$mk_executor_data$fn__4863$fn__4864.invoke(executor.clj:274) [kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at org.apache.storm.util$async_loop$fn__557.invoke(util.clj:494) [kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at clojure.lang.AFn.run(AFn.java:22) [kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:?]
	at java.lang.Thread.run(Thread.java:748) [?:1.8.0_141]
	
	
	[hbase@data-srv001 ~]$ storm jar kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar WordCountTopology  WordCountTopologyHBase
Running: /data/opt/jdk1.8.0_141/bin/java -client -Ddaemon.name= -Dstorm.options= -Dstorm.home=/data/opt/storm -Dstorm.log.dir=/data/opt/storm/logs -Djava.library.path=/usr/local/lib:/opt/local/lib:/usr/lib -Dstorm.conf.file= -cp /data/opt/storm/lib/storm-core-1.1.0.jar:/data/opt/storm/lib/storm-rename-hack-1.1.0.jar:/data/opt/storm/lib/slf4j-api-1.7.21.jar:/data/opt/storm/lib/log4j-slf4j-impl-2.8.jar:/data/opt/storm/lib/kryo-3.0.3.jar:/data/opt/storm/lib/reflectasm-1.10.1.jar:/data/opt/storm/lib/log4j-core-2.8.jar:/data/opt/storm/lib/asm-5.0.3.jar:/data/opt/storm/lib/objenesis-2.1.jar:/data/opt/storm/lib/disruptor-3.3.2.jar:/data/opt/storm/lib/ring-cors-0.1.5.jar:/data/opt/storm/lib/clojure-1.7.0.jar:/data/opt/storm/lib/log4j-api-2.8.jar:/data/opt/storm/lib/servlet-api-2.5.jar:/data/opt/storm/lib/minlog-1.3.0.jar:/data/opt/storm/lib/log4j-over-slf4j-1.6.6.jar:kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar:/data/opt/storm/conf:/data/opt/storm/bin -Dstorm.jar=kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar -Dstorm.dependency.jars= -Dstorm.dependency.artifacts={} WordCountTopology WordCountTopologyHBase
471  [main] INFO  o.a.s.u.TupleUtils - Enabling tick tuple with interval [1]
589  [main] INFO  o.a.s.StormSubmitter - Generated ZooKeeper secret payload for MD5-digest: -6991586069651436967:-7389921609188376431
669  [main] INFO  o.a.s.u.NimbusClient - Found leader nimbus : data-srv001:6627
687  [main] INFO  o.a.s.s.a.AuthUtils - Got AutoCreds []
690  [main] INFO  o.a.s.u.NimbusClient - Found leader nimbus : data-srv001:6627
706  [main] INFO  o.a.s.StormSubmitter - Uploading dependencies - jars...
707  [main] INFO  o.a.s.StormSubmitter - Uploading dependencies - artifacts...
707  [main] INFO  o.a.s.StormSubmitter - Dependency Blob keys - jars : [] / artifacts : []
716  [main] INFO  o.a.s.StormSubmitter - Uploading topology jar kafka-storm-hbase-hdfs-0.0.1-SNAPSHOT-jar-with-dependencies.jar to assigned location: /data/opt/storm/storm-local/nimbus/inbox/stormjar-9c20b80f-27a4-46c6-9aab-468995370869.jar
1078 [main] INFO  o.a.s.StormSubmitter - Successfully uploaded topology jar to assigned location: /data/opt/storm/storm-local/nimbus/inbox/stormjar-9c20b80f-27a4-46c6-9aab-468995370869.jar
1078 [main] INFO  o.a.s.StormSubmitter - Submitting topology WordCountTopologyHBase in distributed mode with conf {"storm.zookeeper.topology.auth.scheme":"digest","storm.zookeeper.topology.auth.payload":"-6991586069651436967:-7389921609188376431","topology.workers":6,"hbase.conf":{"hbase.rootdir":"hdfs:\/\/data-srv001:8020\/hbase"},"topology.debug":false}
1284 [main] INFO  o.a.s.StormSubmitter - Finished submitting topology: WordCountTopologyHBase



