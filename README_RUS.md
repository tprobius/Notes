## В работе

# Notes
Приложение Заметки со списком избранных.

### MVVM, Clean architecture project

#### Описание

Приложение для управления заметками.  
В настоящий момент доступны следующие функции:
  
  - добавление новой заметки;
  - просмотр списка всех заметок;
  - редактирование существующей заметки;
  - удаление / восстановление заметки;
  - добавление заметки в списко избранного;
  - просмотр списка избранных заметок.

<p  align="center" >  
    <img src="./screenshots/Screenshot_20231220_164713.png" alt="notes_list_screen" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20231220_164734.png" alt="new_note_screen" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20231220_164759.png" alt="new_note_screen" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20231220_164814.png" alt="notes_list_screen" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20231220_164838.png" alt="notes_list_screen" width="19%" height="auto">
  </p>
  
  <p align="center"> 
    <img src="./screenshots/Screenshot_20231220_164910.png" alt="add_note_to_favorite_list" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20231220_164929.png" alt="delete_note" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20231220_164952.png" alt="restore_deletion" width="19%" height="auto">
    <img src="./screenshots/Screenshot_20231220_165018.png" alt="restore_deletion" width="19%" height="auto">
</p>


### Стек технологий
- Kotlin
- Coroutines + Flow
- Clean architecture
- MVVM + UDF
- View binding
- Room
- Cicerone
- Koin

### Backlog

- [x] Добавить список избранных заметок
- [x] Добавить сортировку по названию / дате
- [ ] Добавить поддержку русского языка __В РАБОТЕ__
- [ ] Добавить поддержку тёмной темы __В РАБОТЕ__
- [ ] Поменять стиль приложения
- [ ] Покрыть приложение тестами
- [ ] Пофиксить backstack навигации
