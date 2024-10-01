"use strict";

function initScreen() {
    injectSkinBoardSize();
    addListener(".navigation", "mousedown", processNavigation);
    addListener(".ui-panel", "click", processChangeBoardSize);
    changeBoard(getSelector(".place-boats-board"), loadFromStorage("boardSize"), loadFromStorage("boardName"));
}

function processNavigation(e) {
    const $button = e.target;
    if($button.classList.contains("next-page-boat-placement")){
        processToBoatPlacement();
    }
    if($button.id === "back-button"){
        processBackToGameMode();
    }
}

function processChangeBoardSize(e){
    buttonSound();
    const $board = getSelector(".place-boats-board");
    $board.innerHTML="";
    if(e.target.alt === "small"){
        changeBoard($board,10, "small");
    }
    if(e.target.alt === "medium"){
        changeBoard($board,15, "medium");
    }
    if(e.target.alt === "big"){
        changeBoard($board,20, "big");
    }
}
