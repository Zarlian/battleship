"use strict";

const _config = {
    groupnumber: '05',
    errorHandlerSelector: '.errormessages p',
    getAPIUrl: function() { return `https://project-i.ti.howest.be/battleship-${this.groupnumber}/api`;
        },
    delay: 1500
};

let _token = null;


//return `https://project-i.ti.howest.be/battleship-${this.groupnumber}/api`;