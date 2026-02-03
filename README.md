# GALLINACEAS APP S.A. - Sistema de Gestión

Sistema de gestión empresarial para Gallinaceas S.A., especializada en la producción y comercialización de productos avícolas (carne y huevos).

## 📋 Descripción

Aplicación de consola desarrollada en Java que permite gestionar los principales aspectos operativos de la empresa:
- **Información corporativa** y gestión de empleados
- **Control de stock de carne** con sistema de lotes y fechas de caducidad
- **Registro de producción de huevos** por ubicación geográfica
- **Resumen general** con estadísticas y alertas

## 🚀 Requisitos Previos

- **Java 21** o superior
- **Maven 3.6+**

## 📦 Instalación

### 1. Clonar o descargar el proyecto

```bash
cd /home/robot/Documentos/Java/P4/APP_Gallinaceas
```

### 2. Compilar el proyecto

```bash
mvn clean compile
```

## ▶️ Ejecución

### Opción 1: Ejecutar con Maven (Recomendado)

```bash
mvn exec:java
```

### Opción 2: Generar y ejecutar JAR

```bash
# Generar el JAR
mvn clean package

# Ejecutar el JAR
java -jar target/gallinaceas-app-1.0.0.jar
```

### Opción 3: Ejecución directa con javac

```bash
# Compilar
javac -d target/classes src/main/java/com/gallinaceas/*.java

# Ejecutar
java -cp target/classes com.gallinaceas.MainAppConsole
```

## 🎯 Funcionalidades

### 1️⃣ Información de la Empresa
- Consultar datos corporativos (CIF, sede, empleados)
- Añadir empleados al staff
- Reducir empleados del staff

### 2️⃣ Gestión de Carne
- Visualizar stock disponible y lotes activos
- Añadir stock nuevo (genera lotes automáticamente)
- Retirar stock
- Ver detalle de lotes con fechas de envasado y caducidad
- Sistema de alertas para lotes próximos a caducar

**Características del sistema de lotes:**
- Fecha de envasado automática
- Caducidad: 4 días desde el envasado
- Control de días restantes hasta caducidad

### 3️⃣ Gestión de Huevos
- Registrar nueva puesta por ubicación
- Consultar puestas específicas
- Ver total de huevos producidos
- Control de caducidad (7 días desde envasado)

**Sistema de ubicación jerárquico:**
- Tipo de gallina (0-3)
- Provincia (0-99)
- Ciudad (0-999)
- Granja (0-99)

### 4️⃣ Resumen General
- Vista consolidada de toda la operación
- Estadísticas agregadas
- Sistema de alertas:
  - ⚠️ Stock de carne bajo (< 500 kg)
  - ⚠️ Productos caducados

## 🏗️ Estructura del Proyecto

```
APP_Gallinaceas/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── gallinaceas/
│                   ├── MainAppConsole.java   # Aplicación principal
│                   ├── Gallinaceas.java      # Modelo empresa
│                   ├── Carne.java            # Gestión de carne
│                   ├── Huevos.java           # Gestión de huevos
│                   └── Producto.java         # Clase base productos
├── pom.xml                                    # Configuración Maven
└── README.md                                  # Este archivo
```

## 💻 Ejemplo de Uso

```
========================================
   GALLINACEAS S.A. - SISTEMA CONSOLA   
========================================

=== MENÚ PRINCIPAL ===
1. Información de la Empresa
2. Gestión de Carne
3. Gestión de Huevos
4. Ver Resumen General
5. Salir

Seleccione una opción (1-5): 2

=== GESTIÓN DE CARNE ===
Carne [Stock disponible: 1000 kg | Lotes activos: 1]

Lotes activos:
Lote 1: 1000 kg | Carne [Envasado: 03/02/2026 | Caduca: 07/02/2026 | Días restantes: 4]

1. Añadir stock
2. Retirar stock
3. Ver detalle de lotes
4. Volver al menú principal
```

## 🔧 Arquitectura Técnica

### Clases Principales

**MainAppConsole.java**
- Clase principal con interfaz de usuario
- Menús interactivos
- Manejo de excepciones
- Validación de entradas

**Producto.java (Clase abstracta)**
- Fecha de envasado
- Fecha de caducidad
- Días de validez
- Métodos de información

**Carne.java extends Producto**
- Sistema de lotes
- Control de stock por lote
- Gestión de fechas por lote
- Caducidad: 4 días

**Huevos.java extends Producto**
- Estructura de datos 4D
- Indexación por: tipo → provincia → ciudad → granja
- Total de huevos
- Caducidad: 7 días

**Gallinaceas.java**
- Información corporativa
- Gestión de empleados
- Datos de la empresa

## 🛡️ Validaciones

- ✅ Verificación de rangos en índices de ubicación
- ✅ Control de stock antes de retiradas
- ✅ Validación de cantidades positivas
- ✅ Manejo de excepciones NumberFormatException
- ✅ Protección contra valores negativos

## 📊 Características Destacadas

- **Sistema de lotes inteligente** para carne
- **Estructura de datos 4D** para huevos
- **Cálculo automático de caducidades**
- **Alertas proactivas** de stock y caducidad
- **Interfaz intuitiva** con menús claros
- **Validación robusta** de entradas

## 👥 Datos de la Empresa

- **Empresa:** Gallinaceas, S.A.
- **CIF:** A-8888888
- **Sede:** Avenida de la Libertad, 28 - 28028 Madrid
- **Empleados iniciales:** 12

## 📄 Licencia

Proyecto académico - Programación Orientada a Objetos

## 👨‍💻 Desarrollo

**Versión:** 1.0.0  
**Java:** 21  
**Build Tool:** Maven 3.x

---

## 🐛 Resolución de Problemas

### Error: "Command not found: mvn"
**Solución:** Instalar Maven:
```bash
sudo apt install maven  # Ubuntu/Debian
```

### Error: Java version
**Solución:** Verificar versión de Java:
```bash
java -version  # Debe ser >= 21
```

### Error de compilación
**Solución:** Limpiar y recompilar:
```bash
mvn clean compile
```

---

**¡Aplicación lista para usar! ✅**
