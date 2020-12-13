import util.SwingUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
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
        tableModel.setColumnCount(6);
        int absoluteSize = sizeOfAllElementsOfList(list) + list.size();
        tableModel.setRowCount(absoluteSize);
        int i = 0;
        int j ;
        for (List<Triangle> groupOfTriangle : list) {
            for (Triangle triangle : groupOfTriangle) {
                j = 0;
                for (int number : SwingAndListFunction.TriangleIntoList(triangle)) {
                    tableModel.setValueAt(number, i, j);
                    j++;
                }

                i++;
            }
            for (int k =0;k<6;k++) tableModel.setValueAt("", i ,k);
            if (i != absoluteSize){

                i++;

            }

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
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel(); // получаем таблицу
        List<Triangle> list = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            try {

                Triangle.Point point1 = new Triangle.Point(getIntFromTableModelCell(tableModel,i,0),
                                                           getIntFromTableModelCell(tableModel,i,1));

                Triangle.Point point2 = new Triangle.Point(getIntFromTableModelCell(tableModel,i,2),
                                                           getIntFromTableModelCell(tableModel,i,3));

                Triangle.Point point3 = new Triangle.Point(getIntFromTableModelCell(tableModel,i,4),
                                                           getIntFromTableModelCell(tableModel,i,5));

                Triangle triangle = new Triangle(point1,point2,point3);
                if (triangle.isTriangleExist()) list.add(triangle); // записываем
            } catch (NumberFormatException e) {
                SwingUtils.showErrorMessageBox("Error", "Error", e);  // если не перевелось
            }
        }
        return list;
    }

    public static int getIntFromTableModelCell(DefaultTableModel tableModel,int i, int j){
        return Integer.parseInt(String.valueOf(tableModel.getValueAt(i, j)));
    }

    public static List<Integer> TriangleIntoList(Triangle triangle) {  // это не менее ужасно
        List<Integer> list = new ArrayList<>();
        list.add(triangle.point1.x);
        list.add(triangle.point1.y);
        list.add(triangle.point2.x);
        list.add(triangle.point2.y);
        list.add(triangle.point3.x);
        list.add(triangle.point3.y);
        return list;
    }

}
