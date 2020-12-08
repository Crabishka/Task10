import util.SwingUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwingAndListFunction {

    public static void writeListIntoJtable(JTable table, List<Triangle> list) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();    // "реализует интерфейс TableModel и хранит данные ячеек таблицы"
        tableModel.setColumnCount(6);
        tableModel.setRowCount(list.size());
        // int[][] array = new int[list.size()][3];
        int i = 0;
        int j = 0;
        for (Triangle triangle : list) {
            for (int number : SwingAndListFunction.TriangleIntoList(triangle)) {
                tableModel.setValueAt(number, i, j);
                j++;
            }
            j = 0;
            i++;
        }
    }

    public static void writeListOfListIntoJtable(JTable table, List<List<Triangle>> list) { // не получилось перегрузить
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        // list.removeAll(Arrays.asList("", null));
        tableModel.setColumnCount(6);
        tableModel.setRowCount(sizeOfAllElementsOfList(list) + list.size());
        int i = 0;
        int j = 0;
        for (List<Triangle> groupOfTriangle : list) {
            for (Triangle triangle : groupOfTriangle) {
                j = 0;
                for (int number : SwingAndListFunction.TriangleIntoList(triangle)) {
                    tableModel.setValueAt(number, i, j);
                    j++;
                }

                i++;
            }
            for (j = 0; j < 6; j++)
                tableModel.setValueAt("", i, j);
            i++;

        }

    }

    public static int sizeOfAllElementsOfList(List<List<Triangle>> list) {
        int size = 0;
        for (List<Triangle> lists : list) {
            size += lists.size();
        }
        return size;

    }

    public static List<Triangle> readListFromJtable(JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        List<Triangle> list = new ArrayList<>();
        int s;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            try {
                Triangle triangle = new Triangle(   // это выглядит ужасно
                        Integer.parseInt(String.valueOf(tableModel.getValueAt(i, 0))),  // получаем объект, переводим в строку
                        Integer.parseInt(String.valueOf(tableModel.getValueAt(i, 1))),  // потом переводим в число
                        Integer.parseInt(String.valueOf(tableModel.getValueAt(i, 2))),  // боже
                        Integer.parseInt(String.valueOf(tableModel.getValueAt(i, 3))),
                        Integer.parseInt(String.valueOf(tableModel.getValueAt(i, 4))),
                        Integer.parseInt(String.valueOf(tableModel.getValueAt(i, 5))));
                list.add(triangle); // записываем
            } catch (NumberFormatException e) {
                SwingUtils.showErrorMessageBox("Error", "Error", e);  // если не перевелось
            }
        }
        return list;
    }

    public static List<Integer> TriangleIntoList(Triangle triangle) {  // это не менее ужасно
        List<Integer> list = new ArrayList<>();
        list.add(triangle.x1);
        list.add(triangle.y1);
        list.add(triangle.x2);
        list.add(triangle.y2);
        list.add(triangle.x3);
        list.add(triangle.y3);
        return list;
    }

    public static Triangle ListToTriangle(List<Integer> list) {
        int[] a = new int[6];
        int i = 0;
        for (int number : list) {  // зато без get()
            a[i] = number;
            i++;
        }
        Triangle triangle = new Triangle(a[0], a[1], a[2], a[3], a[4], a[5]);
        return triangle;
    }


}
