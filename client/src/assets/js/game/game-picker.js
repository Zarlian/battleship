"use strict";

const _skin = loadFromStorage("skin");

const _gameTypes = {
    classicId : "classic",
    salvoId : "salvo",
    moveId : "move",
    salvoMoveId : "salvo-move"
};

const _GAMEMODES = [
    {
        id: _gameTypes.classicId,
        data: "simple",
        title: "Classic",
        image: `images/${_skin}/classic-game-mode.png`,
        alt: "Icon classic mode",
        description: "This is the classic Battleship experience, take turns shooting at each other's bugs, the last one standing wins."
    }, {
        id: _gameTypes.salvoId,
        data: _gameTypes.salvoId,
        title: "Salvo",
        image: `images/${_skin}/salvo-game-mode.png`,
        alt: "Icon salvo mode",
        description: "Spice things up with salvo-mode,now every bug can shoot once on your turn."
    }, {
        id:  _gameTypes.moveId,
        data:  _gameTypes.moveId,
        title: "Move",
        image: `images/${_skin}/move-game-mode.png`,
        alt: "Icon move mode",
        description: "In move mode you can trick your opponent by moving your bugs, but watch out! You have to choose between moving or shooting."
    }, {
        id: _gameTypes.salvoMoveId,
        data: _gameTypes.salvoMoveId,
        title: "Salvo + Move",
        image: `images/${_skin}/salvo-move-game-mode.png`,
        alt: "Icon salvo + move mode",
        description: "The ultimate Battleship experience, the rules from salvo and move are combined."
    }
];


function convertType(type) {
    if (type === _gameTypes.salvoMoveId) {
        return "move+salvo";
    } else {
        return type;
    }
}

function displayStartPanel(id) {
    const startButton = `images/${_skin}/start-button.png`;
    const $container = getSelector(`#${id} div`);
    $container.innerHTML = "";
    const $template = getSelector("#difficulty").content.firstElementChild.cloneNode(true);
    $template.querySelector("input[type='image']").setAttribute("src", startButton);
    $container.insertAdjacentHTML("beforeend", $template.outerHTML);
}

function displayDescription(id) {
    const $article = getSelector(`#${id} div`);
    $article.innerHTML = "";
    for (const gameMode of _GAMEMODES) {
        if (id === gameMode.id) {
            $article.innerHTML = `<p>${gameMode.description}</p>`;
        }
    }
}

function resetSelected() {
    const $selectedArticle = getSelector(".selected");
    if ($selectedArticle) {
        const id = $selectedArticle.getAttribute("id");
        $selectedArticle.classList.remove("selected");
        displayDescription(id);
    }
}
