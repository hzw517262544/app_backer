package com.bootdo.app.executionlistener;

import com.bootdo.app.domain.ApplyInfoDO;
import com.bootdo.app.service.ApplyInfoService;
import com.bootdo.common.config.ApplicationContextRegister;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplyCancelExecutionListener implements ExecutionListener {
    @Autowired
    private ApplyInfoService applyInfoService;

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        //申请流程结束后将申请状态改为6：取消
        String businessKey = delegateExecution.getProcessBusinessKey();
        ApplyInfoDO applyInfo = new ApplyInfoDO();
        applyInfo.setId(businessKey);
        applyInfo.setApplyStatus("6");
        if(applyInfoService == null){
            applyInfoService = ApplicationContextRegister.getBean(ApplyInfoService.class);
        }
        applyInfoService.update(applyInfo);
    }

    public ApplyInfoService getApplyInfoService() {
        return applyInfoService;
    }

    public void setApplyInfoService(ApplyInfoService applyInfoService) {
        this.applyInfoService = applyInfoService;
    }
}
