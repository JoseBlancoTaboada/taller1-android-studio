# Taller 1 — Android Studio

Tres apps nativas de Android hechas en Java con Android Studio para el Taller 1 de Desarrollo de Apps.

| Carpeta | Qué hace |
|---|---|
| [`EjemploSimple`](EjemploSimple) | Escribes un dato, tocas **Aceptar** y aparece "Mensaje: …" debajo del botón. |
| [`ConvertidordeMoneda`](ConvertidordeMoneda) | Convierte un monto entre USD, COP, EUR, MXN y GBP con tasas fijas, usando el dólar como moneda puente. |
| [`CalculadoraCredito`](CalculadoraCredito) | Con el valor del crédito, el número de cuotas y el interés mensual (%), calcula la cuota, el total a pagar y la ganancia. |

Las tres usan `RelativeLayout` con el mismo patrón: cada vista se ancla a la anterior (`layout_below`, `layout_toEndOf`), el botón se conecta desde Java con `setOnClickListener`, y los datos se validan antes de calcular para que la app no se cierre con una entrada vacía o inválida.

## Instalar las apps

Los APK firmados están en la sección **Releases** de este repositorio. Descarga el de la app que quieras en un celular con Android 8.0 o superior y ábrelo. Si Android lo pide, dale permiso a esa app (Archivos, Chrome…) para "Instalar apps desconocidas".

## Abrir el código

Cada carpeta es un proyecto independiente. En Android Studio: **File → Open** y elige la carpeta de la app, no la raíz del repositorio. Para compilar hace falta el SDK de Android con API 37; las apps corren desde Android 8.0 (API 26).

## Notas

- Las tasas del convertidor son fijas y solo de ejemplo; no se consultan en línea.
- La calculadora usa interés simple, con el interés en decimal (2 % = 0,02):
  - cuota = crédito / cuotas + crédito × interés
  - total = cuota × cuotas
  - ganancia = total − crédito

Autor: José Eduardo Blanco Taboada.
