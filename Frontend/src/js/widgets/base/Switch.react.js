/**
 * Created by Braska on 18.11.2017.
 */
import React from "react";

export default class Switch extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            checked: false
        };
    }

    render() {
        let toggleChecked = () => this.setState({
            checked: !this.state.checked
        });

        let switchClass = (this.state.checked) ? "widget switch checked" : "widget switch";
        let switchBulletClass = (this.state.checked) ? "switchBullet checked" : "switchBullet unchecked";

        return (
            <div className={switchClass} tabIndex="0" onClick={toggleChecked}>
                <div className={switchBulletClass}></div>
            </div>
        );
    }
}
