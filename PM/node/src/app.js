//importampos los modulos
import express from 'express';
import {dirname, join} from 'path';
import {fileURLToPath} from 'url';
import indexRouter from './routes/index.js';


const app = express();
const port = 3300

const __dirname = dirname(fileURLToPath(import.meta.url));
console.log('views', join(__dirname, 'views'));

//configurar motor de plantilla
app.set('view engine', 'ejs');
app.set('views', join(__dirname, 'views'));

//configurar el enrutador
app.use(indexRouter);

//configurar public como estatics
app.use(express.static(join(__dirname, 'public')))

//creamos el servidor
app.listen(process.env.PORT || port);
console.log('El servidor escucha en el puerto '+ port);

