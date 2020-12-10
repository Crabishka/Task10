import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sun.swing.table.DefaultTableCellHeaderRenderer;
import util.*;


public class MainForm extends JFrame {   // в чем отличие JFrame от Jdialog

    private JPanel MainPanel;
    private JTable InputTable;
    private JButton GetFromFileButton;
    private JPanel InputButtonPanel;
    private JButton GetRandomNumbersButton;
    private JButton ExecuteButton;
    private JLabel Output;
    private JPanel ExecutePanel;
    private JScrollPane InputPanel;
    private JPanel SaveButtonPanel;
    private JButton SaveIntoFileButton;
    private JTable OutputTable;
    private JScrollPane OutputPanel;

    private JFileChooser fileChooserOpen;  // выбор директории
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;  // выбор меню, но он удален




    public MainForm() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);  // не работает нормально из-за "пружин"
        this.setTitle("MainForm");      // устанавливает название формы
        this.setLocationRelativeTo(null);
        // this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   // устанавливает правила при нажатии на крестик
        this.setContentPane(MainPanel);  // без понятия, что делает, но без этого не работает
        this.pack();  // без понятия, что делает



        /*
        Честно украденные настройки элементов у Соломатина, потому что вручную все это прописывать скучно и долго.
        Я сделать и вынести настройки отдельных элементов в отдельные методы (например метод для таблиц, метод для кнопок),
        которые бы запускались в MainForm, но что-то не получилось не получилось
         */


        JTableUtils.initJTableForArray(InputTable, 110, true, true, true, false);
        JTableUtils.initJTableForArray(OutputTable, 110, true, true, false, false);
        JTableUtils.resizeJTable(InputTable,1,6,25,-1);
        JTableUtils.resizeJTable(OutputTable,1,6,25,-1);

        fileChooserOpen = new JFileChooser();           // не сильно понимаю, что твориться следующие 20 строчек,
        fileChooserSave = new JFileChooser();           // но это вроде работа с файлами
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");





        GetFromFileButton.addActionListener(new ActionListener() {  // прочитать из файла
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileFunction fileFunсtion = new FileFunction();
                try {
                    if (fileChooserOpen.showOpenDialog(MainPanel) == JFileChooser.APPROVE_OPTION) {
                        List<Triangle> list = fileFunсtion.readListFromFile(fileChooserOpen.getSelectedFile().getPath());
                        SwingAndListFunction.writeListIntoJtable(InputTable, list);
                        JTableUtils.resizeJTable(InputTable,list.size(),6,-1,-1);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        GetRandomNumbersButton.addActionListener(new ActionListener() {  // случайные числа
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[][] a = ArrayUtils.createRandomIntMatrix(
                            InputTable.getRowCount(), InputTable.getColumnCount(), 100);
                    JTableUtils.writeArrayToJTable(InputTable, a);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });  // добавить выбор границы случайных чисел

        ExecuteButton.addActionListener(new ActionListener() {  // Прочитать из таблицы, посчитать, записать в таблицу
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileFunction fileFunction = new FileFunction();
                try {
                    Logic logic = new Logic();
                    List<Triangle> list= SwingAndListFunction.readListFromJtable(InputTable); // прочитал
                    List<List<Triangle>> result = logic.Operation(list); // посчитал
                    JTableUtils.resizeJTable(OutputTable,result.size()+SwingAndListFunction.sizeOfAllElementsOfList(result) - 1,6,-1,-1); // изменил
                    SwingAndListFunction.writeListOfListIntoJtable(OutputTable, result); // записал

                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        SaveIntoFileButton.addActionListener(new ActionListener() { // прочитать из таблицы, записать в файл
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileFunction fileFunсtion = new FileFunction();
                try {
                    List<Triangle> list = SwingAndListFunction.readListFromJtable(InputTable);
                    if (fileChooserSave.showSaveDialog(MainPanel) == JFileChooser.APPROVE_OPTION) {
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        Logic logic = new Logic();
                        fileFunсtion.writeListIntoFile(file,logic.Operation(list));
                       /* FileWriter writer = new FileWriter(file, false);
                        writer.write(Logic.Operation(matrix));
                        writer.close();
                        */
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here


    }
}
