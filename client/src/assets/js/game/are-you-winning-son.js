"use strict";


function getBattleResult() {
    const gameId = loadFromStorage("gameId");
    _token = {
        playerToken : loadFromStorage("playerToken")
    };
    fetchFromServer(`/games/${gameId}`, "Get")
        .then(res => {
            renderBattleFeedback(res.winner);
        });

}

function renderBattleFeedback(result) {
    const myCommander = loadFromStorage("commander");
    if (result === myCommander){
        injectBattleFeedback("won-h1","won-h2");
    }else {
        injectBattleFeedback("lost-h1","lost-h2");
    }
}

function injectBattleFeedback(class1, class2) {
    const $h1 = getSelector("h1");
    $h1.innerHTML = "";
    const $h2 = getSelector("h2");
    $h2.innerHTML = "";
    $h1.setAttribute("class", class1);
    $h2.setAttribute("class", class2);
}

function rematch() {
    shipShuffler();
    saveToStorage("ships", _fleet.ships);
    processForm();
}

function newRandomGame(){
    const randomPrefix = "";
    saveToStorage("prefix", randomPrefix);
    shipShuffler();
    saveToStorage("ships", _fleet.ships);
    processForm();
}

function checkStatus(started) {
    const $status = getSelector("#status");
    $status.innerHTML = "";
    if (started) {
        $status.insertAdjacentHTML("beforeend", `Your game will start soon`);
        toGameScreen();
    } else {
        $status.insertAdjacentHTML("beforeend", `Searching for game.`);
    }
}

function shipShuffler(){
    _fleet.boatData.ships.forEach(boat => {
        const head = generateRandomEmptyCoordinate();
        const tailPositions = createTailsPos(head,boat.size - 1);
        const validTailPositions = getValidTailPos(head, tailPositions);
        const tail = getRandomTail(validTailPositions);
        const boatPos = getFullBoatPosition(head,tail);
        storePositions(boat.name,boatPos);
    });
}
