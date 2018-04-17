package com.whl.blog.web.common;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 操作Excel表格的功能类
 */
public class ExcelReader {

	/**
	 * 去除excel读取多余的空行
	 * @param file
	 * @param colNum
	 * @return
	 */
	public Map<Integer, String> readNoblankExcelContent(File file, int colNum){
		Map<Integer, String> content = readExcelContent(file, colNum);
		if(content != null && content.size() > 0){
			return noBlankExcelMap(content);
		}
		return content;
	} 
	
	/**
	 * 读取Excel数据内容
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public Map<Integer, String> readExcelContent(File file, int colNum) {
		Map<Integer, String> content = new HashMap<Integer, String>();
		String str = "";
		Workbook workbook = getAdapterWorkBook(file);
		Sheet sheet = workbook.getSheetAt(NumberUtils.INTEGER_ONE);

		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 0; i <= rowNum; i++) {
			Row row = sheet.getRow(i);
			int j = 0;
			while (j < colNum) {
				// 每个单元格的数据内容用"@@"分割开，以后需要时用String类的replace()方法还原数据 //
				if (row != null) {
					Cell cell = row.getCell(j);
					String cellValue = " ";
					if (cell != null && StringUtils.isNotBlank(cell.toString())) {
						cellValue = cell.toString();
					}
					if (j == colNum - 1) {
						str += cellValue;
					} else {
						str += cellValue + "@@";
					}
				}
				j++;
			}
			content.put(i, str);
			str = "";
		}
		return content;
	}

	private Map<Integer, String> noBlankExcelMap(Map<Integer, String> content) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		for(int i : content.keySet()){
			String row = content.get(i);
			String line = row.replace("@@", "");
			if(StringUtils.isNotBlank(line)){
				map.put(i, row);
			}
		}
		return map;
	}
	
	private Workbook getAdapterWorkBook(File excelFile) {
		String fileNm = excelFile.getName();
		try {
			if (fileNm.toUpperCase().endsWith("xls")) {
				return new HSSFWorkbook(new FileInputStream(excelFile));
			} else {
				return new XSSFWorkbook(new FileInputStream(excelFile));
			}
		} catch (IOException ex) {
		}
		return null;
	}

	public static void main(String[] args) {
		ExcelReader excelReader = new ExcelReader();
		// 对读取Excel表格内容测试
		File file = new File("D:\\委托付款批量文件011.xlsx");
		Map<Integer, String> map = excelReader.readNoblankExcelContent(file, 6);
		System.out.println("获得Excel表格的内容:");
		for (int i : map.keySet()) {
			String line = map.get(i);
			if (line != null) {
				String[] split = line.split("@@");
				System.out.println("第"+i+"行："+line);
			}
		}
	}
}