//-------------------------------------------------------------------------- 1 - заполнение профиля информацией клиента

let currentUser = JSON.parse(sendRequest('GET', '/getCurrentUser', null));

let profileEmail = document.getElementById('profileEmail');
let profileName = document.getElementById('profileName');
let profileSurname = document.getElementById('profileSurname');
let profilePatronymic = document.getElementById('profilePatronymic');
//--------------------------------------------------------------------------
//-------------------------------------------------------------------------- 2 - заполнение имеющихся счетов у клиента
let accountsCurrentClient = currentUser.accounts;
let accountsClient = document.getElementById('accountsClient');

for (let i = 0; i < accountsCurrentClient.length; i++) {
    var account = JSON.stringify(accountsCurrentClient[i]);
    accountsClient.innerHTML += "<li class='list-group-item'>" + JSON.parse(account).name + " [" + JSON.parse(account).balance + "]</li>";
}

profileEmail.innerHTML += currentUser.email;
profileName.innerHTML += currentUser.name;
profileSurname.innerHTML += currentUser.surname;
profilePatronymic.innerHTML += currentUser.patronymic;
//--------------------------------------------------------------------------
//-------------------------------------------------------------------------- 3 - выполнение перевода
let transferButton = document.getElementById('transferButton');

transferButton.addEventListener('click', transfer);

function transfer() {
    //для передачи в тело POST запроса отдельных параметров
    let idAccountSender = document.getElementById('idAccountSender');
    let idAccountRecipient = document.getElementById('idAccountRecipient');
    let sumForPayment = document.getElementById('sumForPayment');

    //для передачи в тело POST запроса DTO (объекта), внутри которого у нас необходимые для передачи данные
    let dto = false;
    let transferDTO = {
        idAccountSender : idAccountSender.value,
        idAccountRecipient : idAccountRecipient.value,
        sumForPayment : sumForPayment.value
    };
    //
    if (idAccountSender.value != '' && idAccountRecipient != '' && sumForPayment.value != '' && !dto) {
        sendRequest('POST', '/executeTransfer', 'idAccountSender=' + encodeURIComponent(idAccountSender.value) +
                                                '&idAccountRecipient=' + encodeURIComponent(idAccountRecipient.value) +
                                                '&sumForPayment=' + encodeURIComponent(sumForPayment.value));
    }
    else if (idAccountSender.value != '' && idAccountRecipient != '' && sumForPayment.value != '' && dto) {
        sendRequest('POST', '/executeTransfer', JSON.stringify(transferDTO));
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
    if (xhr.status == 200) {
        return xhr.responseText;
    } else {
        console.log(xhr.status)
    }
}