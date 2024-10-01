"use strict";

function loadMyCommander() {
    const $myCommander = getSelector("#my-commander");
    $myCommander.innerHTML = "";
    const myCommander = loadFromStorage("commander");
    $myCommander.insertAdjacentHTML("beforeend", `${myCommander}`);
}
