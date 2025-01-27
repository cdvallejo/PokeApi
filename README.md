# PokeApi - Lista de Pokémon y colección de captura

PokeApi es una aplicación Android que muestra una lista de 150 Pokémon y permite al usuario capturarlos y conservar una colección. Requiere autenticación.
Esta app ha sido desarrollada como tarea para el módulo de Programación Multimedia y Dispositivos Móviles (FP DAM - IES Agudaulce).

## Características principales:

- <b>Autenticación</b> por correo convencional o mediante Google Sign in.
- <b>Visualización:</b> de una lista de Pokémon recogida por petición API (Lista de Pokédex).
- <b>Acción de captura y guardado</b> de Pokémon a una nueva lista (Lista de Pokémon capturados).
- <b>Ajustes:</b>
  * Switch de eliminación de Pokémon capturados (no implementado)
  * Idiomas Inglés y Español (cambia el idioma pero no lo guarda)
  * About: Ventana de información
  * Cerrar sesión: cierre de la sesión de autenticación
- <b>Tecnologías utilizadas:</b>
  * Framework: Android SDK
  * Lenguaje: Java
  * Firebase Authentication para la autenticación y Firebase Database para la base de datos de Pokémon capturados
  * Retrofit para la recogida de datos mediante petición API
  * Componentes:
     * RecyclerView 
     * Fragment
     * Navigation Component
     * View Bindding
     * Coordinator Layout
  
## Instrucciones de uso:
Para descargar el repositorio del proyecto e instalarlo:
 * <b>Opción 1</b>: Pulsar sobre el botón verde CODE y obtener la url Https del proyecto. Desde Android Studio, instalar desde "New Project Version Control".
 * <b>Opción 2</b>: También se puede descargar el zip del proyecto, exportar y abrir localmente.
 * Para poder ejecutar la app hay que tener en cuanta que ha sido compilada con el siguiente Sdk:
   * compileSdk = 34
   * minSdk = 31
   * targetSdk = 34

(App probada con Pixel 8 API 35, Pixel 7a API 34 y Pixel 8 Pro API 31)

## Conclusiones del desarrollador:
Como estudiante de programación, aprender a elaborar una aplicación Android como esta es una oportunidad idónea. La tarea es tan atractiva como exigente para alguien que tiene que adquirir los conocimientos necesarios y aplicar lo ya aprendido.
He tenido sentimientos encontrados a la hora de desarrollarla porque, constantemente, he visto mis grandes limitaciones como estudiante de programación. A pesar de haber superado el primer curso y tener conocimientos de Java, bases de datos, etc.
Intentar levantar esta aplicación me ha supuesto muchos quebraderos de cabeza y frustrarme con aspectos que no he sabido solucionar. Cuando, después de muchas horas tratando de mostrar el listado de los Pokémon por la API, vi en la emulación el hermoso listado de bichitos con su nombre no pude evitar emocionarme... Y eso que no soy precisamente fan de Pokémon.

### He aprendido durante el proceso:
* A organizar mejor el plan de desarrollo: cometí el error de crear la interfaz completa desde el inicio, lo que provocó que, al intentar arrancar desde el Sign In, se atropellaran demasiados errores. Tendría que haber programado una autenticación hacia una pantalla simple y, de ahí, continuar.
* A gestionar opciones con SharedPreferences, si bien no he terminado de comprender el proceso de guardado de idiomas. Me di cuenta de este problema demasiado tarde, estando ya el cambio de idioma aplicado y sin más días para consulta.
* El uso de Retrofit como gestión de API.
* A crear una autenticación por Firebase y una base de datos. La autenticación es lo que más fácil me ha resultado, gracias a los ejemplos de la profesora.
* He comprendido algo mejor la estructura de Activities y navegación por Fragments, recyclersView y sus Adaptadores, aunque me sigue pareciendo un poco abstracto y difícil de seguir por el aspecto tan modular de Android Studio.
* A modificar un layout tras un evento (la transparencia del Pokémon tras ser capturado)

### Espinas clavadas:
* No entiendo por qué la API sólo me ha devuelto correctos los datos de Nombre de Pokémon y la imagen. Mediante logs que puse justo antes de enviar los datos a Firebase, me di cuenta de que el peso, la altura y el tipo (sé que pueden ser varios tipos, pero por simplificar traté de coger sólo uno) me devolvían valores nulos o ceros.
* Como he dicho previamente, el guardado de idiomas: sobretodo sabiendo que, en teoría, es uno de los desafíos más sencillos de la tarea.
* El botón de eliminación de Pokémon, que no he llegado a implementar.

¿He aprendido cosas? ¿Controlo algo mejor lo que ocurre en una app de Android? Sí, pero me decepciona no ser capaz de entender del todo lo que ocurre tras tantas líneas de código y funciones; espero que esa sensación vaya menguando. Haciendo una analogía con el tema de la aplicación, es como si creyeras tener cazado un Pokémon y resulta que éste se escapa... Pero bueno, seguiremos intentando cazarlos.

P.D: Pido disculpas por si la reflexión ha sido un poco larga.

## Capturas de pantalla:
![inicio-sesion](https://github.com/user-attachments/assets/de72dc1c-d2b8-4fc2-98f2-6444ab6b4e59) ![lista-pokedex png](https://github.com/user-attachments/assets/c5f2c816-d3bc-4d6f-913e-f3e03b0b9732) ![lista-capturados png](https://github.com/user-attachments/assets/c9ac6699-6c89-4cf9-be0f-882494d69f3d) ![detalle-pokemon png](https://github.com/user-attachments/assets/943650de-5ed4-4de9-ac93-a324f9037e3b) ![ajustes png](https://github.com/user-attachments/assets/d5ee86e7-a37e-4be2-8ea0-4755c763e284)


