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
    alert('!');
    let idAccountSender = document.getElementById('idAccountSender');
    let idAccountRecipient = document.getElementById('idAccountRecipient');
    let sumForPayment = document.getElementById('sumForPayment');
}



function sendRequest(method, mapping, data) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, mapping, false);

    if (method == 'POST' && data != null) {
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