"use strict";

const _movingCommander = loadFromStorage("commander");

function removeShip(shipName) {
    const $shipImages = document.querySelectorAll(`#${shipName}`);
    for (const shipImage of $shipImages) {
        const $shipLi = shipImage.closest("li");
        $shipLi.innerHTML = "";
        $shipLi.classList.remove("filled");
    }
}

function updateLocationShip(shipName, location) {
    const shipIndex = _fleet.ships.findIndex(ship => ship.name === shipName);
    if (shipIndex !== -1) {
        _fleet.ships[shipIndex].location = location;
        saveToStorage("ships", _fleet.ships);
    }
}

function putSingleImageInPlace(head, tail, id, location, board) {
    head = convertCoordinateFromBackend(head);
    tail = convertCoordinateFromBackend(tail);
    location.forEach((coordinate, index) => {
        setImageDirection(tail, head, coordinate, id, index, board);
    });
}

function positionContainsShip($element) {
    const isImage = $element.target.tagName.toLowerCase() === "img";
    return isImage && hasIdOfShip($element);
}

function hasIdOfShip($element) {
    switch ($element.target.id) {
        case "carrier":
        case "battleship":
        case "cruiser":
        case "submarine":
        case "destroyer":
            return true;
        default:
            return false;
    }
}
