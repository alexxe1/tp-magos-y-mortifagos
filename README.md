# Magos y Mortífagos

> Simulación de batallas entre magos y mortífagos implementada en Java.  
> **UNLaM | Paradigmas de Programación | 2026**

---

## Integrantes

| Nombre |
|--------|
| Belfiore, Ramiro Valentín |
| Francisco, Vladimir |
| Torrente Medici, Ignacio Agustín |
| Torczuk, Elías Alexei |

---

## Diagrama de Clases

Esta fue la primera iteración
<img width="2150" height="1312" alt="diagrama" src="https://github.com/user-attachments/assets/063b0c00-fdae-4b54-b7cf-981e1379b738" />

Este es el diagrama de clases FINAL
<img width="2343" height="2346" alt="diagrama" src="https://github.com/user-attachments/assets/eaf66762-a6cb-4606-8661-7de8ae605949" />

---

## Patrones de Diseño Aplicados

| Patrón | Dónde se aplica |
|--------|----------------|
| **Factory** | `Reclutador` centraliza la creación de personajes. `Hechiceria` centraliza la creación de hechizos a partir de `TipoHechizo`. `TiendaDeObjetos` centraliza la creación de objetos. |
| **Strategy** | `Hechizo` es una interfaz con distintas implementaciones intercambiables (`Expelliarmus`, `AvadaKedavra`, `Protego`, `ExpectoPatronum`). |
| **Template Method** | `calcularDaño` y `calcularCuracion` son abstractos en `Personaje` e implementados de forma distinta en `Mago` y `Mortifago`, sin que el hechizo conozca el tipo del lanzador. |

---

## Cómo ejecutar

1. Clonar el repositorio
2. Importar en Eclipse: `File -> Import -> Git -> Projects from Git`
3. Ejecutar la clase `Main` dentro del paquete `juego`
