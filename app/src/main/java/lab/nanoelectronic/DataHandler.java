package lab.nanoelectronic;

import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class DataHandler {
    private int tableNum;
    private String[][][] tables = new String[2][][];
    private String[][] columnNames = new String[2][];
    private String[] fileName = new String[2];
    private boolean[] available = new boolean[2];
    private boolean[] mostRecentUse = new boolean[2];

    private final int MAXROWS = 100000;

    DataHandler() {
        tableNum = 0;
        available[0] = false;
        available[1] = false;
        mostRecentUse[0] = false;
        mostRecentUse[1] = false;
    }

    public int getTableNum() {
        return tableNum;
    }

    // for test
    /* 
    int openTable() {
        System.err.println("openTable()");
        available[0] = true;
        mostRecentUse[0] = true;
        mostRecentUse[1] = false;
        tables[0] = new Double[2][];
        tables[0][0] = new Double[4];
        tables[0][1] = new Double[3];
        tables[0][0][0] = 1.5;
        tables[0][0][1] = 2.5;
        tables[0][0][2] = 3.5;
        tables[0][0][3] = 4.0;
        tables[0][1][0] = 4.5;
        tables[0][1][1] = 5.5;
        tables[0][1][2] = 6.5;
        columnNames[0] = new String[3];
        columnNames[0][0] = "A";
        columnNames[0][1] = "B";
        columnNames[0][2] = "C";

        tableNum = 1;

        return 1;
    }
    */
    int openTable(String path, String fileType) {
        int status = 0;
        // check if exist
        if (path.equals(fileName[0]) || path.equals(fileName[1])) {
            return status;
        }

        // choose a slot for new table
        int tableIndex;
        if (available[0] == false) {
            tableIndex = 0;
        } else if (available[1] == false) {
            tableIndex = 1;
        } else {
            tableIndex = mostRecentUse[0] ? 1 : 0;
        }

        status = loadFile(tableIndex, path, fileType);
        if (status != 1) {
            return status;
        }
        available[tableIndex] = true;
        mostRecentUse[tableIndex] = true;
        mostRecentUse[1 - tableIndex] = false;
        fileName[tableIndex] = path;
        tableNum = 1;
        // load file
        

        return status;
    }

    int loadFile(int tableIndex, String path, String fileType) {
        int status = 1;
        System.err.println("load file: Start.");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String delimiter;
            if (fileType == "LabVIEW") {
                delimiter = "\t";
                reader.readLine();
                columnNames[tableIndex] = reader.readLine().split(delimiter);
                // test
                System.err.println("column name");
                for (int i = 0; i < columnNames[tableIndex].length; i++) {
                    System.err.print(columnNames[tableIndex][i]);
                    System.err.print(" ");
                }
                System.err.println();

                String line;
                tables[tableIndex] = new String[MAXROWS][];
                int i = 0;
                while((line = reader.readLine()) != null) {
                    tables[tableIndex][i++] = line.split(delimiter);
                    if (i >= MAXROWS) {
                        break;
                    }
                }

                reader.close();
            } else {
                ;
            }
        } catch (IOException e) {
            status = 2;
            //e.printStackTrace();
            return status;
        }

        System.err.printf("load file: End with status = %d.\n", status);


        return status;
    }

    private void removeTable() {
        ;
    }
    
    public String[][] getTable() {
        
        int choosen = available[0] ? 0 : 1;
        System.err.printf("getTable: choose %d\n", choosen);
        return tables[choosen];
    }
    public String[] getColumnName() {
        
        int choosen = available[0] ? 0 : 1;
        System.err.printf("getColumnName: choose %d\n", choosen);
        return columnNames[choosen];
    }

    public Double[][] getTable(int index) {
        return null;
    }

    public String[] getColumnName(int index) {
        return null;
    }
}
