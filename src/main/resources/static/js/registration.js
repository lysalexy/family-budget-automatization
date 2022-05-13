const btn_reg = document.getElementById('reg');
const url = 'http://localhost:8080/newUser';

let username      = document.getElementById('username');
let password      = document.getElementById('password');


btn_reg.onclick = function () {

    let client = {
        "username": username.value,
        "password": password.value
    };

    console.log(client);

    const sendPromise = fetch(url, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(client)
    });

    sendPromise
        .then(function () {
            console.log('Зарегистрирован');
            username='';
            password='';
            setTimeout(function(){
                window.location.href = 'http://localhost:8080/operation.html';
            }, 1000);
        })
        .catch(function () {
            username='';
            password='';
        });
}