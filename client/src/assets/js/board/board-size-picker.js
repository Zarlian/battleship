"use strict";

function changeBoard($board, sizeBoard, boardName){
    const tag ="div ul:last-of-type";
    $board.setAttribute("class", `${boardName} place-boats-board board`);
    generateBoard(tag, $board, sizeBoard);
}

function saveBoardSize() {
    const boards = [
        { name: "small", size: 10 },
        { name: "medium", size: 15 },
        { name: "big", size: 20 },
    ];
    const $board = getSelector(".place-boats-board");
    let board = boards.find(board => $board.classList.contains(board.name));
    if (!board) {
        board = { name: "small", size: 10 };
    }
    saveBoardInStorage(board.name, board.size);
}

function saveBoardInStorage(name, size){
    saveToStorage("boardName", name);
    saveToStorage("boardSize", size);
}
