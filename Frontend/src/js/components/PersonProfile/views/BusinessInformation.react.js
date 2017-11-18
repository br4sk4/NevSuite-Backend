/**
 * Created by Braska on 11.11.2017.
 */
import React from 'react';

import Switch from '../../../widgets/base/Switch.react.js';
import InputField from '../../../widgets/base/InputField.react.js';
import ComboBox from '../../../widgets/base/ComboBox.react.js';
import PersonContacts from './PersonContacts.react.js';

export default class BusinessInformation extends React.Component {

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
                    <div className="form-group">
                        <div className="col-sm-2" style={inputLabelContainerStyle}><label>Kürzel:</label></div>
                        <div className="col-sm-9" style={valueLabel}>br4sk4</div>
                    </div>
                    <div className="form-group">
                        <div className="col-sm-2" style={inputLabelContainerStyle}><label>Eintrittsdatum:</label></div>
                        <div className="col-sm-9" style={valueLabel}>---</div>
                    </div>
                    <div className="form-group">
                        <div className="col-sm-2" style={inputLabelContainerStyle}><label>Firma:</label></div>
                        <div className="col-sm-9" style={valueLabel}>NevSoft</div>
                    </div>
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

        const comboboxData = [
            'NevSoft',
            'Alpha AG',
            'Beta GmbH',
            'Gamma, Co. Ltd.',
            'Delta, Inc.',
            'Epsilon AG',
            'Lambda AG',
            'Rho GmbH',
            'Omega, Inc.',
        ];

        return (
            <div className="panel-body">
                <form className="form-horizontal">
                    <div className="form-group">
                        <div className="col-sm-2" style={inputLabelContainerStyle}><label>Kürzel:</label></div>
                        <div className="col-sm-9"><InputField/></div>
                    </div>
                    <div className="form-group">
                        <div className="col-sm-2" style={inputLabelContainerStyle}><label>Eintrittsdatum:</label></div>
                        <div className="col-sm-9"><InputField/></div>
                    </div>
                    <div className="form-group">
                        <div className="col-sm-2" style={inputLabelContainerStyle}><label>Firma:</label></div>
                        <div className="col-sm-9"><ComboBox showSearchBox data={comboboxData}/></div>
                    </div>
                    <div className="form-group">
                        <div className="col-sm-2" style={inputLabelContainerStyle}><label>Führungskraft:</label></div>
                        <div className="col-sm-9"><Switch/></div>
                    </div>
                    <div className="form-group">
                        <div className="col-sm-2" style={inputLabelContainerStyle}><label>Ausbilder:</label></div>
                        <div className="col-sm-9"><Switch/></div>
                    </div>
                </form>
                <PersonContacts {...this.props}/>
            </div>
        );
    }

    render() {
        const switchEditModeIconClass = (this.state.editMode === true) ? "glyphicon glyphicon-eye-open editModeIcon" : "glyphicon glyphicon-pencil editModeIcon";

        return (
            <div className="panel panel-nevsuite" style={{marginTop: "15px"}}>
                <div className="panel-heading">
                    <strong>Betriebliche Kontaktinformationen</strong>
                    <div className={switchEditModeIconClass} onClick={this.toggleEditMode}/>
                </div>
                {(this.state.editMode === true) ? this.renderEditForm() : this.renderViewForm()}
            </div>
        );
    }

};
