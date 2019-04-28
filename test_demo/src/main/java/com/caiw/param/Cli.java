/**  
 * Project Name:virtbackup  
 * File Name:Cli.java  
 * Package Name:com.dsg.virtbackup  
 * Date:2018年11月30日上午11:36:15  
 * Copyright (c) 2018, wangyl@dsgdata.com All Rights Reserved.  
 *  
*/

package com.caiw.param;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * ClassName:Cli <br/>
 * Date: 2018年11月30日 上午11:36:15 <br/>
 * 
 * @author Administrator
 * @version
 * @since JDK 1.7
 * @see
 */
@Parameters(separators = ":")
public class Cli {
	@Parameter(names = "-ip", description = "Virtualization center IP", required = true, order = 0)
	private String ip;
	
	@Parameter(names = "-user", description = "Virtualization Center Users", required = true, order = 1)
	private String user;
	
	@Parameter(names = "-password", description = "Virtualization Center Password", required = true, order = 2)
	private String password;
	
	@Parameter(names = "-manageip", description = "Management center IP", required = true, order = 3)
	private String manageip;
	
	@Parameter(names = "-manageport", description = "Management center Port", required = true, order = 4)
	private Integer manageport;
	
	@Parameter(names = "-vmid", description = "The id of the virtual machine", required = true, order = 5)
	private String vmid;
	
	@Parameter(names = "-vmname", description = "The name of the virtual machine", required = true, order = 6)
	private String vmname;
	
	@Parameter(names = "-bkpath", description = "Backup version configuration file path", required = true, order = 7)
	private String bkpath;
	
	@Parameter(names = "-resid", description = "The backup result record ID", required = true, order = 8)
	private Integer resid;
	
	@Parameter(names = "-bknum", description = "Backup version number", required = true, order = 9)
	private Integer bknum;
	
	@Parameter(names = "-mod", description = "Operation mode backup/restore", required = true, order = 10)
	private String mod;
	
	@Parameter(names = "-vmnewname", description = "Virtual Machine Recovery Name", required = false, order = 11)
	private String vmnewname;

	@Parameter(names = "-rehoset", description = "Host IP requiring recovery", required = false, order = 12)
	private String rehoset;
	
	@Parameter(names = "-redata", description = "Storage to be restored", required = false, order = 13)
	private String redata;
	@Parameter(names = "-vmRestoreId", description = "Backup version number", required = true, order = 14)
	private Integer vmRestoreId;

	@Parameter(names = "-renetwork", description = "Network to be restored", required = false, order = 15)
	private String renetwork;
	@Parameter(names = "-nodeIp", description = "Network to be restored", required = false, order = 16)
	private String nodeIp;
	@Parameter(names = "-nodePort", description = "Network to be restored", required = false, order = 17)
	private String nodePort;
	@Parameter(names = "-localip", description = "Network to be restored", required = false, order = 17)
	private String localip;
	@Parameter(names = "-port", description = "Virtualization center port", required = true, order = 15)
	private String port;
	@Parameter(names = "-resourcePath", description = "Virtualization center port", required = true, order = 15)
	private String resourcePath;
	@Parameter(names = "-hostUrn", description = "Virtualization center port", required = true, order = 15)
	private String hostUrn;
	@Parameter(names = "-siteUri", description = "Virtualization center port", required = true, order = 15)
	private String siteUri;

	@Parameter(names = "-help", help = true, order = 14)
	private boolean help;

}
