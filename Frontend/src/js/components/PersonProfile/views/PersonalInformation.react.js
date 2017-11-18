/**
 * Created by Braska on 11.11.2017.
 */
import React from 'react';

import InputField from '../../../widgets/base/InputField.react.js';
import ComboBox from '../../../widgets/base/ComboBox.react.js';
import PersonContacts from './PersonContacts.react.js';

export default class PersonalInformation extends React.Component {

    toggleEditMode;

    constructor(props) {
        super(props);
        this.state = {
            editMode: false
        };

        this.toggleEditMode = () => this.setState({
            editMode: !this.state.editMode
        });
    }

    renderViewForm() {
        let inputLabelContainerStyle = {
            textAlign: "right",
            fontSize: "12px"
        };

        let valueLabel = {
            textAlign: "left",
            fontSize: "12px",
            borderBottom: "solid 1px lightgrey"
        };

        return (
            <div className="panel-body">
                <form className="form-horizontal">
                    <form className="form-horizontal">
                        <div className="form-group">
                            <div className="col-sm-2" style={inputLabelContainerStyle}><label>Anrede:</label></div>
                            <div className="col-sm-9" style={valueLabel}>---</div>
                        </div>
                        <div className="form-group">
                            <div className="col-sm-2" style={inputLabelContainerStyle}><label>Vorname:</label></div>
                            <div className="col-sm-9" style={valueLabel}>---</div>
                        </div>
                        <div className="form-group">
                            <div className="col-sm-2" style={inputLabelContainerStyle}><label>Nachname:</label></div>
                            <div className="col-sm-9" style={valueLabel}>---</div>
                        </div>
                    </form>
                </form>
            </div>
        );
    }

    renderEditForm() {
        let inputLabelContainerStyle = {
            textAlign: "right",
            paddingTop: "6px",
            fontSize: "12px"
        };

        return (
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
        );
    }

    render() {
        const switchEditModeIconClass = (this.state.editMode === true) ? "glyphicon glyphicon-eye-open" : "glyphicon glyphicon-pencil";

        return (
            <div className="panel panel-success" style={{width: "1000px", margin: "0 auto"}}>
                <div className="panel-heading" style={{position: "relative"}}>
                    <strong>Persönliche Kontaktinformationen</strong>
                    <div className={switchEditModeIconClass} style={{position: "absolute", fontSize: "16px", top: "14px", right: "14px"}} onClick={this.toggleEditMode}/>
                </div>
                {(this.state.editMode === true) ? this.renderEditForm() : this.renderViewForm()}
            </div>
        );
    }

};
