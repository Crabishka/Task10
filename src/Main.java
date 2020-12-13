
import util.*;
import java.util.List;

import java.util.Locale;


public class Main {

    public static void winMain() throws Exception {
        Locale.setDefault(Locale.ROOT);
        //SwingUtils.setLookAndFeelByName("Windows");
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        // Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });

    }

    public static void main(String[] Args) throws Exception {
        CmdParams params = CmdParams.parseArgs(Args);
        FileFunction fileFunction = new FileFunction();
        if (Args.length == 0 || params.window) {
            winMain();
            return;
        }
        if (params.error){
            System.out.print("Error in Input/Output stream");
        }
        if (params.help) {
            System.out.println("Usage:");
            System.out.println("Fast execute <input-file> <output-file>");
            System.out.println();
            System.out.println("--input <input-file>// add Input file");
            System.out.println("-i <input-file>");
            System.out.println();
            System.out.println("--output <output-file> // add Output file");
            System.out.println("-o <output-file>");
            System.out.println();
            System.out.println("--window // show window");
            System.out.println("-w");
            System.out.println();
         /* System.out.println("If there is not input-file or output-file");
            System.out.println("It will rewrite existing one."); */
            System.out.println("If there are not any input-file and output-file");
            System.out.println("It will returns error in Error Stream");
            System.out.println();
        }
        if (params.inputFile == null && params.outputFile == null) {
            winMain();
        } else {
            List<Triangle> list = fileFunction.readListFromFile(params.inputFile);
            if (list.size() == 0) {
                System.err.println("Can't read array");
                System.exit(-2);
            }
            Logic logic = new Logic();
            fileFunction.writeListIntoFile(params.outputFile, logic.Operation(list));
        }


    }
}
