//package com.caiw.param;
//
//import com.beust.jcommander.JCommander;
//import com.dsg.util.LogFormatter;
//import com.dsg.util.Utility;
//import com.dsg.vmmanage.ResInfo;
//import org.apache.log4j.*;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Properties;
//import java.util.logging.FileHandler;
//import java.util.logging.Level;
//
///**
// * Hello world!
// *
// */
//public class Main {
//	private static Logger log = Logger.getLogger(Main.class.getClass());
//	/*static {
//		Properties props = null;
//		FileInputStream fis = null;
//		try {
//			// 从配置文件dbinfo.properties中读取配置信息
//			props = new Properties();
//			fis = new FileInputStream("/mnt/IDE/workspace/log4j.properties");
//			props.load(fis);
//			props.setProperty("log4j.appender.File.File","E:\\log\\testlog24444442.log");
//			PropertyConfigurator.configure(props);//装入log4j配置信息
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (fis != null)
//				try {
//					fis.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			fis = null;
//		}
//	}*/
//	public static void main(String[] args) {
//		/* 解析参数 */
////		ResInfo resinfo = new ResInfo();
//		Cli cli = new Cli();
//		try {
//			JCommander jCommander = JCommander.newBuilder().addObject(cli).build();
//			jCommander.parse(args);
//			Properties props = null;
//			FileInputStream fis = null;
//			try {
//				// 从配置文件dbinfo.properties中读取配置信息
//				props = new Properties();
//				fis = new FileInputStream("/mnt/IDE/workspace/log4j.properties");
////				fis = new FileInputStream("E:\\work\\log4j.properties");
//				props.load(fis);
//				props.setProperty("log4j.appender.File.File",cli.getBkpath()+"/"+cli.getBknum()+"/"+cli.getVmid()+cli.getMod()+".log");
////				props.setProperty("log4j.appender.File.File",cli.getBkpath()+"\\"+cli.getBknum()+"\\"+cli.getVmid()+cli.getMod()+".log");
//				System.out.print(cli.getBkpath()+"/"+cli.getVmid()+cli.getMod()+".log");
//				PropertyConfigurator.configure(props);//装入log4j配置信息
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				if (fis != null)
//					try {
//						fis.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				fis = null;
//			}
//			if (cli.isHelp()) {
//				log.info("设置jar包名为  java -jar virtbackup.jar");
//				jCommander.setProgramName("java -jar virtbackup.jar");
//				jCommander.usage();
//			}
//			resinfo.setResid(cli.getResid());
//			resinfo.setStarttime(null);
//			resinfo.setBackupres(1);
//			Operation opt = new Operation();
//			switch (cli.getMod()) {
//				case "backup":
//					log.info("备份开始");
//					if(opt.backup(cli,resinfo))
//					{
//						log.info(cli.getMod());
//						resinfo.setResid(cli.getResid());
//						resinfo.setBackupres(2);
//						log.info("当前任务为备份"+"传入的任务结果集id为"+cli.getResid());
//					}
//					else {
//						resinfo.setResid(cli.getResid());
//						resinfo.setBackupres(0);
//						log.info("备份失败");
//					}
//					break;
//				case "restore":
//					log.info("参数 ["+cli.toString()+"]");
//					log.info("恢复任务开始"+"恢复的虚拟机id为"+cli.getVmid()+"恢复的虚拟机的name为"+cli.getVmname());
//					resinfo.setResid(cli.getVmRestoreId());
//					opt.restore(cli);
//					resinfo.setBackupres(2);
//					break;
//			}
//		} catch (Exception e) {
//			log.info(Utility.toString(e));
//			e.printStackTrace();
//			resinfo.setBackupres(0);
//		} finally {
//			resinfo.setMod(cli.getMod());
//			resinfo.sendbackupres(cli.getNodeIp(), Integer.valueOf(cli.getNodePort()));
//		}
//	}
//}
