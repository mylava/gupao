package com.gupao.edu.lotus.server.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@ComponentScan("com.gupao.edu.lotus.server.service.search")
public class RestHighLevelClientConfig {

    @Autowired
    private ElasticsearchProperties elasticsearchProperties;

    @Bean(destroyMethod="close")
    public RestHighLevelClient restHighLevelClient() {
        String host = elasticsearchProperties.getHost();
        String username = elasticsearchProperties.getUsername();
        String password = elasticsearchProperties.getPassword();
        RestHighLevelClient restHighLevelClient = null;
        try {
            String[] hosts = host.split(",");
            HttpHost[] httpHosts = new HttpHost[hosts.length];
            for (int i = 0; i < httpHosts.length; i++) {
                String h = hosts[i];
                httpHosts[i] = new HttpHost(h.split(":")[0], Integer.parseInt(h.split(":")[1]), "http");
            }

            if(!StringUtils.isEmpty(username)) {
                final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                credentialsProvider.setCredentials(AuthScope.ANY,
                        new UsernamePasswordCredentials(username, password));//es账号密码（默认用户名为elastic）
                restHighLevelClient = new RestHighLevelClient(
                        RestClient.builder(httpHosts).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                            @Override
                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                httpClientBuilder.disableAuthCaching();
                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            }
                        })
                );
            }else{
                RestClientBuilder builder = RestClient.builder(httpHosts);
                builder.setRequestConfigCallback(requestConfigBuilder -> {
                    requestConfigBuilder.setConnectTimeout(1000);
                    requestConfigBuilder.setSocketTimeout(30000);
                    requestConfigBuilder.setConnectionRequestTimeout(500);
                    return requestConfigBuilder;
                });
                builder.setHttpClientConfigCallback(httpClientBuilder -> {
                    httpClientBuilder.setMaxConnTotal(1200);
                    httpClientBuilder.setMaxConnPerRoute(1200);
                    return httpClientBuilder;
                });
                restHighLevelClient = new RestHighLevelClient(builder);
            }
        } catch (Exception e) {
            e.printStackTrace(); return null;
        }
        return restHighLevelClient;
    }
}
