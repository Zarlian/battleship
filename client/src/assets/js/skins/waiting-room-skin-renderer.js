"use strict";

function injectSkinWaitingRoom() {
    const skin = loadFromStorage("skin");
    addSkinClassToBody(skin);
    displayButtonsHeader(skin);
    displayGameButtons(skin);
}

function displayGameButtons(skin){
    const $gameButtons = cloneGameButtons(skin);
    injectGameButtons($gameButtons);
}

function cloneGameButtons(skin) {
    const $template = getTemplate("template.game-buttons");
    $template.querySelector(".private").setAttribute("src", `images/${skin}/button-private-game.png`);
    $template.querySelector(".join").setAttribute("src", `images/${skin}/button-join-game.png`);
    $template.querySelector(".random").setAttribute("src", `images/${skin}/button-random-game.png`);
    return $template;
}

function injectGameButtons($template) {
    const $container = getSelector("div.game-buttons");
    $container.insertAdjacentHTML("beforeend", $template.innerHTML);
}
