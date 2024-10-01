"use strict";

function injectSkinIndexScreen() {
    const skins = ["space-skin", "bug-skin", "sea-skin"];
    let skin = loadFromStorage("skin");
    if (skin === null) {
        skin = skins[Math.floor(Math.random() * skins.length)];
        saveToStorage("skin", skin);
    }
    injectImagesOnIndexScreen(skin);
    _audio.cursor = new Audio(fillCursorSoundPath());
    _audio.ambient = createAmbient(skin);
}

function injectImagesOnIndexScreen(skin) {
    addSkinClassToBody(skin);
    displayHeader(skin);
    displayImagesIndexScreenMain(skin);
}


function displayImagesIndexScreenMain(skin){
    const $images = cloneImagesIndexScreenMain(skin);
    injectImagesIndexScreenMain($images);
}

function cloneImagesIndexScreenMain(skin) {
    const $template = getTemplate("#images-main");
    $template.querySelector("img.banner").setAttribute("src", `images/${skin}/title-panel.png`);
    $template.querySelector("img.bee").setAttribute("src", `images/${skin}/hit-by-title-banner.png`);
    $template.querySelector("img.start").setAttribute("src", `images/${skin}/start-button.png`);
    return $template;
}

function injectImagesIndexScreenMain($template) {
    const $main = getSelector("main");
    $main.innerHTML = "";
    $main.insertAdjacentHTML("afterbegin", $template.outerHTML);
}
