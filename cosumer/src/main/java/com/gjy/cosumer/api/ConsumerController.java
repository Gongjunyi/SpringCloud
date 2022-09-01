package com.gjy.cosumer.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.IOException;

@RestController

public class ConsumerController {

    private final String SERVICE_URL = "http://139.196.153.80:8088/api/plant";

    @Resource
    private RestTemplate restTemplate;

    private WebClient webClient = WebClient.builder()
            .baseUrl(SERVICE_URL)
            .build();


    @GetMapping("/httpClientTest")
    public String httpClientTest() throws IOException {
        //创建Httpclient，相当于打开了浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpGet请求，相当于在浏览器输入地址
        HttpGet httpGet = new HttpGet(SERVICE_URL + "/hello");
        CloseableHttpResponse response = null;
        try {
            //执行请求，相当于敲完地址后按下回车，获取响应
            response = httpClient.execute(httpGet);
            // 判断状态码
            if (response.getStatusLine().getStatusCode() == 200){
                //解析响应，获取数据
                String result = EntityUtils.toString(response.getEntity(),"UTF-8");
                System.out.println(result);
            }
        }
        finally {
            if (response != null){
                //关闭资源
                response.close();
            }
            //关闭浏览器
            httpClient.close();
        }
        return "请求成功";
    }

    @GetMapping("/restTemplate")
    public String restTemplateTest(){
        System.out.println(restTemplate.getForObject(SERVICE_URL , String.class));
        return  restTemplate.getForObject(SERVICE_URL , String.class);
    }

    @GetMapping("/webClient")
    private String webClientTest(){
        Mono<String> mono = webClient.get().uri("").retrieve().bodyToMono(String.class);

        mono.subscribe(System.out::println);

        return "请求成功";

    }
}

