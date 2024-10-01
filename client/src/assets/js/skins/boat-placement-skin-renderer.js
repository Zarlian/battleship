"use strict";

function injectSkinBoatPlacement() {
    const skin = loadFromStorage("skin");
    addSkinClassToBody(skin);
    displayButtonsHeader(skin);
    displayButtonsBoatPlacement(skin);
}

function displayButtonsBoatPlacement(skin) {
    const $refreshButtons = cloneShuffleRefreshButtons(skin);
    const $boats = cloneBoatsBoard(skin);
    const $navButtons = cloneNavButtons(skin);
    injectButtonsBoatPlacement($refreshButtons, $boats, $navButtons);
}

function  injectButtonsBoatPlacement($refreshButtons, $boats, $navButtons){
    const $container = getSelector(".buttons");
    $container.innerHTML = "";
    injectShuffleRefreshButtons($refreshButtons, $container);
    injectBoatsBoard($boats, $container);
    injectNavButtons($navButtons, $container);
}

function cloneShuffleRefreshButtons(skin) {
    const $template = getTemplate("template.shuffle-refresh");
    $template.querySelector("#shuffle").setAttribute("src", `images/${skin}/shuffle-button.png`);
    $template.querySelector("#refresh").setAttribute("src", `images/${skin}/refresh-button.png`);
    return $template;
}

function injectShuffleRefreshButtons($template, $container) {
    $container.insertAdjacentHTML("beforeend", $template.outerHTML);
}

function cloneBoatsBoard(skin) {
    const $template = getTemplate("template.boats-board");
    $template.querySelector("#battleship").setAttribute("src", `images/${skin}/ships/battleship/battleship-left.png`);
    $template.querySelector("#carrier").setAttribute("src", `images/${skin}/ships/carrier/carrier-left.png`);
    $template.querySelector("#cruiser").setAttribute("src", `images/${skin}/ships/cruiser/cruiser-left.png`);
    $template.querySelector("#submarine").setAttribute("src", `images/${skin}/ships/submarine/submarine-left.png`);
    $template.querySelector("#destroyer").setAttribute("src", `images/${skin}/ships/destroyer/destroyer-left.png`);
    return $template;
}

function injectBoatsBoard($template, $container) {
    $container.insertAdjacentHTML("beforeend", $template.outerHTML);
}

function cloneNavButtons(skin) {
    const $template = getTemplate("#nav-buttons");
    $template.querySelector(".next-page-name-screen").setAttribute("src", `images/${skin}/next-button.png`);
    $template.querySelector(".back-to-board-size").setAttribute("src", `images/${skin}/back-button.png`);
    return $template;
}

function injectNavButtons($template, $container) {
    $container.insertAdjacentHTML("beforeend", $template.outerHTML);
}
