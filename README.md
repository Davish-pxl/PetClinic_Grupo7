# Spring Boot : Application PetClinic

## Features  

### 1.  Unit Test  --> Tag v1.0.0

mvn test -Dspring.profiles.active=h2

---

## 👥 Grupo 7 — Pruebas para OwnerService

### Flujo de trabajo

1. `main` está protegida: nadie hace push directo.
2. Cada integrante crea su rama desde `main`:
   - Integrante A → `feature/owner-crud`
   - Integrante B → `feature/owner-busquedas`
   - Integrante C → `feature/owner-errores`
3. Cada uno escribe sus pruebas en `src/test/java/com/tecsup/petclinic/services/OwnerServiceTest.java`, hace commit con su nombre y correo reales y abre un Pull Request hacia `main`.
4. Otro integrante revisa y aprueba el PR antes del merge.
5. Como los tres editan el mismo archivo, el 2.º y 3.º PR tendrán conflicto: se resuelve con `git pull origin main` en la rama, se dejan las pruebas de ambos y se hace commit.
6. Antes de cada merge: `./mvnw test` debe pasar.

### Datos de prueba (src/main/resources/data.sql)

| id | Nombre | Apellido | Ciudad |
|----|--------|----------|--------|
| 1 | George | Franklin | Madison |
| 2 | Betty | Davis | Sun Prairie |
| 4 | Harold | Davis | Windsor |
| 6 | Jean | Coleman | Monona |
| 7 | Jeff | Black | Monona |

### Integrantes y pruebas

| Integrante | Rama | Pruebas |
|------------|------|---------|
| A — _(nombre)_ | `feature/owner-crud` | `testCreateOwner`, `testUpdateOwner`, `testDeleteOwner` |
| B — _(nombre)_ | `feature/owner-busquedas` | `testFindOwnerById`, `testFindByLastName`, `testFindByCity` |
| C — _(nombre)_ | `feature/owner-errores` | `testFindOwnerById_NotFound`, `testDeleteOwner_NotFound`, `testFindByLastName_Empty` |

### Conflictos resueltos

_(Describir aquí el conflicto, en qué archivo ocurrió y cómo se resolvió.)_
