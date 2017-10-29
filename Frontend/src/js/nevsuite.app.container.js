'use strict';

import React from 'react';
import {Container} from 'flux/utils';

import NevSuiteAppView from './nevsuite.app.react.js';

import TabStore from './components/TabNavigation/stores/TabStore.js';
import TabActions from './components/TabNavigation/stores/TabActions.js';

import HomeStore from './components/Home/stores/HomeStore.js';
import HomeActions from './components/Home/stores/HomeActions.js';

class NevSuiteApp extends React.Component {

    static getStores() {
        return [
            TabStore,
            HomeStore
        ];
    }

    static calculateState() {
        return {
            tabs: TabStore.getState(),
            home: HomeStore.getState(),
            onAddTab: TabActions.addTab,
            onRemoveTab: TabActions.removeTab,
            onSelectTab: TabActions.selectTab,
            onScrollTabsLeft: TabActions.scrollTabsLeft,
            onScrollTabsRight: TabActions.scrollTabsRight,
            onAddComponentInfo: HomeActions.addComponentInfo,
        };
    }

    render() {
        return (
            <NevSuiteAppView state={NevSuiteApp.calculateState()}/>
        );
    }
}

export default Container.create(NevSuiteApp);