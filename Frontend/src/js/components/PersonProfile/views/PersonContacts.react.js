/**
 * Created by Braska on 11.11.2017.
 */
import React from 'react';

export default class PersonContacts extends React.Component {

    render() {
        let inputLabelContainerStyle = {
            textAlign: "right",
            paddingTop: "5px",
            paddingRight: "5px",
            width: "175px",
            float: "left"
        };

        return (
            <div>
                <div className="row" style={{paddingTop: "10px"}}>
                    <div style={inputLabelContainerStyle}><label>Telefon:</label></div>
                    <div className="input-group input-group-sm" style={{width: "500px"}}>
                        <span className="input-group-addon"><span className="glyphicon glyphicon-earphone" /></span>
                        <input type="text" className="form-control" placeholder="Telefon" />
                    </div>
                </div>
                <div className="row" style={{paddingTop: "10px"}}>
                    <div style={inputLabelContainerStyle}><label>Mobil:</label></div>
                    <div className="input-group input-group-sm" style={{width: "500px"}}>
                        <span className="input-group-addon"><span className="glyphicon glyphicon-phone" /></span>
                        <input type="text" className="form-control" placeholder="Mobil" />
                    </div>
                </div>
            </div>
        );
    }

};