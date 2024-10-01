"use strict";

function cursorController() {
    const $body = getSelector('body');
    switchClasses($body, "left", "right", "still");
    _audio.cursor.pause();
    handleMouseEvent($body);
    setTimeout(cursorController, 1000);
}

function handleMouseEvent($body) {
    let prevX = null;
    window.addEventListener('mousemove', (event) => {
        const xAxis = event.pageX;
        changeDirection(prevX, xAxis, $body);
        prevX = xAxis;
    });
}

function changeDirection(prevX, x, $body) {
    if (prevX !== null) {
        playSound();
        checkDirection(x, prevX, $body);
    }
}

function checkDirection(x, prevX, $body) {
    if (x > prevX) {
        switchClasses($body, "still", "left", "right");
    } else if (x < prevX) {
        switchClasses($body, "still", "right", "left");
    }
}

function switchClasses($body, removeClassOne, removeClassTwo, toAdd) {
    $body.classList.remove(removeClassOne, removeClassTwo);
    $body.classList.add(toAdd);
}

function playSound() {
    if (_audio.cursor.paused) {
        play(_audio.cursor, "cursor-volume");
    }
}


