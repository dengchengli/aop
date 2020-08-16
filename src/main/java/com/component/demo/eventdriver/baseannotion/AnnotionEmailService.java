package com.component.demo.eventdriver.baseannotion;

import com.component.demo.eventdriver.UserRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @Author: Dely
 * @Date: 2019/12/10 13:19
 */

@Service
public class AnnotionEmailService {
    @EventListener
    public void onEvent(UserRegisterEvent event) throws Exception {
        System.out.println("邮件已经发送！"+ event);
    }
}
