# 📚 Tareas de Estudio — CRUD con Room y Firebase

Aplicación Android desarrollada con **Jetpack Compose** para organizar tareas y pendientes de estudio. Las tareas se guardan primero en el celular con **Room** y se sincronizan con **Cloud Firestore** usando **Firebase Authentication anónima**, así la app funciona aunque no haya internet.

## Funcionalidades

- Pantalla de inicio con acceso a la lista de tareas.
- Lista de tareas con materia, fecha de entrega y prioridad (Alta, Media, Baja).
- Marcar una tarea como completada desde la lista o el detalle.
- Contador de tareas pendientes y completadas.
- Crear y editar tareas con formulario validado.
- Eliminar tareas con diálogo de confirmación.
- Sincronización automática después de cada cambio y botón para sincronizar manualmente.
- Mensajes de éxito y error después de cada operación.

## Flujo de navegación

```
Inicio → Lista de Tareas → Detalle de Tarea → Editar Tarea
               │                  ↓ (Eliminar)
               │            Lista de Tareas
               ↓
          Nueva Tarea → Lista de Tareas
```

## Parámetros que viajan entre pantallas

| Parámetro | Descripción |
|---|---|
| `id` | Identificador (UUID) de la tarea seleccionada (Detalle y Editar) |

## Arquitectura

El proyecto sigue **MVVM** con separación por capas:

```
com.example.tareasestudio
├── data
│   ├── local        → Room: TareaEntity, TareaDao, AppDatabase
│   ├── remote       → Firestore: TareaDto, FirestoreTareaDataSource
│   ├── mapper       → Conversión Entity ↔ Dominio ↔ Dto
│   └── repository   → TareaRepositoryImpl (Room + Firestore + sincronización)
├── domain
│   ├── model        → TareaModel, Prioridad, ResultadoSync
│   ├── repository   → TareaRepository (interfaz)
│   └── useCase      → Obtener, Guardar, Eliminar, CambiarEstado, Sincronizar
├── di               → Módulos de Hilt (Database, Firebase, Repository)
├── navigation       → Rutas y NavHost
└── ui
    ├── screen       → Pantallas completas
    ├── section      → Partes grandes de cada pantalla
    ├── composable   → Componentes reutilizables
    ├── state        → TareaUIState
    ├── viewModel    → TareaViewModel
    └── theme        → Colores y tema
```

## ¿Cómo funciona la sincronización?

1. Cada vez que se crea, edita o elimina una tarea, se guarda en **Room** y queda marcada como `pendiente`.
2. La app inicia sesión anónima en Firebase y obtiene un **UID**.
3. Las tareas pendientes se suben a Firestore en la ruta `users/{uid}/tareas/{id}`.
4. Luego se descargan las tareas de la nube y se actualizan en Room si son más recientes.
5. Al eliminar no se borra el registro, se marca como `eliminada = true` para que el cambio también llegue a la nube.

## Tecnologías

- Kotlin + Jetpack Compose + Material 3
- Room (base de datos local)
- Firebase Authentication (anónima)
- Cloud Firestore
- Hilt (inyección de dependencias)
- Navigation Compose
- Coroutines + StateFlow
- Material Icons Extended

## Configuración

1. Clonar el repositorio y abrirlo en Android Studio.
2. Crear un proyecto en [Firebase Console](https://console.firebase.google.com) y registrar una app Android con el paquete `com.example.tareasestudio`.
3. Descargar `google-services.json` y colocarlo en la carpeta `app/`.
4. En Firebase, activar **Firestore Database** y el método de acceso **Anónimo** en Authentication.
5. Publicar las reglas de seguridad de Firestore (cada usuario solo puede leer y escribir sus propias tareas).
6. Sincronizar Gradle y ejecutar la app.

## Ramas del proyecto

| Rama | Contenido |
|---|---|
| `feature/arquitectura-room` | Configuración de Gradle, Hilt, capa de dominio y Room |
| `feature/firebase-sync` | Firebase Auth, Firestore y sincronización |
| `feature/ui-tareas` | Pantallas, componentes, navegación y ViewModel |
