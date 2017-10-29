'use strict';

import React from 'react';
import TabPager from './TabPager.react.js';

export default class TabLane extends React.Component {

    render() {
        return (
            <div className="tabLane"><TabPager {...this.props}/></div>
        );
    }

};