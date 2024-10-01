"use strict";

const _game = {
    salvoSize: 0,
    shots: [],
    myCommander: loadFromStorage("commander"),
    id: loadFromStorage("gameId"),
    enemyBoard: ".enemy-board",
    yourBoard: ".your-board",
    difficulty: loadFromStorage("difficulty")
};

function fireShot(salvo) {
    const emptyList = 0;
    fetchFromServer(`/games/${_game.id}/fleet/${_opponent}/salvo`, 'POST', salvo)
        .then(response => {
            _game.shots.length = emptyList;
            checkResponse(response);
        }).catch(error => errorHandler(error));
}

function checkResponse(result) {
    Object.keys(result).forEach(coordinate => {
        createBullet(coordinate, _game.enemyBoard, result);
    });
    _isAttacker = false;
    getGameInfo();
}

function showDecall(coordinate, board, result) {
    setTimeout(() => {
        if (result[coordinate] === null) {
            playMiss();
            displayMiss(coordinate, _game.enemyBoard);
        } else {
            playHit();
            displayHit(coordinate, _game.enemyBoard);
            checkDifficulty(result, coordinate);
        }
    }, 500);
}

function checkDifficulty(result, coordinate) {
    if (_game.difficulty === "easy") {
        updateSideBoard(result[coordinate], "#enemy-boats");
    }
}

function displayMiss(field, board) {
    const skin = loadFromStorage("skin");
    findLiAlphabetic(field, board).innerHTML = `<img src="images/${skin}/miss.png" alt="miss" title="miss" id="miss">`;
}

function displayHit(field, board) {
    const skin = loadFromStorage("skin");
    findLiAlphabetic(field, board).innerHTML = `<img src="images/${skin}/hit.png" alt="hit" title="hit" id="hit">`;
}

function updateSideBoard(boatName, selector) {
    const shipHit = boatName.toLowerCase();
    const shipLocation = getSelector(`${selector} .${shipHit}`);
    if (shipLocation !== null) {
        updateSideImage(shipLocation, shipHit);
    }
}

function updateSideImage(shipLocation, shipHit) {
    const increment = 1;
    const skin = loadFromStorage("skin");
    let imgNr = getNumberOutOfPath(shipLocation.querySelector("img").src);
    imgNr += increment;
    shipLocation.innerHTML = `<img src="images/${skin}/ships/${shipHit}/${shipHit}-hit-${imgNr}.png" alt="${shipHit}">`;
}

function getNumberOutOfPath(path) {
    const numberOutOfPathOffSet = 5;
    const pathLength = path.length;
    const number = path[pathLength - numberOutOfPathOffSet];
    return parseInt(number);
}

function displaySalvoByEnemy(result) {
    const lastIndex = result.history.length - 1;
    const lastShot = result.history[lastIndex];
    if (lastShot !== undefined) {
        if (lastShot.result !== "ship movement detected") {
            displayShot(lastShot, _opponent, _game.yourBoard);
        }
    }
}

function displayShot(shot, commander, board) {
    if (shot.attackingCommander !== commander) {
        return;
    }

    const result = shot.result;
    for (const coordinate in result) {
        if(result.hasOwnProperty(coordinate)){
            displayShotImage(result, coordinate, board);
        }
    }
}

function displayShotImage(result, coordinate, board) {
    if (result[coordinate] === false) {
        displayMiss(coordinate, board);
    } else {
        const boatName = getBoatName(coordinate);
        if (_game.difficulty === "easy") {
            updateSideBoard(boatName, "#your-boats");
        }
        displayHit(coordinate, board);
    }
}

function getBoatName(coordinate) {
    const $li = findLi(convertCoordinateFromBackend(coordinate));
    return $li.querySelector("img").alt;
}

function fireWhenSalvoComplete() {
    if (_game.shots.length >= _game.salvoSize) {
        _isAttacker = false;
        fireShot({"salvo": _game.shots});
        removeClassSalvo(_game.shots);
    }
}

function addSingleUniqueShotToSalvo(coordinate) {
    if (!_game.shots.includes(coordinate)) {
        _game.shots.push(coordinate);
        addClassSalvo(coordinate);
    }
}

function addClassSalvo(coordinate) {
    findLiAlphabetic(coordinate, _game.enemyBoard).classList.add("salvo");
}

function removeClassSalvo(coordinates) {
    for (const coordinate of coordinates) {
        findLiAlphabetic(coordinate, _game.enemyBoard).classList.remove("salvo");
    }
}

function getSalvoSize(res) {
    const myCommander = loadFromStorage("commander");
    _game.salvoSize = res["salvoSize"][myCommander];
}
