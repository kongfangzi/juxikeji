package com.juxi.lingshibang.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hongqiang.chai
 * @date 2019/11/28 16:26
 */
@Component
@EnableScheduling
public class SchedulerJob {

    /**
     * 定时向dmp中推送数据
     */
//    @Scheduled(cron = "*/10 * * * * ?")
//    public void send2Dmp() {
//        List<UserFaceEntity> faceList = userFaceService.getUserFaceByDate();
//        if(!faceList.isEmpty()){
//            for(UserFaceEntity userFaceEntity:faceList){
//                DealerInfoEntity dealerInfoEntity=dealerInfoService.getDealerByCamera(userFaceEntity.getCamaraName());
//                if(dealerInfoEntity!=null){
//                    userFaceEntity.setDealerCode(dealerInfoEntity.getDealerCode());
//                    userFaceEntity.setDealerName(dealerInfoEntity.getDealerName());
//                    consumerService.send(userFaceEntity);
//                }
//                userFaceService.updateByUserId(userFaceEntity.getId());
//            }
//        }
//    }
}
