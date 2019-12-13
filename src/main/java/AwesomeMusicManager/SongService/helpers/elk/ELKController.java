//package AwesomeMusicManager.SongService.helpers.elk;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//import java.net.InetAddress;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "AwesomeMusicManager.SongService.view.service")
//@ComponentScan(basePackages = { "AwesomeMusicManager.SongService.view.service" })
//public class ELKController {
//
//    @Value("${elasticsearch.home:/usr/local/Cellar/elasticsearch/5.6.0}")
//    private String elasticsearchHome;
//
//    @Value("${elasticsearch.cluster.name:elasticsearch}")
//    private String clusterName;
//
//    @Bean
//    public RestHighLevelClient client() {
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("docker-el", 9200, "http"),
//                        new HttpHost("localhost", 9201, "http")));
//        return client;
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() {
//        return new ElasticsearchTemplate(client());
//    }
//}