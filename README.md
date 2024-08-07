<h1>О проекте</h1>
<b>SecondHand</b> – это учебный проект, в котором была сделана платформа для перепродажи вещей, где каждый может быстро и легко продать или купить товары б/у. 
<hr>
<h2>Оглавление</h2>
<a href="#технологии">Технологии</a><br>
<a href="#разработчики">Команда разработчиков</a><br>
<a href="#старт">Как запустить проект</a><br>
<a href="#использование">Использование приложения</a>
<hr>
<h2 id="технологии">Технологии</h2><br>
Проект разработан с использованием современных технологий и библиотек:<br>
<b>Spring Boot:</b> Ядро приложения, обеспечивающее быстрый старт и удобное управление проектом.<br>
<b>Spring Data JPA:</b> Упрощает работу с базами данных, используя Java Persistence API.<br>
<b>Spring Security:</b> Обеспечивает безопасность приложения, управляя аутентификацией и авторизацией.<br>
<b>MapStruct:</b> Помогает в маппинге объектов, упрощая преобразование данных между различными слоями приложения.<br>
<b>PostgreSQL:</b> PostgreSQL используется как основная система управления базами данных.<br>
<b>Liquibase:</b> Управляет версиями базы данных, позволяя безопасно вносить изменения.<br>
<b>Lombok:</b> Уменьшает количество шаблонного кода, автоматически генерируя стандартные методы (например, getters и setters).
<hr>
<h2 id="разработчики">Команда разработчиков</h2><br>
Павел - Backend Developer<br>
GitHub: <a href="https://github.com/FeralBerry">FeralBerry</a><br>
<br>
Михаил - Backend Developer<br>
GitHub: <a href="https://github.com/miksor2023">miksor2023</a><br>
<h2 id="старт">Как запустить проект</h2><br>
Проект состоит из двух основных частей: бэкенда и фронтенда. Чтобы успешно запустить полнофункциональное приложение, необходимо запустить обе части.
<hr>
<h3>Настройка и запуск бэкенда</h3>
Клонирование репозитория:<br>
<code>git clone https://github.com/AnvilCoder/SecondHand.git </code><br>
Настройка базы данных:<br>
Убедитесь, что у вас установлен PostgreSQL.
Создайте базу данных, которую будет использовать приложение.
Настройте параметры подключения к базе данных в файле конфигурации проекта application.properties. <br>
Запуск приложения:<br>
Откройте проект в вашей IDE.
Запустите приложение, используя Spring Boot.
<hr>
<h3>Настройка и запуск фронтенда</h3>
Фронтенд-часть сайта упакована в Docker контейнер для удобства развёртывания.
<br>
<hr>
<h3>Установка Docker:</h3>
Если у вас ещё не установлен Docker, скачайте и установите Docker Desktop по ссылке: <a href="https://www.docker.com/products/docker-desktop/">Docker Desktop</a>.
<hr>
<h3>Запуск фронтенда через Docker:</h3>
Откройте командную строку или терминал.
Выполните следующую команду для запуска фронтенда:
<code>docker run -p 3000:3000 --rm ghcr.io/bizinmitya/front-react-avito:v1.22</code>
После выполнения команды фронтенд будет доступен на http://localhost:3000.
