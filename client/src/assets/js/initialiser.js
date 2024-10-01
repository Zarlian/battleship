"use strict";

document.addEventListener('DOMContentLoaded', init);

function init() {
    cursorController();
    initScreen();
    playAmbient();
    openVolumeMenu();
    controlVolume();
    goToMainScreen();
}

