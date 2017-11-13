/**
 * Created by Braska on 11.11.2017.
 */
import React from 'react';

import InputField from '../../../widgets/base/InputField.react.js';
import ComboBox from '../../../widgets/base/ComboBox.react.js';
import PersonContacts from './PersonContacts.react.js';

export default class PersonalInformation extends React.Component {

    render() {
        let inputLabelContainerStyle = {
            textAlign: "right",
            paddingTop: "5px",
            paddingRight: "5px",
            width: "175px",
            float: "left"
        };

        return (
            <div className="panel panel-success" style={{width: "800px", margin: "0 auto"}}>
                <div className="panel-heading"><strong>Persönliche Kontaktinformationen</strong></div>
                <div className="panel-body">
                    <div className="row" style={{paddingTop: "10px"}}>
                        <div style={inputLabelContainerStyle}><label>Anrede:</label></div>
                        <ComboBox data={['Herr', 'Frau']}/>
                    </div>
                    <div className="row" style={{paddingTop: "10px"}}>
                        <div style={inputLabelContainerStyle}><label>Vorname:</label></div>
                        <InputField/>
                    </div>
                    <div className="row" style={{paddingTop: "10px"}}>
                        <div style={inputLabelContainerStyle}><label>Nachname:</label></div>
                        <InputField/>
                    </div>
                    <PersonContacts {...this.props}/>
                </div>
            </div>
        );
    }

};
