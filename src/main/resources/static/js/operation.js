urladd = 'http://localhost:8080/operation/add';
urlgetAll = 'http://localhost:8080/operation/getAll';
urlRankArtForDeb = 'http://localhost:8080/operation/rankArticlesOfDebitForThePeriod';
urlRankArtForCred = 'http://localhost:8080/operation/rankArticlesOfCreditForThePeriod';
urlGetOpByArtName = 'http://localhost:8080/operation/getAllByCurrentArticle?articleName=';
urlGetOpByBalance = 'http://localhost:8080/operation/getAllByCurrentBalanceForThePeriod';
urlGetOpByPeriod = 'http://localhost:8080/operation/getAllForThePeriod';
urlMostPopularBal = 'http://localhost:8080/operation/getMostPopularBalanceOfThePeriod';
urlMostPopularArt = 'http://localhost:8080/operation/getMostPopularArticleOfThePeriod';

const operation_table = document.getElementById('operation');
const balance_table = document.getElementById('balance');
const article_table = document.getElementById('article');
const add_btn = document.getElementById('add-btn-operation');
const rank_articles_by_debit_btn = document.getElementById('rank-articles-by-debit-btn');
const rank_articles_by_credit_btn = document.getElementById('rank-articles-by-credit-btn');
const get_all_operations_for_period_btn = document.getElementById('get-all-operations-for-period-btn');
const get_most_popular_balance_for_period_btn = document.getElementById('get-most-popular-balance-for-period-btn');
const get_most_popular_article_for_period_btn = document.getElementById('get-most-popular-article-for-period-btn');
const get_all_operations_by_article_name_btn = document.getElementById('get-all-operations-by-article-name-box-btn');
const get_all_operations_by_balance_for_period_btn = document.getElementById('get-all-operations-by-balance-for-period-btn');

// for adding

const article_id_input = document.getElementById('article_id');
const debit_input = document.getElementById('debit');
const credit_input = document.getElementById('credit');
const create_date_input = document.getElementById('create_date');
const balance_id_input = document.getElementById('balance_id');
const begin_date_input = document.getElementById('begin_date');
const end_date_input = document.getElementById('end_date');
const article_name_input = document.getElementById('article_name');
const cur_balance_id_input = document.getElementById('cur_balance_id');
const cur_begin_date_input = document.getElementById('cur_begin_date');
const cur_end_date_input = document.getElementById('cur_end_date');

function createNode(element) {
    return document.createElement(element);
}

function append(parent, el) {
    return parent.appendChild(el);
}

function clear() {
    for (;  operation_table.getElementsByTagName('tr').length > 1;) {
        operation_table.deleteRow(1);
    }
    for (;  article_table.getElementsByTagName('tr').length > 1;) {
        article_table.deleteRow(1);
    }
    for (;  balance_table.getElementsByTagName('tr').length > 1;) {
        balance_table.deleteRow(1);
    }
    article_id_input.value = '';
    debit_input.value = '';
    credit_input.value = '';
    create_date_input.value = '';
    balance_id_input.value = '';
    article_name_input.value = '';
    cur_balance_id_input.value = '';
    cur_begin_date_input.value = '';
    cur_end_date_input.value = '';
}

function getArt(url, isList) {
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

                append(li, id);
                append(li, name);
                append(article_table, li);
            }
        })
        .catch(function (e) {
            console.log(e);
        });
}

function getBal(url) {
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

            let li = createNode('tr');
            let debit = createNode('td');
            let credit = createNode('td');
            let createDate = createNode('td');
            let id = createNode('id');

            debit.innerHTML = `${data.debit}`;
            credit.innerHTML = `${data.credit}`;
            createDate.innerHTML = `${data.createDate}`;
            id.innerHTML = `${data.id}`;

            append(li, id);
            append(li, debit);
            append(li, credit);
            append(li, createDate);
            append(balance_table, li);
        })
        .catch(function (e) {
            console.log(e);
        });
}

function getOp(url, isList) {
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

                let id = createNode('id');
                let article_id = createNode('td');
                let debit = createNode('td');
                let credit = createNode('td');
                let create_date = createNode('td');
                let balance_id = createNode('td');

                let idV;
                let articleIdV;
                let debitV;
                let creditV;
                let createDateV;
                let balanceIdV;

                if (isList) {
                    idV = `${data[i].id}`;
                    articleIdV = `${data[i].article.id}`;
                    debitV = `${data[i].debit}`;
                    creditV = `${data[i].credit}`;
                    createDateV = `${data[i].createDate}`;
                    balanceIdV = `${data[i].balance.id}`;
                } else {
                    idV = `${data.id}`;
                    articleIdV = `${data.article.id}`;
                    debitV = `${data.debit}`;
                    creditV = `${data.credit}`;
                    createDateV = `${data.createDate}`;
                    balanceIdV = `${data.balance.id}`;
                }

                id.innerHTML = idV;
                article_id.innerHTML = articleIdV;
                debit.innerHTML = debitV;
                credit.innerHTML = creditV;
                create_date.innerHTML = createDateV;
                balance_id.innerHTML = balanceIdV;

                append(li, id);
                append(li, article_id);
                append(li, debit);
                append(li, credit);
                append(li, create_date);
                append(li, balance_id);
                append(operation_table, li);
            }
        })
        .catch(function (e) {
            console.log(e);
        });
}

add_btn.onclick = function () {
    let urlnew = urladd + "?articleID=" + article_id_input.value + "&debit=" + debit_input.value +
    "&credit=" + credit_input.value + "&createDate=" + create_date_input.value+"&balanceID=" + balance_id_input.value;

    const sendPromise = fetch(urlnew, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'}
    });

    sendPromise
        .then(function () {
            console.log('Добавлен');
            clear();
            getOp(urlgetAll, 1);
        })
        .catch(function (e) {
            console.log(e);
            console.log('Не добавлен');
            clear();
            getOp(urlgetAll, 1);
        });
}

function rankArticles(url){
    newurl = url + '?begin=' + begin_date_input.value + '&end=' + end_date_input.value;
    getArt(newurl,1);

}
rank_articles_by_credit_btn.onclick = function (){
    rankArticles(urlRankArtForCred);
}
rank_articles_by_debit_btn.onclick = function (){
    rankArticles(urlRankArtForDeb);
}
get_all_operations_for_period_btn.onclick = function (){
    newurl = urlGetOpByPeriod + '?begin=' + begin_date_input.value + '&end=' + end_date_input.value;
    getOp(newurl,1);
}
get_most_popular_balance_for_period_btn.onclick = function (){
    newurl = urlMostPopularBal + '?begin=' + begin_date_input.value + '&end=' + end_date_input.value;
    getBal(newurl);
}
get_most_popular_article_for_period_btn.onclick = function (){
    newurl = urlMostPopularArt + '?begin=' + begin_date_input.value + '&end=' + end_date_input.value;
    getArt(newurl);
}

get_all_operations_by_article_name_btn.onclick = function (){
    newurl = urlGetOpByArtName + article_name_input.value;
    getOp(newurl,1);
}

get_all_operations_by_balance_for_period_btn.onclick = function (){
    newurl = urlGetOpByBalance + '?balanceID=' + cur_balance_id_input.value+ '&begin=' + cur_begin_date_input.value + '&end=' + cur_end_date_input.value;
    getOp(newurl,1);
}

clear();
getOp(urlgetAll, 1);