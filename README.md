## Introducción

Una aplicación Android creada en Kotlin que permite consultar productos y ver su detalle.

## Arquitectura y Tecnologías Utilizadas

- **Kotlin:** Elegí Kotlin porque es el lenguaje recomendado por Google para el desarrollo de aplicaciones Android. Su seguridad de tipo, concisión y compatibilidad con Java lo hacen ideal para este proyecto.
- **Jetpack Compose:** Decidí implementar Jetpack Compose para construir la UI por su enfoque moderno y declarativo, lo que facilita la creación de interfaces complejas con menos código y en menos tiempo.
- **Coroutines:** La decisión de usar Coroutines se debe a su eficiencia en la gestión de tareas asíncronas, lo que permite una mejor experiencia de usuario al evitar bloqueos en la UI.
- **JUnit4:** Elegí JUnit4 para las pruebas unitarias por su robustez y amplia adopción en la comunidad de desarrollo, asegurando que nuestra lógica de negocio funcione como se espera.
- **Mockk:** Seleccioné Mockk específicamente para Kotlin por su capacidad para simular comportamientos de manera efectiva, facilitando el aislamiento de las pruebas unitarias.
- **MVVM + CLEAN:** La arquitectura MVVM, complementada con principios CLEAN, fue elegida para su separación de responsabilidades, lo que facilita tanto el mantenimiento como las pruebas del código.
- **LiveData:** Opté por LiveData dentro del patrón MVVM para manejar la comunicación entre los ViewModels y las vistas, permitiendo una actualización eficiente y segura de la UI.
- **Navigation Compose:** La implementación de Navigation Compose se debe a su simplificación de la gestión de la navegación en aplicaciones basadas en Jetpack Compose, mejorando la cohesión de la experiencia de usuario.
- **Retrofit:** Decidí utilizar Retrofit para las llamadas a la API por su eficiencia, facilidad de uso y compatibilidad con Kotlin, facilitando la obtención de datos.
- **Hilt:** La elección de Hilt para la inyección de dependencias se basa en su diseño específico para Android, lo que simplifica la gestión de dependencias y mejora la escalabilidad del proyecto.
- **Flow** me permite trabajar con datos de forma reactiva, facilitando el desarrollo de aplicaciones que responden en tiempo real a cambios en los datos o en el estado de la aplicación.
![Screenshot_2024-10-21-16-45-02-859_com c4pn91 mliproject](https://github.com/user-attachments/assets/a35b3463-54c3-44f4-9eee-6717b9bad23d)
![Screenshot_2024-10-21-16-45-05-775_com c4pn91 mliproject](https://github.com/user-attachments/assets/5e97d139-5519-4011-a266-b4aa36269f0f)
![Screenshot_2024-10-21-16-45-10-367_com c4pn91 mliproject](https://github.com/user-attachments/assets/3182dbca-2791-4273-af32-f08d209e7e1d)
![Screenshot_2024-10-21-16-45-14-337_com c4pn91 mliproject](https://github.com/user-attachments/assets/f03ad711-d1af-4e70-87f4-6c679053dfc2)
![Screenshot_2024-10-21-16-45-17-819_com c4pn91 mliproject](https://github.com/user-attachments/assets/4472971e-17ee-4ded-8d9f-01ad9da72acb)
