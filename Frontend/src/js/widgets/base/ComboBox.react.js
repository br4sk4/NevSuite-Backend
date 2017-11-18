/**
 * Created by Braska on 11.11.2017.
 */
import React from "react";

import InputField from './InputField.react.js';

export default class ComboBox extends React.Component {

    toggleDropdownContent;
    toggleItemFocus;
    toggleSearchBoxFocus;
    handleChange;

    constructor(props) {
        super(props);

        this.state = {
            showDropdownContent: false,
            itemGotFocus: false,
            searchBoxGotFocus: false,
            selectedValue: ""
        };

        this.toggleDropdownContent = () => this.setState({
            showDropdownContent: !this.state.showDropdownContent,
            itemGotFocus: false,
            searchBoxGotFocus: false,
            selectedValue: this.state.selectedValue
        });

        this.toggleItemFocus = () => this.setState({
            showDropdownContent: this.state.showDropdownContent,
            itemGotFocus: !this.state.itemGotFocus,
            searchBoxGotFocus: this.state.searchBoxGotFocus,
            selectedValue: this.state.selectedValue
        });

        this.toggleSearchBoxFocus = () => this.setState({
            showDropdownContent: this.state.showDropdownContent,
            itemGotFocus: this.state.itemGotFocus,
            searchBoxGotFocus: !this.state.itemGotFocus,
            selectedValue: this.state.selectedValue
        });

        this.handleChange = (value) => this.setState({
            showDropdownContent: !this.state.showDropdownContent,
            itemGotFocus: false,
            searchBoxGotFocus: false,
            selectedValue: value
        });
    }

    render() {
        let $dropdownList;
        let $dropdownItems;
        let $dropdownContent;

        if ( this.state.showDropdownContent ) {
            if (this.props.data.length > 0) {
                $dropdownItems = [];
            }

            let x;
            for (x = 0; x < this.props.data.length; ++x)  {
                const value = this.props.data[x];

                $dropdownItems.push(
                    <div key={x} className="dropdownItemContainer"><div className="dropdownItem" onMouseDown={this.toggleItemFocus} onMouseUp={() => this.handleChange(value)}>{this.props.data[x]}</div></div>
                );
            }

            $dropdownList = (
                <div className="dropdownItemList">{$dropdownItems}</div>
            );

            let $searchBox=[];
            if ( this.props.showSearchBox ) {
                $searchBox.push(
                    <div key={1} className="dropdownSearchbox" ref="searchbox" tabIndex="0">
                        <InputField itemSelection={this.state.itemGotFocus} toggleFocus={this.toggleSearchBoxFocus} toggleDropdown={this.toggleDropdownContent}/>
                    </div>
                );
                $searchBox.push(<div key={2} style={{height: "6px"}} />);
            }

             $dropdownContent = (
                <div className="dropdownContent">
                    {$searchBox}
                    {$dropdownList}
                </div>
            );
        }

        return (
            <div style={{width: this.props.width||"100%", float: "left"}}>
                <div className="widget" tabIndex="0" onBlur={() => {if ( this.state.showDropdownContent && !this.state.searchBoxGotFocus) this.toggleDropdownContent();}}>
                    <div className="comboboxTextfield" onMouseDown={this.toggleDropdownContent}>
                        <span className="comboboxText">{this.state.selectedValue}</span>
                        <input type="hidden" name="selectedValue" value={this.state.selectedValue}/>
                    </div>
                    <div className="comboboxButton" onMouseDown={this.toggleDropdownContent}><span className="glyphicon glyphicon-chevron-down comboboxIcon" /></div>
                    {$dropdownContent}
                </div>
            </div>
        );
    }
}
