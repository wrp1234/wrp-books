package com.wrp.books.juc.chapter03;

import java.util.List;

/**
 * @author wrp
 * @since 2024年12月08日 10:32
 */
public class FightQueryExample {
    private final static List<String> fightCompany = List.of("CSA", "CEA", "HNA");

    public static void main(String[] args) {
        List<String> results = search("SH", "BJ");
        System.out.println("============result=================");
        results.forEach(System.out::println);
    }

    private static List<String> search(String original, String destination) {
        List<FightQueryTask> tasks = fightCompany.stream().map(f -> createSearchTask(f, original, destination)).toList();
        tasks.forEach(Thread::start);

        // 阻塞当前线程，等待tasks执行完成
        for (FightQueryTask task : tasks) {
            try {
                task.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return tasks.stream().flatMap(t-> t.get().stream()).toList();
    }

    private static FightQueryTask createSearchTask(String fight, String original, String destination) {
        return new FightQueryTask(fight, original, destination);
    }
}
