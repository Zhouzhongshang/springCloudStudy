package com.zhou.servicefeign.controller.zzs.thread;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @program: sc-f-chapter1
 * @description:
 * @author: zzs
 * @create: 2020-12-18 09:56
 **/
@Data
@NoArgsConstructor
public class CallBackFailOneMin {

    public static void main(String[] args) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

            }
        };

    }

}
