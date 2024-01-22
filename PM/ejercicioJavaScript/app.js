window.onload = login;
function login(){
    const usu = document.getElementById("usuario").value;
    const con = document.getElementById("contrasena").value;
    const btn = document.getElementById("login").value;
    btn.addEventListener("Click", hacerlogin);
}

function hacerlogin(){
    const nameUser = document.getElementById("usuario");
    const user = nameUser.value;

}

