"use strict";

function initScreen() {
    injectSkinGameScreen();

    loadMyCommander();
    getGameInfo();

    renderBoard();

    addListener("#history-button", "click", processShowHistory);
    addListener("#close-history", "click", processHideHistory);

    if (isMoveAllowed()) {
        addListener(_game.yourBoard, "click", processMove);
    }
    addListener(_game.enemyBoard, "mouseup", processCreateSalvo);
}

function isMoveAllowed() {
    const type = loadFromStorage("type");
    return (type === "move" || type === "move+salvo");
}

function processMove(e) {
    if (_isAttacker && positionContainsShip(e)) {
        const ship = e.target.id;
        fetchFromServer(`/games/${_game.id}/fleet/${_movingCommander}/${ship}/location`, "PATCH")
            .then(res => {
                moveShip(res);
            }).catch(() => playError());
    }
    removeClassSalvo(_game.shots);
    _game.shots.length = 0;
    getGameInfo();
}

function moveShip(res) {
    const shipName = res.ship.name;
    const shipLocation = res.location;
    const tail = res.location[0];
    const head = res.location[res.location.length - 1];

    removeShip(shipName);
    putSingleImageInPlace(head, tail, shipName, shipLocation, _game.yourBoard);
    updateLocationShip(shipName, shipLocation);
}

function processCreateSalvo(e) {
    const target = e.target.title;
    if (_isAttacker && target !== "hit") {
        const coordinate = getCoordinateAlphabeticFromClick(e);
            addSingleUniqueShotToSalvo(coordinate);
            fireWhenSalvoComplete();
    } else {
        playError();
    }
}

function getCoordinateAlphabeticFromClick($element) {
    const clickOnLi = $element.target.tagName.toLowerCase() === "li";
    const clickOnImg = $element.target.tagName.toLowerCase() === "img";
            if (clickOnLi) {
                return getCoordinateAlphabetic($element.target);
            }
            else if (clickOnImg) {
                return getCoordinateAlphabetic($element.target.closest("li"));
            }
            return null;
}

function processShowHistory(e) {
    buttonSound();
    e.preventDefault();
    const $historyButton = getSelector('#history-button');
    const $closeButton = getSelector('#close-history');
    const $div = getSelector('.summary');
    $div.classList.remove("hidden");
    $historyButton.classList.add("hidden");
    $closeButton.classList.remove("hidden");
}

function processHideHistory(e) {
    buttonSound();
    e.preventDefault();
    const $div = getSelector('.summary');
    const $historyButton = getSelector('#history-button');
    const $closeButton = getSelector('#close-history');
    $div.classList.add("hidden");
    $historyButton.classList.remove("hidden");
    $closeButton.classList.add("hidden");
}

