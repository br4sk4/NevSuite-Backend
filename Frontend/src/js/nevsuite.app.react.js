'use strict';

import React from 'react';

import TabLane from './components/TabNavigation/views/TabLane.react.js';
import TabLaneSpacer from './components/TabNavigation/views/TabLaneSpacer.react.js';
import TabContent from './components/TabNavigation/views/TabContent.react.js';
import Home from './components/Home/views/Home.react.js';

export default class NevSuiteAppView extends React.Component {

    render() {
        return (
            <div className="appContainer">
                <TabLane {...this.props}/>
                <TabLaneSpacer/>
                <TabContent {...this.props}><Home {...this.props}/></TabContent>
            </div>
        );
    }

};