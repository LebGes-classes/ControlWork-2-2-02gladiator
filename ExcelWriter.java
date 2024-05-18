import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

public class ExcelWriter {

    public void writeExcel(Map<String, List<Broadcast>> channelPrograms) {
        String filePath = "вывод.xlsx";
        try (Workbook workbook = new XSSFWorkbook()) {
            for (String channelName : channelPrograms.keySet()) {
                Sheet sheet = workbook.createSheet(channelName);
                List<Broadcast> programs = channelPrograms.get(channelName);

                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Время начала");
                headerRow.createCell(1).setCellValue("Название программы");

                int rowNum = 1;
                for (Broadcast program : programs) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(program.getName());
                    row.createCell(1).setCellValue(program.getTime());
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            System.out.println("Данные успешно записаны в файл: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

