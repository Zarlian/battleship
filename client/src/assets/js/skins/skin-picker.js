"use strict";

function injectSkin() {
    const skin = loadFromStorage("skin");
    addSkinClassToBody(skin);
    displayButtonsHeader(skin);
}

function addSkinClassToBody(skin) {
    getSelector("body").setAttribute("class", `${skin}`);
}

function displayButtonsHeader(skin) {
    const $headerButtons = getSelector(".buttons-header");
    $headerButtons.innerHTML = "";
    injectButtonsHeader($headerButtons, skin);
}


function injectButtonsHeader($headerButtons, skin) {
    $headerButtons.insertAdjacentHTML("beforeend", `
            <img src="images/${skin}/icon-home.png" alt="Home" title="Home" id="home-button">
            <img id="volume-button-general" src="images/${skin}/volume-button-general.png" alt="volume-button-general"
                 title="volume-button-general">
            <div class="volume hidden"></div>
    `);
}

function displayHeader(skin) {
    const $buttonHeader = getSelector(".audio");
    $buttonHeader.innerHTML = "";
    injectHeader($buttonHeader, skin);
}

function injectHeader($buttonHeader, skin){
    $buttonHeader.insertAdjacentHTML("beforeend", `
    <div>
    <img id="volume-button-general" src="images/${skin}/volume-button-general.png" alt="volume-button-general" title="volume-button-general">
        <div class="volume hidden">
            <img class="cursor-volume" src="images/${skin}/volume-button-cursor.png" alt="fly button" title="fly volume button">
                <input class="styled-slider slider-progress" type="range" id="volume-slider" min="0" max="0.6"
                       value="0.6"
                       step="0.06">
        </div>
</div>
    `);
}

function buildVolumeSliders() {
    const skin = loadFromStorage("skin");
    const $volumeMenu = getSelector(".volume");
    $volumeMenu.innerHTML = "";
    injectMusicSlider($volumeMenu);
    injectFxSlider($volumeMenu);
    injectCursorSlider($volumeMenu, skin);
}

function injectMusicSlider($volumeMenu) {
    $volumeMenu.insertAdjacentHTML("beforeend", `
<div>
<img class="music-volume" src="images/volume-button-music.png" alt="volume-button-music" title="volume-button-music">
<input class="styled-slider slider-progress" type="range" id="ambient-volume-slider" min="0" max="0.6" value="${loadFromStorage('ambient-volume')}" step="0.01">
</div>
`);
}

function injectFxSlider($volumeMenu) {
    $volumeMenu.insertAdjacentHTML("beforeend", `
<div>
<img class="fx-volume" src="images/volume-button-fx.png" alt="images/volume-button-fx" title="images/volume-button-fx">
<input class="styled-slider slider-progress" type="range" id="fx-volume-slider" min="0" max="0.6" value="${loadFromStorage('fx-volume')}" step="0.06">
</div>
`);
}

function injectCursorSlider($volumeMenu, skin) {
    $volumeMenu.insertAdjacentHTML("beforeend", `
<div>
<img class="cursor-volume" src="images/${skin}/volume-button-cursor.png" alt="fly button" title="fly volume button">
<input class="styled-slider slider-progress" type="range" id="volume-slider" min="0" max="0.6" value="${loadFromStorage('cursor-volume')}" step="0.06">
</div>
`);
}
