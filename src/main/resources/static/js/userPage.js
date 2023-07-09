let currentUser = JSON.parse(sendRequest('GET', '/getCurrentUser', null));

let methods = document.getElementById('methods');
let divsNeedScrolls = methods.querySelectorAll(".needScroll");

getBasicDataProfile();
getAccountsProfile();
getTransactionsProfile();



//1 - заполнение профиля информацией клиента
function getBasicDataProfile() {
    let profileEmail = document.getElementById('profileEmail');
    let profileName = document.getElementById('profileName');
    let profileSurname = document.getElementById('profileSurname');
    let profilePatronymic = document.getElementById('profilePatronymic');
    profileEmail.innerHTML += currentUser.email;
    profileName.innerHTML += currentUser.name;
    profileSurname.innerHTML += currentUser.surname;
    profilePatronymic.innerHTML += currentUser.patronymic;
}

//2 - заполнение имеющихся счетов у клиента
function getAccountsProfile() {
    let accountsCurrentClient = currentUser.accounts;
    let accountsClient = document.getElementById('accountsClient');
    let accounts = accountsClient.querySelectorAll('li'); //список <li> внутри <ul id='accountsClient'>
    //если у нас уже подгружены аккаунты
    if (accounts.length > 0) {

        let updateCurrentUser = JSON.parse(sendRequest('GET', '/getCurrentUser', null)); //обновляем инфу по аккаунтам у текущего юзера
        let accountsCurrentClientAfter = updateCurrentUser.accounts;
        var count = 0;
        for (var i = 0; i < accountsCurrentClientAfter.length; i++) {
             var account = JSON.stringify(accountsCurrentClientAfter[i]);
             accounts[i].textContent = JSON.parse(account).id + ". " + JSON.parse(account).name + " Баланс: " + JSON.parse(account).balance;
             count++;
        }
        //добавляем стили для аккаунтов, а именно добавляем скролл
        if (count >= 4) {
            divsNeedScrolls[0].style.height = '164px';
            divsNeedScrolls[0].style.overflow = 'auto';
        }
    }
    else if (accounts.length == 0) {
        var count = 0;
        for (let i = 0; i < accountsCurrentClient.length; i++) {
            var account = JSON.stringify(accountsCurrentClient[i]);
            accountsClient.innerHTML +=
            "<li class='list-group-item'>"
            + JSON.parse(account).id + ". "
            + JSON.parse(account).name + " Баланс: "
            + JSON.parse(account).balance + "</li>";
            count++;
        }
        //добавляем стили для аккаунтов, а именно добавляем скролл
        if (count >= 4) {
            divsNeedScrolls[0].style.height = '164px';
            divsNeedScrolls[0].style.overflow = 'auto';
        }
    }
}

//3 - выполнение перевода
let transferButton = document.getElementById('transferButton');
transferButton.addEventListener('click', transfer);

function getTransactionsProfile() {
    let userTransactions = JSON.parse(sendRequest('GET', '/getTransaction', null));
    console.log(userTransactions.length + 'количество транзакций');
    //console.log(userTransactions[0].sender.name); // то что нужно !!! получаем номер аккаунта сендера первой транзакции!!!!
    let transactionsList = document.getElementById('transactionsList');
    let transactions = transactionsList.querySelectorAll('li');//список <li> внутри <ul id='transactionsList'>
    //получить все аккаунты
    let updateCurrentUser = JSON.parse(sendRequest('GET', '/getCurrentUser', null)); //обновляем инфу по аккаунтам у текущего юзера
    let accountsCurrentClientAfter = updateCurrentUser.accounts;

    let accountsParent = document.getElementById('accountsClient');
    let accounts = accountsParent.querySelectorAll('li');

    //удалить транзакции - элементы li
    let transactionParent = document.getElementById('transactionsList');
    let transactionsForDelete = transactionParent.querySelectorAll('li');

    let arrayAccounts = [];
    for (var i = 0; i < accounts.length; i++) {
        arrayAccounts.push(accounts[i].textContent);
    }
    if (transactionsForDelete.length > 0) {
        for(var i = 0; i < transactionsForDelete.length; i++) {
            transactionsForDelete[i].remove();
        }
    }
    //получить все аккаунты
    for (let i = 0; i < userTransactions.length; i++) {
        //если пользователь sender
        var isSenderAccount = false;
        for(let j = 0; j < arrayAccounts.length; j++) {
            if (arrayAccounts[j].includes(userTransactions[i].sender.name)) {
                isSenderAccount = true;
                console.log("совпадение sender");
            }
        }
        if (isSenderAccount) {
            transactionsList.innerHTML += "<li class='list-group-item' style='display: flex;'>"
            + userTransactions[i].dateOfPayment.substring(0, 10)
            + " | Списание: "
            + userTransactions[i].sender.id
            + "<div style='color: red;padding-left: 5px;padding-right: 5px;'> → </div>"
            + userTransactions[i].recipient.id + " | "
            + userTransactions[i].valueOfPayment + " ₽</li>";
        }
        //если пользователь recipient
        var isRecipientAccount = false;
        for(let j = 0; j < arrayAccounts.length; j++) {
            if (arrayAccounts[j].includes(userTransactions[i].recipient.name)) {
                isRecipientAccount = true;
                console.log("совпадение recipient");
            }
        }
        if (!isSenderAccount && isRecipientAccount) {
            transactionsList.innerHTML += "<li class='list-group-item' style='display: flex;'>"
            + userTransactions[i].dateOfPayment.substring(0, 10) + " | Пополнение: "
            + userTransactions[i].sender.id
            + "<div style='color: green;padding-left: 5px;padding-right: 5px;'> → </div>"
            + userTransactions[i].recipient.id + " | "
            + userTransactions[i].valueOfPayment + " ₽</li>";
        }
    }
    transactions = transactionsList.querySelectorAll('li');//список <li> внутри <ul id='transactionsList'>

//добавляем стили для истории транзакций, а именно добавляем скролл
    if (transactions.length >= 4) {
        divsNeedScrolls[1].style.height = '164px';
        divsNeedScrolls[1].style.overflow = 'auto';
    }
    }
//white-space: nowrap - перенос на строку дальше

//метод перевод средств
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
        status = sendRequest('POST', '/executeTransfer', 'idAccountSender=' + encodeURIComponent(idAccountSender.value) +
                                                '&idAccountRecipient=' + encodeURIComponent(idAccountRecipient.value) +
                                                '&sumForPayment=' + encodeURIComponent(sumForPayment.value));
        let infoMessage = document.getElementById('infoMessage');
        infoMessage.innerHTML = JSON.parse(status).message;
        if (JSON.parse(status).success) {
            infoMessage.style.color = 'green';
            getAccountsProfile();
            getTransactionsProfile();
            console.log('вызвали метод getTransactionsProfile после перевода');

        } else {
            infoMessage.style.color = 'red';
        }

        if (infoMessage.textContent != '') {
            responseBlock.style.display = 'initial';
            infoMessage.style.display = '';
        }
    }
    /*else if (idAccountSender.value != '' && idAccountRecipient != '' && sumForPayment.value != '' && dto) {
        status = sendRequest('POST', '/executeTransfer', JSON.stringify(transferDTO));
    }*/
}

//отправка запроса(метод, адрес, тело)
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