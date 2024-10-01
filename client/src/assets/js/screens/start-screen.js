"use strict";

function initScreen() {
    injectSkinIndexScreen();
    addListener("#start-button", "mousedown", processNavigation);
    addListener(".skins", "click", processSkinSwap);
}

function processNavigation(e) {
    processToGameMode();
}

function processSkinSwap(e) {
    const restartSong = 0;
    const skin = e.target.title;
    _audio.ambient.pause();
    saveToStorage("skin", skin);
    saveToStorage("ambient-time", restartSong);
    initScreen();
    playAmbient();
    openVolumeMenu();
    controlVolume();
}

