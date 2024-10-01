"use strict";

function setNewScreen(href) {
    window.location.href = href;
}

function toStartScreen() {
    setNewScreen("index.html");
    saveAudioTimeAmbient();
}

function toGameModeScreen() {
    setNewScreen("game-mode.html");
    saveAudioTimeAmbient();
}

function toBoardSizeScreen() {
    setNewScreen("board-size.html");
    saveAudioTimeAmbient();
}

function toBoatPlacementScreen() {
    saveBoardSize();
    setNewScreen("boat-placement.html");
    saveAudioTimeAmbient();
}

function toNameScreen() {
    setNewScreen("name-screen.html");
    saveAudioTimeAmbient();
}

function toWaitingScreen() {
    setNewScreen("waitingroom.html");
    saveAudioTimeAmbient();
}

function toGameScreen() {
    setNewScreen("game.html");
    saveAudioTimeAmbient();
}

function toWinScreen() {
    setNewScreen("win-or-lose-screen.html");
    saveAudioTimeAmbient();
}

function goToMainScreen() {
    if (window.location.pathname.includes("index") || !window.location.pathname.includes("html")) {
        return;
    }
    addListener("#home-button", "click", processBackToMain);
    saveAudioTimeAmbient();
}
