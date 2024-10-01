"use strict";

function resetBoard() {
    buttonSound();
    reset();
}
function resetContainer(img) {
    img.closest("li").innerHTML = "";
}

function clearBoard(){
    const images = document.querySelectorAll(".placed");
    images.forEach(image => {
        resetContainer(image);
    });
}
function putImageBack($el, name) {
    const skin = loadFromStorage("skin");
    $el.innerHTML = `<img src="images/${skin}/ships/${name}/${name}-left.png" alt=${name} title=${name} id=${name} class="ships">`;
}
function getCorrectBoat($el,counter){
    putImageBack($el,_fleet.boatData.ships[counter].name);
}
function putImagesBack() {
    const $divContainers = document.querySelectorAll(".boats-board div");
    $divContainers.forEach( ($div, index) => {
        getCorrectBoat($div,index);
    });
}

function resetLi() {
    const $liList = document.querySelectorAll(".filled");
    $liList.forEach($li => {
        $li.classList.remove("filled");
    });
}

function reset() {
    putImagesBack();
    clearBoard();
    resetLi();
    _fleet.ships = [];
}
