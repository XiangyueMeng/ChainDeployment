package tron.deployment.shellExecutor;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class BashExecutor {
    public void callScript(String script, String args, String... workspace){
        try {
            String cmd = "bash " + script + " " + args;
//        	String[] cmd = {"sh", script, "4"};
            File dir = null;
            if(workspace[0] != null){
                dir = new File(workspace[0]);
//                File[] files = dir.listFiles();
//                for(File file : files){
//                    System.out.println(file.getName());
//                }
                System.out.println(workspace[0]);
            }
            String[] evnp = {"val=2", "call=Bash Shell"};
            Process process = Runtime.getRuntime().exec(cmd);
//            Process process = Runtime.getRuntime().exec(cmd, evnp, dir);
//            process = Runtime.getRuntime().exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void callDeployScript(){
        try {
//            String s = this.getClass().getClassLoader().getResource("").getPath();
            Resource resource = new ClassPathResource("deploy.bash");
            File script =resource.getFile();
            String cmd = "bash " + script.getAbsolutePath();
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void callBuildScript(){
        try {
//            String s = this.getClass().getClassLoader().getResource("").getPath();
            Resource resource = new ClassPathResource("build.bash");
            File script =resource.getFile();
            String cmd = "bash " + script.getAbsolutePath();
            System.out.println(cmd);
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void callBuildScript2(){
        try {
            Resource resource = new ClassPathResource("build.bash");
            File script =resource.getFile();
            String[] cmd = {"bash", script.getAbsolutePath()};
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BashExecutor call = new BashExecutor();
        call.callBuildScript2();
//        call.callScript("deploy.bash", "", "./");
    }
}
