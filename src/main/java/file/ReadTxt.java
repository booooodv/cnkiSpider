package file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadTxt {
    public static List<String> readTxtFile(String file){
        List result = new ArrayList();
        try {
            InputStreamReader inReader = new InputStreamReader(new FileInputStream(file),"gbk");
            BufferedReader bufferedReader = new BufferedReader(inReader);
            String line = bufferedReader.readLine();
            String context = "";

            while(line != null){
                result.add(line);
                context += line;
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
