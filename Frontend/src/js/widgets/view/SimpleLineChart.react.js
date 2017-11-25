/**
 * Created by Braska on 25.11.2017.
 */
import React from "react";
import Chart from "chart.js";

export default class SimpleLineChart extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        let $ctx = document.getElementById('simpleLineChart').getContext('2d');
        new Chart($ctx, {
            type: 'line',
            data: {
                labels: ["Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"],
                datasets: [
                    {   label: "Soll",
                        backgroundColor: 'transparent',
                        borderColor: 'rgb(7, 65, 47)',
                        data: [8, 8, 8, 8, 8],
                        steppedLine: true
                    },
                    {
                        label: "Ist",
                        backgroundColor: 'rgba(134, 191, 160, 0.3)',
                        borderColor: 'rgb(66, 147, 102)',
                        data: [7, 9.5, 7.5, 10, 8],
                        steppedLine: false
                    }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            max: 12,
                            min: 0,
                            stepSize: 1
                        }
                    }]
                }
            }
        });
    }

    render() {
        return (
            <div style={{width: this.props.width||"100%", float: "left"}}>
                <canvas id="simpleLineChart"/>
            </div>
        );
    }
}
