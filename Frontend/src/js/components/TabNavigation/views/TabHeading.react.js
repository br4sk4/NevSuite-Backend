'use strict';

import React from 'react';

let selectTab;
let removeTab;

export default class TabHeading extends React.Component {

    render() {
        selectTab = () => this.props.state.onSelectTab(this.props.index);
        removeTab = (this.props.state.tabs.get('tabs').get(this.props.index).label != 'Home')
                ? () => this.props.state.onRemoveTab(this.props.index, this.props.state.tabs.get('tabs').get(this.props.index).identifier)
                : () => this.props.state.onSelectTab(this.props.index);

        const tabHeadingStyle = (this.props.state.tabs.get('tabs').get(this.props.index).selected) ? "tabHeading active" : "tabHeading";
        const removeButtonStyle = (this.props.state.tabs.get('tabs').get(this.props.index).label != 'Home') ? "tabRemoveButton glyphicon glyphicon-remove" : "tabRemoveButton";
        const tabHeadingLabelStyle = 'tabHeadingLabel';

        return (
            <div className={tabHeadingStyle}>
                <div className={tabHeadingLabelStyle} onClick={selectTab}><label>{this.props.state.tabs.get('tabs').get(this.props.index).label}</label></div>
                <div className={removeButtonStyle} onClick={removeTab}></div>
            </div>
        );
    }

};