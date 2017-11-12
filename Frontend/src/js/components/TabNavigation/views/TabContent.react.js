'use strict';

import React from 'react';

import Home from '../../Home/views/Home.react.js';
import PersonProfile from '../../PersonProfile/views/PersonProfile.react';

export default class TabContent extends React.Component {

    render() {
        let tabContent;

        for (let i = 0; i < this.props.state.tabs.get('tabs').size; ++i) {
            if ( this.props.state.tabs.get('tabs').get(i).selected ) {
                let tabtype;

                if (this.props.state.tabs.get('tabs').get(i).tabtype == 'HOME') tabtype = <Home {...this.props}/>
                else tabtype = <PersonProfile {...this.props}/>;

                tabContent = <div className='tabContent'>{tabtype}</div>;
            }
        }

        return tabContent;
    }

};