urladd = 'http://localhost:8080/article/add/name=';
urldel = 'http://localhost:8080/article/deleteByName/name=';
urlget = 'http://localhost:8080/article/getByName/name=';
urlgetAll = 'http://localhost:8080/article';

const table = document.getElementById('article');
const add_btn = document.getElementById('add-btn-article');

// for adding
const name_input = document.getElementById('name_article');

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
            console.log(data.length);
            for (let i = 0; i < data.length; i++) {

                let li = createNode('tr');
                let name = createNode('td');
                let id = createNode('id');
                let delete_btn = createNode('button');

                name.innerHTML = `${data[i].name}`;
                id.innerHTML = `${data[i].id}`;

                delete_btn.innerHTML = '-';
                delete_btn.id = 'delete-btn-' + data[i].id;
                delete_btn.className = "btn btn-primary";

                let td_btn_delete = createNode('td');
                append(td_btn_delete, delete_btn);
                append(li, td_btn_delete);
                append(li, id);
                append(li, name);
                append(table, li);
            }
        })
        .catch(function (e) {
            console.log(e);
        });
}

add_btn.onclick = function () {

     let new_article_url = urladd + name_input.value;
    console.log(new_article_url);

    console.log("url готов");

    const sendPromise = fetch(new_article_url, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'}
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


console.log("wsfmkd");
clear();
get(urlgetAll);