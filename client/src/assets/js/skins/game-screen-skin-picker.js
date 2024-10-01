"use strict";

function injectSkinGameScreen() {
    const skin = loadFromStorage("skin");
    addSkinClassToBody(skin);
    displayButtonsHeader(skin);
    displayButtonsFooter(skin);
    displayShipsGameScreen(skin);
}

function displayButtonsFooter(skin){
    const $buttonsFooter = cloneButtonsFooter(skin);
    injectButtonsFooter($buttonsFooter);
}

function displayShipsGameScreen(skin){
    const $ships = cloneShipsGameScreen(skin);
    injectShipsGameScreen($ships);
}

function cloneButtonsFooter(skin) {
    const $template = getTemplate("template.buttons");
    $template.querySelector(".close").setAttribute("src", `images/${skin}/button-large-close.png`);
    $template.querySelector(".history").setAttribute("src", `images/${skin}/button-large-history.png`);
    return $template;
}

function injectButtonsFooter($template) {
    const $container = getSelector("div.buttons");
    $container.insertAdjacentHTML("beforeend", $template.innerHTML);
}

function cloneShipsGameScreen(skin) {
    const $template = getTemplate("template.boats");
    $template.querySelector("img.carrier-ship").setAttribute("src", `images/${skin}/ships/carrier/carrier-hit-0.png`);
    $template.querySelector("img.battleship-ship").setAttribute("src", `images/${skin}/ships/battleship/battleship-hit-0.png`);
    $template.querySelector("img.cruiser-ship").setAttribute("src", `images/${skin}/ships/cruiser/cruiser-hit-0.png`);
    $template.querySelector("img.submarine-ship").setAttribute("src", `images/${skin}/ships/submarine/submarine-hit-0.png`);
    $template.querySelector("img.destroyer-ship").setAttribute("src", `images/${skin}/ships/destroyer/destroyer-hit-0.png`);
    return $template;
}

function injectShipsGameScreen($template) {
    const $commanderContainer = getSelector("#your-boats");
    $commanderContainer.insertAdjacentHTML("beforeend", $template.outerHTML);

    const $opponentContainer = getSelector("#enemy-boats");
    $opponentContainer.insertAdjacentHTML("beforeend", $template.outerHTML);
}
