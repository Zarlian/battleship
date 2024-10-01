"use strict";

function processForm() {
    const gameData = createGameData();
    const prefix = loadFromStorage("prefix");
    if (prefix !== "") {
        gameData.prefix = loadFromStorage("prefix");
    }
    postGameData(gameData);
}

function createGameData() {
    const boardSize = parseInt(loadFromStorage("boardSize"));
    return {
        commander: loadFromStorage("commander"),
        type: loadFromStorage("type"),
        fleet: {
            rows: boardSize,
            cols: boardSize,
            ships: loadFromStorage("ships")
        }
    };
}

function saveLocal(response) {
    saveToStorage('playerToken', response.playerToken);
    saveToStorage('gameId', response.gameId);
}

function postGameData(gameData) {
    fetchFromServer('/games', 'POST', gameData)
        .then(response => {
            _token = {};
            _token.playerToken = response.playerToken;
            saveLocal(response);

            checkForOtherPlayer(response.gameId);

        })
        .catch(error => errorHandler(error));
}

function checkGameStatus(response, gameId) {
    if (response.started) {
        checkStatus(response.started);
    } else {
        checkStatus(response.started);
        setTimeout(() => {
                checkForOtherPlayer(gameId);
            },
            _config.delay);
    }
}

function checkForOtherPlayer(gameId) {
    fetchFromServer(`/games/${gameId}`, 'GET')
        .then(response => {
            checkGameStatus(response, gameId);
        })
        .catch(error => errorHandler(error));
}
