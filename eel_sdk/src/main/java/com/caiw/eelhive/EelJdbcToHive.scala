package com.caiw.eelhive

import io.eels.Predicate
import io.eels.component.avro.AvroSource
import io.eels.component.hive.{HiveDDL, HiveSink, HiveSource}
import io.eels.component.jdbc.JdbcSource
import io.eels.schema.PartitionConstraint
import javax.sql.DataSource
import org.apache.commons.dbcp.BasicDataSource
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.hive.conf.HiveConf
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient



object EelJdbcToHive {
  def main(args: Array[String]): Unit = {
    implicit val conf: Configuration = new Configuration()
    implicit val hadoopFileSystem: FileSystem = FileSystem.get(new Configuration())
    implicit val hiveMetaStoreClient: HiveMetaStoreClient = new HiveMetaStoreClient(new HiveConf())

    val dataSource = new BasicDataSource()
    dataSource.setDriverClassName("com.mysql.jdbc.Driver")
    dataSource.setUrl("jdbc:mysql://192.168.23.120:3306/test")
    dataSource.setUsername("root")
    dataSource.setPassword("mysql123")
    dataSource.setPoolPreparedStatements(false)
    dataSource.setInitialSize(5)

    JdbcSource(() => dataSource.getConnection, "select * from person")
      .withFetchSize(10)
      .toDataStream
      .withLowerCaseSchema
      // Transformation - add title to row
      .map { row =>
      if (row.get("name").toString == "Alice") row.add("title", "Mrs") else row.add("title", "Mr")
      }
      .to(HiveSink("dsgdata", "person").withInheritPermission(true))



    hiveMetaStoreClient.close()
    hadoopFileSystem.close()
  }

}
