package tron.deployment.pojo;

import java.io.Serializable;

public class Configuration implements Serializable {
    private static final long serialVersionUID = 1L;
//    set to public to enable reflection of the field
    public String net_type;
    public String storage_db_engine;
    public int node_http_fullNodePort;

    public Configuration(String net_type, String storage_db_engine, int node_http_fullNodePort) {
        this.net_type = net_type;
        this.storage_db_engine = storage_db_engine;
        this.node_http_fullNodePort = node_http_fullNodePort;
    }

}
