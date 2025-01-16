package com.wrp.books.juc.chapter26;

/**
 * 说明书
 * @author wrp
 * @since 2025年01月16日 11:06
 **/
public abstract class InstructionBook {

    public final void create() {
        this.firstProcess();
        this.secondProcess();
    }

    protected abstract void firstProcess();

    protected abstract void secondProcess();
}
