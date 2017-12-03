/**
 * Created by Braska on 25.11.2017.
 */
import React from 'react';

import ComboBox from '../../../widgets/control/ComboBox.react.js';
import DateInputField from '../../../widgets/control/DateInputField.react.js';
import TimeseriesChart from '../../../widgets/view/SimpleLineChart.react.js';

export default class PersonalInformation extends React.Component {

    constructor(props) {
        super(props);

        this.formatDate = function(year, month, day) {
            let monthString = (month < 10 ) ? "0" + month : month;
            let dayString = (day < 10 ) ? "0" + day : day;
            return  dayString + "." + monthString + "." + year;
        };

        const today = new Date();
        this.state = {
            date: this.formatDate(today.getFullYear(), today.getMonth() + 1, today.getDate())
        };

        this.dateChanged = function(newDate) {
            this.setState({
                date: newDate
            });
        }
    }

    render() {
        const date = new Date();
        const inputLabelContainerStyle = {
            textAlign: "right",
            paddingTop: "6px",
            fontSize: "12px"
        };

        return (
            <div className="panel panel-nevsuite" style={{marginTop: "15px"}}>
                <div className="panel-heading">
                    <strong>Performance</strong>
                </div>
                <div className="panel-body">
                    <form className="form-horizontal">
                        <div className="form-group">
                            <div className="col-sm-12">
                                <div className="col-sm-1" style={inputLabelContainerStyle}><label>Datum:</label></div>
                                <div className="col-sm-5"><DateInputField value={this.state.date} dateChanged={(newDate) => this.dateChanged(newDate)} /></div>
                                <div className="col-sm-1" style={inputLabelContainerStyle}><label>Raster:</label></div>
                                <div className="col-sm-5"><ComboBox data={["Tag", "Monat", "Jahr"]} value="Tag" /></div>
                            </div>
                        </div>
                        <div className="col-sm-12" style={{marginTop: "15px"}}>
                            <TimeseriesChart date={this.state.date}/>
                        </div>
                    </form>
                </div>
            </div>
        );
    }

};

