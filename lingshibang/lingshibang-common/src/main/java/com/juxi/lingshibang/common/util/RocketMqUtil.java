package com.juxi.lingshibang.common.util;//package com.juxi.lingshibang.common.util;
//
//import com.aliyun.openservices.ons.api.Message;
//import com.aliyun.openservices.ons.api.SendResult;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.SerializationUtils;
//
///**
// * mq  topic util
// * @author linn
// */
//@Slf4j
//public class RocketMqUtil {
//
//    /**
//     * 发送消息
//     * @param rocketMQService
//     * @param topic
//     * @param tags
//     * @param object
//     */
//    public static void sendMessage(RocketMQProducer rocketMQService, String topic, String tags, Object object) {
//        SendResult sendResult = rocketMQService.sendMessage(new Message(topic, tags, SerializationUtils.serialize(object)));
//        log.info("send message success. {}", sendResult.toString());
//    }
//
//    /**
//     * 单向发送
//     * @param rocketMQService
//     * @param topic
//     * @param tags
//     * @param object
//     */
//    public static void sendOneWayMessage(RocketMQProducer rocketMQService, String topic, String tags, Object object) {
//        rocketMQService.sendOneWayMessage(new Message(topic, tags, SerializationUtils.serialize(object)));
//    }
//}
