package com.wrp.books.juc.chapter21;

/**
 * {@link ThreadLocal} 案例
 * @author wrp
 * @since 2025年01月15日 18:39
 **/
public class ActionContext {
    private static final ThreadLocal<Object> context =
            ThreadLocal.withInitial(Object::new);

    public static Object get() {
        return context.get();
    }
}
