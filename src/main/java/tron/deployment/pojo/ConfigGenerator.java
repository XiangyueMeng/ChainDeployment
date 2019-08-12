package tron.deployment.pojo;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigRenderOptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class ConfigGenerator {
    public static void main(String[] args){
        ConfigGenerator configGenerator = new ConfigGenerator();
        configGenerator.generateConfig(new Configuration("mainnet", "LEVELDB", 8090));
    }
    public void generateConfig(Configuration configuration){
        // Load the original config file
        File defaultConfigFile = new File("./java-tron/src/main/resources/config.conf");
        Config defaultConfig = ConfigFactory.parseFile(defaultConfigFile);
        //use string builder to generate Config String
        StringBuilder configSB = new StringBuilder();
        Field[] fields = configuration.getClass().getFields();
        for(Field field : fields ){
            String fieldName = field.getName();
            // parse to right format
            fieldName = fieldName.replaceAll("_",".");
            Object value = null;
            try {
                value = field.get(configuration); // get the value
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            // generate the config string in typesafe's format
            if (value!=null) {
                configSB.append(fieldName).append("=")
                        .append(value.toString()).append(",");
            }
        }
//        check the config string
//        System.out.println(configSB.toString());
        Config modifiedConfig = ConfigFactory.parseString(configSB.toString());
        Config newConfig = modifiedConfig.withFallback(defaultConfig);
        String configStr = newConfig.root().render(ConfigRenderOptions.defaults().setOriginComments(false).setComments(false).setJson(false));
//        Resource configResource = new ClassPathResource("config.conf");
//        File configFile = new File("./src/main/resources/config.conf");
//        create a new file
        File tempConfigFile = new File("./java-tron/src/main/resources/temp.conf");
        try{
            tempConfigFile.delete();
            tempConfigFile.createNewFile();
            FileWriter fileWriter = new FileWriter(tempConfigFile);
            fileWriter.write(configStr);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("finish generation");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
