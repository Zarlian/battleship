"use strict";

function addListener(selector, type, func){
    getSelector(`${selector}`).addEventListener(`${type}`,func);
}

function getTemplate(selector){
    return getSelector(`${selector}`).content.firstElementChild.cloneNode(true);
}

function getSelector(selector){
    return document.querySelector(`${selector}`);
}

function addClass($element, className) {
    $element.classList.add(className);
}
