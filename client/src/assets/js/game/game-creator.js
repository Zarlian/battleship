"use strict";

function renderSetToken() {
    buttonSound();
    const $form = getSelector('#setToken');
    $form.classList.remove("hidden");
    hideTokenInput();
}

function renderTokenInput() {
    buttonSound();
    const $form = getSelector("#friendToken");
    $form.classList.remove("hidden");
    hideSetToken();
}

function createRandomGame() {
    buttonSound();
    hideTokenInput();
    hideSetToken();
    const randomPrefix = "";
    saveToStorage("prefix", randomPrefix);
    processForm();
}

function hideSetToken() {
    const $form = getSelector("#setToken");
    $form.classList.add("hidden");
}

function hideTokenInput() {
    const $form = getSelector("#friendToken");
    $form.classList.add("hidden");
}

function checkStatus(started) {
    const $status = getSelector("#status");
    const $create = getSelector("#createFriendCode");
    const $input = getSelector("#inputFriend");
    $status.innerHTML = "";
    showStatus(started, $status, $create, $input);
}

function showStatus(started, $status, $create, $input){
    if (started) {
        showStartSoon($status);
    } else {
        showSearchingGame($status, $create, $input);
    }
}

function showStartSoon($status){
    playWaiting();
    $status.insertAdjacentHTML("beforeend", `<p>Your game will start soon</p>`);
    window.location.href = "game.html";
}

function showSearchingGame($status, $create, $input){
    playWaiting();
    $status.insertAdjacentHTML("beforeend", `<p>Searching for game.</p>`);
    $create.classList.add("hidden");
    $input.classList.add("hidden");
}
