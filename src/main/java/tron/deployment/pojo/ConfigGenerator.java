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
//        Config defaultConfig = ConfigFactory.parseFile(new File("./src/main/resources/temp.conf"));
//        Config myConfig = ConfigFactory.parseString("net.type=testnet");
//        Config newConfig = myConfig.withFallback(defaultConfig);
//        String s = newConfig.root().render(ConfigRenderOptions.defaults().setOriginComments(false).setComments(false).setJson(false));
//        Config checkConfig = ConfigFactory.parseString(s);
        ConfigGenerator configGenerator = new ConfigGenerator();
        configGenerator.generateConfig(new Configuration("mainnet", "LEVELDB", 8090));
    }
    public void generateConfig(Configuration configuration){
        File defaultConfigFile = new File("./java-tron/src/main/resources/config.conf");
        Config defaultConfig = ConfigFactory.parseFile(defaultConfigFile);
        StringBuilder configSB = new StringBuilder();
        Field[] fields = configuration.getClass().getFields();
        for(Field field : fields ){
            String fieldName = field.getName();
            fieldName = fieldName.replaceAll("_",".");
//            System.out.println(fieldName);
            Object value = null;
            try {
                value = field.get(configuration);
//                System.out.println(value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value!=null) {
                configSB.append(fieldName).append("=")
                        .append(value.toString()).append(",");
            }
        }
        System.out.println(configSB.toString());
//        configSB.append("net.type=").append(configuration.getNet_type()).append(",")
//                .append("storage.db.engine=").append(configuration.getStorage_db_engine()).append(",")
//                .append("node.http.fullNodePort=").append(configuration.getNode_http_fullNodePort());
        Config modifiedConfig = ConfigFactory.parseString(configSB.toString());
        Config newConfig = modifiedConfig.withFallback(defaultConfig);
        String configStr = newConfig.root().render(ConfigRenderOptions.defaults().setOriginComments(false).setComments(false).setJson(false));
//        Resource configResource = new ClassPathResource("config.conf");
//        File configFile = new File("./src/main/resources/config.conf");
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
