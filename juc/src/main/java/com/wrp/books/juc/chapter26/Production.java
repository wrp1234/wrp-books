package com.wrp.books.juc.chapter26;

/**
 * 产品说明书
 * @author wrp
 * @since 2025年01月16日 11:07
 **/
public class Production extends InstructionBook {
    public final int prodId;

    public Production(int prodId) {
        this.prodId = prodId;
    }

    @Override
    protected void firstProcess() {
        System.out.println("execute the " + prodId + " first process");
    }

    @Override
    protected void secondProcess() {
        System.out.println("execute the " + prodId + " second process");
    }
}
