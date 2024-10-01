"use strict";

function initScreen() {
    injectSkinWinScreen();
    getBattleResult();
    //fetchFromServer('/ships', 'GET').then(ships => _fleet.boatData = ships).catch(errorHandler);
    addListener("#home-button", "mousedown", processBackToMain);
    addListener(".continue-buttons", "mousedown", processNavigation);
}

function processNavigation(e){
    const $button = e.target;
    if($button.id === "rematch"){
        processRematch();
    }
    if($button.id === "new-random"){
        processNewRandomGame();
    }
}
