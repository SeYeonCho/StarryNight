import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test {
    public static void main(String[] args) throws Exception{
        String command = "ls -al";
        shellCmd(command);

    }
    public static void shellCmd(String command) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }



}
