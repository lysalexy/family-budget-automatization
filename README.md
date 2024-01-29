# Web-приложение для автоматизации семейного бюджета

Проект представляет собой приложение для ведения семейного бюджета. База данных хранится в СУБД MySQL, спроетироовано
REST API рамках дисциплины "Объектно-ориентированное программирование"
Санкт-Петербургского Политехнического университета им. Петра Великого (ВШПИ, Программная инженерия, 2-ой курс).

## Схема базы данных
![база данных](https://github.com/Lysenko-Aleksandra/family-budget-automatization/assets/78423459/dcd5ec3d-d425-4d0b-9dfa-9e1ccad45599)

### Функциональность приложения

Подробное описание функционала с демонстрацией исходного вида приложения представлено в
виде [отчета](https://disk.yandex.ru/i/VPuQLllgM0SM3A)

## Стек технологий

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![Bootstrap](https://img.shields.io/badge/bootstrap-%238511FA.svg?style=for-the-badge&logo=bootstrap&logoColor=white)

## Запуск и установка

Требуется запущенный MySQL.
Необходимо заполнить application.properties следующими данными

| Название переменной внутри url | Описание                  |
|--------------------------------|---------------------------|
| spring.datasource.url          | url базы данных           |
| spring.datasource.username     | Пользователь MySQL        |
| spring.datasource.password     | Пароль пользователя MySQL |
