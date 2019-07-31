package tron.deployment.shellExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestCmd {
    //java中的RunTime类，每个java程序都有一个RunTme的运行实例，能够使运行程序与其运行的环境相连接。
    // Process 类能够创建本地进程，也可以通过RunTime的实例exec()方法创建。
    // Process 的 getInputStream() 会获取窗体命令执行的结果，可以把它转化成字符串进行输出
    public static void main(String[] args) {
        String cmdStr = "pwd";
        Runtime run = Runtime.getRuntime();
        try {
            Process process = run.exec(cmdStr);
            InputStream in = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(reader);
            StringBuffer sb = new StringBuffer();
            String message;
            while((message = br.readLine()) != null) {
                sb.append(message);
            }
            System.out.println(sb);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
