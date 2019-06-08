# Многомодульный maven. Многопоточность. XML. Веб сервисы. Удаленное взаимодействие

## [Программа проекта](Description.md#Программа-проекта-1)

## Ветки репозитория
- master - соответствуюет развитию проекта по версии автора курса
- homework - развитие проекта по моей версии, с учетом изменений от автора
- HW1..HW11 - домашние задания

### _Разработка полнофункционального многомодульного Maven проекта_
#### состоящего из 3-х веб приложений:

![image](https://cloud.githubusercontent.com/assets/13649199/23876457/ab01ff0a-084e-11e7-964f-49c90579fac9.png)

- **приложение импорта** из XML (JAXB, StAX, XPath, XSLT)
- **многопоточного почтового веб-сервиса** (JavaMail, java.util.concurrent, JAX-WS, MTOM, хендлеры авторизации, логирования и статистики) 
- **веб приложения отправки почты с вложениями**
  - по SOAP (JAX-WS, MTOM)
  - по JAX-RS (Jersey)
  - по JMS ([ActiveMQ](http://activemq.apache.org/))
  - через [AKKA](http://akka.io/)
  - используя асинхронные сервлеты 3.0
- сохранение данных в PostgreSQL используя [jDBI](http://jdbi.org/)
- миграция базы [LiquiBase](http://www.liquibase.org/)
- использование в проекте [Guava](https://github.com/google/guava/wiki), [Thymleaf](http://www.thymeleaf.org/), [Lombok](https://projectlombok.org/), [StreamEx](https://github.com/amaembo/streamex), 
[Typesafe Config](https://github.com/typesafehub/config), [Java Microbenchmark JMH](http://openjdk.java.net/projects/code-tools/jmh)

### Используемое ПО
-  <a href="http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html">JDK8</a>
-  <a href="http://git-scm.com/downloads">Git</a>
-  <a href="http://www.jetbrains.com/idea/download/index.html">IntelliJ IDEA</a>

### История ветки `homework`

#### HW1
- реализация в принципе совпадает с реализацией автора курса
- добавлены оптимизированные реализации автора курса

#### HW2
- схема не определяет порядок элементов в Payload, поэтому StAX реализация не оптимальна
(все данные при парсинге остаются в памяти)
- в схеме не используются элементы верхнего уровня
- в схеме вместо xs:ID используется xs:key
- структура данных сделана немного по-другому
- смерджены решения домашнего задания от автора курса

#### HW3
- схема данных будет использована от автора курса, чтобы упростить работу над проектом
- смерджил свой вариант ДЗ
- моя реализация загрузки и парсинга находится в [FileUpload.java](web/upload/src/main/java/ru/javaops/masterjava/web/FileUpload.java)
(исключена из запуска веб-контейнера)
- при многопоточной реализации JaxbParser в отличие от автора я оставил утильные методы marshal/unmarshal.
В остальном решение проблемы такое же. 

#### HW4
- обязательные задания достаточно простые
- опциональное задание не осилил самостоятельно

4 домашнее задание взято у автора курса

#### HW5
- обязательные задания сделаны
- не смог сделать связь с городами при импорте пользователей

В отличие от моих реализаций в DAO автора нет связанных объектов - вместо этого используются внешние ключи.
У городов нет суррогатного ключа - используется текстовый id как в xml. Почему-то я даже не думал делать 
таким образом, а пытался сделать по подобию JPA/Hibernate.

не особо нравится то как я сделал, посмотрю что там у автора курса. Вообще многие решения идейно совпадают,
но реализация хромает

#### HW6
- В своем решении не сделал тесты DAO для сохранения результатов отправки e-mail.
- Вместо того чтобы инициализировать DBI перед публикацией веб-сервиса(простое
действие автора курса), сделал деплой веб-сервиса в томкат(полезно :) )
