# API-REST

## Esta API expone dos endpoints al usuario.

--- 
**Login:** Recibe un usuario y contraseña, y devuelve usuario y token.

- URL: 
```
/login
```

- Método: 
```
POST
```

- Parámetros: 
```
    username
    password
```

- Respuesta exitosa:
```
    {
        username: tuNombreDeUsuario,
        password: null,
        token: tuJWT
    }
```

- Respuesta errónea:
```
    null
```

---

**atm:** Recibe una lista de parámetros de búsqueda y una lista de los textos a buscar. Los mismos deben ser correlativos, en orden y la misma cantidad. Devuelve una lista de objetos JSON con los cajeros encontrados.

- URL: 
```
/atm
```

- Método: 
```
GET
```

- Parámetros: 
```
    q
    fields
```

- Respuesta exitosa:
```
    [
        {
            "address": {
                "street": direccion,
                "housenumber": número,
                "postalcode": código postal,
                "city": ciudad,
                "geoLocation": {
                    "lat": latitud,
                    "lng": longitud
                }
            },
            "distance": distancia,
            "type": tipo
	    }
    ]
```

- Respuesta errónea:
```
    null
```