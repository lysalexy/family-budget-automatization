urladd = 'http://localhost:8080/article/add';
urldel = 'http://localhost:8080/article/deleteByName?name=';
urlget = 'http://localhost:8080/article/getByName?name=';
urlrename = 'http://localhost:8080/article/rename?';
urlgetAll = 'http://localhost:8080/article';
urlgetDel = 'http://localhost:8080/article/deleted';

const table = document.getElementById('article');
const add_btn = document.getElementById('add-btn-article');
const get_by_name_btn = document.getElementById('get-btn-article');
const del_btn = document.getElementById('del-btn-article');

// for adding
const name_input = document.getElementById('name_article');
const name_art_input = document.getElementById('get_name_article');
const new_input_art = document.getElementById('new_name_art');

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
    name_art_input.value = '';
    new_input_art.value = '';
}

function get(url, isList) {
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
            let length;
            if (isList) {
                length = data.length;
            } else {
                length = 1;
            }
            for (let i = 0; i < length; i++) {
                let li = createNode('tr');
                let name = createNode('td');
                let id = createNode('id');
                let delete_btn = createNode('button');
                let rename_btn = createNode('button');

                let nameV;
                let idV;

                if (isList) {
                    nameV = `${data[i].name}`;
                    idV = `${data[i].id}`;
                } else {
                    nameV = `${data.name}`;
                    idV = `${data.id}`;
                }

                name.innerHTML = nameV;
                id.innerHTML = idV;

                delete_btn.innerHTML = '-';
                delete_btn.name = 'delete-btn-' + nameV;
                delete_btn.className = "btn btn-primary";

                rename_btn.innerHTML = 'Переименовать эту статью';
                rename_btn.className = "btn btn-primary";


                rename_btn.onclick = function () {

                    let NewName = createNode('input');
                    NewName.className = 'form-control';
                    NewName.value = new_input_art.value;

                    let send_btn = createNode('button');
                    send_btn.innerHTML = '+';
                    send_btn.name = `${nameV}`;
                    send_btn.className = "btn btn-primary";

                    let newurlrename = urlrename + 'oldName=' + send_btn.name + '&newName=' + new_input_art.value;

                    console.log(newurlrename);

                    const sendPromise = fetch(newurlrename, {
                        method: 'PUT',
                        headers: {'Content-Type': 'application/json'}
                    });

                    sendPromise
                        .then(function () {
                            console.log('Имя статьи бюджета успешно изменено');
                            clear();
                            get(urlgetAll, 1);
                        })
                        .catch(function (e) {
                            console.log(e);
                            console.log('Имя статьи бюджета НЕ изменено');
                            clear();
                            get(urlgetAll, 1);
                        });
                }

                delete_btn.onclick = onDelete;

                let td_btn_delete = createNode('td');
                append(td_btn_delete, delete_btn);
                append(li, td_btn_delete);
                append(li, id);
                append(li, name);
                append(li, rename_btn);
                append(table, li);
            }
        })
        .catch(function (e) {
            console.log(e);
        });
}

function onDelete() {
    const currentUrl = urldel + this.name.split('-')[2];
    fetch(currentUrl, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'}
    })
        .then(function () {
            console.log('Удален');
            clear();
            get(urlgetAll, 1);
        })
        .catch(function (e) {
            console.log('Не удален');
            console.log(e);
            clear();
            get(urlgetAll, 1);
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
            get(urlgetAll, 1);
        })
        .catch(function (e) {
            console.log(e);
            console.log('Не добавлен');
            clear();
            get(urlgetAll, 1);
        });
}

del_btn.onclick = function () {
    get(urlgetDel, 1);
}

clear();
get(urlgetAll, 1);