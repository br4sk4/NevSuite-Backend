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
    }

    readTimeseries() {
        let xhttp = new XMLHttpRequest();

        let data = '';
        let xData = [];
        let yData = [];

        let date = (this.props.date !== "" && this.props.date !== undefined) ? this.props.date : "01.01.2017";
        let dayTo = parseInt(date.split('.')[0]) + 1;
        let timestampFrom = date.split('.')[2] + "-" + date.split('.')[1] + "-" + date.split('.')[0] + "T00:00:00Z";
        let timestampTo = date.split('.')[2] + "-" + date.split('.')[1] + "-" + (dayTo < 10 ? "0" + dayTo : dayTo) + "T00:00:00Z";

        let setState = (xData, yData) => {
            debugger;
            this.setState({
                xData : xData,
                yData : yData
            })
        };

        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                data = JSON.parse(this.responseText);
                xData.push('00:00');
                yData.push(data.timeseries.valueMap[0].value);
                data.timeseries.valueMap.forEach(function(tuple) {
                    let time = tuple.timestamp.split('T')[1].split('Z')[0];
                    xData.push(time.split(':')[0] + ':' + time.split(':')[1]);
                    yData.push(tuple.value);
                });
                setState(xData, yData);
            } else if (this.readyState == 4) {
                data = [];
            }
        };
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
                    label: "Timeseries",
                    backgroundColor: 'rgba(134, 191, 160, 0.3)',
                    borderColor: 'rgb(66, 147, 102)',
                    data: this.state.yData,
                    steppedLine: true,
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
                            max: 100,
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
