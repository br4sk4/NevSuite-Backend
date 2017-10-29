'use strict';

export default class Tab {

    identifier;
    label;
    tabtype;
    selected;
    visible;

    constructor(identifier, label, tabtype, selected, visible) {
        this.identifier = identifier;
        this.label = label;
        this.tabtype = tabtype;
        this.selected = selected;
        this.visible = visible;
    }

};