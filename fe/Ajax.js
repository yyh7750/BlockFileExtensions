/**
 * 고정 확장자 체크 시 db에 저장하기 위한 호출
 * @param {*} extension 고정 확장자 체크 표시된 element
 */
function addFixedExtension(extension, type) {
    httpRequest = new XMLHttpRequest();
    httpRequest.open('POST', url + '/fix-extension', true);
    data.extension = extension;
    data.type = type;
    httpRequest.setRequestHeader("Content-Type", "application/json");
    httpRequest.send(JSON.stringify(data));

    httpRequest.onload = function () {
        if (this.status != 201) {
            console.log(this);
            alert("고정 확장자에 포함되어 있는 확장자입니다. 리스트에서 해제되었습니다.");
            location.reload();
        }
    }
}

/**
 * 새로 고침 시 커스텀 확장자 유지되도록 설정
 */
function getCustomExtensions() {
    httpRequest = new XMLHttpRequest();
    httpRequest.open('GET', url + '/custom-extension', true);
    httpRequest.send();
    httpRequest.onload = function () {
        if (this.status == 200) {
            let customArray = [];
            let response = JSON.parse(this.response);

            customArray = response.list;

            for (let i = 0; i < customArray.length; i++) {
                createBtn(customArray[i].extension);
            }

            document.getElementById("custom-extension-cnt").innerText = response.cnt;
        }
    }
}

/**
 * 새로 고침 시 체크박스 유지되도록 설정
 */
function getFixedExtensions() {
    httpRequest = new XMLHttpRequest();

    let checkList = document.getElementsByName('extentions');
    for (let i = 0; i < checkList.length; i++) {
        checkArray.push(checkList[i].value);
    }

    httpRequest.open('GET', url + '/fix-extension', true);
    httpRequest.send();
    httpRequest.onload = function () {
        if (this.status == 200) {
            let checkedList = JSON.parse(this.response);

            for (let i = 0; i < checkedList.length; i++) {
                let index = checkArray.indexOf(checkedList[i].extension);
                if (index >= 0) {
                    checkList.item(index).checked = true;
                }
            }
        }
    }
}

/**
 * 커스텀 파일 확장자 차단
 */
function addCustomExtension() {
    let inputValue = document.getElementById("custom-extension").value;

    if (!regex.test(inputValue)) {
        alert("공백 불가, 영어만 입력 가능합니다.");
        document.getElementById("custom-extension").value = "";
        return;
    }

    if (checkArray.includes(inputValue)) {
        addFixedExtension(inputValue, 1);
        return;
    }
    httpRequest = new XMLHttpRequest();
    httpRequest.open('POST', url + '/custom-extension');
    data.extension = inputValue;
    data.type = 1;
    httpRequest.setRequestHeader("Content-Type", "application/json");
    httpRequest.send(JSON.stringify(data));

    httpRequest.onload = function () {
        if (this.status == 201) {
            location.reload();
        }
    }
}

/**
 * 커스텀 파일 확장자 버튼 만들기
 */
function createBtn(extension) {
    var button = document.createElement('button');
    button.className = 'custom-extension-btn';
    button.type = 'button';
    button.innerHTML = extension + " X";
    button.id = extension;

    button.onclick = function () {
        deleteCustomExtension(extension);
    };

    document.getElementById("custom-extension-div").appendChild(button);
}

/**
 * 커스텀 파일 확장자 버튼을 누른 후 confirm 메세지의 '예'를 누를경우 삭제
 */
function deleteCustomExtension(extension) {
    httpRequest = new XMLHttpRequest();
    httpRequest.open('DELETE', url + '/custom-extension');
    data.extension = extension;
    data.type = 1;
    httpRequest.setRequestHeader("Content-Type", "application/json");
    httpRequest.send(JSON.stringify(data));

    httpRequest.onload = function () {
        if (this.status == 409) {
            alert("해제되었습니다.");
            location.reload();
        }
    }
}
