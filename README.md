<h1 align="center">MyWMS</h1>
<h4 align="center"><i>Простой учебный проект склада (Warehouse Management System)</i></h4>

![](https://kartinki.pics/uploads/posts/2022-12/1670248886_15-kartinkin-net-p-sklad-kartinka-dlya-prezentatsii-pinterest-16.jpg)

---

<h2 align="center">Стек технологий</h2>

- Java 17
- Java Servlets(Jakarta EE)
- MySQL
- TomCat 11
- Maven
- JDBC

---

<h2 align="center">Функционал</h2>

- Просмотр списка категорий
- Просмотр списка товаров по категории

---

<h2 align="center">Как запустить проект</h2>

1. Клонировать проект:
```bash
git clone https://github.com/TeddyFloodK-2SO/myWMS.git
```
2. Собрать проект:
```bash
mvn clean package
```
3. Скопировать .war в папку webapps TomCat.
4. Запустить TomCat
5. Открыть в браузере:
- http://localhost:8080/myWMS-1.0-SNAPSHOT/categories
- http://localhost:8080/myWMS-1.0-SNAPSHOT/items?categoryId=1 или кликнуть выбранную категорию

---

<h2 align="center">Назначение</h2>

<h4 align="center"><i>Проект создан для демонстрации базовых навыков работы с Java, Servlets, JDBC и MySQL</i></h4>







