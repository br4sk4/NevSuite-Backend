/**
 * Created by Braska on 11.11.2017.
 */
import React from 'react';

import InputField from '../../../widgets/base/InputField.react.js';
import ComboBox from '../../../widgets/base/ComboBox.react.js';
import PersonContacts from './PersonContacts.react.js';

export default class BusinessInformation extends React.Component {

    render() {
        let inputLabelContainerStyle = {
            textAlign: "right",
            paddingTop: "6px",
            fontSize: "12px"
        };

        return (
            <div className="panel panel-info" style={{width: "1000px", margin: "0 auto", marginTop: "15px"}}>
                <div className="panel-heading"><strong>Betriebliche Kontaktinformationen</strong></div>
                <div className="panel-body">
                    <form className="form-horizontal">
                        <div className="form-group">
                            <div className="col-sm-2" style={inputLabelContainerStyle}><label>Kürzel:</label></div>
                            <div className="col-sm-9"><InputField width="100%"/></div>
                        </div>
                        <div className="form-group">
                            <div className="col-sm-2" style={inputLabelContainerStyle}><label>Eintrittsdatum:</label></div>
                            <div className="col-sm-9"><InputField width="100%"/></div>
                        </div>
                        <div className="form-group">
                            <div className="col-sm-2" style={inputLabelContainerStyle}><label>Firma:</label></div>
                            <div className="col-sm-9"><ComboBox width="100%" data={['Beta AG', 'ABC GmbH', 'XYZ GmbH']}/></div>
                        </div>
                    </form>
                    <PersonContacts {...this.props}/>
                </div>
            </div>
        );
    }

};
