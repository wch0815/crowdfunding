package com.cheng.controller;

import com.alipay.api.AlipayApiException;
import com.cheng.bean.AlipayBean;
import com.cheng.service.PayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/* 订单接口 */
@RestController()
@RequestMapping("order")
public class OrderController {
    @Resource
    private PayService payService;//调用支付服务​

    /*阿里支付*/
    @PostMapping(value = "alipay")
    public String alipay(String out_trade_no, String subject, String total_amount, String body, HttpSession session) throws AlipayApiException {
        System.out.println(out_trade_no);
        System.out.println(subject);
        System.out.println("total_amount = " + total_amount);
        System.out.println(body);
        session.setAttribute("pay", total_amount);
        return payService.aliPay(new AlipayBean()
                .setBody(body)
                .setOut_trade_no(out_trade_no)
                .setTotal_amount(new StringBuffer().append(total_amount))
                .setSubject(subject));
    }

}
