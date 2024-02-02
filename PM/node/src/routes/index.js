//Importo solo el enrutador desde express
import { Router } from "express";

//inicio de enrutador y almaceno en una constante
const router = Router();

//rutas
router.get('/', (req, res) => res.render('home', {title: 'Home'}));
router.get('/login', (req, res) => res.render('login', {title: 'login'}));
router.get('/registro', (req, res) => res.render('registro', {title: 'Registro'}));

export default router;