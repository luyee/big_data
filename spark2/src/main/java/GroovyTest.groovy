/**
 * @Author: caiwei* @Description:
 * @Date: create in 2019/4/25 11:55 
 */
class GroovyTest {
    static void main(String[] args) {
        def fieldStructure = "ysmc|string||0|0|0|ysmc\n" +
                "yymc|string||0|0|0|yymc\n" +
                "id|string||0|0|0|id"
        def dbName = "default"
        def tableName = "cw_test001"
        def feedts = System.currentTimeMillis()
        def fields = ","
        def fieldLines = fieldStructure.split("\n")
        def dataTransformScript = "import org.apache.spark.sql._\n" +
                "var df = sqlContext.sql(\"SELECT tbl10.`sczh`, tbl10.`xb`, tbl10.`ysmc`, tbl10.`zy`, tbl10.`yymc`, tbl10.`id`  FROM  `test_201904161623`.`5f8ab6cfb7ff06e26f3db5f0a84442b5` tbl10 \")\n" +
                "\n" +
                "df"
        for(String line : fieldLines){
            def fieldInfo = line.split("\\|")
            fields = fields + ",\"" +fieldInfo[0] + "\""
        }
        fields = fields.replaceAll(",,","")
        // Write script
        def script = "sqlContext.setConf(\"hive.exec.dynamic.partition\", \"true\")\n"
        script = script + "sqlContext.setConf(\"hive.exec.dynamic.partition.mode\", \"nonstrict\")\n"
        script = script + dataTransformScript
        script = script + ".select("+fields+")"
        script = script + ".withColumn(\"processing_dttm\", org.apache.spark.sql.functions.lit(\"" + feedts + "\"))"
        script = script + ".write.mode(SaveMode.Overwrite)"
        script = script + ".insertInto(\"" + dbName + "." + tableName +  "_feed" + "\")"

        script = script.replaceAll("\n\n","\n")
        println(script)
    }
}
