/**
 * Created by Braska on 19.11.2017.
 */
import React from "react";

export default class DateInputField extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            focus: false,
            showCalendar: false,
            selectedIndex: 0
        }
    }

    render() {
        let $dropdownContent;

        let toggleFocus = () => this.setState({
            focus: !this.state.focus,
            showCalendar: this.state.showCalendar,
            selectedIndex: this.state.selectedIndex
        });

        let toggleCalendar = () => this.setState({
            focus: this.state.focus,
            showCalendar: !this.state.showCalendar,
            selectedIndex: this.state.selectedIndex
        });

        let decrement = () => {
            const newIndex = (this.state.selectedIndex === 0) ? 11 : this.state.selectedIndex - 1;
            this.setState({
                focus: this.state.focus,
                showCalendar: this.state.showCalendar,
                selectedIndex: newIndex
            });
        };

        let increment = () => {
            const newIndex = (this.state.selectedIndex === 11) ? 0 : this.state.selectedIndex + 1;
            this.setState({
                focus: this.state.focus,
                showCalendar: this.state.showCalendar,
                selectedIndex: newIndex
            });
        };

        let x;
        let $days = [];
        let dayNames = ['Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa', 'So'];
        let months = [
            {shortName: 'Jan', fullName: 'Januar'},
            {shortName: 'Feb', fullName: 'Februar'},
            {shortName: 'Mär', fullName: 'März'},
            {shortName: 'Apr', fullName: 'April'},
            {shortName: 'Mai', fullName: 'Mai'},
            {shortName: 'Jun', fullName: 'Juni'},
            {shortName: 'Jul', fullName: 'Juli'},
            {shortName: 'Aug', fullName: 'August'},
            {shortName: 'Sep', fullName: 'September'},
            {shortName: 'Okt', fullName: 'Oktober'},
            {shortName: 'Nov', fullName: 'November'},
            {shortName: 'Dez', fullName: 'Dezember'}];

        dayNames.forEach(function(value) {
            $days.push(<div key={value} className="calendarDayName glyphicon"><span style={{display: "block", cursor: "default"}}><center><strong>{value}</strong></center></span></div>);
        });

        for (x = 1; x <= 42; x++) {
            $days.push(<div key={x} className="calendarNumberButton glyphicon"><span style={{display: "block", cursor: "default"}}><center>{x}</center></span></div>);
        }

        if ( this.state.showCalendar ) {
            $dropdownContent = (
                <div className="calendarContent">
                    <div className="calendarPagerButton glyphicon glyphicon-chevron-left" onClick={decrement}/>
                    <div className="calendarMainButton glyphicon"><span style={{display: "block", cursor: "default"}}><center>{months[this.state.selectedIndex].fullName}</center></span></div>
                    <div className="calendarPagerButton glyphicon glyphicon-chevron-right" onClick={increment}/>
                    {$days}
                </div>
            );
        }

        const widgetClass = (this.state.focus === false) ? "widget" : "widgetFocused";

        return (
            <div style={{width: this.props.width||"100%", float: "left"}}>
                <div className={widgetClass} tabIndex="0" onBlur={() => {if (this.state.showCalendar) toggleCalendar()}}>
                    <input className="dateInputField" type="text" onFocus={toggleFocus} onBlur={toggleFocus}/>
                    <div className="dateInputButton" onClick={toggleCalendar}><span className="glyphicon glyphicon-calendar comboboxIcon"/></div>
                    {$dropdownContent}
                </div>
            </div>
        );
    }

}