# 📝 Note-Taking App

A simple Note-Taking Android app built with **Jetpack Compose**, **Room**, and **MVVM Architecture** as part of a 1-hour internship challenge. The app allows users to **add**, **edit**, **delete**, and **search** notes with local persistence using Room database.

---

## 📱 Features

- ✅ Add new notes  
- 📝 Edit existing notes  
- ❌ Delete notes with **confirmation dialog**  
- 🔄 Undo delete via **Snackbar**
- 🔍 Real-time search through notes
- 💾 Persistent storage using **Room**

---

## 🛠 Tech Stack

| Layer            | Technology Used                        |
|------------------|----------------------------------------|
| UI               | Jetpack Compose, Material 3            |
| State Management | ViewModel + LiveData                   |
| Persistence      | Room Database                          |
| Language         | Kotlin                                 |
| Architecture     | MVVM                                   |
| Minimum SDK      | 31                                     |


---

## 🧠 Architecture
```plaintext
MainActivity
    └── NoteViewModel
            └── NoteRepository (DAO calls)
                    └── Room Database (NoteDatabase + NoteDao)


com.prathambudhwani.notestask
│
├── data
│   ├── Note.kt
│   ├── NoteDao.kt
│   └── NoteDatabase.kt
│
├── ui
│   ├── NoteScreen.kt
│   └── NoteViewModel.kt
│
└── MainActivity.kt
