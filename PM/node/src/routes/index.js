//Importo solo el enrutador desde express
import { Router } from "express";
import { home, login, registro, insertUsers } from "../controllers/controllers.js";
import { pool } from "../db.js";

//Inicio de enrutador y almaceno en una constante
const router = Router();

//rutas
router.get('/', home);
router.get('/login', login);
router.get('/registro', registro);

router.get('/connect', async (req, res) =>{
    await pool.query('SELECT 1+1 AS RESULT')
res.json(result[0])
});

router.post('/insertUsers', insertUsers)

export default router;