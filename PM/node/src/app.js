//importar modulos
import express from 'express';
import {dirname} from 'path';
import { fileURLToPath } from 'url';
import {join} from 'path';
import indexRoute from './routes/index.js';


const app = express();
const port = 3300;


const _dirname = dirname(fileURLToPath(import.meta.url));
console.log(_dirname);

//Configurmaos el contenido estatico en public 
app.use(express.static(join(_dirname, 'public')));

//Condfigurar el enrutador
app.use(indexRoute);

//Creamos el server
app.listen((process.env.PORT || port));
console.log('el servidor escucha por el puerto ', 3000);

//Configurar el motor de plantillas
app.set('view engine', 'ejs');
app.set('views', join(_dirname, 'views'));

