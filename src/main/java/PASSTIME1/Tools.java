/**
 *
 *  @author Redzik Mateusz S18819
 *
 */

package PASSTIME1;


import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class Tools {
    static Options createOptionsFromYaml(String fileName) throws Exception{
        Map map = readFromYamlFileToMap(fileName);
       return new Options(
               (String)map.get("host"),
               (int) map.get("port"),
               (boolean)map.get("concurMode"),
               (boolean)map.get("showSendRes"),
               (Map)map.get("clientsMap")
       );
    }

    private static Map readFromYamlFileToMap(String fileName) throws FileNotFoundException {
        InputStream inputStream =  new FileInputStream(new File(fileName));
        Yaml yaml = new Yaml();
        Map<String, Object> dataAsMap =  (Map<String, Object>)yaml.load(inputStream);
        return  dataAsMap;
    }

}
