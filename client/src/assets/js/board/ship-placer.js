"use strict";



function renderImage($tailLi) {
    const tailCoordinate = getTailCoordinate($tailLi);
    const headCoordinate = getHeadCoordinate();

    const $img = getSelector(".selected");
    storeBoatPositions(headCoordinate, tailCoordinate, $img);
    putImageInPlace(headCoordinate, tailCoordinate, $img.id, ".place-boats-board");

    removeClass("boat-head");
    removePossibleTails();
    removeImage();
}

function getTailCoordinate($tailLiContainer) {
    return getCoordinate($tailLiContainer);
}

function getHeadCoordinate() {
    return getCoordinate(getSelector(".boat-head"));
}

function storeBoatPositions(headCoordinate, tailCoordinate, $img) {
    const boatPos = getFullBoatPosition(headCoordinate, tailCoordinate);
    storePositions($img.id, boatPos);
}

function putImageInPlace(head, tail, id, board) {
    const ship = getShipByName(id);
    ship.location.forEach((coordinate, index) => {
        setImageDirection(tail, head, coordinate, id, index, board);
    });
}

function getShipByName(shipName) {
    return _fleet.ships.find(ship => ship.name === shipName);
}

function setImageDirection(tail, head, coordinate, id, index, board) {
    if (tail.column > head.column) {
        renderImgOnCoordinate(coordinate, id, index, "left", board);
    }
    if (tail.column < head.column) {
        renderImgOnCoordinate(coordinate, id, index, "right", board);
    }
    if (tail.row > head.row) {
        renderImgOnCoordinate(coordinate, id, index, "down", board);
    }
    if (tail.row < head.row) {
        renderImgOnCoordinate(coordinate, id, index, "up", board);
    }
}

function renderImgOnCoordinate(coordinate, id, counter, direction, board) {
    const skin = loadFromStorage("skin");
    const $li = findLiAlphabetic(coordinate, board);
    $li.classList.add("filled");
    $li.innerHTML = `<img src="images/${skin}/ships/${id}/${id}-${counter}.png" alt=${id} title=${id} id=${id} class="placed ${direction}">`;
}

function removePossibleTails() {
    const $liList = document.querySelectorAll(".tail");
    $liList.forEach($li => {
        $li.classList.remove("tail");
    });
}

function removeImage() {
    const $div = getSelector(".selected").closest("div");
    $div.innerHTML = "";
}

function setHeadPos($element) {
    addClass($element, "boat-head");
}

function createTailsPos(coordinate, boatSize) {
    return [{
        row: (coordinate.row + boatSize),
        column: coordinate.column
        },
        {
            row: (coordinate.row - boatSize),
            column: coordinate.column
        },
        {
            row: coordinate.row,
            column: (coordinate.column + boatSize)
        },
        {
            row: coordinate.row,
            column: (coordinate.column - boatSize)
        }
    ];
}

function isBoatOverlapping(head, tail) {
    const boatPosIfPlaced = getFullBoatPosition(head, tail);
    return _fleet.ships.some(ship => {
        return ship.location.some(position => {
            return boatPosIfPlaced.includes(position);
        });
    });
}

function isInsideBoard(tail, boardSize) {
    return tail.row >= 0 && tail.column >= 0 && tail.row <= boardSize && tail.column <= boardSize;
}

function isTailValidPos(head, tail, boardSize) {
    return isInsideBoard(tail, boardSize) && !isBoatOverlapping(head, tail);
}

function renderTailsPos(head, tails) {
    const boardOffset = 1;
    const boardSize = loadFromStorage("boardSize") - boardOffset;
    tails.filter(tail => isTailValidPos(head, tail, boardSize))
         .forEach(tail => {
             const $li = findLi(tail);
             addClass($li, "tail");
         });
}

function findPossibleTails($element) {
    const headSize = 1;
    const boatSize = getCorrectBoatSize(getSelector(".selected").id) - headSize;
    const head = getCoordinate($element);
    const tails = createTailsPos(head, boatSize);

    renderTailsPos(head, tails);
}

function isHeadPosSet() {
    return getSelector(".boat-head") !== null;
}

function isBoatSelected() {
    return getSelector(".selected") !== null;
}

function removeClass(className) {
    if (!isBoatSelected()) {
        return;
    }
    const $selectedElement = getSelector(`.${className}`);
    $selectedElement.classList.remove(className);
}

function setUpRendering(id) {
    const $img = getSelector(`#${id}`);
    $img.classList.add("selected");
    return $img;
}
