"use strict";

function injectSkinWinScreen() {
    const skin = loadFromStorage("skin");
    addSkinClassToBody(skin);
    displayWinScreen(skin);
}

function displayWinScreen(skin){
    const $continueButtons = cloneContinueButtons(skin);
    displayContinueButtons($continueButtons);
    displayButtonsHeader(skin);
}

function cloneContinueButtons(skin) {
    const $template = getTemplate("template.continue-buttons");
    $template.querySelector("#rematch").setAttribute("src", `images/${skin}/button-private-game.png`);
    $template.querySelector("#new-random").setAttribute("src", `images/${skin}/button-random-game.png`);
    return $template;
}

function displayContinueButtons($template) {
    const $container = getSelector("div.continue-buttons");
    $container.insertAdjacentHTML("beforeend", $template.innerHTML);
}
