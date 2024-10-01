"use strict";

const _audio = {
    volume : 0.5,
    ambientVolumeKey : "ambient-volume",
    ambient : createAmbient(),
    cursor : new Audio(fillCursorSoundPath())
};

let isCreated = false;

function createAudio(path) {
    return new Audio(path);
}

function buttonSound() {
    const buttonClick = createAudio("assets/sounds/buttons/button.wav");
    buttonClick.volume = decideVolume();
    buttonClick.play();
}

function createAmbient() {
    const currentSkin = loadFromStorage("skin");
    if(currentSkin === null){
        return null;
    }
    return createAudio(`assets/sounds/${currentSkin}/ambient.mp3`);
}

function playAmbient() {
    const ambientTime = getAudioTime();
    play(_audio.ambient, _audio.ambientVolumeKey, ambientTime);
    if (loadFromStorage(_audio.ambientVolumeKey) !== null) {
        _audio.ambient.volume = loadFromStorage(_audio.ambientVolumeKey);
    } else {
        _audio.ambient.volume = 0.6;
    }
    _audio.ambient.loop = true;
}

function controlVolume() {
    buildVolumeSliders();
    changeAmbientVolume();
    changeCursorVolume();
    changeFxVolume();
}

function processToGameMode() {
    buttonSound();
    setTimeout(toGameModeScreen, 250);
}

function processBackToMain() {
    buttonSound();
    setTimeout(toStartScreen, 250);
}

function playToBoardSize() {
    buttonSound();
    setTimeout(toBoardSizeScreen, 250);
}

function processBackToBoardSize() {
    buttonSound();
    setTimeout(toBoardSizeScreen, 250);
}

function processBackToGameMode() {
    buttonSound();
    setTimeout(toGameModeScreen, 250);
}

function processToBoatPlacement() {
    buttonSound();
    setTimeout(toBoatPlacementScreen, 250);
}

function processToWaitingScreen() {
    buttonSound();
    setTimeout(waitingFunction, 250);
}

function waitingFunction() {
    //I need a function here to use the setTimeout, but it doesn't have to do anything, find another solution
}

function processRematch() {
    buttonSound();
    setTimeout(rematch, 250);
}

function processNewRandomGame() {
    buttonSound();
    setTimeout(newRandomGame, 250);
}

function processToNameScreen() {
    if (_fleet.ships.length >= 5){
        playToNameScreen();
    } else{
        playError();
    }
}

function playToNameScreen() {
    buttonSound();
    setTimeout(toNameScreen, 250);
}

function playHover() {
    const hover = createAudio("assets/sounds/bug-skin/hover-game-mode.mp3");
    hover.volume = decideVolume();
    hover.play();
}

function playError() {
    const error = createAudio("assets/sounds/error.mp3");
    error.volume = decideVolume();
    error.play();
}

function playHit() {
    const skin = loadFromStorage("skin");
    const hit = createAudio(`assets/sounds/${skin}/bug-hit.mp3`);
    hit.volume = decideVolume();
    hit.play();
}

function playMiss() {
    const skin = loadFromStorage("skin");
    const miss = createAudio(`assets/sounds/${skin}/bug-mis.mp3`);
    miss.volume = decideVolume();
    miss.play();
}

function playShot() {
    const skin = loadFromStorage("skin");
    const shot = createAudio(`assets/sounds/${skin}/flying-bullet.mp3`);
    shot.volume = decideVolume();
    shot.play();
}

function playSelectBug() {
    const skin = loadFromStorage("skin");
    const selectBug = createAudio(`assets/sounds/${skin}/bug-selected.mp3`);
    selectBug.volume = decideVolume();
    selectBug.play();
}


function playWaiting() {
    if (!isCreated) {
        const waiting = createAudio("assets/sounds/waiting.mp3");
        waiting.volume = _audio.volume;
        waiting.play();
        waiting.loop = true;
        isCreated = true;
    }
}

// change ambient volume

function changeAmbientVolume() {
    addListener("#ambient-volume-slider", "input", changeVolumeAmbient);
}

function changeVolumeAmbient() {
    const ambientVolume = getSelector("#ambient-volume-slider").value;
    saveToStorage(_audio.ambientVolumeKey, ambientVolume);
    _audio.ambient.addEventListener("canplay", function () {
        const ambientVolume = loadFromStorage(_audio.ambientVolumeKey);
            _audio.ambient.volume = ambientVolume;
    });
    play(_audio.ambient, _audio.ambientVolumeKey, _audio.ambient.currentTime);
}

//change fx volume

function changeFxVolume() {
    addListener("#fx-volume-slider", "input", changeVolumeFx);
}

function changeVolumeFx() {
    const fxVolume = getSelector("#fx-volume-slider").value;
    saveToStorage("fx-volume", fxVolume);
}

//change cursor volume

function changeCursorVolume() {
    addListener("#volume-slider", "input", changeVolumeCursor);
}

function changeVolumeCursor() {
    const localStorageKey = "cursor-volume";
    const cursorVolume = getSelector("#volume-slider").value;
    saveToStorage(localStorageKey, cursorVolume);
    _audio.cursor.addEventListener("canplay", function () {
        const cursorVolume = loadFromStorage(localStorageKey);
        if (cursorVolume !== null) {
            _audio.cursor.volume = cursorVolume;
        }
    });
}

//general

function play(audio, localStorage, startTime = 0) {
    if (loadFromStorage(localStorage) !== null) {
        audio.volume = loadFromStorage(localStorage);
    } else {
        audio.volume = _audio.volume;
    }
    audio.currentTime = startTime;

    const playAttempt = setInterval(() => {
        audio
            .play()
            .then(() => {
                clearInterval(playAttempt);
            })
            .catch((error) => {
                // do nothing and keep trying
            });
    }, 500);
}


function openVolumeMenu() {
    addListener("#volume-button-general", "click", unhideMenu);
}

function unhideMenu() {
    buttonSound();
    getSelector(".volume").classList.toggle("hidden");
}

function decideVolume() {
    let volume;
    const fxVolume = loadFromStorage("fx-volume");
    if (fxVolume !== null) {
        volume = fxVolume;
    } else {
        volume = _audio.volume;
    }
    return volume;
}

function fillCursorSoundPath(){
    const skin = loadFromStorage("skin");
    if(skin === null){
        return "assets/sounds/bug-skin/cursor.mp3";
    } else {
        return `assets/sounds/${skin}/cursor.mp3`;
    }
}

function saveAudioTimeAmbient(){
    saveToStorage("ambient-time",_audio.ambient.currentTime);
}

function getAudioTime(){
   const ambientTime = loadFromStorage("ambient-time");
   if (ambientTime === null){
       return 0;
   } else {
       return ambientTime;
   }
}

