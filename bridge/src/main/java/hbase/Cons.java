package hbase;

/**
 * Created by nickozoulis on 23/10/2015.
 */
public class Cons {
    // HBase Configuration constants
    public static final String hbase_IP_address = "192.168.1.201";//127.0.0.1
    public static final String hbase_port = "2181";

    // HBase table constants
    public static final String queries = "queries";
    public static final String batch_views = "batch_views";
    public static final String stream_views = "stream_views";
    public static final String raw_data = "raw_data";
    public static final String cfAttributes = "a";
    public static final String cfQueries = "q"; // column family for Queries table
    public static final String cfViews = "v"; // column family for batch and stream Views
    public static final String max_qid = "max_qid";
    public static final String clusters = "clusters";
    public static final String clusters_ = "c_";
    public static final String filter = "filter";
    public static final String numOfAttr = "numOfAttr";

    public static final int delay = 3000; // Thread sleep delay in milliseconds
    public static final int batchDelay = 30000; // Every hour 360000
    public static final int K = 500; // The fixed number of clusterHeads
    public static final String dataset = "/Users/nickozoulis/IdeaProjects/master_team_project/batch_layer/src/main/resources/input.txt";
    public static final int iterations = 10;
    public static final int runs = 1;
}
