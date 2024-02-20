//importar modulos
import express from 'express';
import {dirname} from 'path';
import { fileURLToPath } from 'url';
import {join} from 'path';
import indexRoute from './routes/index.js'
import {PORT} from './config.js'


const app = express();

app.use(express.json());

const _dirname = dirname(fileURLToPath(import.meta.url));
console.log(_dirname);

//Configurmaos el contenido estatico en public 
app.use(express.static(join(_dirname, 'public')));

//Condfigurar el enrutador
app.use(indexRoute);

//Creamos el server
app.listen(PORT);
console.log('el servidor escucha por el puerto ', 3300);

//Configurar el motor de plantillas
app.set('view engine', 'ejs');
app.set('views', join(_dirname, 'views'));

