## In progress
[Project board](https://github.com/users/tprobius/projects/2).  
[Project prototype](https://www.figma.com/file/qDHVuojUkUOkn2yWveRxKp/Notes?type=design&node-id=8-2&mode=design).

# Notes
Notes app with favorite list.

### MVVM, Clean architecture project

#### Description

The application allows you to manage your notes.  
The following functions are available now:
  
  - add a new note;
  - see existing notes list;
  - edit an existing note;
  - delete / restore a note;
  - add a note to the favorites list;
  - see favorite notes list.

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


### Tech stack
- Kotlin
- Coroutines
- Clean architecture
- MVVM + UDF
- View binding
- Room
- Cicerone
- Koin

### Backlog

- [x] Add favorite notes list
- [x] Add sorting of notes by date/title
- [x] Add localization support depends on system language
- [ ] Add localization support depends on app language __IN PROGRESS__
- [ ] Add night mode support __IN PROGRESS__
- [ ] Add saving sorting settings between application launching
- [ ] Change app style
- [ ] Сover the application with tests
- [ ] Fix navigation backstak 
