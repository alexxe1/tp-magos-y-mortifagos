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
<img width="2348" height="2346" alt="diagrama_final" src="https://github.com/user-attachments/assets/861f28d7-f4d3-44d7-94b1-1f77a4dfca84" />

---

## Patrones de Diseño Aplicados

| Patrón | Dónde se aplica |
|--------|----------------|
| **Factory** | `Reclutador` centraliza la creación de personajes a partir de `TipoMago` y `TipoMortifago`. `Hechiceria` centraliza la creación de hechizos a partir de `TipoHechizo`. `TiendaDeObjetos` centraliza la creación de objetos a partir de `TipoObjeto` |
| **Strategy** | `Hechizo` es una interfaz con distintas implementaciones intercambiables (`Expelliarmus`, `AvadaKedavra`, `Protego`, `ExpectoPatronum`). |
| **Template Method** | `calcularDaño` y `calcularCuracion` son abstractos en `Personaje` e implementados de forma distinta en `Mago` y `Mortifago`, sin que el hechizo conozca el tipo del lanzador. |

> [!NOTE]
Los enum (`TipoObjeto`, `TipoMago`, `TipoMortifago` y `TipoHechizo`) tienen como único propósito facilitar la instanciación de los objetos. Una vez creados, el comportamiento de la aplicación depende exclusivamente del polimorfismo y no de los valores de estos enum.
---

## Cómo ejecutar

1. Clonar el repositorio
2. Importar en Eclipse: `File -> Import -> Git -> Projects from Git`
3. Ejecutar la clase `Main` dentro del paquete `juego`
