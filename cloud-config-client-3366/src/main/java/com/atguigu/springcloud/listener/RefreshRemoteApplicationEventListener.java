package com.atguigu.springcloud.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RefreshRemoteApplicationEventListener implements ApplicationListener<RefreshRemoteApplicationEvent> {

    @Override
    public void onApplicationEvent(RefreshRemoteApplicationEvent event) {
        StringBuffer stringBuffer = new StringBuffer();
        String message = stringBuffer
                .append("\nDestinationService = " + event.getDestinationService())
                .append("\nId = " + event.getId())
                .append("\nClassName = " + event.getSource().getClass().getName())
                .append("\nOriginService" + event.getOriginService())
                .toString();
        log.info("Event received, message = {}", message);
    }
}
