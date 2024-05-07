package AdminFolder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TableExporter {

    public static void exportToExcel(JTable table, String filePath) {
        TableModel model = table.getModel();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Table Data");

        // Create header row
        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < model.getColumnCount(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(model.getColumnName(col));
        }

        // Populate data rows
        for (int row = 0; row < model.getRowCount(); row++) {
            Row dataRow = sheet.createRow(row + 1); // Starting from row 1 for data
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cell = dataRow.createCell(col);
                cell.setCellValue(String.valueOf(model.getValueAt(row, col)));
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(new File(filePath))) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
