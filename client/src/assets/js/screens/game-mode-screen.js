"use strict";

function initScreen() {
    const GAMEMODES = ".game-modes";
    showAllModes();
    injectSkin();
    addListener(GAMEMODES, "mouseenter", processHover);
    addListener(GAMEMODES, "click", processClick);
    addListener(GAMEMODES, "submit", processForm);
}

function processHover(e) {
    playHover();
}

function processClick(e) {
    const $article = e.target.closest("article");
    const $target = e.target.tagName.toLowerCase();
    if ($target === "article" || $target === "img" || $target === "h2" || $target === "p") {
        const id = $article.id;
        resetSelected();
        displayStartPanel(id);
        $article.className = "selected";
    }
}

function processForm(e) {
    e.preventDefault();
    const $article = e.target.closest("article");
    let type = $article.dataset.gameMode;
    type = convertType(type);
    saveToStorage("type", type);

    const difficulty = $article.querySelector("input[name='difficulty']:checked").value;
    saveToStorage("difficulty", difficulty);
    playToBoardSize();
}

function showAllModes() {
    const $template = getTemplate("#game-mode");
    const $container = getSelector(".game-modes");
    for (const gameMode of _GAMEMODES) {
        $template.setAttribute("id", gameMode.id);
        $template.dataset.gameMode = gameMode.data;
        $template.querySelector("img").setAttribute("src", gameMode.image);
        $template.querySelector("img").setAttribute("alt", gameMode.alt);
        $template.querySelector("img").setAttribute("title", gameMode.alt);
        $template.querySelector("h2").innerText = gameMode.title;
        $template.querySelector("p").innerText = gameMode.description;

        $container.insertAdjacentHTML("beforeend", $template.outerHTML);
    }
}
