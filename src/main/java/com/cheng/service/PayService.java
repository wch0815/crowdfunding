package com.cheng.service;

import com.alipay.api.AlipayApiException;
import com.cheng.bean.AlipayBean;

/*支付服务*/
public interface PayService {
    /*支付宝*/
    String aliPay(AlipayBean alipayBean) throws AlipayApiException;
}
