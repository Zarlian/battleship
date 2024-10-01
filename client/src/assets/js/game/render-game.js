"use strict";

function renderBoard() {
    const yourBoard = "#your-play-board";
    const enemyBoard = "#enemy-play-board";
    const $yourBoard = getSelector(yourBoard);
    const $enemyBoard = getSelector(enemyBoard);
    buildBoard(yourBoard, $yourBoard);
    buildBoard(enemyBoard, $enemyBoard);
    drawYourShips(yourBoard);
}

function buildBoard(tag, $board) {
    const boardSize = window.loadFromStorage("boardSize");
    const boardName = window.loadFromStorage("boardName");
    $board.classList.add(`${boardName}`);
    generateBoard(`${tag} ul:last-of-type`, $board, boardSize);
}

function drawYourShips(board) {
    _fleet.ships = loadFromStorage("ships");
    _fleet.ships.forEach(ship => {
        const tail = convertCoordinateFromBackend(ship.location[0]);
        const head = convertCoordinateFromBackend(ship.location[ship.location.length - 1]);
        ship.location.forEach((coordinate, index) => {
            setImageDirection(tail, head, coordinate, ship.name, index, board);
        });
    });
}
