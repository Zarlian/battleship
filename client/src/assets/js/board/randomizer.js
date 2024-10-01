"use strict";

function randomPlacer() {
    buttonSound();
    reset();
    _fleet.boatData.ships.forEach(boat => {
        const {head, tail, $img, boatPos} = placeBoat(boat);
        storePositions($img.id, boatPos);
        putImageInPlace(head, tail, $img.id, ".place-boats-board");
        removePossibleTails();
        removeImage();
    });
}

function placeBoat(boat) {
    const head = generateRandomEmptyCoordinate();
    const tailPositions = createTailsPos(head, boat.size - 1);
    const validTailPositions = getValidTailPos(head, tailPositions);
    const tail = getRandomTail(validTailPositions);
    const $img = setUpRendering(boat.name);
    const boatPos = getFullBoatPosition(head, tail);
    return {head, tail, $img, boatPos};
}


function getRandomTail(validTailPositions) {
    const random = Math.floor(Math.random() * validTailPositions.length);
    return validTailPositions[random];
}

function generateRandomEmptyCoordinate() {
    let coordinate = generateRandomCoordinate();
    while (isCoordinateTaken(coordinate)) {
        coordinate = generateRandomCoordinate();
    }
    return coordinate;
}

function generateRandomCoordinate() {
    return {
        row: generateRandomNumber(),
        column: generateRandomNumber()
    };
}

function generateRandomNumber() {
    const boardSize = loadFromStorage("boardSize");
    return Math.floor(Math.random() * boardSize);
}

function isCoordinateTaken(coordinate) {
    const alphaCoordinate = convertCoordinateAlphabetic(coordinate);
    return _fleet.ships.some(ship => ship.location.includes(alphaCoordinate));
}

function getValidTailPos(head, tailPositions) {
    const boardSize = loadFromStorage("boardSize") - 1;
    return tailPositions.filter(tail => isTailValidPos(head,tail,boardSize));
}
