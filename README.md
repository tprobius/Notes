# In progress
### Started 13.10.2023

# Notes
Notes app with favorite TDD, MVVM, Clean Architecture

### Учебный проект по TDD, MVVM, Clean architecture

[User story](https://www.figma.com/file/qDHVuojUkUOkn2yWveRxKp/Notes?type=design&node-id=0-1&mode=design&t=79RMyRNU54IIRdEs-0)  
[Прототип в Figma](https://www.figma.com/file/qDHVuojUkUOkn2yWveRxKp/Notes?type=design&node-id=8-2&mode=design&t=79RMyRNU54IIRdEs-0)

### Запуск приложения.

Клонировать ветку `master` этого репозитория и импортировать в **Android Studio**
```bash
ssh:
git@github.com:tprobius/Notes.git
```
или

```bash
https:
https://github.com/tprobius/Notes.git
```

Запустить на эмуляторе утройства в Android Studio.

### Генерация APK.

В Android Studio:
1. ***Build*** menu
2. ***Generate APK...***
3. Установить приложение на телефон.

### Стек.

Проект реализован с применением подхода TDD, MVVM + UDF и Clean Architecture. 
AdapterDelegates используется на перспективу дальнейшего развития проекта - для поддержки отображения различных вариантов элементов списка.

- Kotlin
- Clean Architecture
- MVVM + UDF
- View binding
- Coroutine
- Dagger/Hilt
- Room
