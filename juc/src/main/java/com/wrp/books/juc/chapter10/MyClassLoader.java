package com.wrp.books.juc.chapter10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author wrp
 * @since 2024年12月10日 16:57
 **/
public class MyClassLoader extends ClassLoader {

    private final static Path DEFAULT_CLASS_DIR = Paths.get("E:","temp", "classloader1");
    private final Path classDir;
    public MyClassLoader() {
        this(DEFAULT_CLASS_DIR);
    }

    public MyClassLoader(Path path) {
        super();
        this.classDir = path;
    }

    public MyClassLoader(Path path, ClassLoader parent) {
        super(parent);
        this.classDir = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = this.readClassBytes(name);
        if(classBytes.length == 0) {
            throw new ClassNotFoundException(name);
        }

        return this.defineClass(name, classBytes, 0, classBytes.length);
    }



    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if(!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException(name);
        }

        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }

    @Override
    public String toString() {
        return "My ClassLoader";
    }
}
