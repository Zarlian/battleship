"use strict";

let _isAttacker = true;
_token = {
    playerToken: loadFromStorage("playerToken")
};
let _opponent = "";

function getGameInfo() {
    const gameId = loadFromStorage("gameId");
    fetchFromServer(`/games/${gameId}`, "Get")
        .then(res => {
            renderGame(res);
            checkWhosTurn(res);
            renderWhosTurn(_isAttacker);
            getSalvoSize(res);
            checkFinish(res);
            waitForOpponent();
        });
}

function renderGame(res) {
    renderEnemyName(res);
    renderHistory(res.history);
    displaySalvoByEnemy(res);
}

function waitForOpponent() {
    if (!_isAttacker) {
        setTimeout(getGameInfo, _config.delay);
    }
}

function getOpponentName(res) {
    const myCommander = loadFromStorage("commander");
    const attackingCommander = res.attackingCommander;
    const defendingCommander = res.defendingCommander;
    if (attackingCommander === myCommander) {
        _opponent = defendingCommander;
    } else {
        _opponent = attackingCommander;
    }
    return _opponent;
}

function renderEnemyName(res) {
    const opponent = getOpponentName(res);
    const $enemy = getSelector("#enemy-commander");
    $enemy.innerHTML = "";
    $enemy.insertAdjacentHTML("beforeend", `${opponent}`);
}

function renderHistory(data) {
    const $history = getSelector(".summary div");
    $history.innerHTML = "";
    injectHistory(data, $history);
}

function injectHistory(data, $history){
    data.forEach(turn => {
        $history.insertAdjacentHTML("beforeend", `<p class="commander-name">${turn.attackingCommander}</p>`);
        const result = turn.result;
        renderResultPerTurn(turn, $history, result);
    });
}

function renderResultPerTurn(turn, $history, result){
    if (turn.result === "ship movement detected") {
        $history.insertAdjacentHTML("beforeend", `<p class="move">${result}</p>`);
    } else {
        renderResult(result, $history);
    }
}

function renderResult(result, $history) {
    for (const object in result) {
        if (object === "ship") {
            const ship = result[object];
            $history.insertAdjacentHTML("beforeend", `<p class="move">${ship.name}</p>`);
        } else {
            $history.insertAdjacentHTML("beforeend", `<p class="move">${object}: ${result[object]}</p>`);
        }
    }
}

function checkWhosTurn(res) {
    const myCommander = loadFromStorage("commander");
    _isAttacker = res.attackingCommander === myCommander;
}

function renderWhosTurn(isAttacker) {
    const $who = getSelector("#whoTurn");
    let message = "";

    if (isAttacker) {
        message = "Time to Attack!";
    } else {
        message = "Brace for impact!";
    }

    $who.innerHTML = "";
    $who.insertAdjacentHTML("beforeend", message);
}


function checkFinish(res) {
    if (res.winner !== null) {
        toWinScreen();
    }
}
