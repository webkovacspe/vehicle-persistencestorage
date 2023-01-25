package hu.kovacspeterzoltan.bootcamp.vehiclepersistencestorage.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {
    public static final String DELIMITER = ",";
    String fileName;

    public void createFileIfNotExists() {
        try {
            File f = new File(fileName);
            if (!f.exists() || f.isDirectory()) {
                boolean newFileCreated = f.createNewFile();
                if (!newFileCreated) {
                    throw new IOException();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public CSVManager(String fileName) {
        this.fileName = fileName;
    }
    public List<String[]> load() {
        List<String[]> csv = new ArrayList<>();
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                csv.add(line.split(DELIMITER));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    assert br != null;
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return csv;
    }
    public void save(List<String[]> csv) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(fileName, false);
            bw = new BufferedWriter(fw);
            for (String[] columns: csv) {
                bw.write(String.join(DELIMITER, columns));
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw != null) {
                try {
                    assert bw != null;
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}