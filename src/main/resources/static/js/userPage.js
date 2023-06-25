let currentUser = JSON.parse(sendRequest('GET', '/getCurrentUser', null));
getBasicDataProfile();
getAccountsProfile();

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
        for (var i = 0; i < accountsCurrentClientAfter.length; i++) {
             var account = JSON.stringify(accountsCurrentClientAfter[i]);
             accounts[i].textContent = JSON.parse(account).id + ". " + JSON.parse(account).name + " Баланс: " + JSON.parse(account).balance;
        }
    }
    if (accounts.length == 0) {
        for (let i = 0; i < accountsCurrentClient.length; i++) {
            var account = JSON.stringify(accountsCurrentClient[i]);
            accountsClient.innerHTML += "<li class='list-group-item'>" + JSON.parse(account).id + ". " + JSON.parse(account).name + " Баланс: " + JSON.parse(account).balance + "</li>";
        }
    }
}

//3 - выполнение перевода
let transferButton = document.getElementById('transferButton');
transferButton.addEventListener('click', transfer);


//метода перевод средств
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
            //getCurrentUserTransactions();
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