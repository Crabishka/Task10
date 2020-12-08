import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import util.SwingUtils;

public class FileFunction {

    public List<Triangle> readListFromFile(String inputFile) throws NumberFormatException { // стоило бы разбить на несколько методов...
        //   List<Integer> triangleList = new ArrayList<>(); // список о отдельном взятом треугольнике
        List<Triangle> list = new ArrayList<>();   // список информации о треугольниках

        try (Scanner scanner = new Scanner(new File(inputFile), "UTF-8")) { // пытаемся создать класс

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();           // берем линию
                if (line == null || line.trim().length() == 0) // проверяем
                    break;
                String[] numbers = line.split(" ");     // разбиваем
                if (numbers.length != 6) SwingUtils.showInfoMessageBox("Try another numbers", "Error"); // проверяем
                try {
                    Triangle triangle = new Triangle(   // это выглядит ужасно
                            Integer.parseInt(numbers[0]),
                            Integer.parseInt(numbers[1]),
                            Integer.parseInt(numbers[2]),
                            Integer.parseInt(numbers[3]),
                            Integer.parseInt(numbers[4]),
                            Integer.parseInt(numbers[5]));
                    list.add(triangle); // записываем
                } catch (NumberFormatException e) {
                    SwingUtils.showErrorMessageBox("NumberError", "NumberError", e);  // если не перевелось
                }

            }

        } catch (FileNotFoundException e) {
            SwingUtils.showErrorMessageBox("FileError", "FileError", e); // если не нашелся файл
        }

        return list;
    }

    public void writeListIntoFile(String outputFile, List<List<Triangle>> list) throws IOException {
        FileWriter fileWriter = new FileWriter(outputFile, false);
        for (List<Triangle> groupOfTriangle : list) {
            for (Triangle triangle : groupOfTriangle) {
                for (int number : SwingAndListFunction.TriangleIntoList(triangle)) {
                    fileWriter.append(String.valueOf(number) + " ");
                }
                fileWriter.append("\n");
            }
            fileWriter.append("\n");

        }
        fileWriter.close();
    }

}
