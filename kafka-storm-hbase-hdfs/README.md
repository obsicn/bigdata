Run the topology in Eclipse environment.

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



