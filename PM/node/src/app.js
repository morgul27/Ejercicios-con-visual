//importampos los modulos
import express from 'express';

const app = express();

//creamos el servidor
app.listen(3000);
console.log('El servidor escucha en el puerto 3000');

//express
const dirname = dirname(fileURLToPath(import.meta.url));