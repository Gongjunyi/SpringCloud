package com.mqxu.cloud.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 商品微服务接口
 * @author: mqxu
 * @date: 2022-09-01
 **/
@RestController
public class CloudGoodsApi {
    /**
     * 读取应用的启动端口
     */
    @Value("${server.port}")
    private String applicationServerPort;

    @GetMapping("/goodsServiceTest")
    public String goodsServiceTest() {
        // 返回信息给调用端
        System.out.println("this is goods service from port:" + applicationServerPort);
        return "this is goods service from port:" + applicationServerPort;
    }
}
