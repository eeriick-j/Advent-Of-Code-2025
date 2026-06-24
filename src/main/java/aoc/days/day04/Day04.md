# Day 04 – Available rolls of paper Counter 

## 📌 Problema
Existen un conjunto de rollos de papel, y se quiere contar los que son accesibles.

El objetivo es obtener la suma de la mejor subsecuencia de todos los bancos.

---

## 🧠 Soluciones
El problema se modela con una matriz de rollos de papel.

Se aplican dos estrategias:

- **Parte 1:** contador de rollos accesibles
- **Parte 2:** contador de rollos accesibles con eliminación tras acceso

---

## 🏗  Arquitectura

- `Day04Parser` → transforma el input en una `char[][]`
- `Day04` → solver

---

## 🧩 Decisiones de diseño

- Se separa `Parser` y `Solver` para mantener **SRP**
- El estado del problema se encapsula en el propio solver `Day04` para evitar sobreingeniería
- Se usa un método de validación de accesibilidad de un rollo de papel (`isAccessible`)

---

## 🔷 Parte 1

Objetivo: obtener la suma de rollos accesibles sin eliminación

Se parte de: `char[][]`

Se iteró en el grid y, para cada caracter, si es un rollo de papel, se verifican las 8 posiciones adyacentes. 
Si en ellas hay menos de 4 rollos, se incrementa un contador de rollos accesibles, que será retornado al final.

---

## 🔷 Parte 2

Objetivo: obtener la suma de rollos accesibles con eliminación tras detección

Se parte de: `char[][]`

Se iteró en el grid y, para cada caracter, si es un rollo de papel, se verifican las 8 posiciones adyacentes. 
Si en ellas hay menos de 4 rollos, se incrementa un contador de rollos accesibles, que será retornado al final.

Tras acabar de iterar en todo el grid, podrían haber rollos que antes no eran accesibles y ahora sí
lo son porque algunos rollos adyacentes hayan sido eliminados. 
Se hacen múltiples iteraciones sobre las distintas versiones del grid, hasta que no haya ningún rollo accesible.

---