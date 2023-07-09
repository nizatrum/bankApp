let infoMsg = document.getElementById('infoMsg');


//Для ввода номера счета при создании-----------------------------------------------------------------
function mask(e) {
    var matrix = this.placeholder,// .defaultValue
        i = 0,
        def = matrix.replace(/\D/g, ""),
        val = this.value.replace(/\D/g, "");
    def.length >= val.length && (val = def);
    matrix = matrix.replace(/[_\d]/g, function(a) {
      return val.charAt(i++) || "_"
    });
    this.value = matrix;
    i = matrix.lastIndexOf(val.substr(-1));
    i < matrix.length && matrix != this.placeholder ? i++ : i = matrix.indexOf("_");
}
var input = document.querySelector("#nameAccount");
input.addEventListener("input", mask, false);
//-------------------------------------------------------------------------------------- create account for client
let clientIdForAccount = document.getElementById('clientIdForAccount');
let nameAccount = document.getElementById('nameAccount');
let btnForCreateAccount = document.getElementById('btnForCreateAccount');
//-------------------------------------------------------------------------------------- create account for client

//-------------------------------------------------------------------------------------- get accounts client with id
let userId = document.getElementById('userId');
let btnGetAccounts = document.getElementById('btnGetAccounts');
//-------------------------------------------------------------------------------------- get accounts client with id

//-------------------------------------------------------------------------------------- get transactions by id client
let idClientForTransactions = document.getElementById('idClientForTransactions');
let btnGetTransactions = document.getElementById('btnGetTransactions');
//-------------------------------------------------------------------------------------- get transactions by id client

btnForCreateAccount.addEventListener('click', createAccount);
btnGetAccounts.addEventListener('click', getAccounts);
btnGetTransactions.addEventListener('click', getTransactions);


function createAccount() {
    if (clientIdForAccount.value != '') {
        if (nameAccount.value != '') {
            var response = sendRequest('POST',
                                        '/createAccount',
                                        'clientId=' + encodeURIComponent(clientIdForAccount.value) +
                                        '&nameAccount=' + encodeURIComponent(nameAccount.value));
            document.getElementById('responseBlock').style.display = 'initial';
            infoMsg.textContent = JSON.parse(response).message;
            if (JSON.parse(response).success) {
               infoMsg.style.color = "green";
            } else {
               infoMsg.style.color = "red";
            }
        }
    }
}

function getAccounts() {
    if (userId.value != '') {
        var client = JSON.parse(sendRequest('GET', '/getClient?id=' + userId.value, false));
        if (client.id == userId.value) {
            var accounts = client.accounts;
            document.getElementById('responseBlock').style.display = 'initial';
            infoMsg.style.color = 'gray';
            infoMsg.innerHTML = 'Клиент c ID ' + client.id + '<br>'
                    + 'Фамилия: ' + client.surname + '<br>'
                    + 'Имя: ' + client.name + '<br>'
                    + 'Отчество: ' + client.patronymic + '<br>';
            if (accounts.length > 0) {
                for (var i = 0; i < accounts.length; i++) {
                    infoMsg.innerHTML += 'ID: ' + accounts[i].id
                    + ', номер: ' + accounts[i].name
                    + ', баланс: ' + accounts[i].balance + '<br>';
                }
            } else {
                infoMsg.innerHTML += 'Аккаунты отсутствуют';
            }
        } else {
            infoMsg.style.color = 'red';
            infoMsg.innerHTML = 'Указанный пользователь не найден';
        }
    }
    else {
        alert('Заполните форму');
    }
}
//отправка запроса(метод, адрес, тело)
//function sendRequest(method, mapping, data) {
//    var xhr = new XMLHttpRequest();
//    xhr.open(method, mapping, false);
//
//    if (method == 'POST' && data != null) {
//        // Если используем классический вариант передачи данных через POST запрос
//        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//        // Если используем DTO в POST запросе
//        //xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
//        xhr.send(data);
//    } else {
//        xhr.send();
//    }
//    if (xhr.status == 200) {
//        return xhr.responseText;
//    } else {
//        console.log(xhr.status)
//    }
//}

function getTransactions() {
    if (idClientForTransactions.value != '') {
        var client = JSON.parse(sendRequest('GET', '/getClient?id=' + idClientForTransactions.value, false));
        if (client.id == idClientForTransactions.value) {
            var transactions = JSON.parse(sendRequest('GET', '/getTransactionByClient?id=' + idClientForTransactions.value, false));
            if (transactions.length > 0) {
                document.getElementById('responseBlock').style.display = 'initial';
                infoMsg.style.color = 'gray';
                infoMsg.innerHTML = 'Транзакции клиента с ID ' + client.id + ':<br>'
                for (var i = 0; i < transactions.length; i++) {
                    infoMsg.innerHTML += 'Дата: ' + transactions[i].dateOfPayment + '<br>'
                        + 'Отправитель c ID ' + transactions[i].sender.id
                        + ' перевел на ID ' + transactions[i].recipient.id
                        + ' сумму, в размере ' + transactions[i].valueOfPayment + '<br>';
                }
            } else {
                infoMsg.innerHTML = 'Транзакции отсутствуют';
            }
        } else {
            infoMsg.innerHTML = 'Указанный пользователь не найден';
        }
    }
}

function sendRequest(method, mapping, data) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, mapping, false);
    if (method == 'POST' && data != null) {
        // Если используем классический вариант передачи данных через POST запрос
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        // Если используем DTO в POST запросе
        //xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
        xhr.send(data);
    } else {
        xhr.send();
    }
    return xhr.responseText;
}