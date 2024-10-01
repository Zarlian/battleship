"use strict";

function initScreen(){
    injectSkin();
    addListener("input[type=submit]", "mousedown", processNavigation);
    addListener("form", "submit", processSaveCommander);
}

function processNavigation(e){
    processToWaitingScreen();
}

function processSaveCommander(e) {
    e.preventDefault();
    const input = getSelector("#name").value;
    saveToStorage("commander",input);
    toWaitingScreen();
}

