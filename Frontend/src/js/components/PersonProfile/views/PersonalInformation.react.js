/**
 * Created by Braska on 11.11.2017.
 */
import React from 'react';

import InputField from '../../../widgets/control/InputField.react.js';
import ComboBox from '../../../widgets/control/ComboBox.react.js';
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
                <div className="form-horizontal">
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
                </div>
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
                    <PersonContacts {...this.props}/>
                </form>
            </div>
        );
    }

    render() {
        const modeIconClass = (this.state.editMode === true)
            ? "editModeIcon glyphicon glyphicon-eye-open"
            : "editModeIcon glyphicon glyphicon-pencil";

        return (
            <div className="panel panel-nevsuite">
                <div className="panel-heading">
                    <strong>Persönliche Kontaktinformationen</strong>
                    <div className={modeIconClass} onClick={this.toggleEditMode}/>
                </div>
                {(this.state.editMode === true) ? this.renderEditForm() : this.renderViewForm()}
            </div>
        );
    }

};
