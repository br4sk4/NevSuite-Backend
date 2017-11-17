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
            paddingTop: "6px",
            fontSize: "12px"
        };

        return (
            <div className="panel panel-success" style={{width: "1000px", margin: "0 auto"}}>
                <div className="panel-heading"><strong>Persönliche Kontaktinformationen</strong></div>
                <div className="panel-body">
                    <form className="form-horizontal">
                        <div className="form-group">
                            <div className="col-sm-2" style={inputLabelContainerStyle}><label>Anrede:</label></div>
                            <div className="col-sm-9"><ComboBox width="100%" data={['Herr', 'Frau']}/></div>
                        </div>
                        <div className="form-group">
                            <div className="col-sm-2" style={inputLabelContainerStyle}><label>Vorname:</label></div>
                            <div className="col-sm-9"><InputField width="100%"/></div>
                        </div>
                        <div className="form-group">
                            <div className="col-sm-2" style={inputLabelContainerStyle}><label>Nachname:</label></div>
                            <div className="col-sm-9"><InputField width="100%"/></div>
                        </div>
                    </form>
                    <PersonContacts {...this.props}/>
                </div>
            </div>
        );
    }

};
