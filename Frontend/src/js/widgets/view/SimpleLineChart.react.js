/**
 * Created by Braska on 25.11.2017.
 */
import React from "react";
import Chart from "../../../node_modules/chart.js/src/chart.js";

export default class SimpleLineChart extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            xData: [],
            actDate: this.props.date
        };

        let setData = (xData, yData) => {
            this.setState({
                xData : xData,
                yData : yData,
                actDate: this.props.date
            })
        };

        this.formatTimestamp = function(date) {
            let yearString = date.getFullYear();
            let month = date.getMonth() + 1;
            let monthString = (month < 10 ) ? "0" + month : month;
            let day = date.getDate();
            let dayString = (day < 10 ) ? "0" + day : day;
            return  yearString + "-" + monthString + "-" + dayString + "T00:00:00Z";
        };

        this.processResponse = function() {
            let xData = [];
            let yData = [];

            if (this.readyState == 4 && this.status == 200) {
                const data = JSON.parse(this.responseText);
                if ( data.timeseries.valueMap.length > 0 ) {
                    xData.push('00:00');
                    yData.push(data.timeseries.valueMap[0].value);
                    data.timeseries.valueMap.forEach(function (tuple) {
                        let time = tuple.timestamp.split('T')[1].split('Z')[0];
                        xData.push(time.split(':')[0] + ':' + time.split(':')[1]);
                        yData.push(tuple.value);
                    });
                    setData(xData, yData);
                } else {
                    setData(xData, yData);
                }
            } else if (this.readyState == 4) {
                setData(xData, yData);
            }
        };
    }

    readTimeseries() {
        let xhttp = new XMLHttpRequest();

        let dateString = (this.props.date !== "" && this.props.date !== undefined) ? this.props.date : "01.01.2017";
        let actDate = new Date(dateString.split('.')[2], parseInt(dateString.split('.')[1]) - 1, dateString.split('.')[0]);
        let nextDate = new Date(dateString.split('.')[2], parseInt(dateString.split('.')[1]) - 1, parseInt(dateString.split('.')[0]) + 1);

        const timestampFrom = this.formatTimestamp(actDate);
        const timestampTo = this.formatTimestamp(nextDate);

        xhttp.onreadystatechange = this.processResponse;
        xhttp.open("GET", "http://localhost:8080/timeseries/DomainService/timeseries/" + timestampFrom + "/" + timestampTo, true);
        xhttp.send();
    }

    createChart() {
        let $ctx = document.getElementById('simpleLineChart').getContext('2d');
        new Chart($ctx, {
            type: 'line',
            data: {
                labels: this.state.xData,
                datasets: [{
                    label: 'Timeseries',
                    backgroundColor: 'rgba(134, 191, 160, 0.3)',
                    borderColor: 'rgb(66, 147, 102)',
                    data: this.state.yData,
                    steppedLine: false,
                    lineTension: 0,
                    pointRadius: 0
                }]
            },
            options: {
                animation: {
                    duration: 0,
                },
                legend: {
                    display: false
                },
                scales: {
                    yAxes: [{
                        ticks: {
                            max: 200,
                            min: 0,
                            stepSize: 10
                        }
                    }],
                    xAxes: [{
                        ticks: {
                            autoSkip: true,
                            maxTicksLimit: 24
                        }
                    }]
                }
            }
        });
    }

    componentDidUpdate() {
        this.createChart();
        if ( this.state.actDate !== this.props.date ) {
            this.setState({
                xData: this.state.xData,
                actDate: this.props.date
            });
            this.readTimeseries();
        }
    }

    componentDidMount() {
        this.createChart();
        this.readTimeseries();
    }

    render() {
        return (
            <div style={{width: this.props.width||"100%", float: "left"}}>
                <canvas id="simpleLineChart"/>
            </div>
        );
    }
}
