# Effective Java



## Chp1 引言



## Chp2 创建和销毁对象

### Tip1 使用静态工厂方法创建对象

#### 优点

- 可以使用更容易理解的名称，而构造器名称比较固定
  - 常用：from、of、valueOf、instance、create、newInstance、getType、newType、type

```java
public class Person {
    private int age;
    private String name;
    private Traffic traffic;

    public Person() {}

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
    
    public static Person of(int age, String name) {
        return new Person(age, name);
    }
}
```

- 不必创建重复对象，对象可以重复利用
  - 实现单例模式

```java
public class Person {
    private final static Person DEFAULT = new Person();
    
	public static Person of() {
        return DEFAULT;
    }
}
```

- 可以返回类的子类对象，更灵活

- 根据参数不同可以返回不同的类对象

```java
public class Person {
    public static Person generate() {
        return System.currentTimeMillis()%2 == 1 ? new Man() : new Woman();
    }
}
```

- 返回的对象类可以不存在，便于后期扩展
  - SPI，如JDBC

```java
public class Person {
    public static Person generateWithoutClass() {
        ServiceLoader<Traffic> loader = ServiceLoader.load(Traffic.class);
        Person person = generate();
        person.traffic = loader.findFirst().orElseThrow();
        return person;
    }
}
```

#### 缺点

1. 如果类不含有公共的或受保护的构造器，就不能被其他类继承
2. 难以找到对应的静态工厂方法

### Tip2 使用Builder创建对象

> 静态工厂方法和构造器都不能很好的扩展大量的可选参数

#### 优点

- 更容易阅读和编写
  - 结合lombok，更容易实现builder


```java
@Getter
@Builder
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;
}
```

- 更安全，实现不可变类

#### 缺点

- 需要创建Builder类

