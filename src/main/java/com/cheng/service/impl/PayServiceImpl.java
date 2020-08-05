package com.cheng.service.impl;

import com.alipay.api.AlipayApiException;
import com.cheng.bean.AlipayBean;
import com.cheng.config.AlipayUtil;
import com.cheng.service.PayService;
import org.springframework.stereotype.Service;

/* 支付服务 */
@Service(value = "alipayOrderService")
public class PayServiceImpl implements PayService {
  @Override
  public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
    return AlipayUtil.connect(alipayBean);
   }

}
