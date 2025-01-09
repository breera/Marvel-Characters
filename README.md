# Marvel Characters App

Marvel Characters App is a sample project showcasing Clean Architecture principles with a modularized approach. It demonstrates fetching and displaying Marvel characters, utilizing the Paging 3 library for efficient data handling and Kotlin-based architecture components.

## 🏗️ Project Architecture

This project follows **Clean Architecture** principles and is divided into the following modules:

### 1. **Main Module (`app`)**
- Contains the application-level components like `MainActivity`, dependency injection setup, and application entry point (`MarvelCharacterApplication`).

### 2. **Feature Module (`character-feature`)**
- Focuses on handling character-related functionality.
- Follows a layered architecture:
  - **Data Layer:** Handles data fetching from remote sources via `DefaultRepository` and `PagingSource`.
  - **Domain Layer:** Contains use cases and domain models such as `GetCharactersUseCase` and `CharactersRepository`.
  - **Presentation Layer:** Contains UI components for various screens (`detail`, `home`, `pager`, etc.) and state management.

### 3. **Core Module (`core`)**
- Provides shared utility classes, services, and base components used across the app.
- Includes abstractions and common implementations.

### 4. **Theme Module (`theme`)**
- Contains reusable UI themes, styles, and design components for consistent branding across the app.

---

## 📂 Directory Structure

Below is the directory structure for key modules:

### `app`
```
app/
├── src/
│   ├── main/
│   │   ├── java/com/breera/marvelcharactersapp/
│   │   │   ├── di/
│   │   │   │   └── KoinInitializer.kt
│   │   │   ├── MainActivity.kt
│   │   │   ├── MarvelCharacterApplication.kt
│   │   │   └── Route.kt
│   │   └── res/
│   │       └── AndroidManifest.xml
│   ├── test/
│   │   └── ExampleUnitTest.kt
```

### `character-feature`
```
character-feature/
├── src/
│   ├── main/
│   │   ├── java/com/breera/character_feature/
│   │   │   ├── data/
│   │   │   │   ├── dto/
│   │   │   │   ├── mappers/
│   │   │   │   └── remote/
│   │   │   │       ├── CharacterPagingSource.kt
│   │   │   │       ├── CharactersRepositoryImpl.kt
│   │   │   │       ├── DefaultRepository.kt
│   │   │   │       └── DefaultRepositoryImpl.kt
│   │   │   ├── di/
│   │   │   ├── domain/
│   │   │   │   ├── model/
│   │   │   │   │   ├── CharactersRepository.kt
│   │   │   │   │   ├── GetCharactersUseCase.kt
│   │   │   │   │   └── GetSectionInfoUseCase.kt
│   │   │   ├── presentation/
│   │   │   │   ├── detail/
│   │   │   │   ├── home/
│   │   │   │   ├── pager/
│   │   │   │   ├── previewdata/
│   │   │   │   └── shareddata/
│   │   └── res/
│   │       └── AndroidManifest.xml
```

---

## 🛠️ Tech Stack

- **Kotlin**: Primary language for app development.
- **Paging 3**: Efficient data pagination.
- **Clean Architecture**: Structured and modularized codebase.
- **Koin**: Dependency Injection for simplified DI management.
- **Jetpack Components**: LiveData, ViewModel, etc.
- **JUnit 5**: Unit testing framework.

---

## ⚙️ Features

1. **Marvel Characters List**:
   - Fetches and displays a list of Marvel characters with paginated loading.

2. **Character Details**:
   - Displays detailed information about a selected character.
   - 
3. **LazyLoadImages**:
   - Instead of fetching all images atonce by giving id , it fetches one image from one API using resourceURI as per requirements.

3. **Reusable Components**:
   - A modular and scalable design that ensures reusability.

4. **Themes**:
   - Consistent and customizable app theming.

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Giraffe or later.
- Gradle 8.0+.
- Kotlin 1.9+.

### Steps to Run:
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Open the project in Android Studio.
3. Sync Gradle files.
4. Run the app on an emulator or physical device.

---

## 🔍 Testing

### Unit Testing
Unit tests are written using JUnit 5 and Mockito for mocking dependencies. They are located in the `test` directory of each module.

To run the tests:
```bash
./gradlew test
```
