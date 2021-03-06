package com.okar.icz.utils;

import com.caucho.hessian.client.HessianProxyFactory;
import com.okar.icz.model.Account;
import com.okar.icz.service.AccountService;

import java.net.MalformedURLException;

/**
 * Created by wangfengchen on 15/4/29.
 */
public class RemoteServiceFactory {

    private static final String REMOTE_SERVICE_URL = "http://192.168.1.6:9080";

    public static AccountService createAccountService() throws MalformedURLException {
        HessianProxyFactory factory = new HessianProxyFactory();
        // 不设置会报 expected hessian reply at 0x48
        factory.setHessian2Reply(false);
        return factory.create(AccountService.class, REMOTE_SERVICE_URL + "/accountservice");
    }
}
