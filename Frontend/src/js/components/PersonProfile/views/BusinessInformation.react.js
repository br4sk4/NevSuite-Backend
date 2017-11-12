/**
 * Created by Braska on 11.11.2017.
 */
import React from 'react';

import ComboBox from '../../../widgets/base/ComboBox.react.js';
import PersonContacts from './PersonContacts.react.js';

export default class BusinessInformation extends React.Component {

    render() {
        let inputLabelContainerStyle = {
            textAlign: "right",
            paddingTop: "5px",
            paddingRight: "5px",
            width: "175px",
            float: "left"
        };

        return (
            <div className="panel panel-info" style={{width: "800px", margin: "0 auto", marginTop: "15px"}}>
                <div className="panel-heading"><strong>Betriebliche Kontaktinformationen</strong></div>
                <div className="panel-body">
                    <form>
                        <div className="row" style={{paddingTop: "10px"}}>
                            <div style={inputLabelContainerStyle}><label>Kürzel:</label></div>
                            <div className="input-group input-group-sm" style={{width: "500px"}}>
                                <input type="text" className="form-control" placeholder="Kürzel"/>
                            </div>
                        </div>
                        <div className="row" style={{paddingTop: "10px"}}>
                            <div style={inputLabelContainerStyle}><label>Eintrittsdatum:</label></div>
                            <div className="input-group input-group-sm" style={{width: "500px"}}>
                                <input type="text" className="form-control" placeholder="Eintrittsdatum"/>
                            </div>
                        </div>
                        <div className="row" style={{paddingTop: "10px"}}>
                            <div style={inputLabelContainerStyle}><label>Firma:</label></div>
                            <ComboBox data={['Beta AG', 'ABC GmbH', 'XYZ GmbH']}/>
                        </div>
                        <PersonContacts {...this.props}/>
                    </form>
                </div>
            </div>
        );
    }

};
