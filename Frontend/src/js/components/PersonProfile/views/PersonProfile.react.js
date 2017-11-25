/**
 * Created by Braska on 11.11.2017.
 */
import React from 'react';

import PersonalInformation from './PersonalInformation.react';
import BusinessInformation from './BusinessInformation.react';
import PersonPerformance from './PersonPerformance.react';

export default class PersonProfile extends React.Component {

    render() {
        return (
            <div className='componentContainer' style={{padding: '10px'}}>
                <PersonalInformation {...this.props} />
                <BusinessInformation {...this.props} />
                <PersonPerformance {...this.props} />
            </div>
        );
    }

};
