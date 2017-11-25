/**
 * Created by Braska on 19.11.2017.
 */
import React from "react";

export default class DateInputField extends React.Component {

    constructor(props) {
        super(props);

        let date = new Date();
        const year = date.getFullYear();
        const month = date.getMonth();

        this.state = {
            focus: false,
            showCalendar: false,
            viewMode: "month",
            selectedYear: year,
            selectedMonth: month,
            actDate: ""
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

        this.days = ['Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa', 'So'];

        this.formatDate = function(year, month, day) {
            let monthString = (month < 10 ) ? "0" + month : month;
            let dayString = (day < 10 ) ? "0" + day : day;
            return  dayString + "." + monthString + "." + year;
        };

        this.getDayOfWeekIndex = function(day) {
            return (day === 0) ? 6 : day - 1;
        };

        this.toggleFocus = () => this.setState({
            focus: !this.state.focus,
            showCalendar: this.state.showCalendar,
            viewMode: this.state.viewMode,
            selectedYear: this.state.selectedYear,
            selectedMonth: this.state.selectedMonth,
            actDate: this.state.actDate
        });

        this.toggleCalendar = () => this.setState({
            focus: this.state.focus,
            showCalendar: !this.state.showCalendar,
            viewMode: this.state.viewMode,
            selectedYear: this.state.selectedYear,
            selectedMonth: this.state.selectedMonth,
            actDate: this.state.actDate
        });

        this.decrement = () => {
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
                selectedMonth: month,
                actDate: this.state.actDate
            });
        };

        this.increment = () => {
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
                selectedMonth: month,
                actDate: this.state.actDate
            });
        };

        this.setActualDate = function(dateString) {
            if ( this.props.dateChanged !== undefined ) {
                this.props.dateChanged(dateString);
            }
            this.setState({
                focus: this.state.focus,
                showCalendar: !this.state.showCalendar,
                viewMode: this.state.viewMode,
                selectedYear: this.state.selectedYear,
                selectedMonth: this.state.selectedMonth,
                actDate: dateString
            });
        }

        this.switchToMonthView = (month) => {
            this.setState({
                focus: this.state.focus,
                showCalendar: this.state.showCalendar,
                viewMode: "month",
                selectedYear: this.state.selectedYear,
                selectedMonth: month,
                actDate: this.state.actDate
            });
        };

        this.switchToYearView = (year) => {
            this.setState({
                focus: this.state.focus,
                showCalendar: this.state.showCalendar,
                viewMode: "year",
                selectedYear: year,
                selectedMonth: this.state.selectedMonth,
                actDate: this.state.actDate
            });
        };

        this.onChange = this.onChange.bind(this);
    }

    onChange(e) {
        const dateString = e.target.value;
        this.setState({
            focus: this.state.focus,
            showCalendar: this.state.showCalendar,
            viewMode: this.state.viewMode,
            selectedYear: this.state.selectedYear,
            selectedMonth: this.state.selectedMonth,
            actDate: dateString
        });
    }

    renderMonthView() {
        let x;
        let $dayTiles = [];

        this.days.forEach(function(value) {
            $dayTiles.push(
                <div key={value} className="calendarDayName glyphicon">
                    <span style={{display: "block", cursor: "default", textAlign: "center"}}>
                        <strong>{value}</strong>
                    </span>
                </div>
            );
        });

        let dayOfWeekIndex = this.getDayOfWeekIndex(new Date(
            this.state.selectedYear,
            this.state.selectedMonth,
            1
        ).getDay());
        for (x = 0; x < dayOfWeekIndex; x++) {
            $dayTiles.push(
                <div key={x} className="calendarEmptyTile glyphicon">
                    <span style={{display: "block", cursor: "default"}}/>
                </div>
            );
        }

        let daysOfMonth = new Date(
            this.state.selectedYear,
            this.state.selectedMonth + 1,
            0
        ).getDate();
        for (x = 1; x <= daysOfMonth; x++) {
            const dateString = this.formatDate(
                this.state.selectedYear,
                this.state.selectedMonth + 1,
                x
            );

            $dayTiles.push(
                <div key={x + 10} className="calendarNumberButton glyphicon">
                    <span style={{display: "block", cursor: "default", textAlign: "center"}} onClick={() => {this.setActualDate(dateString)}}>{x}</span>
                </div>
            );
        }

        return $dayTiles;
    }

    renderYearView() {
        let x;
        let $monthTiles = [];

        for (x = 0; x <= 11; x++) {
            const month = x;
            $monthTiles.push(
                <div key={x} className="calendarTextButton glyphicon" onClick={() => {this.switchToMonthView(month)}}>
                    <span style={{display: "block", cursor: "default", textAlign: "center"}}>{this.months[x].shortName}</span>
                </div>
            );
        }

        return $monthTiles;
    }

    render() {
        let $calendarContent;

        let $view = (this.state.viewMode === "month") ? this.renderMonthView() : this.renderYearView();
        let displayText = (this.state.viewMode === "month")
            ? this.months[this.state.selectedMonth].fullName + ", " + this.state.selectedYear
            : this.state.selectedYear;

        const widgetClass = (this.state.focus === false) ? "widget" : "widgetFocused";
        if ( this.state.showCalendar ) {
            const year = this.state.selectedYear;
            $calendarContent = (
                <div className="calendarContent">
                    <div className="calendarPagerButton glyphicon glyphicon-chevron-left" onClick={this.decrement}/>
                    <div className="calendarMainButton glyphicon" onClick={() => {this.switchToYearView(year)}}>
                        <span style={{display: "block", cursor: "default", textAlign: "center"}}>{displayText}</span>
                    </div>
                    <div className="calendarPagerButton glyphicon glyphicon-chevron-right" onClick={this.increment}/>
                    {$view}
                </div>
            );
        }

        return (
            <div style={{width: this.props.width||"100%", float: "left"}}>
                <div className={widgetClass} tabIndex="0" onBlur={() => {if (this.state.showCalendar) this.toggleCalendar()}}>
                    <input className="dateInputField" type="text" value={(this.state.actDate !== "") ? this.state.actDate : this.props.value} onFocus={this.toggleFocus} onBlur={this.toggleFocus} onChange={this.onChange}/>
                    <div className="dateInputButton" onClick={this.toggleCalendar}>
                        <span className="glyphicon glyphicon-calendar comboboxIcon"/>
                    </div>
                    {$calendarContent}
                </div>
            </div>
        );
    }

}