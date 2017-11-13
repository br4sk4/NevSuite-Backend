/**
 * Created by Braska on 11.11.2017.
 */
import React from 'react';

export default class ComboBox extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showDropdownContent: false,
            selectedValue: '',
            selectedIndex: -1
        };
    }

    render() {
        let $dropdownList;
        let $dropdownItems;

        let toggleDropdownContent = () => this.setState({
            showDropdownContent: !this.state.showDropdownContent,
            selectedIndex: this.state.selectedIndex,
            selectedValue: this.state.selectedValue
        });

        if ( this.state.showDropdownContent ) {
            if (this.props.data.length > 0) $dropdownItems = [];

            let x;
            for (x = 0; x < this.props.data.length; ++x)  {
                const index = x;
                const value = this.props.data[x];
                let handleChange = () => this.setState({
                    showDropdownContent: !this.state.showDropdownContent,
                    selectedIndex: index,
                    selectedValue: value
                });

                $dropdownItems.push(
                    <div key={x} className="dropdownItemContainer"><div className="dropdownItem" onClick={handleChange}>{this.props.data[x]}</div></div>
                );
            }

            let $searchBox;
            if ( this.props.showSearchBox ) {
                $searchBox = (
                    <div className="input-group input-group-sm" style={{width: "500px", padding: "3px"}}>
                        <span className="input-group-addon"><span className="glyphicon glyphicon-search"/></span>
                        <input id="halo" className="form-control" type="text"/>
                    </div>
                );
            }

            $dropdownList = (
                <div className="dropdownContent" style={{width: "500px"}}>
                    {$searchBox}
                    {$dropdownItems}
                </div>
            );
        }

        return (
            <div style={{width: "500px", float: "left"}} tabIndex="0" onBlur={() => {if ( this.state.showDropdownContent ) toggleDropdownContent();}}>
                <div className="input-group input-group-sm" style={{width: "500px"}}>
                    <span className="form-control" style={{cursor: "default"}} onClick={toggleDropdownContent}>
                        {this.state.selectedValue}
                        <input type="hidden" name="selectedValue" value={this.state.selectedValue}/>
                    </span>
                    <span className="input-group-addon btn btn-default" style={{cursor: "default"}} onClick={toggleDropdownContent}><span className="glyphicon glyphicon-chevron-down" /></span>
                </div>
                {$dropdownList}
            </div>
        );
    }
}
