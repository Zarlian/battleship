"use strict";

function generateBoard(tag, $location, size){
    for (let i = 0; i < size; i++) {
        generateColumn($location, i);
        generateRows(tag, size);
    }
}

function generateRows(tag, size) {
    for (let j = 0; j < size; j++) {
        generateRow(tag, j);
    }
}

function generateColumn($location, column) {
    $location.insertAdjacentHTML("beforeend", `<ul data-row="${column}"</ul>`);
}

function generateRow(tag, row) {
    const $lastUl = getSelector(tag);
    $lastUl.insertAdjacentHTML("beforeend", `<li data-column="${row}"></li>`);
}


