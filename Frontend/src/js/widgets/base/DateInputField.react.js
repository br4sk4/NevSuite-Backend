/**
 * Created by Braska on 19.11.2017.
 */
import React from "react";

export default class DateInputField extends React.Component {

    months;

    constructor(props) {
        super(props);

        this.state = {
            focus: false,
            showCalendar: false,
            viewMode: "month",
            selectedYear: 2017,
            selectedMonth: 0
        };

        this.months = [
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

        this.dayNames = ['Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa', 'So'];
    }

    renderMonthView() {
        let x;
        let $dayTiles = [];

        this.dayNames.forEach(function(value) {
            $dayTiles.push(
                <div key={value} className="calendarDayName glyphicon">
                    <span style={{display: "block", cursor: "default", textAlign: "center"}}>
                        <strong>{value}</strong>
                    </span>
                </div>
            );
        });

        for (x = 1; x <= 42; x++) {
            $dayTiles.push(
                <div key={x} className="calendarNumberButton glyphicon">
                    <span style={{display: "block", cursor: "default", textAlign: "center"}}>{x}</span>
                </div>
            );
        }

        return $dayTiles;
    }

    renderYearView() {
        let x;
        let $monthTiles = [];

        let switchToMonthView = (month) => {
            this.setState({
                focus: this.state.focus,
                showCalendar: this.state.showCalendar,
                viewMode: "month",
                selectedYear: this.state.selectedYear,
                selectedMonth: month
            });
        };

        for (x = 0; x <= 11; x++) {
            const month = x;
            $monthTiles.push(
                <div key={x} className="calendarTextButton glyphicon" onClick={() => {switchToMonthView(month)}}>
                    <span style={{display: "block", cursor: "default", textAlign: "center"}}>{this.months[x].shortName}</span>
                </div>
            );
        }

        return $monthTiles;
    }

    render() {
        let $dropdownContent;

        let toggleFocus = () => this.setState({
            focus: !this.state.focus,
            showCalendar: this.state.showCalendar,
            viewMode: this.state.viewMode,
            selectedYear: this.state.selectedYear,
            selectedMonth: this.state.selectedMonth
        });

        let toggleCalendar = () => this.setState({
            focus: this.state.focus,
            showCalendar: !this.state.showCalendar,
            viewMode: this.state.viewMode,
            selectedYear: this.state.selectedYear,
            selectedMonth: this.state.selectedMonth
        });

        let decrement = () => {
            const year = (this.state.viewMode === "year")
                ? this.state.selectedYear - 1
                : (this.state.selectedMonth === 0)
                    ? this.state.selectedYear - 1
                    : this.state.selectedYear;

            const month = (this.state.viewMode === "month")
                ? ((this.state.selectedMonth === 0)
                    ? 11
                    : this.state.selectedMonth - 1)
                : this.state.selectedMonth;

            this.setState({
                focus: this.state.focus,
                showCalendar: this.state.showCalendar,
                viewMode: this.state.viewMode,
                selectedYear: year,
                selectedMonth: month
            });
        };

        let increment = () => {
            const year = (this.state.viewMode === "year")
                ? this.state.selectedYear + 1
                : (this.state.selectedMonth === 11)
                    ? this.state.selectedYear + 1
                    : this.state.selectedYear;

            const month = (this.state.viewMode === "month")
                ? ((this.state.selectedMonth === 11)
                    ? 0
                    : this.state.selectedMonth + 1)
                : this.state.selectedMonth;

            this.setState({
                focus: this.state.focus,
                showCalendar: this.state.showCalendar,
                viewMode: this.state.viewMode,
                selectedYear: year,
                selectedMonth: month
            });
        };

        let switchToYearView = (year) => {
            this.setState({
                focus: this.state.focus,
                showCalendar: this.state.showCalendar,
                viewMode: "year",
                selectedYear: year,
                selectedMonth: this.state.selectedMonth
            });
        };

        let $view = (this.state.viewMode === "month") ? this.renderMonthView() : this.renderYearView();
        let displayText = (this.state.viewMode === "month")
            ? this.months[this.state.selectedMonth].fullName + ", " + this.state.selectedYear
            : this.state.selectedYear;

        const widgetClass = (this.state.focus === false) ? "widget" : "widgetFocused";
        if ( this.state.showCalendar ) {
            const year = this.state.selectedYear;
            $dropdownContent = (
                <div className="calendarContent">
                    <div className="calendarPagerButton glyphicon glyphicon-chevron-left" onClick={decrement}/>
                    <div className="calendarMainButton glyphicon" onClick={() => {switchToYearView(year)}}>
                        <span style={{display: "block", cursor: "default", textAlign: "center"}}>{displayText}</span>
                    </div>
                    <div className="calendarPagerButton glyphicon glyphicon-chevron-right" onClick={increment}/>
                    {$view}
                </div>
            );
        }

        return (
            <div style={{width: this.props.width||"100%", float: "left"}}>
                <div className={widgetClass} tabIndex="0" onBlur={() => {if (this.state.showCalendar) toggleCalendar()}}>
                    <input className="dateInputField" type="text" onFocus={toggleFocus} onBlur={toggleFocus}/>
                    <div className="dateInputButton" onClick={toggleCalendar}>
                        <span className="glyphicon glyphicon-calendar comboboxIcon"/>
                    </div>
                    {$dropdownContent}
                </div>
            </div>
        );
    }

}