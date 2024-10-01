"use strict";


function getRow($element) {
    return $element.closest("ul").dataset.row;
}

function getColumn($element) {
    return $element.dataset.column;
}

function findUl(coordinate){
    return getSelector(`[data-row="${coordinate.row}"]`);
}

function findLi(coordinate){
    const $ul = findUl(coordinate);
    return $ul.querySelector(`[data-column="${coordinate.column}"]`);
}

function findLiAlphabetic(AlphabeticCoordinate, board) {
    const coordinate = convertCoordinateFromBackend(AlphabeticCoordinate);
    const $row = getSelector(`${board} [data-row="${coordinate.row}"]`);
    return $row.querySelector(`[data-column="${coordinate.column}"]`);
}

function getCoordinate($element) {
    return {
        row:parseInt(getRow($element)),
        column:parseInt(getColumn($element))
    };
}

function getCoordinateAlphabetic($element) {
    const coordinate = getCoordinate($element);
    return convertCoordinateAlphabetic(coordinate);
}

function convertCoordinateAlphabetic(coordinate) {
    const row = convertToLetter(coordinate.row);
    const column = parseInt(coordinate.column) + 1;
    return `${row}-${column}`;
}


function convertToLetter(number) {
    const letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
        "L", "M", "N", "O", "P", "Q", "R", "S", "T"];
    return [letters[number]];
}

function convertToNumber(letter) {
    const letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
        "L", "M", "N", "O", "P", "Q", "R", "S", "T"];
    return letters.indexOf(letter);
}

function convertCoordinateFromBackend(coordinate) {
    const numberCoordinate = {};
    let row = coordinate[0];
    let column = coordinate.slice(2);
    row = convertToNumber(row);
    column = column - 1;
    numberCoordinate.row = row;
    numberCoordinate.column = column;
    return numberCoordinate;
}

