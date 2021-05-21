package com.zhou.servicefeign.controller.zzs.thread.saletickets;

/**
 * @program: sc-f-chapter1
 * @description: 票务库存
 * @author: zzs
 * @create: 2021-05-20 16:19
 **/
public class TicketsInventory {

    private int tickets = 1000;

    public void salesTickets()  {
        if (getTickets()>=1){
            --tickets;
            System.out.println(Thread.currentThread().getName()+":"+tickets);
        }
    }

    protected int getTickets() {
        return tickets;
    }
}
