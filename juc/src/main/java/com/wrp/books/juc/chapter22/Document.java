package com.wrp.books.juc.chapter22;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * balking模式，如果有B线程处理了，A线程就不处理
 * @author wrp
 * @since 2025年01月15日 18:46
 **/
public class Document {
    // 是否处理的标志
    private boolean changed = false;

    private final List<String> content = new ArrayList<>();

    // 自动保存线程
    private static AutoSaveThread autoSaveThread;

    private final FileWriter writer;

    private Document(String path, String name) throws IOException {
        this.writer = new FileWriter(new File(path, name), true);
    }

    public static Document open(String path, String name) throws IOException {
        Document doc = new Document(path, name);
        autoSaveThread = new AutoSaveThread(doc);
        autoSaveThread.start();
        return doc;
    }

    public void edit(String content) {
        synchronized (this) {
            this.content.add(content);
            this.changed = true;
        }
    }

    public void close() throws IOException {
        autoSaveThread.interrupt();
        writer.close();
    }

    public void save() throws IOException {
        synchronized (this) {
            // 判断文档是否被修改
            if(!changed) {
                return;
            }

            System.out.println(Thread.currentThread() + " execute the save action");
            for (String s : content) {
                writer.write(s);
                writer.write('\n');
            }
            writer.flush();
            // 保存后，调整标记
            changed = false;
            this.content.clear();
        }
    }

}
