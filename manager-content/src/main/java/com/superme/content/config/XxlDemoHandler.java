package com.superme.content.config;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class XxlDemoHandler {
    @XxlJob("Demo")
    public ReturnT<String> demo(){
        String param = XxlJobHelper.getJobParam();
        XxlJobHelper.log("测试开始");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(param);
        System.out.println("测试完成！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        System.out.println();
        System.out.println();
        System.out.println();
        XxlJobHelper.log("测试开结束");
        return ReturnT.SUCCESS;
    }
}
