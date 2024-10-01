"use strict";

function initScreen() {
    injectSkinWaitingRoom();
    addListener(".game-buttons", "click", processGamePicker);
    addListener("#setToken", "submit", processCreateFriendGame);
    addListener("#friendToken", "submit", processConnectFriend);
}

function processGamePicker(e) {
    const $button = e.target;
    if($button.id === "createFriendCode"){
        renderSetToken();
    }
    if($button.id === "inputFriend"){
        renderTokenInput();
    }
    if($button.id === "random"){
        createRandomGame();
    }
}

function processCreateFriendGame(e) {
    e.preventDefault();
    buttonSound();
    const setPrefix = getSelector("#setInput").value;
    saveToStorage("prefix", setPrefix);
    processForm();
}

function processConnectFriend(e) {
    e.preventDefault();
    buttonSound();
    const tokenInput = getSelector("#tokenInput").value;
    saveToStorage("prefix", tokenInput);
    processForm();
}

