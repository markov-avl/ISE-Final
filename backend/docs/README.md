# Video games [Backend]

## Создание проекта

Есть несколько способов создания Spring Boot проекта, был использован классический способ:
1. Перейти на сайт https://start.spring.io.
2. Настроить проект под свои нужды.
   
   Использованные настройки:
   ![spring-initializr](spring-initializr.png)

   - Project: **maven** - наиболее часто используемый сборщик проектов на Java.
   - Language: **Java** - наиболее часто используемый ЯП для Spring Boot проектов.
   - Spring Boot: **3.2.6** - последняя стабильная версия на данный момент.
   - Project Metadata - доп. информация о проекте.
   - Packaging: **Jar** - наиболее часто используемый способ пакетирования Java-приложений.
   - Java: **21** - последняя LTS-версия Java на данный момент.
   - Dependencies:
     * **Lombok** - эта зависимость помогает уменьшить повторяющийся код в Java-приложениях за счёт использования аннотаций. Она автоматически создаёт методы, конструкторы и другие элементы кода, что делает код более читаемым и компактным. 
     * **Spring Web** - эта зависимость позволяет создавать веб-приложения с использованием Spring MVC. Она включает в себя Apache Tomcat в качестве встроенного контейнера, что облегчает разработку и развёртывание веб-приложений. 
     * **Spring Reactive Web** - эта зависимость предназначена для создания реактивных веб-приложений с помощью Spring WebFlux и Netty. Реактивные приложения обрабатывают запросы асинхронно и могут легко масштабироваться. 
     * **Liquibase Migration** - эта зависимость используется для управления миграциями базы данных с помощью Liquibase. Она позволяет создавать и применять изменения в структуре базы данных с помощью файла конфигурации, что облегчает управление изменениями в базе данных. 
     * **PostgreSQL Driver** - эта зависимость предоставляет JDBC и R2DBC драйверы для подключения Java-программ к базе данных PostgreSQL. Она позволяет использовать стандартный Java-код для взаимодействия с базой данных PostgreSQL. 
     * **Validation** - эта зависимость включает в себя Bean Validation с использованием Hibernate Validator. Она помогает проводить валидацию данных в приложении, что улучшает его надёжность и безопасность. 
     * **Spring REST Docs** - эта зависимость используется для документирования RESTful-сервисов с помощью комбинации ручной и автоматической генерации документации с использованием Spring MVC Test. Это помогает создавать полезную и понятную документацию к вашему API.
3. Нажать на кнопку "Generate".
4. Скачать полученный архив.
5. Распаковать архив в удобной директории. 
6. Открыть свою среду разработки (например, IntelliJ IDEA). 
7. Импортировать проект в эту среду разработки.

## Перенос модели БД в проект

1. Создать директорию `./src/main/java/ru/dvfu/videogames` (все перечисленные ниже объекты лучше создавать в этом пакете).
2. Создать абстрактный класс `BaseEntity`, от которого будут наследоваться все остальные сущности БД:
```java
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter                   // Добавляет геттеры всех аттрибутов класса
@Setter                   // Добавляет сеттеры всех аттрибутов класса
@NoArgsConstructor        // Добавляет пустой констурктор класса
@Accessors(chain = true)  // Модифицирует сеттеры, делая this возвращаемым объектом сеттеров
@MappedSuperclass         // Помечает данный класс суперклассом для сущностей
public abstract class BaseEntity implements Serializable {

    @Id                                                  // Помечает аттрибут как идентификатор записей в таблице
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Стратегия генерации значения AUTO
    @Column(nullable = false)                            // Помечает аттрибут как NOT NULL
    private Long id;                                     // Тип аттрибута id - BIGINT

}
```
3. Создать сущность (класс) `Genre`:
```java
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString                 // Добавляет человеко-читаемый вывод объекта
@Builder                  // Добавляет билдер объекта
@Entity                   // Помечает класс как сущность
public class Genre extends BaseEntity {
    
    // Идентификатор сущности возьмется из BaseEntity и будет одинаковым для всех наследумеых сущностей

    @Column(nullable = false, unique = true)  // Помечает аттрибут как NOT NULL UNIQUE
    private String name;                      // Тип аттрибута name - VARCHAR

}
```
4. Создать сущности `Publisher` и `Platfrom` по аналогии с `Genre`.
5. Создать сущность `Game`:
```java
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@Builder
@Entity
public class Game extends BaseEntity implements Serializable {

    @ManyToOne                     // Помечает связь с Publisher как n:1
    @JoinColumn(nullable = false)  // Помечает связь с Publisher как NOT NULL
    private Publisher publisher;

    @ManyToOne                     // Помечает связь с Genre как n:1
    @JoinColumn(nullable = false)  // Помечает связь с Genre как NOT NULL
    private Genre genre;

    @Column(nullable = false)
    private String name;

}
```
6. Создать сущность `ReleasedGame` по аналогии с `Game` (для `ReleasedGame.year` взять тип `Integer`).
7. Создать перечисление `Region`:
```java
public enum Region {

    NA,
    EU,
    JP,
    GLOBAL,
    OTHER

}
```
8. Создать сущность `Sale`:
```java
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@Builder
@Entity
public class Sale extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private ReleasedGame releasedGame;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)  // Сохранение и чтение аттрибута будет по строковому значению перечисления
    private Region region;        // Тип аттрибута region - VARCHAR

    @Column(nullable = false)
    private Double numberOfSales;  // Тип аттрибута numberOfSales - DOUBLE PRECISION

}
```

## Миграция данных

WIP