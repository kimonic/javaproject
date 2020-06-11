package com.dzx.util;

import java.io.*;

public class OutTxt {


    public static void main(String args[]) throws IOException {
//        Desktop.getDesktop().open(new File("E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\产品需求书_语音助手需求列表_V7.2.2_社交二代.xlsm"));
        OutputStream inputStream = Runtime.getRuntime().exec("explorer E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\产品需求书_语音助手需求列表_V7.2.2_社交二代.xlsm").getOutputStream();
//
//        ArrayList<String> columnList = new ArrayList<String>();
////        File file = new File("E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\产品需求书_语音助手需求列表_V7.2.2_社交二代.xlsm");
////        File file = new File("E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\IOT平台指令协议-20200514(addvirtualcenter) (1).xlsx");
//        File file = new File("C:\\Users\\dingzhixin.ex\\Desktop\\AIOT3.0.5总的冒烟用例.xlsx");
////        FileWriter filewriter = new FileWriter("E:\\work\\resource\\aiot资料\\慧享家\\aiot第四期\\虚拟体验中心\\产品需求书_语音助手需求列表_V7.2.2_社交二代.xlsm");
//        try {
//            FileInputStream in = new FileInputStream(file);
//
//            XSSFWorkbook wb = new XSSFWorkbook(in);
//
//            Sheet sheet = wb.getSheetAt(0); //取得“测试.xlsx”中的第一个表单
//            int firstRowNum = sheet.getFirstRowNum();
//            int lastRowNum = sheet.getLastRowNum();
//
//            Row row = null;
//            Cell cell_a = null;
//            for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
//                row = sheet.getRow(i); //取得第i行 （从第二行开始取，因为第一行是表头）
//                cell_a = row.getCell(1); //取得i行的第一列
//                String cellValue = cell_a.getStringCellValue().trim();
//                //System.out.println(cellValue);
////                filewriter.write(cellValue + "\r\n");   //将取出的.xlsx表中的数据写入到txt文件中
//                System.out.println(cellValue);
//                columnList.add(cellValue);
//            }
////            filewriter.flush();
////            filewriter.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}