urladd = 'http://localhost:8080/balance/add';
urldel = 'http://localhost:8080/balance/deleteByID?ID=';
urlgetAll = 'http://localhost:8080/balance/getAll';
urlgetMostProf = 'http://localhost:8080/balance/getMostProfitable';
urlgetMostSpend = 'http://localhost:8080/balance/getMostSpending';
urlgetAmountById = 'http://localhost:8080/balance/getAmountByID?ID=';
urlgetSumAmount = 'http://localhost:8080/balance/getSummaryAmount';
urlgetRankedByCrD = 'http://localhost:8080/balance/rankByCreateDate';
urlgetAv = 'http://localhost:8080/balance/getAvailable';

const table = document.getElementById('balance');
const add_btn = document.getElementById('add-btn-balance');
const get_most_prof_btn = document.getElementById('mostProf-btn-balance');
const get_most_spend_btn = document.getElementById('mostSpend-btn-balance');
const amount_btn = document.getElementById('amount-btn-balance');
const create_date_btn = document.getElementById('create-date-btn-balance');
const all_bal_btn = document.getElementById('all-bal-btn-balance');


// for adding
const create_date_input = document.getElementById('create_date');
const debit_input = document.getElementById('debit');
const credit_input = document.getElementById('credit');
const cur_amount_output = document.getElementById('amount');
const sum_amount_output = document.getElementById('sum_amount');
const id_input = document.getElementById('get_id_balance');

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
    create_date_input.value = '';
    debit_input.value = '';
    credit_input.value = '';
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
                let debit = createNode('td');
                let credit = createNode('td');
                let createDate = createNode('td');
                let id = createNode('id');
                let delete_btn = createNode('button');
                let get_amount_btn = createNode('button');

                let debitV;
                let creditV;
                let createDateV;
                let idV;

                if (isList) {
                    debitV = `${data[i].debit}`;
                    creditV = `${data[i].credit}`;
                    createDateV = `${data[i].createDate}`;
                    idV = `${data[i].id}`;
                } else {
                    debitV = `${data.debit}`;
                    creditV = `${data.credit}`;
                    createDateV = `${data.createDate}`;
                    idV = `${data.id}`;
                }

                debit.innerHTML = debitV;
                credit.innerHTML =creditV;
                createDate.innerHTML = createDateV;
                id.innerHTML = idV;

                delete_btn.innerHTML = '-';
                delete_btn.id = 'delete-btn-' + idV;
                delete_btn.className = "btn btn-primary";

                delete_btn.onclick = onDelete;

                get_amount_btn.innerHTML = '-';
                get_amount_btn.id = 'get-amount-btn-' + idV;
                get_amount_btn.className = "btn btn-primary";

                get_amount_btn.onclick = onAmount;

                let td_btn_delete = createNode('td');
                let td_btn_get_amount = createNode('td');
                append(td_btn_delete, delete_btn);
                append(li, td_btn_delete);
                append(li, id);
                append(li, debit);
                append(li,credit);
                append(li, createDate);
                append(td_btn_get_amount, get_amount_btn);
                append(li,td_btn_get_amount);
                append(table, li);
            }
        })
        .catch(function (e) {
            console.log(e);
        });
}

function onDelete() {
    const currentUrl = urldel + this.id.split('-')[2];
    console.log(currentUrl);
    fetch(currentUrl, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'}
    })
        .then(function () {
            console.log('Удален');
            clear();
            get(urlgetAv, 1);
        })
        .catch(function (e) {
            console.log('Не удален');
            console.log(e);
            clear();
            get(urlgetAv, 1);
        });
}

function onAmount() {
    const currentUrl = urlgetAmountById + this.id.split('-')[3];
    console.log(currentUrl);
    let myPromise = fetch(currentUrl);
    myPromise
        .then(res => res.text())
        .then (function (res){
            cur_amount_output.value = res;
            console.log('Остаток получен');
            clear();
            get(urlgetAv, 1);
        })
        .catch(function (e) {
            console.log('Остаток НЕ получен');
            console.log(e);
            clear();
            get(urlgetAv, 1);
        });
}

add_btn.onclick = function () {
    let urlnew = urladd +'?createDate=' + create_date_input.value+'&debit=' + debit_input.value+'&credit='+credit_input.value;

    const sendPromise = fetch(urlnew, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'}
    });

    sendPromise
        .then(function () {
            console.log('Добавлен');
            clear();
            get(urlgetAv, 1);
        })
        .catch(function (e) {
            console.log(e);
            console.log('Не добавлен');
            clear();
            get(urlgetAv, 1);
        });
}

get_most_prof_btn.onclick= function () {
    get(urlgetMostProf);
}

get_most_spend_btn.onclick= function () {
    get(urlgetMostSpend);
}


amount_btn.onclick= function () {
    let myPromise = fetch(urlgetSumAmount);
    myPromise
        .then(res => res.text())
        .then (function (res){
            sum_amount_output.value = res;
            console.log('Суммарный остаток получен');
            clear();
            get(urlgetAv, 1);
        })
        .catch(function (e) {
            console.log('Суммарный остаток НЕ получен');
            console.log(e);
            clear();
            get(urlgetAv, 1);
        });
}

create_date_btn.onclick= function () {
    get(urlgetRankedByCrD,1);
}

all_bal_btn.onclick= function () {
    get(urlgetAll,1);
}
clear();
get(urlgetAv, 1);