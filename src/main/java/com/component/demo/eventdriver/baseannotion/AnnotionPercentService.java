package com.component.demo.eventdriver.baseannotion;

import com.component.demo.eventdriver.UserRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @Author: Dely
 * @Date: 2019/12/10 13:20
 */

@Service
public class AnnotionPercentService {
    @EventListener
    public void onPercent(UserRegisterEvent event) {
        System.out.println("积分已经添加"+ event.getSource());
    }
}
