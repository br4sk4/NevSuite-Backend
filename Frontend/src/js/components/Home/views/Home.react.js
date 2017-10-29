import React from 'react';

export default class Home extends React.Component {

    render() {
        let elements = [];

        let components = this.props.state.home.get('componentInfos');

        for (let i = 0; i < components.size; ++i) {
            elements.push(<div key={i} name="" className={'alert alert-' + components.get(i).classes} role='alert'>{components.get(i).name}: {components.get(i).response}</div>);
        }

        return <div className='componentContainer' style={{padding: '10px'}}>{elements}<div id="dtInputFrom"></div></div>;
    }

};