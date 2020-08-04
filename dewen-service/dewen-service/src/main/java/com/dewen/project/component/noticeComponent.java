package com.dewen.project.component;

import com.dewen.project.domain.CompanyRecord;
import com.dewen.project.domain.CustomerNotice;
import com.dewen.project.repository.CompanyRecordRepository;
import com.dewen.project.repository.CustomerNoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/8/4 16:31
 */
@Slf4j
@Component
public class noticeComponent {

    @Autowired
    private CompanyRecordRepository companyRecordRepository;
    @Autowired
    private CustomerNoticeRepository customerNoticeRepository;

    @Transactional
    @Scheduled(cron = "*/30 * * * * ?")
    public void sendMessageV1(){
        log.info("===============巡查记录检测================");
        // 查询超过完成时间未审批的巡查记录
        List<CompanyRecord> companyRecords = companyRecordRepository.findRecord();
        CustomerNotice customerNotice;
        for (CompanyRecord companyRecord : companyRecords) {
            customerNotice = new CustomerNotice();
            customerNotice.setCompanyRecord(companyRecord);
            customerNotice.setContent("【"+companyRecord.getCompanyId().getName()+"】的巡查执法记录【"+companyRecord.getId()+"】已超过完成时间，请及时审批");
            customerNotice.setTitle("巡查记录审批完成提示");
            customerNotice.setCreateDate(new Date());
            customerNotice.setEnabled(1);
            customerNotice.setModifyDate(new Date());
            customerNotice.setReceiverUser("");
            customerNotice.setSendUser("");
            customerNoticeRepository.save(customerNotice);
        }
    }
}
