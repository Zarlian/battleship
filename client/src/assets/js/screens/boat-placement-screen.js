"use strict";



function initScreen() {
    injectSkinBoatPlacement();
    changeBoard(getSelector(".place-boats-board"), loadFromStorage("boardSize"), loadFromStorage("boardName"));
    fetchFromServer('/ships', 'GET').then(ships => _fleet.boatData = ships).catch(errorHandler);

    addListener(".shuffle-reset", "click", processShuffleReset);
    addListener(".boats-board", "click", processSelectedBoat);
    addListener(".place-boats-board", "click", processBoardClick);
    addListener("nav", "mousedown", processNavigation);
    addListener(".next-page-name-screen", "click", processSaveFleetPos);
}

function processShuffleReset(e) {
    const $button = e.target;
    if($button.id === "shuffle"){
        randomPlacer();
    }
    if($button.id === "refresh"){
        resetBoard();
    }
}

function processBoardClick(e) {
    const $element = e.target;
    if ($element.tagName.toLowerCase() !== "li") {
        return;
    }
    if ($element.classList.contains("tail")) {
        handleTailClick($element);
    } else {
        handleNonTailClick($element);
    }
}

function handleTailClick($element){
    addClass($element, "selected-tail");
    renderImage($element);
    playSelectBug();
}

function handleNonTailClick($element){
    if (isHeadPosSet()) {
        removeClass("boat-head");
        removePossibleTails();
    }
    if (isBoatSelected()) {
        setHeadPos($element);
        findPossibleTails($element);
    }
}

function processSelectedBoat(e) {
    if (!e.target.classList.contains("ships")) {
        return;
    }
    playSelectBug();
    removePossibleTails();
    removeClass("selected");
    addClass(e.target, "selected");
}

function processNavigation(e) {
    const $button = e.target;
    if($button.classList.contains("next-page-name-screen")){
        processToNameScreen();
    }
    if($button.id === "back"){
        processBackToBoardSize();
    }
}

function processSaveFleetPos(e) {
    saveToStorage("ships",_fleet.ships);
}

