'use strict';

export default class ComponentInfo {

    name;
    classes;
    response;

    constructor(name, classes, response) {
        this.name = name;
        this.classes = classes;
        this.response = response;
    }

};