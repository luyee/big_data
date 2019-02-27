package com.caiw.eelhive

import io.eels.Predicate
import io.eels.component.hive.HiveSource
import io.eels.schema.PartitionConstraint
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.hive.conf.HiveConf
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient

object EelHiveSelect {
  def main(args: Array[String]): Unit = {
      implicit val conf: Configuration = new Configuration()
      implicit val hadoopFileSystem: FileSystem = FileSystem.get(new Configuration())
      implicit val hiveMetaStoreClient: HiveMetaStoreClient = new HiveMetaStoreClient(new HiveConf())

      val hiveDs = HiveSource("dsgdata","person")

      hiveDs
        //添加条件
        .withPredicate(Predicate.or(Predicate.equals("name", "Fred"), Predicate.equals("name", "Alice")))
        .withPartitionConstraint(PartitionConstraint.equals("title","Mr"))
        .toDataStream
        .collect
        .foreach(println(_))


       hiveMetaStoreClient.close()
       hadoopFileSystem.close()
  }
}
