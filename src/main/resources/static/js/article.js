urladd = 'http://localhost:8080/article/add';
urldel = 'http://localhost:8080/article/deleteByName?name=';
urlget = 'http://localhost:8080/article/getByName?name=';
urlrename = 'http://localhost:8080/article/rename?';
urlgetAll = 'http://localhost:8080/article';

const table = document.getElementById('article');
const add_btn = document.getElementById('add-btn-article');
const get_by_name_btn = document.getElementById('get-btn-article');
const rename_btn = document.getElementById('rename-box-article');

// for adding
const name_input = document.getElementById('name_article');
const name_art_input = document.getElementById('get_name_article');
const old_input = document.getElementById('old_name_article');
const new_input = document.getElementById('new_name_article');

function createNode(element) {
    return document.createElement(element);
}

function append(parent, el) {
    return parent.appendChild(el);
}

function clear() {
    for (; table.getElementsByTagName('tr').length > 1;) {
        table.deleteRow(1);
    }
    name_input.value = '';
}

function get(url) {
    console.log("начинаем фетч");
    clear();
    console.log("почистили");
    const myPromise = fetch(url);
    console.log("заканчиваем фетч");
    myPromise
        .then(data => data.json())
        .then(function (data) {
            console.log(data);
            console.log(data.length);
            for (let i = 0; i < data.length; i++) {

                let li = createNode('tr');
                let name = createNode('td');
                let newName = createNode('input');
                newName.className = 'form-control';
                newName.value = new_input.value;
                let id = createNode('id');
                let delete_btn = createNode('button');
                let renamet_btn = createNode('button');

                name.innerHTML = `${data[i].name}`;
                id.innerHTML = `${data[i].id}`;

                delete_btn.innerHTML = '-';
                delete_btn.name = 'delete-btn-' + data[i].name;
                delete_btn.className = "btn btn-primary";

                renamet_btn.innerHTML = '-';
                renamet_btn.className = "btn btn-primary";

                renamet_btn.onclick = function () {

                    // let NewName = createNode('input');
                    //
                    // NewName.className = 'form-control';
                    //
                    // NewName.value = `${data[i].name}`;

                    let send_btn = createNode('button');
                    send_btn.innerHTML = '+';
                    send_btn.name = `${data[i].name}`;
                    send_btn.className = "btn btn-primary";

                    let newurlrename = urlrename + 'oldName=' + send_btn.name + '&newName=' + new_input.value;

                    console.log(newurlrename);

                    const sendPromise = fetch(newurlrename, {
                        method: 'PUT',
                        headers: {'Content-Type': 'application/json'}
                    });

                    sendPromise
                        .then(data => data.json())
                        .then(function (data) {
                            console.log('Имя статьи бюджета успешно изменено');
                            console.log(data);
                            clear();
                            get(urlgetAll);
                        })
                        .catch(function (e) {
                            console.log(e);
                            console.log('Имя статьи бюджета НЕ изменено');
                            clear();
                            get(urlgetAll);
                        });

                }

                delete_btn.onclick = onDelete;

                let td_btn_delete = createNode('td');
                append(td_btn_delete, delete_btn);
                append(li, td_btn_delete);
                append(li, id);
                append(li, name);
                append(li, renamet_btn);
                append(table, li);
            }
        })
        .catch(function (e) {
            console.log(e);
        });
}

function onDelete() {
    const currentUrl = urldel+ this.name.split('-')[2];
    fetch(currentUrl, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'}
    })
        .then(function () {
            console.log('Удален');
            clear();
            get(urlgetAll);
        })
        .catch(function (e) {
            console.log('Не удален');
            console.log(e);
            clear();
            get(urlgetAll);
        });
}

function getOne(url) {
    console.log("начинаем фетч");
    console.log(url);
    clear();
    console.log("почистили");
    const myPromise = fetch(decodeURI(url));
    console.log("заканчиваем фетч");
    myPromise
        .then(data => data.json())
        .then(function (data) {
            console.log(data);

            let li = createNode('tr');
            let name = createNode('td');
            let id = createNode('id');
            let delete_btn = createNode('button');

            name.innerHTML = `${data.name}`;
            id.innerHTML = `${data.id}`;

            delete_btn.innerHTML = '-';
            delete_btn.id = 'delete-btn-' + data.id;
            delete_btn.className = "btn btn-primary";

            let td_btn_delete = createNode('td');
            append(td_btn_delete, delete_btn);
            append(li, td_btn_delete);
            append(li, id);
            append(li, name);
            append(table, li);

        })
        .catch(function (e) {
            console.log(e);
        });
}

add_btn.onclick = function () {

    let new_article = {
        "name": name_input.value
    };

    console.log(new_article);

    const sendPromise = fetch(urladd, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(new_article)
    });

    sendPromise
        .then(data => data.json())
        .then(function (data) {
            console.log('Добавлен');
            console.log(data);
            clear();
            get(urlgetAll);
        })
        .catch(function (e) {
            console.log(e);
            console.log('Не добавлен');
            clear();
            get(urlgetAll);
        });
}

get_by_name_btn.onclick = function () {

    let urlGetByName = urlget + name_art_input.value;

    console.log(urlGetByName);

    getOne(encodeURI(urlGetByName));
}


rename_btn.onclick = function () {

    let newurlrename = urlrename + 'oldName=' + old_input.value + '&newName=' + new_input.value;

    console.log(newurlrename);

    const sendPromise = fetch(newurlrename, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'}
    });

    sendPromise
        .then(data => data.json())
        .then(function (data) {
            console.log('Имя статьи бюджета успешно изменено');
            console.log(data);
            clear();
            get(urlgetAll);
        })
        .catch(function (e) {
            console.log(e);
            console.log('Имя статьи бюджета НЕ изменено');
            clear();
            get(urlgetAll);
        });
}

clear();
get(urlgetAll);