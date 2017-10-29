'use strict';

import {ReduceStore} from 'flux/utils';
import Immutable from 'immutable';

import NevSuiteAppDispatcher from '../../../nevsuite.app.dispatcher.js';
import ComponentInfo from './ComponentInfo.js';
import HomeActionTypes from './HomeActionTypes.js';
import HomeActions from './HomeActions.js';
import Home from '../views/Home.react.js';

class HomeStore extends ReduceStore {

    constructor() {
        super(NevSuiteAppDispatcher);
    }

    createComponent(name, url) {
        let response = '';
        let classes = 'info home';
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                classes = 'success home';
                response = this.responseText;
                HomeActions.addComponentInfo(name, classes, response);
            } else if (this.readyState == 4) {
                classes = 'danger home';
                response = this.status + ' (' + this.statusText + ')';
                HomeActions.addComponentInfo(name, classes, response);
            }
        };
        xhttp.open("GET", url, true);
        xhttp.send();
    }

    getInitialState() {
        let componentInfos = [];

        const host = ( window.location.hostname != "" ) ? window.location.hostname + ':' + window.location.port : 'localhost:8080';
        this.createComponent("Common Backend", 'http://' + host + '/backend-webservice/ComponentService/respond');
        this.createComponent("Timeseries", 'http://' + host + '/timeseries-webservice/ComponentService/respond');

        return Immutable.fromJS({
            componentInfos: componentInfos,
        });
    }

    reduce(state, action) {
        const componentInfos = [];

        switch (action.type) {

            case HomeActionTypes.ADD_COMPONENT_INFO:

                if (!action.name) return state;

                let components = state.get('componentInfos');
                for (let i = 0; i < components.size; ++i) {
                    componentInfos.push(new ComponentInfo(components.get(i).name, components.get(i).classes, components.get(i).response));
                }
                componentInfos.push(new ComponentInfo(action.name, action.classes, action.response));

                return Immutable.fromJS({
                    componentInfos: componentInfos,
                });

            default:
                return state;
        }

        return state;
    }
}

export default new HomeStore();