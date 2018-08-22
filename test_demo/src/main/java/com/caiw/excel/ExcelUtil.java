package com.caiw.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName ExcelUtil
 * @Author caiwe
 * @CreateTime 2018/8/21  17:03
 **/

public class ExcelUtil {

    public static void main(String[] args) {
        String a = "abcdefcdefgh";
        String b = "cd";
        String c = a.replace(b,"");
        System.out.println(c);
    }


    public static XSSFWorkbook readXlsx(String path) throws Exception {
        InputStream is = new FileInputStream(path);
        return new XSSFWorkbook(is);
    }

    public static List<List<String>> readSheet(XSSFSheet xssfSheet){
        List<List<String>> result = new ArrayList<List<String>>();
        if (xssfSheet == null) {
            return result;
        }
        // 处理当前页，循环读取每一行
        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);
            int minColIx = xssfRow.getFirstCellNum();
            int maxColIx = xssfRow.getLastCellNum();
            if(xssfRow.getCell(minColIx).toString().contains("数据元标识符")){
                continue;
            }
            List<String> rowList = new ArrayList<String>();
            for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                XSSFCell cell = xssfRow.getCell(colIx);
                if (cell == null) {
                    continue;
                }
                rowList.add(cell.toString());
            }
            result.add(rowList);
        }
        return result;
    }



    /**
     * 改造poi默认的toString（）方法如下
     * @Title: getStringVal
     * @Description: 1.对于不熟悉的类型，或者为空则返回""控制串
     *               2.如果是数字，则修改单元格类型为String，然后返回String，这样就保证数字不被格式化了
     * @param @param cell
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getStringVal(XSSFCell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case Cell.CELL_TYPE_NUMERIC:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }
}
