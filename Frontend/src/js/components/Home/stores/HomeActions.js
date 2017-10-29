'use strict';

import HomeActionTypes from './HomeActionTypes';
import NevSuiteAppDispatcher from '../../../nevsuite.app.dispatcher.js';

const HomeActions = {

    addComponentInfo(name, classes, response) {
        NevSuiteAppDispatcher.dispatch({
            type: HomeActionTypes.ADD_COMPONENT_INFO,
            name,
            classes,
            response
        });
    },

};

export default HomeActions;