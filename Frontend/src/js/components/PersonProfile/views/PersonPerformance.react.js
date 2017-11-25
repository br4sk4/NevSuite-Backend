/**
 * Created by Braska on 25.11.2017.
 */
import React from 'react';

import PerformanceChart from '../../../widgets/view/SimpleLineChart.react.js';

export default class PersonalInformation extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="panel panel-nevsuite" style={{marginTop: "15px"}}>
                <div className="panel-heading">
                    <strong>Performance</strong>
                </div>
                <div className="panel-body">
                    <form className="form-horizontal">
                        <div className="col-sm-10 col-sm-offset-1">
                            <PerformanceChart/>
                        </div>
                    </form>
                </div>
            </div>
        );
    }

};

