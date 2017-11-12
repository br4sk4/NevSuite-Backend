/**
 * Created by Braska on 11.11.2017.
 */
import React from 'react';

import PersonContacts from './PersonContacts.react.js';

export default class PersonalInformation extends React.Component {

    render() {
        let inputLabelContainerStyle = {
            textAlign: "right",
            paddingTop: "5px",
            paddingRight: "5px",
            width: "150px",
            float: "left"
        };

        return (
            <div className="panel panel-success" style={{width: "800px"}}>
                <div className="panel-heading"><strong>Persönliche Kontaktinformationen</strong></div>
                <div className="panel-body">
                    <div className="row" style={{paddingTop: "10px"}}>
                        <div style={inputLabelContainerStyle}><label>Vorname:</label></div>
                        <div className="input-group input-group-sm" style={{width: "500px", float: "center"}}>
                            <input type="text" className="form-control" placeholder="Vorname" />
                        </div>
                    </div>
                    <div className="row" style={{paddingTop: "10px"}}>
                        <div style={inputLabelContainerStyle}><label>Nachname:</label></div>
                        <div className="input-group input-group-sm" style={{width: "500px", float: "center"}}>
                            <input type="text" className="form-control" placeholder="Nachname" />
                        </div>
                    </div>
                    <PersonContacts {...this.props}/>
                </div>
            </div>
        );
    }

};
