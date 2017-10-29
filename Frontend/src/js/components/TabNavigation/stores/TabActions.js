'use strict';

import TabActionTypes from './TabActionTypes';
import NevSuiteAppDispatcher from '../../../nevsuite.app.dispatcher.js';

const TabActions = {

    addTab(identifier, label, tabtype, selected) {
        NevSuiteAppDispatcher.dispatch({
            type: TabActionTypes.ADD_TAB,
            identifier,
            label,
            tabtype,
            selected
        });
    },

    removeTab(index, identifier) {
        NevSuiteAppDispatcher.dispatch({
            type: TabActionTypes.REMOVE_TAB,
            index,
            identifier
        });
    },

    selectTab(index) {
        NevSuiteAppDispatcher.dispatch({
            type: TabActionTypes.SELECT_TAB,
            index
        });
    },

    scrollTabsLeft() {
        NevSuiteAppDispatcher.dispatch({
            type: TabActionTypes.SCROLL_TABS_LEFT
        });
    },

    scrollTabsRight() {
        NevSuiteAppDispatcher.dispatch({
            type: TabActionTypes.SCROLL_TABS_RIGHT
        });
    },

};

export default TabActions;