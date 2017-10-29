'use strict';

import {ReduceStore} from 'flux/utils';
import Immutable from 'immutable';
import uuid from 'uuid/v4.js';

import NevSuiteAppDispatcher from '../../../nevsuite.app.dispatcher.js';

import Tab from './Tab.js';
import TabActionTypes from './TabActionTypes';

class TabStore extends ReduceStore {

    constructor() {
        super(NevSuiteAppDispatcher);
    }

    getInitialState() {
        return Immutable.fromJS({tabs: [new Tab(uuid(), 'Home', 'HOME', true, true)], tabVisibilityIndex: 0, tabPagerLeftVisible: false, tabPagerRightVisible: false});
    }

    reduce(state, action) {
        let isPagerLeftVisible;
        let isPagerRightVisible;
        let visibilityIndex;
        const tabs = [];

        switch (action.type) {

            case TabActionTypes.ADD_TAB:
                if (!action.label) return state;

                visibilityIndex = state.get('tabVisibilityIndex');
                visibilityIndex = ( (state.get('tabs').size - visibilityIndex) > 4 ) ? visibilityIndex = state.get('tabs').size - 4 : visibilityIndex;

                for (let i = 0; i < state.get('tabs').size; ++i) {
                    ( action.selected && state.get('tabs').get(i).selected )
                        ? tabs.push(new Tab(state.get('tabs').get(i).identifier, state.get('tabs').get(i).label, state.get('tabs').get(i).tabtype, false, (i >= visibilityIndex)))
                        : tabs.push(new Tab(state.get('tabs').get(i).identifier, state.get('tabs').get(i).label, state.get('tabs').get(i).tabtype, state.get('tabs').get(i).selected, (i >= visibilityIndex)));
                }
                tabs.push(new Tab(action.identifier, action.label, action.tabtype, action.selected, true));

                isPagerLeftVisible = ( visibilityIndex > 0 );
                isPagerRightVisible = ( visibilityIndex < tabs.length - 1 );

                return Immutable.fromJS({
                    tabs: tabs,
                    tabVisibilityIndex: visibilityIndex,
                    tabPagerLeftVisible: isPagerLeftVisible,
                    tabPagerRightVisible: isPagerRightVisible
                });

            case TabActionTypes.REMOVE_TAB:
                if (!action.index) return state;

                const removeLast = state.get('tabs').size - 1 == action.index;
                const removeSelected = state.get('tabs').get(action.index).selected;

                visibilityIndex = state.get('tabVisibilityIndex');

                if (visibilityIndex >= state.get('tabs').size - 1) visibilityIndex = state.get('tabs').size - 2;

                for (let i = 0; i < state.get('tabs').size; i++) {
                    if ( removeLast && removeSelected && i == action.index - 1 ) tabs.push(new Tab(state.get('tabs').get(i).identifier, state.get('tabs').get(i).label, state.get('tabs').get(i).tabtype, true, (i >= visibilityIndex)));
                    else if ( removeSelected && i == action.index + 1 ) tabs.push(new Tab(state.get('tabs').get(i).identifier, state.get('tabs').get(i).label, state.get('tabs').get(i).tabtype, true, (i >= visibilityIndex)));
                    else if ( i != action.index ) tabs.push(new Tab(state.get('tabs').get(i).identifier, state.get('tabs').get(i).label, state.get('tabs').get(i).tabtype, state.get('tabs').get(i).selected, (i >= visibilityIndex)));
                }

                isPagerLeftVisible = ( visibilityIndex > 0 );
                isPagerRightVisible = ( visibilityIndex < tabs.length - 1 );

                return Immutable.fromJS({
                    tabs: tabs,
                    tabVisibilityIndex: visibilityIndex,
                    tabPagerLeftVisible: isPagerLeftVisible,
                    tabPagerRightVisible: isPagerRightVisible
                });

            case TabActionTypes.SELECT_TAB:
                if (action.index < 0) return state;

                visibilityIndex = state.get('tabVisibilityIndex');

                for (let i = 0; i < state.get('tabs').size; ++i) {
                    ( i == action.index )
                        ? tabs.push(new Tab(state.get('tabs').get(i).identifier, state.get('tabs').get(i).label, state.get('tabs').get(i).tabtype, true, (i >= visibilityIndex)))
                        : tabs.push(new Tab(state.get('tabs').get(i).identifier, state.get('tabs').get(i).label, state.get('tabs').get(i).tabtype, false, (i >= visibilityIndex)));
                }

                isPagerLeftVisible = ( visibilityIndex > 0 );
                isPagerRightVisible = ( visibilityIndex < tabs.length - 1 );

                return Immutable.fromJS({
                    tabs: tabs,
                    tabVisibilityIndex: visibilityIndex,
                    tabPagerLeftVisible: isPagerLeftVisible,
                    tabPagerRightVisible: isPagerRightVisible
                });

            case TabActionTypes.SCROLL_TABS_LEFT:
                if (action.index < 0) return state;

                visibilityIndex = state.get('tabVisibilityIndex');

                if ( visibilityIndex > 0 ) visibilityIndex--;

                for (let i = 0; i < state.get('tabs').size; ++i) {
                    tabs.push(new Tab(state.get('tabs').get(i).identifier, state.get('tabs').get(i).label, state.get('tabs').get(i).tabtype, state.get('tabs').get(i).selected, (i >= visibilityIndex)));
                }

                isPagerLeftVisible = ( visibilityIndex > 0 );
                isPagerRightVisible = ( visibilityIndex < tabs.length - 1 );

                return Immutable.fromJS({
                    tabs: tabs,
                    tabVisibilityIndex: visibilityIndex,
                    tabPagerLeftVisible: isPagerLeftVisible,
                    tabPagerRightVisible: isPagerRightVisible
                });

            case TabActionTypes.SCROLL_TABS_RIGHT:
                if (action.index < 0) return state;

                visibilityIndex = state.get('tabVisibilityIndex');

                if ( visibilityIndex < state.get('tabs').size ) visibilityIndex++;

                for (let i = 0; i < state.get('tabs').size; ++i) {
                    tabs.push(new Tab(state.get('tabs').get(i).identifier, state.get('tabs').get(i).label, state.get('tabs').get(i).tabtype, state.get('tabs').get(i).selected, (i >= visibilityIndex)));
                }

                isPagerLeftVisible = ( visibilityIndex > 0 );
                isPagerRightVisible = ( visibilityIndex < tabs.length - 1 );

                return Immutable.fromJS({
                    tabs: tabs,
                    tabVisibilityIndex: visibilityIndex,
                    tabPagerLeftVisible: isPagerLeftVisible,
                    tabPagerRightVisible: isPagerRightVisible
                });

            default:
                return state;
        }
    }

}

export default new TabStore();