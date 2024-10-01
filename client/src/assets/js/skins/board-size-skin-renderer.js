"use strict";

function injectSkinBoardSize() {
    const skin = loadFromStorage("skin");
    displayButtonsHeader(skin);
    addSkinClassToBody(skin);
    displaySkinButtonsPanel(skin);
}

function displaySkinButtonsPanel(skin) {
    const $boardSizeButtons = cloneSkinSizeButtons(skin);
    const $navButtons = cloneSkinNavButtons(skin);
    injectSkinButtonsPanel($boardSizeButtons, $navButtons);
}

function injectSkinButtonsPanel($boardSizeButtons, $navButtons){
    const $container = getSelector("div.panel");
    $container.innerHTML = "";
    injectSkinSizeButtons($boardSizeButtons, $container);
    injectSkinNavButtons($navButtons, $container);
}

function cloneSkinSizeButtons(skin) {
    const $template = getTemplate("template.user-panel");
    $template.querySelector(".small-button").setAttribute("src", `images/${skin}/small-board-button.png`);
    $template.querySelector(".medium-button").setAttribute("src", `images/${skin}/medium-board-button.png`);
    $template.querySelector(".big-button").setAttribute("src", `images/${skin}/big-board-button.png`);
    return $template;
}

function injectSkinSizeButtons($template, $container) {
    $container.insertAdjacentHTML("beforeend", $template.outerHTML);
}

function cloneSkinNavButtons(skin) {
    const $template = getTemplate("template.navigation");
    $template.querySelector(".next-page-boat-placement").setAttribute("src", `images/${skin}/next-button.png`);
    $template.querySelector("#back-button").setAttribute("src", `images/${skin}/back-button.png`);
    return $template;
}

function injectSkinNavButtons($template, $container) {
    $container.insertAdjacentHTML("beforeend", $template.outerHTML);
}
