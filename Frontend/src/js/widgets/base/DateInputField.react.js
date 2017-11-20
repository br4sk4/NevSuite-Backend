/**
 * Created by Braska on 19.11.2017.
 */
import React from "react";

export default class DateInputField extends React.Component {

    months;
    days;

    constructor(props) {
        super(props);

        this.state = {
            focus: false,
            showCalendar: false,
            viewMode: "month",
            selectedYear: 2017,
            selectedMonth: 0,
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

        let monthNumber = (this.state.selectedMonth) < 10 ? "0" + (this.state.selectedMonth + 1) : (this.state.selectedMonth + 1);
        let date = new Date(this.state.selectedYear + "-" + monthNumber + "-01");
        let daysOfMonth = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
        let dayOfWeekIndex = (date.getDay() === 0 ) ? 6 : date.getDay() - 1;

        let setDate = (date) => {
            let day = (date.getDate() < 10) ? ("0" + date.getDate()) : date.getDate();
            let month = ((date.getMonth() + 1) < 10) ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            const dateString = day
                    + "." + month
                    + "." + date.getFullYear();

            this.setState({
                focus: this.state.focus,
                showCalendar: !this.state.showCalendar,
                viewMode: this.state.viewMode,
                selectedYear: this.state.selectedYear,
                selectedMonth: this.state.selectedMonth,
                actDate: dateString
            });
        };

        this.days.forEach(function(value) {
            $dayTiles.push(
                <div key={value} className="calendarDayName glyphicon">
                    <span style={{display: "block", cursor: "default", textAlign: "center"}}>
                        <strong>{value}</strong>
                    </span>
                </div>
            );
        });

        for (x = 0; x < dayOfWeekIndex; x++) {
            $dayTiles.push(
                <div key={x} className="calendarEmptyTile glyphicon">
                    <span style={{display: "block", cursor: "default"}}/>
                </div>
            );
        }

        for (x = 1; x <= daysOfMonth; x++) {
            const newDate = new Date(
                this.state.selectedYear,
                this.state.selectedMonth,
                x
            );

            $dayTiles.push(
                <div key={x + 10} className="calendarNumberButton glyphicon">
                    <span style={{display: "block", cursor: "default", textAlign: "center"}} onClick={() => {setDate(newDate)}}>{x}</span>
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
                selectedMonth: month,
                actDate: this.state.actDate
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
            selectedMonth: this.state.selectedMonth,
            actDate: this.state.actDate
        });

        let toggleCalendar = () => this.setState({
            focus: this.state.focus,
            showCalendar: !this.state.showCalendar,
            viewMode: this.state.viewMode,
            selectedYear: this.state.selectedYear,
            selectedMonth: this.state.selectedMonth,
            actDate: this.state.actDate
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
                selectedMonth: month,
                actDate: this.state.actDate
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
                selectedMonth: month,
                actDate: this.state.actDate
            });
        };

        let switchToYearView = (year) => {
            this.setState({
                focus: this.state.focus,
                showCalendar: this.state.showCalendar,
                viewMode: "year",
                selectedYear: year,
                selectedMonth: this.state.selectedMonth,
                actDate: this.state.actDate
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
                    <input className="dateInputField" type="text" value={this.state.actDate} onFocus={toggleFocus} onBlur={toggleFocus} onChange={this.onChange}/>
                    <div className="dateInputButton" onClick={toggleCalendar}>
                        <span className="glyphicon glyphicon-calendar comboboxIcon"/>
                    </div>
                    {$dropdownContent}
                </div>
            </div>
        );
    }

}