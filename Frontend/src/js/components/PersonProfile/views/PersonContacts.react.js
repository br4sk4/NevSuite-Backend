/**
 * Created by Braska on 11.11.2017.
 */
import React from 'react';

export default class PersonContacts extends React.Component {

    render() {
        let inputLabelContainerStyle = {
            textAlign: "right",
            paddingTop: "6px",
            fontSize: "12px"
        };

        return (
            <div className="form-horizontal">
                <div className="form-group">
                    <div className="col-sm-2" style={inputLabelContainerStyle}><label>Telefon:</label></div>
                    <div className="col-sm-9">
                        <div className="input-group input-group-sm" style={{width: "100%"}}>
                            <span className="input-group-addon"><span className="glyphicon glyphicon-earphone" /></span>
                            <input type="text" className="form-control" placeholder="Telefon" />
                        </div>
                    </div>
                </div>
                <div className="form-group">
                    <div className="col-sm-2" style={inputLabelContainerStyle}><label>Mobil:</label></div>
                    <div className="col-sm-9">
                        <div className="input-group input-group-sm" style={{width: "100%"}}>
                            <span className="input-group-addon"><span className="glyphicon glyphicon-phone" /></span>
                            <input type="text" className="form-control" placeholder="Mobil" />
                        </div>
                    </div>
                </div>
            </div>
        );
    }

};