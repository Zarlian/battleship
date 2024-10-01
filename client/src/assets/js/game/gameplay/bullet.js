"use strict";

function createBullet(coordinate, board, result) {
    const skin = loadFromStorage("skin");
    const $bullet = document.createElement('img');
    $bullet.classList.add("bullet");
    $bullet.src = `images/${skin}/bullet.png`;
    $bullet.style.position = 'absolute';
    $bullet.style.left = '0';
    $bullet.style.top = '0';
    document.body.appendChild($bullet);
    animateBullet($bullet, coordinate, board, result, skin);
}

function animateBullet(bullet, coordinate, board, result, skin) {
    const targetLi = findLiAlphabetic(coordinate, board);
    playShot();
    const path = setUpPath(targetLi);
    const speed = 30;
    bullet.animate(path, {
        duration: path.length * speed,
        easing: 'linear',
        fill: 'forwards'
    });

    playExplosion(bullet, targetLi, skin, coordinate, board, result, path);
}

function playExplosion(bullet, targetLi, skin, coordinate, board, result, path) {
    const speed = 30;
    setTimeout(() => {
        bullet.parentNode.removeChild(bullet);
        targetLi.innerHTML = `<img src="images/${skin}/boom.gif" alt="boom" id="boom">`;
        showDecall(coordinate, board, result);
    }, path.length * speed);
}

function setUpPath(targetLi) {
    const targetRect = targetLi.getBoundingClientRect();
    const range = getRange(targetRect);
    return generatePath(range.start, range.target);
}

function getRange(targetRect) {
    return {
        target : getTargetPosition(targetRect),
        start : getStartPosition(getTargetPosition(targetRect))
    };
}

function getTargetPosition(targetRect) {
    return {
        x : targetRect.left,
        y : targetRect.top
    };
}

function getStartPosition(target) {
    const boardOffset = 400;
    return {
        x : getSelector(_game.yourBoard).getBoundingClientRect().right - boardOffset,
        y : target.y
    };
}

function generateControlPoints(start, target) {
    const amountOffControlPoints = 12;
    const controlPoints = [];
    for (let i = 1; i < amountOffControlPoints; i++) {
        const cpX = (target.x - start.x) / amountOffControlPoints * i + start.x;
        const cpY = calculateControlPointY(start.y, target.y, i, amountOffControlPoints);
        controlPoints.push({x: cpX, y: cpY});
    }
    return controlPoints;
}

function getYOffSet() {
    return {
        min : 30,
        max : 75
    };
}

function calculateControlPointY(startY, targetY, i, amountOffControlPoints) {
    const yOffset = getYOffSet();
    if (i <= 3) {
        return startY - yOffset.min * i;
    } else if (i >= 9) {
        return targetY - yOffset.min * (amountOffControlPoints - i);
    } else {
        return startY - yOffset.max;
    }
}

function generatePath(start, target) {
    const controlPoints = generateControlPoints(start, target);
    const path = [];
    path.push({transform: `translate(${start.x}px, ${start.y}px)`});
    for (const item of controlPoints) {
        path.push({transform: `translate(${item.x}px, ${item.y}px)`});
    }
    path.push({transform: `translate(${target.x}px, ${target.y}px)`});
    return path;
}
