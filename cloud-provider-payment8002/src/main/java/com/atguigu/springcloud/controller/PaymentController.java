package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        log.info("**********插入结果："+result);
        if(result>0){
            return new CommonResult(200,"插入成功,返回结果+"+result+"\t 服务端口："+serverPort);
        }else {
            return new CommonResult(444,"插入数据失败",null);
        }
    }

    @GetMapping ("get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment=paymentService.getPaymentById(id);
        log.info("**********查询结果："+payment+":yyyyy");
        if(payment!=null){
            return new CommonResult(200,"查询成功,服务端口："+serverPort,payment);
        }else {
            return new CommonResult(444,"没有对应记录！查询Id:"+id,null);
        }
    }
}
