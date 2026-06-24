# Day 05 – Fresh Ingredients Counter

## 📌 Problema
Existen un conjunto de ingredientes con ID y un conjunto de rangos de IDs de ingredientes frescos.

El objetivo es obtener el número de ingredientes frescos.

---

## 🧠 Soluciones
El problema se modela con una lista de IDs de ingredientes `List<Long>`
y una lista de rangos de IDs `List<IDRange>`

Se aplican dos estrategias:

- **Parte 1:** cuenta de ingredientes frescos desde la lista de IDs de ingredientes
- **Parte 2:** cuenta de ingredientes frescos desde la lista de rangos de IDs de ingredientes frescos

---

## 🏗  Arquitectura

- `IDRange` → modelo de dato (lower bound, upper bound)
- `Pair` -> modelo de dato wrapper (`List<IDRange>` + `List<Integer>` de IDs)
- `Day05Parser` → transforma el input en un `Pair`
- `Day05` → solver

---

## 🧩 Decisiones de diseño

- Se separa `Parser` y `Solver` para mantener **SRP**
- El estado del problema se encapsula en el propio solver `Day03` para evitar sobreingeniería
- Se usan dos métodos de conteo:
    - verificación de inclusión de cada ID en algún rango (`numIngredientsFresh()`)
    - fusión de rangos (`merge()`)

---

## 🔷 Parte 1

Objetivo: obtener la cuenta de ingredientes frescos desde una lista de IDs de ingredientes.

Se parte de: 
- `List<Integer>` con IDs de ingredientes  
- `List<IDRange>`

Se iteró por la `List<Integer>`, y para cada ID, se verifica si está incluido en algún rango de `List<IDRange>`. 
Si pertenece a alguno, se incrementa el contador que, al final de todas las iteraciones, será devuelto.

---

## 🔷 Parte 2

Objetivo: obtener la cuenta de ingredientes frescos desde los rangos de IDs válidos

Se parte de:
- `List<Integer>` con IDs de ingredientes
- `List<IDRange>`

En este caso no se usa la lista de IDs de ingredientes `List<Integer>`, sino que se utiliza la fusión de 
todos los rangos de `List<IDRange` que sean contiguos o se solapen.

Tras la fusión, se retorna la suma de todas las diferencias de límites (upper bound - lower bound para cada rango)

---