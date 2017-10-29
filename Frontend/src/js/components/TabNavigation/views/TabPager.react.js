'use strict';

import React from 'react';
import uuid from 'uuid/v4';
import TabHeading from './TabHeading.react.js';

let scrollLeft;
let srcollRight;
let addTab;

export default class TabPager extends React.Component {

    render() {
        const tabHeadings = [];

        scrollLeft = ( this.props.state.tabs.get('tabPagerLeftVisible') ) ? () => this.props.state.onScrollTabsLeft() : () => function() {};
        srcollRight = ( this.props.state.tabs.get('tabPagerRightVisible') ) ? () => this.props.state.onScrollTabsRight() : () => function() {};
        addTab = () => this.props.state.onAddTab(uuid(), 'Tab', 'EMPTY', true);

        for (let i = 0; i < this.props.state.tabs.get('tabs').size; ++i) {
            if ( this.props.state.tabs.get('tabs').get(i).visible ) tabHeadings.push(<TabHeading key={i} index={i} state={this.props.state}/>);
        }

        const classLeftPager = "tabPagerButtonLeft glyphicon glyphicon-chevron-left" + ((!this.props.state.tabs.get('tabPagerLeftVisible')) ? " disabled" : "");
        const classRightPager = "tabPagerButtonRight glyphicon glyphicon-chevron-right" + ((!this.props.state.tabs.get('tabPagerRightVisible')) ? " disabled" : "");
        const classAddTab = "tabPagerButtonPlus glyphicon glyphicon-plus";

        return (
            <div className="tabPager">
                <div className={classLeftPager} onClick={scrollLeft}></div>
                <div className={classRightPager} onClick={srcollRight}></div>
                <div className={classAddTab} onClick={addTab}></div>
                {tabHeadings}
            </div>
        );
    }

};