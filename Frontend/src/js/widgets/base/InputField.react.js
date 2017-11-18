/**
 * Created by Braska on 13.11.2017.
 */
import React from "react";

export default class InputField extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            value: ""
        };
    }

    render() {
        let changeHandler = () => this.setState({value: event.target.value});

        return (
            <div style={{width: this.props.width||"100%", float: "left"}} onMouseDown={this.props.toggleFocus} onBlur={() => {if (!this.props.itemSelection) this.props.toggleDropdown();}}>
                <input className="widget inputField" type="text" defaultValue={this.state.value} onChange={changeHandler} />
            </div>
        );
    }
}
