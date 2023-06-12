//-------------------Почти динамичное поведение блока (#responseContent) при отправки get запроса-------------------
let errorMsg = document.getElementById('errorMsg');
let responseBlock = document.getElementById('responseBlock');
let infoClient = document.getElementById('infoClient');
let getClientButton = document.getElementById('getClientButton');
let inputId = document.getElementById('inputId');
let idField = document.getElementById('idField');

if (idField.textContent != '') {
    responseBlock.style.display = 'initial';
    errorMsg.style.display = 'none';
}
else if (errorMsg.textContent != '') {
    responseBlock.style.display = 'initial';
    infoClient.style.display = 'none';
    errorMsg.style.display = '';
}

getClientButton.addEventListener('click', showMeResponseBlock);

function showMeResponseBlock() {
    if (inputId.value != null) {
        responseBlock.style.display = 'initial';
    }
}
//------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------
//let previewButton = document.getElementById('previewButton');
//previewButton.addEventListener('click', showMeClientForUpdate);
//
//function showMeClientForUpdate(e) {
//    let updateId = document.getElementById('updateId').value;
//    var xhr = new XMLHttpRequest();
//    xhr.open('GET', '/getClient?id=' + updateId, false);
//    xhr.send();
//        if (xhr.status == 200) {
//            updateName.value = '';
//            updateSurname.value = '';
//            updatePatronymic.value = '';
//            let client = JSON.parse(xhr.responseText);
//            updateName.disabled = false;
//            updateSurname.disabled = false;
//            updatePatronymic.disabled = false;
//            updateButton.disabled = false;
//
//            updateName.value = client.name;
//            updateSurname.value = client.surname;
//            updatePatronymic.value = client.patronymic;
//        } else {
//            updateName.value = '';
//            updateSurname.value = '';
//            updatePatronymic.value = '';
//
//            updateName.disabled = true;
//            updateSurname.disabled = true;
//            updatePatronymic.disabled = true;
//            clientForUpdatingButton.disabled = true;
//        }
//}


//------------------------------------------------------------------------------------------------------------------
let updateId = document.getElementById('updateId');
let updateName = document.getElementById('updateName');
let updateSurname = document.getElementById('updateSurname');
let updatePatronymic = document.getElementById('updatePatronymic');
let updateButton = document.getElementById('updateButton');

if (updateId.value == '') {
    updateName.disabled = true;
    updateSurname.disabled = true;
    updatePatronymic.disabled = true;
    updateButton.disabled = true;
}

updateId.addEventListener('input', showMeClientForUpdate);

function showMeClientForUpdate(e) {
    var xhr = new XMLHttpRequest();
        xhr.open('GET', '/getClient?id=' + e.target.value, false);
        xhr.send();
        if (xhr.status == 200) {
            let client = JSON.parse(xhr.responseText);
            updateName.disabled = false;
            updateSurname.disabled = false;
            updatePatronymic.disabled = false;
            updateButton.disabled = false;

            updateName.value = client.name;
            updateSurname.value = client.surname;
            updatePatronymic.value = client.patronymic;
        } else {
            updateName.value = '';
            updateSurname.value = '';
            updatePatronymic.value = '';

            updateName.disabled = true;
            updateSurname.disabled = true;
            updatePatronymic.disabled = true;
            updateButton.disabled = true;
            clientForUpdatingButton.disabled = true;
        }
}




